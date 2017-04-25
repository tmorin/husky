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
package org.ehealth_connector.cda.ch.mtps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.ehealth_connector.cda.ihe.pharm.DispenseItemEntry;
import org.ehealth_connector.cda.ihe.pharm.DispenseSection;
import org.ehealth_connector.cda.testhelper.TestUtils;
import org.junit.Test;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.ch.CHPackage;
import org.openhealthtools.mdht.uml.cda.ihe.pharm.PHARMFactory;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Test cases
 */
public class CdaChMtpsDisTest extends TestUtils {

	/** The SLF4J logger instance. */
	protected final Logger log = LoggerFactory.getLogger(getClass());

	private final XPathFactory xpathFactory = XPathFactory.newInstance();
	private final XPath xpath = xpathFactory.newXPath();

	/**
	 *<div class="en">Test class for the PHARM DIS document.</div>
	 * <div class="de"></div>	 
	 */
	public CdaChMtpsDisTest() {
		super();
	}

	/**
	 * @param document
	 * @return
	 * @throws Exception
	 */
	private CdaChMtpsDis deserializeCda(String document) throws Exception {
		final InputSource source = new InputSource(new StringReader(document));
		return new CdaChMtpsDis(
				(org.openhealthtools.mdht.uml.cda.ch.CdaChMtpsDis) CDAUtil.load(source));
	}

	/**
	 * @param document
	 * @return
	 * @throws Exception
	 */
	private CdaChMtpsDis deserializeCdaDirect(String document) throws Exception {
		final InputStream stream = new ByteArrayInputStream(document.getBytes());
		final ClinicalDocument clinicalDocument = CDAUtil.loadAs(stream,
				CHPackage.eINSTANCE.getCdaChMtpsDis());
		return new CdaChMtpsDis(
				(org.openhealthtools.mdht.uml.cda.ch.CdaChMtpsDis) clinicalDocument);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void deserializeCdaDirectTest() throws Exception {
		final CdaChMtpsDis cda = new CdaChMtpsDis();
		final String deserialized = this.serializeDocument(cda);
		log.debug(deserialized);
		final CdaChMtpsDis cdaDeserialized = deserializeCdaDirect(deserialized);
		assertTrue(cdaDeserialized != null);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void deserializeCdaTest() throws Exception {
		final CdaChMtpsDis cda = new CdaChMtpsDis();
		final String deserialized = this.serializeDocument(cda);
		log.debug(deserialized);
		final CdaChMtpsDis cdaDeserialized = deserializeCda(deserialized);
		assertTrue(cdaDeserialized != null);
		assertEquals("Pharmacy Dispense", cdaDeserialized.getDispenseSection().getTitle());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void deserializeCdaTestTemplateId() throws Exception {
		final CdaChMtpsDis cda = new CdaChMtpsDis();
		final String deserialized = this.serializeDocument(cda);
		log.debug(deserialized);
		final CdaChMtpsDis cdaDeserialized = deserializeCda(deserialized);
		assertTrue(cdaDeserialized != null);
	}

	/**
	 * @param document
	 * @return
	 * @throws Exception
	 */
	private ClinicalDocument deserializeClinicalDocument(String document) throws Exception {
		final InputSource source = new InputSource(new StringReader(document));
		return CDAUtil.load(source);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void deserializeClinicalDocumentTest() throws Exception {
		final CdaChMtpsDis cda = new CdaChMtpsDis();
		final String deserialized = this.serializeDocument(cda);
		log.debug(deserialized);
		final ClinicalDocument cdaDeserialized = deserializeClinicalDocument(deserialized);
		assertTrue(cdaDeserialized != null);
	}

	/**
	 * @throws XPathExpressionException
	 */
	@Test
	public void failedModellingTest() throws XPathExpressionException {
		org.openhealthtools.mdht.uml.cda.ihe.pharm.DispenseSection cda = PHARMFactory.eINSTANCE
				.createDispenseSection().init();
		DispenseSection dispenseSection = new DispenseSection(cda);

		final Document document = dispenseSection.getDocument();

		XPathExpression expr = xpath
				.compile("//section/templateId[@root='1.3.6.1.4.1.19376.1.9.1.2.3']");
		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		expr = xpath.compile("//*/code[@code='10160-0' and @codeSystem='2.16.840.1.113883.6.1']");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

	}

	/**
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	private String serializeDocument(CdaChMtpsDis doc) throws Exception {
		final ByteArrayOutputStream boas = new ByteArrayOutputStream();
		CDAUtil.save(doc.getDoc(), boas);
		return boas.toString();
	}

	/**
	 * @throws XPathExpressionException
	 */
	@Test
	public void testDocumenHeader() throws XPathExpressionException {
		final CdaChMtpsDis cda = new CdaChMtpsDis();
		final Document document = cda.getDocument();

		// realmCode
		XPathExpression expr = xpath.compile("//realmCode[@code='CHE']");
		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		// typeId
		expr = xpath
				.compile("//typeId[@root='2.16.840.1.113883.1.3' and @extension='POCD_HD000040']");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		// CH Dispense
		expr = xpath.compile("//templateId[@root='2.16.756.5.30.1.1.1.1.3.8.1.11']");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		expr = xpath.compile("//templateId[@root='1.3.6.1.4.1.19376.1.5.3.1.1.1']");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		// ihe pharm dis template id
		expr = xpath.compile("//templateId[@root='1.3.6.1.4.1.19376.1.9.1.1.3']");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		// ihe pharm dis code
		expr = xpath.compile("//code[@code='60593-1']");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());
	}

	/**
	 * @throws XPathExpressionException
	 */
	@Test
	public void testDocumentSection() throws XPathExpressionException {
		final CdaChMtpsDis cda = new CdaChMtpsDis();
		final Document document = cda.getDocument();

		XPathExpression expr = xpath
				.compile("//*/section/templateId[@root='1.3.6.1.4.1.19376.1.9.1.2.3']");
		NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		expr = xpath.compile("//*/section/title[text()='Pharmacy Dispense']");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		expr = xpath.compile("//*/section/templateId[@root='2.16.840.1.113883.10.20.1.8']");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());

		expr = xpath.compile("//*/code[@code='60590-7' and @codeSystem='2.16.840.1.113883.6.1']");
		nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		assertEquals(1, nodes.getLength());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testDocumentSectionDeserializeWithEntries() throws Exception {

		final CdaChMtpsDis cda = new CdaChMtpsDis();

		final DispenseItemEntry disEntry = new DispenseItemEntry();
		disEntry.setTextReference("#dis");
		cda.getDispenseSection().setDispenseItemEntry(disEntry);

		final String deserialized = this.serializeDocument(cda);
		log.debug(deserialized);
		final CdaChMtpsDis cdaDeserialized = deserializeCda(deserialized);

		assertTrue(cdaDeserialized != null);

		assertEquals("#dis",
				cdaDeserialized.getDispenseSection().getDispenseItemEntry().getTextReference());
	}

}
