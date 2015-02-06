/*******************************************************************************
 *
 * The authorship of this code and the accompanying materials is held by
 * medshare GmbH, Switzerland. All rights reserved.
 * http://medshare.net
 *
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 *
 * This code is are made available under the terms of the
 * Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the
 * Creative Commons Attribution-ShareAlike 3.0 Switzerland License.
 *
 * Year of publication: 2014
 *
 *******************************************************************************/

package org.ehc.cda;

import java.util.Date;
import java.util.List;

import org.ehc.cda.ch.enums.HistoryOfPastIllness;
import org.ehc.cda.ch.enums.RouteOfAdministration;
import org.ehc.cda.ch.enums.StatusCode;
import org.ehc.common.Author;
import org.ehc.common.DateUtil;
import org.ehc.common.Value;
import org.openhealthtools.mdht.uml.cda.CDAFactory;
import org.openhealthtools.mdht.uml.cda.Consumable;
import org.openhealthtools.mdht.uml.cda.ManufacturedProduct;
import org.openhealthtools.mdht.uml.cda.Material;
import org.openhealthtools.mdht.uml.cda.Observation;
import org.openhealthtools.mdht.uml.cda.ihe.IHEFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.CD;
import org.openhealthtools.mdht.uml.hl7.datatypes.CE;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.ED;
import org.openhealthtools.mdht.uml.hl7.datatypes.EN;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_PQ;
import org.openhealthtools.mdht.uml.hl7.datatypes.SXCM_TS;
import org.openhealthtools.mdht.uml.hl7.datatypes.TEL;
import org.openhealthtools.mdht.uml.hl7.vocab.ActClass;
import org.openhealthtools.mdht.uml.hl7.vocab.ActClassObservation;
import org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ActMoodDocumentObservation;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ActRelationshipEntryRelationship;
import org.openhealthtools.mdht.uml.hl7.vocab.x_DocumentSubstanceMood;

/**
 * Eine Impfung
 * 
 */
public class Immunization {

	private org.openhealthtools.mdht.uml.cda.ihe.Immunization mImmunization;
	private RouteOfAdministration routeCode;

	/**
	 * Constructor.
	 * 
	 * @param appliedAt
	 * @param vaccineName
	 * @param atcCode
	 * @param diseases - 
	 */
	public Immunization(Date appliedAt, String vaccineName, String atcCode, List<HistoryOfPastIllness> diseases) {
		mImmunization = IHEFactory.eINSTANCE.createImmunization().init();
		mImmunization.setClassCode(ActClass.SBADM);
		mImmunization.setMoodCode(x_DocumentSubstanceMood.EVN);
		mImmunization.setNegationInd(Boolean.FALSE);

		// TODO define OID constant
		addTemplateId(ii("2.16.756.5.30.1.1.1.1.1", "CDA-CH.Body.MediL3"));

		// TODO add correct GTIN for vaccination
		mImmunization.getIds().add(ii("1.3.160", "7680006690011"));

		//mImmunization.setText(createText());
		mImmunization.setStatusCode(StatusCode.COMPLETED.getCS());
		mImmunization.getEffectiveTimes().add(convertDate(appliedAt));
		mImmunization.setPriorityCode(createPriorityCode());
		mImmunization.setRouteCode(getRouteCodeAsCE());
		mImmunization.setDoseQuantity(createDoseQuantity());
		mImmunization.setConsumable(createConsumable(vaccineName, atcCode));
		addObservations(diseases);
	}

	/**
	 * Constructor.
	 * 
	 * @param immunization
	 */
	public Immunization(org.openhealthtools.mdht.uml.cda.ihe.Immunization immunization) {
		mImmunization = immunization;
	}

