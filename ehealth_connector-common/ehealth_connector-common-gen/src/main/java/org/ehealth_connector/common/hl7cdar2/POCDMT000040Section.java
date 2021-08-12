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
// Generiert: 2020.07.09 um 01:07:39 PM CEST
//

package org.ehealth_connector.common.hl7cdar2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java-Klasse für POCD_MT000040.Section complex type.
 *
 * <p>
 * Das folgende Schemafragment gibt den erwarteten Content an, der in dieser
 * Klasse enthalten ist.
 *
 * <pre>
 * &lt;complexType name="POCD_MT000040.Section">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="realmCode" type="{urn:hl7-org:v3}CS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="typeId" type="{urn:hl7-org:v3}POCD_MT000040.InfrastructureRoot.typeId" minOccurs="0"/>
 *         &lt;element name="templateId" type="{urn:hl7-org:v3}II" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{urn:hl7-org:v3}II" minOccurs="0"/>
 *         &lt;element name="code" type="{urn:hl7-org:v3}CE" minOccurs="0"/>
 *         &lt;element name="title" type="{urn:hl7-org:v3}ST" minOccurs="0"/>
 *         &lt;element name="text" type="{urn:hl7-org:v3}StrucDoc.Text" minOccurs="0"/>
 *         &lt;element name="confidentialityCode" type="{urn:hl7-org:v3}CE" minOccurs="0"/>
 *         &lt;element name="languageCode" type="{urn:hl7-org:v3}CS" minOccurs="0"/>
 *         &lt;element name="subject" type="{urn:hl7-org:v3}POCD_MT000040.Subject" minOccurs="0"/>
 *         &lt;element name="author" type="{urn:hl7-org:v3}POCD_MT000040.Author" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="informant" type="{urn:hl7-org:v3}POCD_MT000040.Informant12" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="entry" type="{urn:hl7-org:v3}POCD_MT000040.Entry" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="component" type="{urn:hl7-org:v3}POCD_MT000040.Component5" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="nullFlavor" type="{urn:hl7-org:v3}NullFlavor" />
 *       &lt;attribute name="classCode" type="{urn:hl7-org:v3}ActClass" fixed="DOCSECT" />
 *       &lt;attribute name="moodCode" type="{urn:hl7-org:v3}ActMood" fixed="EVN" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "POCD_MT000040.Section", propOrder = { "realmCode", "typeId", "templateId", "id",
		"code", "title", "text", "confidentialityCode", "languageCode", "subject", "author",
		"informant", "entry", "component" })
public class POCDMT000040Section {

	protected List<CS> realmCode;
	protected POCDMT000040InfrastructureRootTypeId typeId;
	protected List<II> templateId;
	protected II id;
	protected CE code;
	protected ST title;
	protected StrucDocText text;
	protected CE confidentialityCode;
	protected CS languageCode;
	protected POCDMT000040Subject subject;
	protected List<POCDMT000040Author> author;
	protected List<POCDMT000040Informant12> informant;
	protected List<POCDMT000040Entry> entry;
	protected List<POCDMT000040Component5> component;
	@XmlAttribute(name = "ID")
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlID
	@XmlSchemaType(name = "ID")
	protected String idAttr;
	@XmlAttribute(name = "nullFlavor")
	protected List<String> nullFlavor;
	@XmlAttribute(name = "classCode")
	protected List<String> classCode;
	@XmlAttribute(name = "moodCode")
	protected List<String> moodCode;

	/**
	 * Gets the value of the author property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the author property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows: <pre>
	 *    getAuthor().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link POCDMT000040Author }
	 *
	 *
	 */
	public List<POCDMT000040Author> getAuthor() {
		if (author == null) {
			author = new ArrayList<POCDMT000040Author>();
		}
		return this.author;
	}

	/**
	 * Gets the value of the classCode property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the classCode property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows: <pre>
	 *    getClassCode().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 *
	 *
	 */
	public List<String> getClassCode() {
		if (classCode == null) {
			classCode = new ArrayList<String>();
		}
		return this.classCode;
	}

	/**
	 * Ruft den Wert der code-Eigenschaft ab.
	 *
	 * @return possible object is {@link CE }
	 *
	 */
	public CE getCode() {
		return code;
	}

	/**
	 * Gets the value of the component property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the component property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows: <pre>
	 *    getComponent().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link POCDMT000040Component5 }
	 *
	 *
	 */
	public List<POCDMT000040Component5> getComponent() {
		if (component == null) {
			component = new ArrayList<POCDMT000040Component5>();
		}
		return this.component;
	}

	/**
	 * Ruft den Wert der confidentialityCode-Eigenschaft ab.
	 *
	 * @return possible object is {@link CE }
	 *
	 */
	public CE getConfidentialityCode() {
		return confidentialityCode;
	}

