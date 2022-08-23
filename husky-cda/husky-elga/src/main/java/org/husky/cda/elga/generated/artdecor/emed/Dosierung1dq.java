/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.cda.elga.generated.artdecor.emed;

import javax.annotation.processing.Generated;

import org.husky.common.hl7cdar2.IVLPQ;

/**
 * Dosierung1dq
 * <p>
 * <p>
 * Identifier: 1.2.40.0.34.11.30037<br>
 * Effective date: 2015-09-05 00:00:00<br>
 * Status: active
 */
@Generated(value = "org.husky.codegenerator.cda.ArtDecor2JavaGenerator", date = "2022-02-22")
public class Dosierung1dq extends IVLPQ {

    public Dosierung1dq() {
    }

    /**
     * Creates fixed contents for CDA Element hl7DoseQuantity
     */
    private static org.husky.common.hl7cdar2.IVLPQ createHl7DoseQuantityFixedValue() {
        org.husky.common.hl7cdar2.IVLPQ retVal = new org.husky.common.hl7cdar2.IVLPQ();
        return retVal;
    }

    /**
     * Adds a predefined org.husky.common.hl7cdar2.IVLPQ, filled by:
     * @return the predefined element.
     */
    public static org.husky.common.hl7cdar2.IVLPQ getPredefinedDoseQuantity() {
        return createHl7DoseQuantityFixedValue();
    }
}