	private void addObservations(List<HistoryOfPastIllness> diseases) {
		if (diseases == null) {
			return;
		}

		int i = 0;
		for (HistoryOfPastIllness disease : diseases) {
			mImmunization.addObservation(createObservation(disease));
			// TODO how can we do this otherwise
			mImmunization.getEntryRelationships().get(i++).setTypeCode(x_ActRelationshipEntryRelationship.SUBJ);
		}
	}

	private void addTemplateId(II id) {
		mImmunization.getTemplateIds().add(id);
	}

	private SXCM_TS convertDate(Date appliedAt) {
		SXCM_TS timestamp = DatatypesFactory.eINSTANCE.createSXCM_TS();
		timestamp.setValue(DateUtil.formatDate(appliedAt));
		return timestamp;
	}

	private Consumable createConsumable(String vaccineName, String atcCode) {
		Consumable consumable = CDAFactory.eINSTANCE.createConsumable();

		consumable.setManufacturedProduct(createManufacturedProduct(vaccineName, atcCode));

		return consumable;
	}

	private IVL_PQ createDoseQuantity() {
		IVL_PQ quantity = DatatypesFactory.eINSTANCE.createIVL_PQ();
		// TODO set correct values (viavac doesn't have such...)
		quantity.setValue(0.5);
		quantity.setUnit("ml");
		return quantity;
	}

	private CE createEpsosPivotCode() {
		CE code = DatatypesFactory.eINSTANCE.createCE();

		// TODO 141025, set correct code
		code.setCode("111164008");
		// TODO 141028, set correct display name
		code.setDisplayName("Poliomyelitis vaccine");

		code.setCodeSystem("1.3.6.1.4.1.12559.11.10.1.3.1.42.28");
		code.setCodeSystemName("epSOSVaccine");

		return code;
	}

	/**
	 * Medikationsdaten muessen den GTIN (swissIndex) sowie den ATC-Code enthalten.
	 * @return
	 */
	private CE createEpsosPivotCodeElement(String atcCode) {
		CE code = createEpsosPivotCode();

		code.setOriginalText(createText());
		// TODO set correct GTIN
		code.getTranslations().add(createTranslation("7680006690011", "1.3.160"));

		code.getTranslations().add(createTranslation(atcCode, "2.16.840.1.113883.6.73"));
		return code;
	}

	private Material createManufacturedMaterial(String vaccineName, String atcCode) {
		Material material = CDAFactory.eINSTANCE.createMaterial();
		material.setName(createName(vaccineName));

		material.setCode(createEpsosPivotCodeElement(atcCode));

		return material;
	}

	private ManufacturedProduct createManufacturedProduct(String vaccineName, String atcCode) {
		ManufacturedProduct product = CDAFactory.eINSTANCE.createManufacturedProduct();
		// TODO define OID constant
		product.getTemplateIds().add(ii("1.3.6.1.4.1.19376.1.5.3.1.4.7.2"));
		product.getTemplateIds().add(ii("2.16.840.1.113883.10.20.1.53"));
		product.setManufacturedMaterial(createManufacturedMaterial(vaccineName, atcCode));
		return product;
	}

	private EN createName(String vaccineName) {
		EN name = DatatypesFactory.eINSTANCE.createEN();
		name.addText(vaccineName);
		return name;
	}

	private Observation createObservation(HistoryOfPastIllness disease) {
		Observation observation = CDAFactory.eINSTANCE.createObservation();
		observation.setClassCode(ActClassObservation.OBS);
		observation.setMoodCode(x_ActMoodDocumentObservation.EVN);
		// TODO define OID constant
		observation.getTemplateIds().add(ii("2.16.756.5.30.1.1.1.1.3.2.1", "CDA-CH.VACD.Body.MediL3.Reason"));
		// TODO define OID constant
		observation.getIds().add(ii("2.16.756.5.30.1.1.1.1.3.2.1", "66502037-9B9C-4ECB-9D24-A8EAD5D77D4B"));
		observation.setCode(disease.getCD());
		observation.setStatusCode(StatusCode.COMPLETED.getCS());
		return observation;
	}