	/**
	 * Gets the value of the entry property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the entry property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows: <pre>
	 *    getEntry().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link POCDMT000040Entry }
	 *
	 *
	 */
	public List<POCDMT000040Entry> getEntry() {
		if (entry == null) {
			entry = new ArrayList<POCDMT000040Entry>();
		}
		return this.entry;
	}

	/**
	 * Ruft den Wert der id-Eigenschaft ab.
	 *
	 * @return possible object is {@link II }
	 *
	 */
	public II getId() {
		return id;
	}

	/**
	 * Ruft den Wert der idAttr-Eigenschaft ab.
	 *
	 * @return possible object is {@link String }
	 *
	 */
	public String getIDAttr() {
		return idAttr;
	}

	/**
	 * Gets the value of the informant property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the informant property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows: <pre>
	 *    getInformant().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link POCDMT000040Informant12 }
	 *
	 *
	 */
	public List<POCDMT000040Informant12> getInformant() {
		if (informant == null) {
			informant = new ArrayList<POCDMT000040Informant12>();
		}
		return this.informant;
	}

	/**
	 * Ruft den Wert der languageCode-Eigenschaft ab.
	 *
	 * @return possible object is {@link CS }
	 *
	 */
	public CS getLanguageCode() {
		return languageCode;
	}

	/**
	 * Gets the value of the moodCode property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the moodCode property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows: <pre>
	 *    getMoodCode().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 *
	 *
	 */
	public List<String> getMoodCode() {
		if (moodCode == null) {
			moodCode = new ArrayList<String>();
		}
		return this.moodCode;
	}

	/**
	 * Gets the value of the nullFlavor property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the nullFlavor property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows: <pre>
	 *    getNullFlavor().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 *
	 *
	 */
	public List<String> getNullFlavor() {
		if (nullFlavor == null) {
			nullFlavor = new ArrayList<String>();
		}
		return this.nullFlavor;
	}

	/**
	 * Gets the value of the realmCode property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the realmCode property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows: <pre>
	 *    getRealmCode().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link CS }
	 *
	 *
	 */
	public List<CS> getRealmCode() {
		if (realmCode == null) {
			realmCode = new ArrayList<CS>();
		}
		return this.realmCode;
	}

	/**
	 * Ruft den Wert der subject-Eigenschaft ab.
	 *
	 * @return possible object is {@link POCDMT000040Subject }
	 *
	 */
	public POCDMT000040Subject getSubject() {
		return subject;
	}

	/**
	 * Gets the value of the templateId property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the templateId property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows: <pre>
	 *    getTemplateId().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link II }
	 *
	 *
	 */
	public List<II> getTemplateId() {
		if (templateId == null) {
			templateId = new ArrayList<II>();
		}
		return this.templateId;
	}

	/**
	 * Ruft den Wert der text-Eigenschaft ab.
	 *
	 * @return possible object is {@link StrucDocText }
	 *
	 */
	public StrucDocText getText() {
		return text;
	}

	/**
	 * Ruft den Wert der title-Eigenschaft ab.
	 *
	 * @return possible object is {@link ST }
	 *
	 */
	public ST getTitle() {
		return title;
	}

	/**
	 * Ruft den Wert der typeId-Eigenschaft ab.
	 *
	 * @return possible object is {@link POCDMT000040InfrastructureRootTypeId }
	 *
	 */
	public POCDMT000040InfrastructureRootTypeId getTypeId() {
		return typeId;
	}

	/**
	 * Legt den Wert der code-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link CE }
	 *
	 */
	public void setCode(CE value) {
		this.code = value;
	}

	/**
	 * Legt den Wert der confidentialityCode-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link CE }
	 *
	 */
	public void setConfidentialityCode(CE value) {
		this.confidentialityCode = value;
	}

	/**
	 * Legt den Wert der id-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link II }
	 *
	 */
	public void setId(II value) {
		this.id = value;
	}

	/**
	 * Legt den Wert der idAttr-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link String }
	 *
	 */
	public void setIDAttr(String value) {
		this.idAttr = value;
	}

	/**
	 * Legt den Wert der languageCode-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link CS }
	 *
	 */
	public void setLanguageCode(CS value) {
		this.languageCode = value;
	}

	/**
	 * Legt den Wert der subject-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link POCDMT000040Subject }
	 *
	 */
	public void setSubject(POCDMT000040Subject value) {
		this.subject = value;
	}

	/**
	 * Legt den Wert der text-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link StrucDocText }
	 *
	 */
	public void setText(StrucDocText value) {
		this.text = value;
	}

	/**
	 * Legt den Wert der title-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link ST }
	 *
	 */
	public void setTitle(ST value) {
		this.title = value;
	}

	/**
	 * Legt den Wert der typeId-Eigenschaft fest.
	 *
	 * @param value
	 *            allowed object is {@link POCDMT000040InfrastructureRootTypeId
	 *            }
	 *
	 */
	public void setTypeId(POCDMT000040InfrastructureRootTypeId value) {
		this.typeId = value;
	}

}
