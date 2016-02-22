package org.ehealth_connector.cda.ch.lab.lrtp;

import java.util.ArrayList;
import java.util.List;

import org.ehealth_connector.cda.utils.CdaUtil;
import org.openhealthtools.mdht.uml.cda.Organizer;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ActRelationshipEntryRelationship;

public class SpecimenAct extends org.ehealth_connector.cda.ch.lab.AbstractSpecimenAct {

	public SpecimenAct() {
		super();
	}

	public SpecimenAct(org.openhealthtools.mdht.uml.cda.ihe.lab.SpecimenAct mdht) {
		super(mdht);
	}

	public void addLaboratoryBatteryOrganizer(LaboratoryBatteryOrganizer laboratoryBatteryOrganizer) {
		getMdht().addOrganizer(laboratoryBatteryOrganizer.copy());
		// Set the right type for the entryRelationship
		CdaUtil.setEntryRelationshipTypeCode(getMdht().getEntryRelationships(),
				x_ActRelationshipEntryRelationship.COMP);
	}

	public List<LaboratoryBatteryOrganizer> getLaboratoryBatteryOrganizers() {
		ArrayList<LaboratoryBatteryOrganizer> list = new ArrayList<LaboratoryBatteryOrganizer>();
		if (getMdht() != null && getMdht().getLaboratoryBatteryOrganizers() != null) {
			for (Organizer organizer : this.getMdht().getOrganizers()) {
				if (organizer instanceof org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratoryBatteryOrganizer) {
					org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratoryBatteryOrganizer iheOrganizer = (org.openhealthtools.mdht.uml.cda.ihe.lab.LaboratoryBatteryOrganizer) organizer;
					list.add(new LaboratoryBatteryOrganizer(iheOrganizer));
				}
			}
		}
		return list;
	}
}
