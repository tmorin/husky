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

package org.ehealth_connector.common.enums.valuesets.model;

import java.util.List;

/**
 * <div class="en">This POJO class is used to parse the configuration from the
 * file <code>valuesets-sources.yaml</code> into an object for easier
 * handling</div>
 */
public class ValueSetConfiguration {

	private String baseUrl;

	private List<ValueSet> valueSets;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public List<ValueSet> getValueSets() {
		return valueSets;
	}

	public void setValueSets(List<ValueSet> valueSets) {
		this.valueSets = valueSets;
	}
}
