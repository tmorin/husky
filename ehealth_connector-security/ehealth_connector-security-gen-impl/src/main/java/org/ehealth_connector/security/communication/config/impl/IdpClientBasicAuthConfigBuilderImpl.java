/*
 *
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
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
package org.ehealth_connector.security.communication.config.impl;

import org.ehealth_connector.security.communication.config.ClientConfigBuilder;

/**
 * <!-- @formatter:off -->
 * <div class="en">Class implementing the interface ClientConfigBuilder serving as builder to create config for client with basic authentication.</div>
 * <div class="de">Klasser die das Interface ClienConfiguilder implementiert. Dient dazu eine Clientkonfiguration zu bilden für die Kommunikation mit BasicAuthentication.</div>
 * <div class="fr">VOICIFRANCAIS</div>
 * <div class="it">ITALIANO</div>
 * <!-- @formatter:on -->
 */
public class IdpClientBasicAuthConfigBuilderImpl implements ClientConfigBuilder {

	private IdpClientBasicAuthConfigImpl config;

	public IdpClientBasicAuthConfigBuilderImpl() {
		config = new IdpClientBasicAuthConfigImpl();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.ehealth_connector.security.communication.config.ClientConfigBuilder#url(java.lang.String)
	 */
	@Override
	public IdpClientBasicAuthConfigBuilderImpl url(String aEndpointUrl) {
		config.setUrl(aEndpointUrl);
		return this;
	}

	public IdpClientBasicAuthConfigBuilderImpl basicAuthUsername(String aBasicAuthUsername) {
		config.setBasicAuthUsername(aBasicAuthUsername);
		return this;
	}

	public IdpClientBasicAuthConfigBuilderImpl basicAuthPassword(String aBasicAuthPassword) {
		config.setBasicAuthPassword(aBasicAuthPassword);
		return this;
	}

	public IdpClientBasicAuthConfigImpl create() {
		return config;
	}

}
