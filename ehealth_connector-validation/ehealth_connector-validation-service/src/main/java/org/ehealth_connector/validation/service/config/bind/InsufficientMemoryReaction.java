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
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren.
// Generiert: 2017.04.30 um 04:58:07 AM CEST
//

package org.ehealth_connector.validation.service.config.bind;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java-Klasse für InsufficientMemoryReaction.
 *
 * <p>
 * Das folgende Schemafragment gibt den erwarteten Content an, der in dieser
 * Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="InsufficientMemoryReaction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="throwException"/>
 *     &lt;enumeration value="returnValidationError"/>
 *     &lt;enumeration value="sleep"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "InsufficientMemoryReaction")
@XmlEnum
public enum InsufficientMemoryReaction {

	@XmlEnumValue("throwException")
	THROW_EXCEPTION("throwException"), @XmlEnumValue("returnValidationError")
	RETURN_VALIDATION_ERROR("returnValidationError"), @XmlEnumValue("sleep")
	SLEEP("sleep");
	public static InsufficientMemoryReaction fromValue(String v) {
		for (InsufficientMemoryReaction c : InsufficientMemoryReaction.values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

	private final String value;

	InsufficientMemoryReaction(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

}
