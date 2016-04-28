//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2013.04.08 at 08:04:19 AM CEST
//
/*******************************************************************************
 *
 * The authorship of this code and the accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. http://medshare.net
 *
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 *
 * This code is are made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * Year of publication: 2015
 *
 *******************************************************************************/

package org.ehealth_connector.validation.service.config.bind;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the net.medshare.cda.validator.config.bind
 * package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _Configuration_QNAME = new QName("", "configuration");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * net.medshare.cda.validator.config.bind
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link ApplicationType }
	 * 
	 */
	public ApplicationType createApplicationType() {
		return new ApplicationType();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ConfigurationType }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "", name = "configuration")
	public JAXBElement<ConfigurationType> createConfiguration(ConfigurationType value) {
		return new JAXBElement<ConfigurationType>(_Configuration_QNAME, ConfigurationType.class,
				null, value);
	}

	/**
	 * Create an instance of {@link ConfigurationType }
	 * 
	 */
	public ConfigurationType createConfigurationType() {
		return new ConfigurationType();
	}

	/**
	 * Create an instance of {@link RuleSetType }
	 * 
	 */
	public RuleSetType createRuleSetType() {
		return new RuleSetType();
	}

	/**
	 * Create an instance of {@link SchematronType }
	 * 
	 */
	public SchematronType createSchematronType() {
		return new SchematronType();
	}

}
