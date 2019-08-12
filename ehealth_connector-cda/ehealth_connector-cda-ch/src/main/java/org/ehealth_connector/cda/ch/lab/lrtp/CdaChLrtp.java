/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://gitlab.com/ehealth-connector/api/wikis/Team/
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
package org.ehealth_connector.cda.ch.lab.lrtp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ehealth_connector.cda.ch.lab.AbstractLaboratoryReport;
import org.ehealth_connector.cda.ch.lab.BaseChSpecimenAct;
import org.ehealth_connector.cda.ch.lab.BloodGroupObservation;
import org.ehealth_connector.cda.ch.lab.StudiesSummarySection;
import org.ehealth_connector.cda.ch.lab.lrtp.enums.ReportScopes;
import org.ehealth_connector.cda.ihe.lab.ReferralOrderingPhysician;
import org.ehealth_connector.common.enums.LanguageCode;
import org.ehealth_connector.common.mdht.Author;
import org.ehealth_connector.common.mdht.Code;
import org.ehealth_connector.common.mdht.Identificator;
import org.ehealth_connector.common.mdht.IntendedRecipient;
import org.ehealth_connector.common.mdht.enums.CountryCode;
import org.openhealthtools.mdht.uml.cda.CDAFactory;
import org.openhealthtools.mdht.uml.cda.DocumentationOf;
import org.openhealthtools.mdht.uml.cda.Patient;
import org.openhealthtools.mdht.uml.cda.PatientRole;
import org.openhealthtools.mdht.uml.cda.RecordTarget;
import org.openhealthtools.mdht.uml.cda.Section;
import org.openhealthtools.mdht.uml.cda.ServiceEvent;
import org.openhealthtools.mdht.uml.cda.ch.ChFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.AD;
import org.openhealthtools.mdht.uml.hl7.datatypes.CE;
import org.openhealthtools.mdht.uml.hl7.datatypes.CS;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.PN;
import org.openhealthtools.mdht.uml.hl7.datatypes.TEL;
import org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor;

/**
 * The Class CdaChLrtp. <div class="en">The document implements the normative
 * specification regarding the sematic interoperability for systems for the
 * electronic notification of laboratory results in the transplantation process.
 * Transplantation center transfering laboratory data of the organ donor
 * recipients to SOAS, so that they can be considered in the organ donor
 * process.</div> <div class="de">Das vorliegende Dokument gibt normativ die
 * Spezifikationen betreffend die semantische Interoperabilität von Systemen für
 * die elektronische Meldung von Laborbefunden im Transplantationsprozess vor.
 * Die Transplantationszentren übermitteln die Labordaten der Organempfänger an
 * SOAS, damit diese bei der Organzuteilung berücksichtigt werden können.</div>
 */
