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

package org.ehealth_connector.cda.ch.textbuilder;

import java.util.List;

import org.ehealth_connector.cda.BaseProblemConcern;
import org.ehealth_connector.cda.BaseProblemEntry;
import org.ehealth_connector.cda.enums.ContentIdPrefix;
import org.ehealth_connector.cda.textbuilder.TextBuilder;
import org.ehealth_connector.common.mdht.Value;
import org.ehealth_connector.common.utils.DateUtilMdht;

/**
 * Builds the &lt;text&gt; part of the Immunization recommendations.
 *
 * Always builds the whole part (not only adds one immunization recommendation).
 *
 */
public class ProblemConcernEntryChTextBuilder extends TextBuilder {

	private final List<org.ehealth_connector.cda.BaseProblemConcern> problemConcerns;
	private final String contentIdPrefix;

	/**
	 * Constructor.
	 *
	 * @param problemConcerns
	 *            a list of Problem Concerns
	 * @param section
	 *            the section
	 */
	public ProblemConcernEntryChTextBuilder(List<BaseProblemConcern> problemConcerns,
			ContentIdPrefix section) {
		this.problemConcerns = problemConcerns;
		contentIdPrefix = section.getContentIdPrefix();
	}

	private void addBody() {
		append("<tbody>");
		int i = 0;
		for (final org.ehealth_connector.cda.BaseProblemConcern problemConcern : problemConcerns) {
			addRow(problemConcern, i);
			i++;
		}
		append("</tbody>");
	}

	private void addHeader() {
		// Currently only German available. Translation contributions are
		// welcome
		append("<thead>");
		append("<tr>");
		append("<th>Bereich</th>");
		append("<th>Leiden</th>");
		append("<th>Von</th>");
		append("<th>Bis</th>");
		append("</tr>");
		append("</thead>");
	}

	private void addRow(org.ehealth_connector.cda.BaseProblemConcern problemConcerns, int index) {
		// Currently only German available. Translation contributions are
		// welcome
		int i = 0;
		for (BaseProblemEntry problem : problemConcerns.getProblemEntries()) {
			append("<tr>");
			if (i == 0 && problemConcerns.getConcern() != null) {
				addCell(problemConcerns.getConcern());
			} else {
				addCell("");
			}

			Value val = problem.getValue();
			if (val != null)
				addCellWithContent(val.toString(), contentIdPrefix, index);
			else
				addCell("");

			addCell(DateUtilMdht.formatDateCH(problem.getStartDate()));
			addCell(DateUtilMdht.formatDateCH(problem.getEndDate()));

			append("</tr>");
			i++;
		}
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
}
