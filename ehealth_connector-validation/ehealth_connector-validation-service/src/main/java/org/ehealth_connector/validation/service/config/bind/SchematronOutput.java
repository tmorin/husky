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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2016.03.23 at 04:54:51 PM CET
//

package org.ehealth_connector.validation.service.config.bind;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="ns-prefix-in-attribute-values" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="uri" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="prefix" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="active-pattern" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="document" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="fired-rule" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="context" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="document" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="failed-assert">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="test" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="see" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *                 &lt;attribute name="role" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="location" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="context" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *       &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="schemaVersion" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "nsPrefixInAttributeValuesOrActivePatternOrFiredRule" })
@XmlRootElement(name = "schematron-output")
public class SchematronOutput {

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 *
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this class.
	 *
	 * <pre>
	 * &lt;complexType>
	 *   &lt;simpleContent>
	 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
	 *       &lt;attribute name="document" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *     &lt;/extension>
	 *   &lt;/simpleContent>
	 * &lt;/complexType>
	 * </pre>
	 *
	 *
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "value" })
	public static class ActivePattern {

		@XmlValue
		protected String value;
		@XmlAttribute(name = "document")
		protected String document;
		@XmlAttribute(name = "id")
		protected String id;
		@XmlAttribute(name = "name")
		protected String name;

		/**
		 * Gets the value of the document property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getDocument() {
			return document;
		}

		/**
		 * Gets the value of the id property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getId() {
			return id;
		}

		/**
		 * Gets the value of the name property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getName() {
			return name;
		}

		/**
		 * Gets the value of the value property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Sets the value of the document property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setDocument(String value) {
			this.document = value;
		}

		/**
		 * Sets the value of the id property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setId(String value) {
			this.id = value;
		}

		/**
		 * Sets the value of the name property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setName(String value) {
			this.name = value;
		}

		/**
		 * Sets the value of the value property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setValue(String value) {
			this.value = value;
		}

	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 *
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this class.
	 *
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
	 *       &lt;/sequence>
	 *       &lt;attribute name="test" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *       &lt;attribute name="see" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
	 *       &lt;attribute name="role" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *       &lt;attribute name="location" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *       &lt;attribute name="context" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 *
	 *
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "content" })
	public static class FailedAssert {

		@XmlElementRef(name = "text", namespace = "http://purl.oclc.org/dsdl/svrl", type = JAXBElement.class, required = false)
		@XmlMixed
		protected List<Serializable> content;
		@XmlAttribute(name = "test")
		protected String test;
		@XmlAttribute(name = "see")
		@XmlSchemaType(name = "anyURI")
		protected String see;
		@XmlAttribute(name = "role")
		protected String role;
		@XmlAttribute(name = "location")
		protected String location;
		@XmlAttribute(name = "context")
		protected String context;
		@XmlAttribute(name = "id")
		protected String id;

		/**
		 * Gets the value of the content property.
		 *
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the content property.
		 *
		 * <p>
		 * For example, to add a new item, do as follows: <pre>
		 *    getContent().add(newItem);
		 * </pre>
		 *
		 *
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link JAXBElement }{@code <}{@link String }{@code >} {@link String }
		 *
		 *
		 */
		public List<Serializable> getContent() {
			if (content == null) {
				content = new ArrayList<Serializable>();
			}
			return this.content;
		}

		/**
		 * Gets the value of the context property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getContext() {
			return context;
		}

		/**
		 * Gets the value of the id property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getId() {
			return id;
		}

		/**
		 * Gets the value of the location property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getLocation() {
			return location;
		}

		/**
		 * Gets the value of the role property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getRole() {
			return role;
		}

		/**
		 * Gets the value of the see property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getSee() {
			return see;
		}

		/**
		 * Gets the value of the test property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getTest() {
			return test;
		}

		/**
		 * Sets the value of the context property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setContext(String value) {
			this.context = value;
		}

		/**
		 * Sets the value of the id property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setId(String value) {
			this.id = value;
		}

		/**
		 * Sets the value of the location property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setLocation(String value) {
			this.location = value;
		}

		/**
		 * Sets the value of the role property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setRole(String value) {
			this.role = value;
		}

		/**
		 * Sets the value of the see property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setSee(String value) {
			this.see = value;
		}

		/**
		 * Sets the value of the test property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setTest(String value) {
			this.test = value;
		}

	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 *
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this class.
	 *
	 * <pre>
	 * &lt;complexType>
	 *   &lt;simpleContent>
	 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
	 *       &lt;attribute name="context" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *       &lt;attribute name="document" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *     &lt;/extension>
	 *   &lt;/simpleContent>
	 * &lt;/complexType>
	 * </pre>
	 *
	 *
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "value" })
	public static class FiredRule {

		@XmlValue
		protected String value;
		@XmlAttribute(name = "context")
		protected String context;
		@XmlAttribute(name = "id")
		protected String id;
		@XmlAttribute(name = "document")
		protected String document;
		@XmlAttribute(name = "name")
		protected String name;

		/**
		 * Gets the value of the context property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getContext() {
			return context;
		}

		/**
		 * Gets the value of the document property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getDocument() {
			return document;
		}

		/**
		 * Gets the value of the id property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getId() {
			return id;
		}

		/**
		 * Gets the value of the name property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getName() {
			return name;
		}

		/**
		 * Gets the value of the value property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Sets the value of the context property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setContext(String value) {
			this.context = value;
		}

		/**
		 * Sets the value of the document property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setDocument(String value) {
			this.document = value;
		}

		/**
		 * Sets the value of the id property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setId(String value) {
			this.id = value;
		}

		/**
		 * Sets the value of the name property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setName(String value) {
			this.name = value;
		}

		/**
		 * Sets the value of the value property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setValue(String value) {
			this.value = value;
		}

	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 *
	 * <p>
	 * The following schema fragment specifies the expected content contained
	 * within this class.
	 *
	 * <pre>
	 * &lt;complexType>
	 *   &lt;simpleContent>
	 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
	 *       &lt;attribute name="uri" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *       &lt;attribute name="prefix" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *     &lt;/extension>
	 *   &lt;/simpleContent>
	 * &lt;/complexType>
	 * </pre>
	 *
	 *
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "value" })
	public static class NsPrefixInAttributeValues {

		@XmlValue
		protected String value;
		@XmlAttribute(name = "uri")
		protected String uri;
		@XmlAttribute(name = "prefix")
		protected String prefix;

		/**
		 * Gets the value of the prefix property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getPrefix() {
			return prefix;
		}

		/**
		 * Gets the value of the uri property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getUri() {
			return uri;
		}

		/**
		 * Gets the value of the value property.
		 *
		 * @return possible object is {@link String }
		 *
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Sets the value of the prefix property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setPrefix(String value) {
			this.prefix = value;
		}

		/**
		 * Sets the value of the uri property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setUri(String value) {
			this.uri = value;
		}

		/**
		 * Sets the value of the value property.
		 *
		 * @param value
		 *            allowed object is {@link String }
		 *
		 */
		public void setValue(String value) {
			this.value = value;
		}

	}

	@XmlElements({
			@XmlElement(name = "ns-prefix-in-attribute-values", type = SchematronOutput.NsPrefixInAttributeValues.class),
			@XmlElement(name = "active-pattern", type = SchematronOutput.ActivePattern.class),
			@XmlElement(name = "fired-rule", type = SchematronOutput.FiredRule.class),
			@XmlElement(name = "failed-assert", type = SchematronOutput.FailedAssert.class) })
	protected List<Object> nsPrefixInAttributeValuesOrActivePatternOrFiredRule;

	@XmlAttribute(name = "title")
	protected String title;

	@XmlAttribute(name = "schemaVersion")
	protected String schemaVersion;

	/**
	 * Gets the value of the nsPrefixInAttributeValuesOrActivePatternOrFiredRule
	 * property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the
	 * nsPrefixInAttributeValuesOrActivePatternOrFiredRule property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows: <pre>
	 *    getNsPrefixInAttributeValuesOrActivePatternOrFiredRule().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link SchematronOutput.NsPrefixInAttributeValues }
	 * {@link SchematronOutput.ActivePattern }
	 * {@link SchematronOutput.FiredRule }
	 * {@link SchematronOutput.FailedAssert }
	 *
	 *
	 */
	public List<Object> getNsPrefixInAttributeValuesOrActivePatternOrFiredRule() {
		if (nsPrefixInAttributeValuesOrActivePatternOrFiredRule == null) {
			nsPrefixInAttributeValuesOrActivePatternOrFiredRule = new ArrayList<Object>();
		}
		return this.nsPrefixInAttributeValuesOrActivePatternOrFiredRule;
	}

	/**
	 * Gets the value of the schemaVersion property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getSchemaVersion() {
		return schemaVersion;
	}

	/**
	 * Gets the value of the title property.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the value of the schemaVersion property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setSchemaVersion(String value) {
		this.schemaVersion = value;
	}

	/**
	 * Sets the value of the title property.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setTitle(String value) {
		this.title = value;
	}

}