@SuppressWarnings("deprecation")
public class CdaChLrtp
		extends AbstractLaboratoryReport<org.openhealthtools.mdht.uml.cda.ch.CdaChLrtpV1> {

	/**
	 * This class implements the default comparison algorithm for HL7 CDA
	 * Battery Organizers.
	 */
	private class LaboratoryBatteryOrganizerComparator
			implements Comparator<LaboratoryBatteryOrganizer> {

		/**
		 *
		 * Compares two Organizers on their effective date timestamp.
		 *
		 * {@inheritDoc}
		 *
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(LaboratoryBatteryOrganizer a, LaboratoryBatteryOrganizer b) {
			if ((a == null) && (b == null))
				return 0;
			else if ((a == null) && (b != null))
				return -1;
			else if ((a != null) && (b == null))
				return 1;
			else {
				if ((a.getEffectiveTime() == null) && (b.getEffectiveTime() == null))
					return 0;
				else if ((a.getEffectiveTime() == null) && (b.getEffectiveTime() != null))
					return -1;
				else if ((a.getEffectiveTime() != null) && (b.getEffectiveTime() == null))
					return 1;
				else
					return a.getEffectiveTime().compareTo(b.getEffectiveTime());
			}
		}
	}

	private int sectionIndex = 0;

	/**
	 * Standard constructor.
	 */
	public CdaChLrtp() {
		this(LanguageCode.ENGLISH);
	}

	/**
	 * Instantiates a new cda ch lrtp.
	 *
	 * @param languageCode
	 *            the language code
	 */
	protected CdaChLrtp(LanguageCode languageCode) {
		this(languageCode, null, null);
	}

	/**
	 * Constructor with the recommended elements for the LRTP document Header.
	 *
	 * @param languageCode
	 *            the language code
	 * @param author
	 *            the author of the document (a laboratory)
	 * @param refOrderingPhysician
	 *            a physician
	 * @param patient
	 *            the patient
	 * @param recipient
	 *            the recipient (e.g. the Bundesamt für Gesundheit)
	 * @param scope
	 *            the scope of this organ donor.
	 * @param soasCode
	 *            <div class="en">the SOAS code (will be set in
	 *            recordTarget/patientRole/id/extension). Other Ids will be
	 *            deleted.</div> <div class="de">Die Spender- resp. Empfänger-ID
	 *            (der sogenannte SOAS-Code wird gesetzt in
	 *            recordTarget/patientRole/id/extension). Andere Ids werden
	 *            gelöscht.</div>
	 *
	 */
	public CdaChLrtp(LanguageCode languageCode, Author author,
			ReferralOrderingPhysician refOrderingPhysician,
			org.ehealth_connector.common.mdht.Patient patient, IntendedRecipient recipient,
			ReportScopes scope, String soasCode) {
		this(languageCode);
		// set SOAS ID
		patient.getMdhtPatientRole().getIds().clear();
		patient.addId(new Identificator("2.16.756.5.30.1.129.1.1.1", soasCode));
		setPatient(patient);
		setEmtpyCustodian();
		addAuthor(author);
		addReferralOrderingPhysician(refOrderingPhysician);
		addIntendedRecipient(recipient);
		addDocumentationOf(scope);
	}

	/**
	 * Instantiates a new cda ch lrtp.
	 *
	 * @param languageCode
	 *            language of the document contents
	 * @param styleSheet
	 *            an extensible style sheet (XSLT) to transform and render the
	 *            document
	 * @param css
	 *            cascading style sheet (CSS) to add style information for
	 *            rendering
	 */
	public CdaChLrtp(LanguageCode languageCode, String styleSheet, String css) {
		super(ChFactory.eINSTANCE.createCdaChLrtpV1().init(), languageCode, styleSheet, css);
		// Fix RealmCode
		final CS cs = DatatypesFactory.eINSTANCE.createCS();
		cs.setCode(CountryCode.SWITZERLAND.getCodeAlpha3());
		getDoc().getRealmCodes().clear();
		getDoc().getRealmCodes().add(cs);

	}

	/**
	 * Constructor with the recommended elements for the LRTP document Header.
	 *
	 * @param languageCode
	 *            the language code
	 * @param styleSheet
	 *            an extensible style sheet (XSLT) to transform and render the
	 *            document
	 * @param css
	 *            cascading style sheet (CSS) to add style information for
	 *            rendering
	 * @param author
	 *            the author of the document (a laboratory)
	 * @param refOrderingPhysician
	 *            a physician
	 * @param patient
	 *            the patient
	 * @param recipient
	 *            the recipient (e.g. the Bundesamt für Gesundheit)
	 * @param scope
	 *            the scope of this organ donor.
	 * @param soasCode
	 *            <div class="en">the SOAS code (will be set in
	 *            recordTarget/patientRole/id/extension). Other Ids will be
	 *            deleted.</div> <div class="de">Die Spender- resp. Empfänger-ID
	 *            (der sogenannte SOAS-Code wird gesetzt in
	 *            recordTarget/patientRole/id/extension). Andere Ids werden
	 *            gelöscht.</div>
	 *
	 */
	public CdaChLrtp(LanguageCode languageCode, String styleSheet, String css, Author author,
			ReferralOrderingPhysician refOrderingPhysician,
			org.ehealth_connector.common.mdht.Patient patient, IntendedRecipient recipient,
			ReportScopes scope, String soasCode) {
		this(languageCode, styleSheet, css);
		// set SOAS ID
		patient.getMdhtPatientRole().getIds().clear();
		patient.addId(new Identificator("2.16.756.5.30.1.129.1.1.1", soasCode));
		setPatient(patient);
		setEmtpyCustodian();
		addAuthor(author);
		addReferralOrderingPhysician(refOrderingPhysician);
		addIntendedRecipient(recipient);
		addDocumentationOf(scope);
	}

	/**
	 * Instantiates a new cda ch lrtp.
	 *
	 * @param doc
	 *            mdht model document
	 */
	public CdaChLrtp(org.openhealthtools.mdht.uml.cda.ch.CdaChLrtpV1 doc) {
		super(doc);
	}

	/**
	 * <div class="en">Convenience function to add the DocumentationOf element.
	 * In case of LRTP it determines the case of organ donor. </div>
	 * <div class="de">Convenience Funktion um das documentationOf element
	 * hinzuzufügen. Die Fallunterscheidung der untersuchten Person resp. der
	 * Geltungsbereich des Befundes (Leichenspender, Lebendspender,
	 * Organempfänger) wird im CDA Header mit dem documentationOf Element
	 * dokumentiert.</div>
	 *
	 * @param scope
	 *            the scope of this organ donor.
	 */
	public void addDocumentationOf(ReportScopes scope) {
		final DocumentationOf dof = CDAFactory.eINSTANCE.createDocumentationOf();
		final ServiceEvent se = CDAFactory.eINSTANCE.createServiceEvent();
		se.setCode(scope.getCE());
		dof.setServiceEvent(se);
		getMdht().getDocumentationOfs().add(dof);
	}

	/**
	 * Convenience function to add a Laboratory Battery Organizer and create the
	 * necessary elements, if they do not exist. If the elements exist, their
	 * contents will not be overwritten.
	 *
	 * These elements are: LaboratorySpecialtySection,
	 * LaboratoryReportProcessingEntry, and SpecimenAct with the given
	 * Laboratory Battery Organizer
	 *
	 * @param organizer
	 *            the LaboratoryBatteryOrganizer holding at least one
	 *            LaboratoryObservation
	 * @param sectionCode
	 *            the LOINC code for the LaboratorySpecialtySection
	 */
	public void addLaboratoryBatteryOrganizer(LaboratoryBatteryOrganizer organizer,
			Code sectionCode) {
		LaboratorySpecialtySection laboratorySpecialtySection = getLaboratorySpecialtySection(
				sectionCode);
		if (laboratorySpecialtySection == null) {
			sectionIndex++;
			if (sectionCode != null) {
				laboratorySpecialtySection = new LaboratorySpecialtySection(sectionCode,
						getLanguageCode());
				getMdht().setCode(sectionCode.getCE());
			} else {
				laboratorySpecialtySection = new LaboratorySpecialtySection();
			}
		}
		laboratorySpecialtySection.addLaboratoryBatteryOrganizer(sectionCode, organizer,
				getLanguageCode());
		if (isNarrativeTextGenerationEnabled()) {
			laboratorySpecialtySection.setText(generateNarrativeTextLaboratoryObservations(
					laboratorySpecialtySection, sectionIndex, "lss"));
		}
		addLaboratorySpecialtySection(laboratorySpecialtySection);

		// set the fixed laboratory Code
		final CE ce = DatatypesFactory.eINSTANCE.createCE();
		ce.setCode("11502-2");
		ce.setCodeSystem("2.16.840.1.113883.6.1");
		ce.setCodeSystemName("LOINC");
		ce.setDisplayName("LABORATORY REPORT.TOTAL");
		getMdht().setCode(ce);

	}

	/**
	 * Adds a LaboratorySpecialtySection.
	 *
	 * @param laboratorySpecialtySection
	 *            the section
	 */
	public void addLaboratorySpecialtySection(
			org.ehealth_connector.cda.ch.lab.lrtp.LaboratorySpecialtySection laboratorySpecialtySection) {
		getMdht().addSection(laboratorySpecialtySection.copy());
	}

	/**
	 * Applies the privacy filter to all record target elements.
	 *
	 * <table summary="Elements which will be kept">
	 * <thead>
	 * <tr>
	 * <th>Element name (english)</th>
	 * <th>Element name (german)</th>
	 * <th>CDA element</th>
	 * </tr>
	 * </thead><tbody>
	 * <tr>
	 * <td>Familiy name</td>
	 * <td>Nachname</td>
	 * <td>recordTarget/patientRole/patient/name[0]/family</td>
	 * </tr>
	 * <tr>
	 * <td>Given name</td>
	 * <td>Vorname</td>
	 * <td>recordTarget/patientRole/patient/name[0]/given</td>
	 * </tr>
	 * <tr>
	 * <td>Date of birth</td>
	 * <td>Geburtsdatum</td>
	 * <td>recordTarget/patientRole/patient/birthTime</td>
	 * </tr>
	 * <tr>
	 * <td>Gender</td>
	 * <td>Geschlecht</td>
	 * <td>recordTarget/patientRole/patient/administrativeGenderCode</td>
	 * </tr>
	 * </tbody>
	 * </table>
	 */
	public void applyPrivacyFilter() {
		byte index = 0;
		for (final RecordTarget originalRt : getMdht().getRecordTargets()) {
			// Get original elements
			PatientRole originalPr = null;
			if (originalRt.getPatientRole() != null) {
				originalPr = originalRt.getPatientRole();
			}
			Patient originalP = null;
			if ((originalPr != null) && (originalPr.getPatient() != null)) {
				originalP = originalPr.getPatient();
			}

			// Initialize new elements
			final RecordTarget processedRt = CDAFactory.eINSTANCE.createRecordTarget();
			final PatientRole processedPr = CDAFactory.eINSTANCE.createPatientRole();
			final Patient processedP = CDAFactory.eINSTANCE.createPatient();
			processedRt.setPatientRole(processedPr);
			processedPr.setPatient(processedP);

			// Copy all necessary elements from the original to the processed
			// recordTarget
			// Patient Name
			if ((originalP != null) && !originalP.getNames().isEmpty()) {
				if (originalP.getNames().get(0) != null) {
					final PN pn = DatatypesFactory.eINSTANCE.createPN();
					pn.getGivens().addAll(originalP.getNames().get(0).getGivens());
					pn.getFamilies().addAll(originalP.getNames().get(0).getFamilies());
					processedP.getNames().add(pn);
				}
			}

			// Birth time
			if ((originalP != null) && (originalP.getBirthTime() != null)) {
				processedP.setBirthTime(EcoreUtil.copy(originalP.getBirthTime()));
			}

			// Gender
			if ((originalP != null) && (originalP.getAdministrativeGenderCode() != null)) {
				processedP.setAdministrativeGenderCode(
						EcoreUtil.copy(originalP.getAdministrativeGenderCode()));
			}

			// Addr MSK
			final AD ad = DatatypesFactory.eINSTANCE.createAD();
			ad.setNullFlavor(NullFlavor.MSK);
			processedPr.getAddrs().add(ad);

			// Telecom (MSK)
			final TEL tel = DatatypesFactory.eINSTANCE.createTEL();
			tel.setNullFlavor(NullFlavor.MSK);
			processedPr.getTelecoms().add(tel);

			// SOAS ID
			if ((originalPr != null) && !originalPr.getIds().isEmpty()) {
				for (final II ii : originalPr.getIds()) {
					if (ii.getRoot().equals("2.16.756.5.30.1.129.1.1.1")) {
						processedPr.getIds().add(EcoreUtil.copy(ii));
					}
				}
			}

			getMdht().getRecordTargets().set(index, processedRt);
			index++;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ehealth_connector.cda.ch.lab.AbstractLaboratoryReport#
	 * createDocumentTitle()
	 */
	@Override
	protected String createDocumentTitle() {
		switch (this.getLanguageCode()) {
		case FRENCH:
			return ("Rapport de laboratoire dans le processus de transplantation");
		case GERMAN:
			return ("Laborbefund im Transplantationsprozess");
		case ITALIAN:
			return ("it: TOTRANSLATE");
		case ENGLISH:
			return ("Laboratory Report in the Transplantation Process");
		}
		return "Laboratory report";
	}

	/**
	 * Gets the BloodGroupObservation.
	 *
	 * @return the BloodGroupObservation
	 */
	public BloodGroupObservation getBloodGroupObservation() {
		if ((getStudiesSummarySection() != null)
				&& (getStudiesSummarySection().getBloodGroup() != null)) {
			return new BloodGroupObservation(getStudiesSummarySection().getBloodGroup().getMdht());
		}
		return null;
	}

	/**
	 * Gets the CodedVitalSignsSection.
	 *
	 * @return the CodedVitalSignsSection
	 */
	public CodedVitalSignsSection getCodedVitalSignsSection() {
		for (final Section s : getMdht().getAllSections()) {
			if (s instanceof org.openhealthtools.mdht.uml.cda.ihe.CodedVitalSignsSection) {
				return new CodedVitalSignsSection(
						(org.openhealthtools.mdht.uml.cda.ihe.CodedVitalSignsSection) s);
			}
		}
		return null;
	}

	/**
	 * <div class="en">Convenience function to get the LRTP relevant
	 * DocumentationOf elements. In case of LRTP it determines the case of organ
	 * donor. </div> <div class="de">Convenience Funktion um die LRTP-relevanten
	 * documentationOf Elemente zu erhalten. Die Fallunterscheidung der
	 * untersuchten Person resp. der Geltungsbereich des Befundes
	 * (Leichenspender, Lebendspender, Organempfänger) wird im CDA Header mit
	 * dem documentationOf Element dokumentiert.</div>
	 *
	 * @return the scope of this organ donor.
	 */
	public List<ReportScopes> getDocumentationOfs() {
		final List<ReportScopes> rl = new ArrayList<ReportScopes>();
		for (final DocumentationOf dof : getMdht().getDocumentationOfs()) {
			if ((dof.getServiceEvent() != null) && (dof.getServiceEvent().getCode() != null)) {
				final ReportScopes rs = ReportScopes
						.getEnum(dof.getServiceEvent().getCode().getCode());
				if (rs != null) {
					rl.add(rs);
				}
			}
		}
		return rl;
	}

	/**
	 * Gets the laboratory specialty section.
	 *
	 * @return the laboratory specialty section
	 */
	@Override
	public LaboratorySpecialtySection getLaboratorySpecialtySection() {
		if (!getMdht().getLaboratorySpecialtySections().isEmpty()) {
			return new LaboratorySpecialtySection(
					getMdht().getLaboratorySpecialtySections().get(0));
		}
		return null;
	}

	/**
	 * Gets the laboratory specialty section.
	 *
	 * @return the laboratory specialty section
	 */
	public LaboratorySpecialtySection getLaboratorySpecialtySection(Code sectionCode) {
		for (org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratorySpecialtySection section : getMdht()
				.getLaboratorySpecialtySections()) {
			Code currentSectionCode = new Code(section.getCode());
			if (currentSectionCode.getCode().equals(sectionCode.getCode())
					&& currentSectionCode.getCodeSystem().equals(sectionCode.getCodeSystem()))
				return new LaboratorySpecialtySection(section);
		}
		return null;
	}

	/**
	 * Gets the laboratory specialty section.
	 *
	 * @return the laboratory specialty section
	 */
	public List<LaboratorySpecialtySection> getLaboratorySpecialtySections() {
		final List<LaboratorySpecialtySection> ls = new ArrayList<LaboratorySpecialtySection>();
		for (final org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratorySpecialtySection lss : getMdht()
				.getLaboratorySpecialtySections()) {
			ls.add(new LaboratorySpecialtySection(lss));
		}
		return ls;
	}

	/**
	 * Convenience function to return all LaboratoryBatteryOrganizers directly
	 * from all underlying
	 * LaboratorySpecialtySection/LaboratoryReportDataProcessingEntry/
	 * SpecimenAct elements (even if they reside in a different
	 * SpecialtySection).
	 *
	 * @return a list of LaboratoryBatteryOrganizers.
	 */
	public List<LaboratoryBatteryOrganizer> getLrtpLaboratoryBatteryOrganizerList() {
		final ArrayList<LaboratoryBatteryOrganizer> lbol = new ArrayList<LaboratoryBatteryOrganizer>();
		for (final LaboratorySpecialtySection lss : getLaboratorySpecialtySections()) {
			final LaboratoryReportDataProcessingEntry lrdpe = lss
					.getLaboratoryReportDataProcessingEntry();
			if (lrdpe != null) {
				final SpecimenAct se = lrdpe.getSpecimenAct();
				if (se != null) {
					lbol.addAll(se.getLrtpLaboratoryBatteryOrganizers());
				}
			}
		}
		lbol.sort(new LaboratoryBatteryOrganizerComparator());
		return lbol;
	}

	/**
	 * Returns the narrative Text of the CodedVitalSignsSection.
	 *
	 * @return the narrative Text. Returns null, if this text does not exist.
	 */
	public String getNarrativeTextSectionCodedVitalSignsSection() {
		if ((getCodedVitalSignsSection() != null)
				&& (getCodedVitalSignsSection().getMdht().getText() != null)
				&& (getCodedVitalSignsSection().getMdht().getText().getText() != null)) {
			return getCodedVitalSignsSection().getMdht().getText().getText();
		}
		return null;
	}

	/**
	 * Returns the narrative Text of the StudiesSummarySection.
	 *
	 * @return the narrative Text. Returns null, if this text does not exist.
	 */
	public String getNarrativeTextSectionStudiesSummarySection() {
		if ((getStudiesSummarySection() != null)
				&& (getStudiesSummarySection().getText() != null)) {
			return getStudiesSummarySection().getText();
		}
		return null;
	}

	/**
	 * Convenience function, which returns the SpecimenAct directly from the
	 * underlying LaboratorySpecialtySection/LaboratoryReportDataProcessingEntry
	 * element.
	 *
	 * @return the SpecimenAct. Returns null, if this element does not exist.
	 */
	public BaseChSpecimenAct getSpecimenAct() {
		if ((getLaboratorySpecialtySection() != null)
				&& (getLaboratorySpecialtySection()
						.getLaboratoryReportDataProcessingEntry() != null)
				&& (getLaboratorySpecialtySection().getLaboratoryReportDataProcessingEntry()
						.getSpecimenAct() != null)) {
			return getLaboratorySpecialtySection().getLaboratoryReportDataProcessingEntry()
					.getSpecimenAct();
		}
		return null;
	}

	/**
	 * Gets the VitalSignsOrganizer.
	 *
	 * @return the VitalSignsOrganizer
	 */
	public VitalSignsOrganizer getVitalSignsOrganizer() {
		if ((getCodedVitalSignsSection() != null)
				&& (getCodedVitalSignsSection().getVitalSignsOrganizer() != null)) {
			return new VitalSignsOrganizer(
					getCodedVitalSignsSection().getVitalSignsOrganizer().getMdht());
		}
		return null;
	}

	/**
	 * Convenience function, which adds a BloodGroupObservation and creates the
	 * StudiesSummarySection automatically with the current LanguageCode of the
	 * document.
	 *
	 * @param observation
	 *            the observation
	 */
	public void setBloodGroupObservation(BloodGroupObservation observation) {
		// Check if this section already exists. If so, get it, else create it.
		StudiesSummarySection sss;
		if (getStudiesSummarySection() != null) {
			sss = getStudiesSummarySection();
		} else {
			sss = new StudiesSummarySection(getLanguageCode());
		}
		sss.setBloodGroup(observation);
		setStudiesSummary(sss);
	}

	/**
	 * Sets the CodedVitalSignsSection.
	 *
	 * @param codedVitalSigns
	 *            the CodedVitalSignsSection
	 */
	public void setCodedVitalSignsSection(CodedVitalSignsSection codedVitalSigns) {
		if (getCodedVitalSignsSection() == null) {
			getMdht().addSection(codedVitalSigns.copy());
		}
	}

	/**
	 * Sets the section/text element for the CodedVitalSignsSection.
	 *
	 * @param text
	 *            the text
	 */
	public void setNarrativeTextSectionCodedVitalSignsSection(String text) {
		if (getCodedVitalSignsSection() != null) {
			getCodedVitalSignsSection().getMdht().createStrucDocText(text);
		}
	}

	/**
	 * Sets the section/text element for the StudiesSummarySection.
	 *
	 * @param text
	 *            the text
	 */
	public void setNarrativeTextSectionStudiesSummarySection(String text) {
		if (getStudiesSummarySection() != null) {
			getStudiesSummarySection().getMdht().createStrucDocText(text);
		}
	}

	/**
	 * Sets the StudiesSummarySection.
	 *
	 * @param section
	 *            the StudiesSummarySection
	 */
	public void setStudiesSummary(StudiesSummarySection section) {
		if (getStudiesSummarySection() == null) {
			getMdht().addSection(section.copy());
		}
	}

	/**
	 * Convenience function, which adds a Vital Sign Organizer and creates the
	 * Vital Sign Section automatically with the current LanguageCode of the
	 * document.
	 *
	 * @param organizer
	 *            the organizer
	 */
	public void setVitalSignsOrganizer(VitalSignsOrganizer organizer) {
		// Check if this section already exists. If so, get it, else create it.
		CodedVitalSignsSection cvs;
		if (getCodedVitalSignsSection() != null) {
			cvs = getCodedVitalSignsSection();
		} else {
			cvs = new CodedVitalSignsSection(getLanguageCode());
		}
		cvs.setVitalSignsOrganizer(organizer);
		setCodedVitalSignsSection(cvs);
	}
}