	private CE createPriorityCode() {
		CE priorityCode = DatatypesFactory.eINSTANCE.createCE();
		priorityCode.setCode("R");
		priorityCode.setDisplayName("Routine");

		// TODO define OID constant
		priorityCode.setCodeSystem("2.16.840.1.113883.5.7");
		priorityCode.setCodeSystemName("ActPriority");
		return priorityCode;
	}	

	/**
	 * Only codes defined in cda-ch-vacd-voc.xml are allowed.
	 * 
	 * Actually these are: IDINJ, NASNEB, PO, SQ, TRNSDERMD and IM.
	 * 
	 * @return CE
	 */
	private CE createRouteCode() {
		CE ce = DatatypesFactory.eINSTANCE.createCE();
		ce.setCode(routeCode.getCodeValue());
		ce.setDisplayName(routeCode.getdisplayName());

		// TODO define OID constant
		ce.setCodeSystem("2.16.840.1.113883.5.112");
		ce.setCodeSystemName("VACD_RouteOfAdministration");
		return ce;
	}

	private CE createRouteCodeUnknown() {
		CE routeCode = DatatypesFactory.eINSTANCE.createCE();
		routeCode.setNullFlavor(NullFlavor.NA);
		return routeCode;
	}

	private TEL createTEL() {
		TEL tel = DatatypesFactory.eINSTANCE.createTEL();
		tel.setValue("#i1");
		return tel;
	}

	private ED createText() {
		ED text = DatatypesFactory.eINSTANCE.createED();
		text.setReference(createTEL());		
		return text;
	}

	private CD createTranslation(String code, String codeSystem) {
		CD translation = DatatypesFactory.eINSTANCE.createCD();
		translation.setCode(code);
		translation.setCodeSystem(codeSystem);
		return translation;
	}

	/**
	 * Returns author of this immunization.
	 * 
	 * @return Author
	 */
	public Author getAuthor() {
		try {
			org.openhealthtools.mdht.uml.cda.Author author = mImmunization.getAuthors().get(0);
			return new Author(author);
		} catch(IndexOutOfBoundsException e) {
			// no author available
			return null;
		}

	}


	/**
	 * Gibt die Dosis der Impfung zur��ck
	 * 
	 * @return Dosis Dosis der Impfung
	 */
	public Value getDosage() {
		return null;
	}

	public Date getEffectiveTime() {
		SXCM_TS date = mImmunization.getEffectiveTimes().get(0);
		return DateUtil.parseDateyyyyMMdd(date.getValue());
	}

	/**
	 * Returns the encapsulated IHE class.
	 * Method should be package private.
	 * 
	 * @return org.openhealthtools.mdht.uml.cda.ihe.Immunization
	 */
	public org.openhealthtools.mdht.uml.cda.ihe.Immunization getImmunization() {
		return mImmunization;
	}

	/**
	 * Gibt das Medikament fuer die Impfung zurueck.
	 * 
	 * @return Medikament Medikament für die Impfung
	 */
	public Medication getMedication() {
		return null;
	}

	private CE getRouteCodeAsCE() {
		if (routeCode == null) {
			return createRouteCodeUnknown();
		} else {
			return createRouteCode();
		}
	}

	public String getVaccineName() {
		return mImmunization.getConsumable().getManufacturedProduct().getManufacturedMaterial().getName().getText();
	}

	private II ii(String root) {
		return DatatypesFactory.eINSTANCE.createII(root);	
	}

	private II ii(String root, String extension) {
		return DatatypesFactory.eINSTANCE.createII(root, extension);	
	}

	/**
	 * Optionaly, one can set the route code (Einnahmearten).
	 * If not set, <routeCode nullFlavor="NA"/> is written to CDA document.
	 * 
	 * @param routeCode
	 */
	public void setRouteCode(RouteOfAdministration routeCode) {
		this.routeCode = routeCode;
	}

}
