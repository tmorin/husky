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
package org.ehealth_connector.security.ch.ppq.impl;

import org.ehealth_connector.security.ch.ppq.PrivacyPolicyQueryResponse;
import org.ehealth_connector.security.core.SecurityObject;
import org.opensaml.saml.saml2.core.Response;

/**
 * <!-- @formatter:off -->
 * <div class="en">Class implementing the corresponding interfaces for PrivacyPolicyQueryResponse and SecurityObject.</div>
 * <div class="de">Die Klasse implementiert das entsprechende Interfaces PrivacyPolicyQueryResponse und SecurityObject.</div>
 * <div class="fr"></div>
 * <div class="it"></div>
 * <!-- @formatter:on -->
 */
public class PrivacyPolicyQueryResponseImpl
		implements PrivacyPolicyQueryResponse, SecurityObject<Response> {

	private Response wrappedObject;

	protected PrivacyPolicyQueryResponseImpl(Response aInternalObject) {
		wrappedObject = aInternalObject;
	}

	@Override
	public Response getWrappedObject() {
		return wrappedObject;
	}

}
