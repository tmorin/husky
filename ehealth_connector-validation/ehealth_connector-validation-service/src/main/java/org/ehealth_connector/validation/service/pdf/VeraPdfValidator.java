/*
 *
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 * For exact developer information, please refer to the commit history of the forge.
 *
 * This code is made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * This line is intended for UTF-8 encoding checks, do not modify/delete: äöüéè
 *
 */
package org.ehealth_connector.validation.service.pdf;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;
import java.util.Set;

import javax.xml.transform.stream.StreamSource;

import org.ehealth_connector.validation.service.config.Configuration;
import org.ehealth_connector.validation.service.config.ConfigurationException;
import org.ehealth_connector.validation.service.enums.Severity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.verapdf.core.EncryptedPdfException;
import org.verapdf.core.ModelParsingException;
import org.verapdf.core.ValidationException;
import org.verapdf.pdfa.Foundries;
import org.verapdf.pdfa.PDFAParser;
import org.verapdf.pdfa.PDFAValidator;
import org.verapdf.pdfa.VeraGreenfieldFoundryProvider;
import org.verapdf.pdfa.flavours.PDFAFlavour;
import org.verapdf.pdfa.results.TestAssertion;
import org.verapdf.pdfa.results.ValidationResult;

import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XPathCompiler;
import net.sf.saxon.s9api.XPathSelector;
import net.sf.saxon.s9api.XdmItem;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XdmValue;

/**
 * This class is executing the validation of a given PDF document.
 *
 */
public class VeraPdfValidator {

	/** Not initialized error message */
	public final static String ERR_NOT_INITIALIZED = "PdfValidatorAPI initialization failed";

	/** Not installed error message */
	public final static String ERR_NOT_INSTALLED = "PdfValidatorAPI not installed";

	/** veraPDF validator flavour */
	private PDFAFlavour flavour;

	/** The SLF4J logger instance. */
	private final Logger log = LoggerFactory.getLogger(getClass());

	/** PDF compliance level as string */
	private String pdfLevel;

	/** Current PDF validation results */
	private VeraPdfValidationResult pdfValidationResult = null;

	/** veraPDF validator */
	private PDFAValidator pdfValidator = null;

	/**
	 * Default constructor
	 *
	 * @param config
	 *            Current configuration
	 */
	public VeraPdfValidator(Configuration config) {
		this.pdfLevel = config.getPdfLevel();
	}

	/**
	 * Gets the PDF compliance level
	 *
	 * @return the PDF compliance level
	 */
	public String getPdfLevel() {
		return pdfLevel;
	}

	/**
	 * Gets the current PDF validation results
	 *
	 * @return the current PDF validation results
	 */
	public VeraPdfValidationResult getPdfValidationResults() {
		return pdfValidationResult;
	}

	/**
	 * Gets the API version of the PDF validator
	 *
	 * @return the API version of the PDF validator
	 */
	public String getPdfValidatorVersion() {
		String retVal = "none";
		if (pdfValidator == null) {
			initialize("none");
		}
		if (pdfValidator != null) {
			retVal = pdfValidator.getDetails().getVersion();
		}
		return retVal;
	}

	/**
	 * Initializes the PDF validator
	 *
	 * @param lineNumber
	 *            the line number where the PDF starts within the CDA document
	 */
	private void initialize(String lineNumber) {
		if (pdfValidator == null) {
			log.info("Trying to initialize veraPdfValidator...");
			pdfValidationResult = new VeraPdfValidationResult();
			flavour = PDFAFlavour.fromString(pdfLevel);
			VeraGreenfieldFoundryProvider.initialise();
			pdfValidator = Foundries.defaultInstance().createValidator(flavour, false);
		} else
			log.info("PdfValidatorAPI already initialized...");
	}

	/**
	 * Sets the given PDF validation results
	 *
	 * @param pdfValidationResults
	 *            the desired PDF validation results
	 */
	public void setPdfValidationResults(VeraPdfValidationResult pdfValidationResults) {
		this.pdfValidationResult = pdfValidationResults;
	}

	/**
	 * Validates all PDF within the given CDA document
	 *
	 * @param cdaFile
	 *            the desired CDA document to be validated
	 * @throws ConfigurationException
	 * @throws SaxonApiException
	 * @throws IOException
	 */
	public void validateCda(File cdaFile)
			throws ConfigurationException, SaxonApiException, IOException {
		validateCda(new StreamSource(cdaFile));
	}

