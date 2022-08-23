/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.cda.elga.generated.artdecor.base;

import java.util.List;
import javax.annotation.processing.Generated;
import org.husky.common.hl7cdar2.ObjectFactory;
import org.husky.common.hl7cdar2.POCDMT000040Participant1;

/**
 * HeaderParticipantBetreuorg
 * <p>
 * <p>
 * Identifier: 1.2.40.0.34.11.1.1.7<br>
 * Effective date: 2013-10-16 00:00:00<br>
 * Status: active
 */
@Generated(value = "org.husky.codegenerator.cda.ArtDecor2JavaGenerator", date = "2022-02-21")
public class HeaderParticipantBetreuorg extends POCDMT000040Participant1 {

    public HeaderParticipantBetreuorg() {
        super.getTypeCode().add("IND");
        super.getTemplateId().add(createHl7TemplateIdFixedValue("1.2.40.0.34.11.1.1.7"));
        super.setAssociatedEntity(createHl7AssociatedEntityFixedValue("CAREGIVER"));
    }

    /**
     * Creates fixed contents for CDA Element hl7AssociatedEntity
     *
     * @param classCode the desired fixed value for this argument.
     */
    private static org.husky.common.hl7cdar2.POCDMT000040AssociatedEntity createHl7AssociatedEntityFixedValue(String classCode) {
        ObjectFactory factory = new ObjectFactory();
        org.husky.common.hl7cdar2.POCDMT000040AssociatedEntity retVal = factory.createPOCDMT000040AssociatedEntity();
        retVal.getClassCode().add(classCode);
        return retVal;
    }

    /**
     * Creates fixed contents for CDA Element hl7TemplateId
     *
     * @param root the desired fixed value for this argument.
     */
    private static org.husky.common.hl7cdar2.II createHl7TemplateIdFixedValue(String root) {
        ObjectFactory factory = new ObjectFactory();
        org.husky.common.hl7cdar2.II retVal = factory.createII();
        retVal.setRoot(root);
        return retVal;
    }

    /**
     * Gets the hl7AssociatedEntity
     */
    public org.husky.common.hl7cdar2.POCDMT000040AssociatedEntity getHl7AssociatedEntity() {
        return associatedEntity;
    }

    /**
     * Gets the hl7TemplateId
     */
    public List<org.husky.common.hl7cdar2.II> getHl7TemplateId() {
        return templateId;
    }

    /**
     * Sets the hl7AssociatedEntity
     */
    public void setHl7AssociatedEntity(org.husky.common.hl7cdar2.POCDMT000040AssociatedEntity value) {
        this.associatedEntity = value;
    }

    /**
     * Sets the hl7TemplateId
     */
    public void setHl7TemplateId(org.husky.common.hl7cdar2.II value) {
        getTemplateId().clear();
        getTemplateId().add(value);
    }
}
