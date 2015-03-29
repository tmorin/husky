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

package org.ehealth_connector.cda.ch.textbuilder;

import java.util.ArrayList;

import org.ehealth_connector.cda.ch.enums.SectionsVACD;

/**
 * Builds the <text> part of the Immunization recommendations.
 * 
 * Always builds the whole part (not only adds one immunization recommendation).
 * 
 */
public class ProblemConcernEntryTextBuilder extends TextBuilder {

	private ArrayList<org.ehealth_connector.cda.ProblemConcern> problemConcerns;
	private String contentIdPrefix;

	/**
	 * Constructor.
	 * 
	 * @param problemConcerns a list of Problem Concerns
	 */
	public ProblemConcernEntryTextBuilder(
			ArrayList<org.ehealth_connector.cda.ProblemConcern> problemConcerns,
			SectionsVACD section) {
		this.problemConcerns = problemConcerns;
		contentIdPrefix = section.getContentIdPrefix();
	}

	/**
	 * Returns HTML formatted string.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		append("<table border='1' width='100%'>");
		addHeader();
		addBody();
		append("</table>");
		return super.toString();
	}

	private void addBody() {
		append("<tbody>");
		int i = 1;
		for (org.ehealth_connector.cda.ProblemConcern problemConcern : problemConcerns) {
			addRow(problemConcern, i++);
		}
		append("</tbody>");
	}

	private void addHeader() {
		append("<thead>");
		append("<tr>");
		append("<th>Risikokategorie</th>");
		append("<th>Risikofaktor</th>");
		append("</tr>");
		append("</thead>");
	}

	private void addRow(
			org.ehealth_connector.cda.ProblemConcern problemConcern, int i) {
		append("<tr>");
		addCell("Komplikationsrisiko");
		if (problemConcern.getConcern() != null) {
			addCellWithContent(problemConcern.getConcern(), contentIdPrefix, i);
		} else {
			addCell("");
		}
		append("</tr>");
	}
}