	/**
	 * Validates all PDF within the given CDA document
	 *
	 * @param cdaStream
	 *            the desired CDA document to be validated
	 * @throws ConfigurationException
	 * @throws SaxonApiException
	 * @throws IOException
	 */
	public void validateCda(StreamSource cdaStream)
			throws ConfigurationException, SaxonApiException, IOException {

		pdfValidationResult = new VeraPdfValidationResult();

		final Processor proc = new Processor(false);

		final DocumentBuilder builder = proc.newDocumentBuilder();
		builder.setLineNumbering(true);
		XdmNode hl7Doc;

		hl7Doc = builder.build(cdaStream);

		final XPathCompiler xpath = proc.newXPathCompiler();
		xpath.declareNamespace("", "urn:hl7-org:v3");

		final XPathSelector selector = xpath
				.compile("//*[@mediaType='application/pdf' and @representation='B64']").load();
		selector.setContextItem(hl7Doc);
		final XdmValue children = selector.evaluate();
		if (children.size() > 0) {
			for (final XdmItem item : children) {
				final XdmNode nonXMLBodyNode = (XdmNode) item;
				final String pdf = item.getStringValue().trim();
				validatePdf(pdf, String.valueOf(nonXMLBodyNode.getLineNumber()));
			}
		}
	}

	/**
	 * Validates the given PDF
	 *
	 * @param pdfStrB64
	 *            the desired PDF as Base64 string taken from the CDA document
	 * @param lineNumber
	 *            the line number where the PDF starts within the CDA document
	 * @throws IOException
	 * @throws ConfigurationException
	 */
	private void validatePdf(String pdfStrB64, String lineNumber)
			throws IOException, ConfigurationException {

		initialize(lineNumber);
		ValidationResult result = null;
		Boolean aborted = false;
		if (pdfValidator != null) {
			pdfValidationResult.resetIsDone();
			byte[] decodedBytes;
			decodedBytes = Base64.getMimeDecoder().decode(pdfStrB64);

			PDFAParser parser;
			try {
				parser = Foundries.defaultInstance()
						.createParser(new ByteArrayInputStream(decodedBytes), flavour);
				result = pdfValidator.validate(parser);
			} catch (ModelParsingException | EncryptedPdfException | ValidationException e) {
				aborted = true;
				VeraPdfValidationResultEntry failure = new VeraPdfValidationResultEntry();
				String errMsg = e.getClass().getName() + ": " + e.getMessage();
				if (e.getCause() != null) {
					if (e.getCause().getMessage() != null)
						errMsg = errMsg + " (" + e.getCause().getMessage() + ")";
				}
				errMsg = errMsg
						+ "*** Note: veraPDF was not thread save with Version 1.8.1. See also https://github.com/veraPDF/veraPDF-library/issues/896";
				failure.setErrMsg(errMsg, Severity.CustomWarning);
				pdfValidationResult.setIsDone();
				pdfValidationResult.setPdfValid(false);
				pdfValidationResult.add(failure);
			}
			if (!aborted) {
				if (result != null) {
					pdfValidationResult.setIsDone();
					if (!result.isCompliant()) {
						Set<TestAssertion> assertionSet = result.getTestAssertions();
						Iterator<TestAssertion> iterator = assertionSet.iterator();
						while (iterator.hasNext()) {
							TestAssertion assertion = iterator.next();
							VeraPdfValidationResultEntry failure = new VeraPdfValidationResultEntry();
							failure.setPdfSpecificationId(
									assertion.getRuleId().getSpecification().getId());
							failure.setPdfSpecificationName(
									assertion.getRuleId().getSpecification().getName());
							failure.setVeraPdfRule(assertion.getRuleId().getClause());
							failure.setVeraPdfTestNumber(
									Integer.toString(assertion.getRuleId().getTestNumber()));
							failure.setPdfContext(assertion.getLocation().getContext());
							failure.setErrMsg(assertion.getMessage(), Severity.Error);
							failure.setLineNumber(lineNumber);

							Boolean realFailure = true;
							// These rules appear under .Net, only. In order to
							// synchronize Java and .Net validation results,
							// these
							// failures are suppressed.
							if ("6.2.2".equals(assertion.getRuleId().getClause())
									&& (assertion.getRuleId().getTestNumber() == 1))
								realFailure = false;
							if ("6.2.3".equals(assertion.getRuleId().getClause())
									&& (assertion.getRuleId().getTestNumber() == 5))
								realFailure = false;
							if ("6.2.3".equals(assertion.getRuleId().getClause())
									&& (assertion.getRuleId().getTestNumber() == 4))
								realFailure = false;
							if ("6.3.4".equals(assertion.getRuleId().getClause())
									&& (assertion.getRuleId().getTestNumber() == 1))
								realFailure = false;

							if (realFailure)
								pdfValidationResult.add(failure);
						}
						pdfValidationResult
								.setPdfValid(pdfValidationResult.getEntries().size() == 0);
					} else {
						pdfValidationResult.setPdfValid(true);
						VeraPdfValidationResultEntry success = new VeraPdfValidationResultEntry();
						success.setLineNumber(lineNumber);
						success.setErrMsg("PDF is compliant", Severity.Information);
						pdfValidationResult.add(success);
					}
					pdfValidationResult.setIsDone();
				} else {
					VeraPdfValidationResultEntry pdfVResult = new VeraPdfValidationResultEntry();
					pdfVResult.setLineNumber(lineNumber);
					pdfValidationResult.add(pdfVResult);
					pdfValidationResult.setPdfValid(true);
				}
			}
			pdfValidator.close();
		}
	}
}
