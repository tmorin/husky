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
package org.ehealth_connector.security.deserialization.impl;

import org.ehealth_connector.security.communication.xua.XUserAssertionResponse;
import org.ehealth_connector.security.communication.xua.impl.XUserAssertionResponseBuilderImpl;
import org.ehealth_connector.security.exceptions.DeserializeException;
import org.opensaml.soap.wstrust.RequestSecurityTokenResponse;
import org.w3c.dom.Element;

/**
 * <!-- @formatter:off -->
 * <div class="en">Class implementing the corresponding interface Deserializer<Response> .</div>
 * <div class="de">Die Klasse implementiert das entsprechende Interface Deserializer<Response> .</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public class XUserAssertionResponseDeserializerImpl
		extends AbstractDeserializerImpl<RequestSecurityTokenResponse, XUserAssertionResponse> {

	@Override
	public XUserAssertionResponse fromXmlByteArray(byte[] aByteArray) throws DeserializeException {
		try {
			final RequestSecurityTokenResponse response = getOpenSamlDeserializer()
					.deserializeFromByteArray(aByteArray);
			return new XUserAssertionResponseBuilderImpl().create(response);
		} catch (final Exception e) {
			throw new DeserializeException(e);
		}
	}

	@Override
	public XUserAssertionResponse fromXmlElement(Element aXmlElement) throws DeserializeException {
		try {
			final RequestSecurityTokenResponse response = getOpenSamlDeserializer()
					.deserializeFromXml(aXmlElement);
			return new XUserAssertionResponseBuilderImpl().create(response);
		} catch (final Exception e) {
			throw new DeserializeException(e);
		}
	}

	@Override
	public XUserAssertionResponse fromXmlString(String aXmlString) throws DeserializeException {
		try {
			final RequestSecurityTokenResponse response = getOpenSamlDeserializer()
					.deserializeFromString(aXmlString);
			return new XUserAssertionResponseBuilderImpl().create(response);
		} catch (final Exception e) {
			throw new DeserializeException(e);
		}
	}

}
