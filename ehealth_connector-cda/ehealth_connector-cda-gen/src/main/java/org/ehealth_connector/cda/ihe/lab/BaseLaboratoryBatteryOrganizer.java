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
package org.ehealth_connector.cda.ihe.lab;

import java.util.ArrayList;
import java.util.List;

import org.ehealth_connector.cda.BaseObservation;
import org.ehealth_connector.cda.MdhtOrganizerFacade;
import org.ehealth_connector.common.mdht.Identificator;
import org.ehealth_connector.common.mdht.enums.StatusCode;
import org.openhealthtools.mdht.uml.cda.Observation;
import org.openhealthtools.mdht.uml.cda.ihe.lab.LABFactory;

/**
 * A base LaboratoryBatteryOrganizer.
 */
public class BaseLaboratoryBatteryOrganizer extends
		MdhtOrganizerFacade<org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratoryBatteryOrganizer> {

	/** The human information. */
	String humanInformation;

	/**
	 * Instantiates a new abstract laboratory battery organizer.
	 */
	public BaseLaboratoryBatteryOrganizer() {
		super(LABFactory.eINSTANCE.createLaboratoryBatteryOrganizer().init());
		setStatusCode(StatusCode.COMPLETED);
	}

	/**
	 * Instantiates a new abstract laboratory battery organizer.
	 *
	 * @param mdht
	 *            the mdht
	 */
	public BaseLaboratoryBatteryOrganizer(
			org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratoryBatteryOrganizer mdht) {
		super(mdht);
	}

	/**
	 * Adds the id for HIV.
	 *
	 * @param id
	 *            the id root: OID of the used System e.g. Berda Code:
	 *            2.16.756.5.30.1.129.1.2.1. extension: anonyme number for each
	 *            consultation.
	 */
	public void addId(Identificator id) {
		getMdht().getIds().add(id.getIi());
	}

	/**
	 * Gets the laboratory observations.
	 *
	 * @return the laboratory observations
	 */
	public List<BaseObservation> getLaboratoryObservations() {
		final List<BaseObservation> loList = new ArrayList<BaseObservation>();
		for (final Observation o : getMdht().getObservations()) {
			if (o instanceof org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratoryObservation) {
				loList.add(new BaseObservation(o));
			}
		}
		return loList;
	}
}
