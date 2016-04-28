//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2016.04.08 at 10:09:10 AM CEST
//

package org.ehealth_connector.validation.service.schematron.bind;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * ehealth_connector.validation.service.schematron.bind package.
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

	private final static QName _TextLangText_QNAME = new QName("http://purl.oclc.org/dsdl/svrl",
			"langText");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * ehealth_connector.validation.service.schematron.bind
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link ActivePattern }
	 * 
	 */
	public ActivePattern createActivePattern() {
		return new ActivePattern();
	}

	/**
	 * Create an instance of {@link DiagnosticReference }
	 * 
	 */
	public DiagnosticReference createDiagnosticReference() {
		return new DiagnosticReference();
	}

	/**
	 * Create an instance of {@link FailedAssert }
	 * 
	 */
	public FailedAssert createFailedAssert() {
		return new FailedAssert();
	}

	/**
	 * Create an instance of {@link FiredRule }
	 * 
	 */
	public FiredRule createFiredRule() {
		return new FiredRule();
	}

	/**
	 * Create an instance of {@link LangTextType }
	 * 
	 */
	public LangTextType createLangTextType() {
		return new LangTextType();
	}

	/**
	 * Create an instance of {@link NsPrefixInAttributeValues }
	 * 
	 */
	public NsPrefixInAttributeValues createNsPrefixInAttributeValues() {
		return new NsPrefixInAttributeValues();
	}

	/**
	 * Create an instance of {@link SchematronOutput }
	 * 
	 */
	public SchematronOutput createSchematronOutput() {
		return new SchematronOutput();
	}

	/**
	 * Create an instance of {@link SuccessfulReport }
	 * 
	 */
	public SuccessfulReport createSuccessfulReport() {
		return new SuccessfulReport();
	}

	/**
	 * Create an instance of {@link Text }
	 * 
	 */
	public Text createText() {
		return new Text();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link LangTextType }
	 * {@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://purl.oclc.org/dsdl/svrl", name = "langText", scope = Text.class)
	public JAXBElement<LangTextType> createTextLangText(LangTextType value) {
		return new JAXBElement<LangTextType>(_TextLangText_QNAME, LangTextType.class, Text.class,
				value);
	}

}
