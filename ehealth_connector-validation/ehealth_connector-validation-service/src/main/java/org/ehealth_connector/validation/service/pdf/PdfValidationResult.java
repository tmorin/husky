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

package org.ehealth_connector.validation.service.pdf;

import java.util.ArrayList;
import java.util.List;

public class PdfValidationResult {

	private boolean validationDone = false;

	private final List<PdfValidationResultEntry> results = new ArrayList<PdfValidationResultEntry>();

	public void add(PdfValidationResultEntry entry) {
		results.add(entry);
	}

	public List<PdfValidationResultEntry> getEntries() {
		return results;
	}

	public boolean isDone() {
		return validationDone;
	}

	public boolean isPdfValid() {
		boolean retVal = true;
		for (PdfValidationResultEntry entry : results) {
			if (entry.hasError())
				retVal = false;
		}
		return retVal;
	}

	public boolean isPdfValidatorApiInstalled() {
		boolean retVal = true;
		for (PdfValidationResultEntry entry : results) {
			for (String msg : entry.getErrMsgsSorted()) {
				if (msg.contains(PdfValidator.ERR_NOT_INSTALLED)) {
					retVal = false;
					break;
				}
			}
			if (!retVal)
				break;
		}
		return retVal;
	}

	public boolean isPdfValidatorApiLicensed() {
		boolean retVal = true;
		for (PdfValidationResultEntry entry : results) {
			for (String msg : entry.getErrMsgsSorted()) {
				if (msg.contains(PdfValidator.ERR_INVALID_LICENSE)) {
					retVal = false;
					break;
				}
			}
			if (!retVal)
				break;
		}
		return retVal;
	}

	public void resetIsDone() {
		validationDone = false;
	}

	public void setIsDone() {
		validationDone = true;
	}
}
