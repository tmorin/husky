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
package org.ehealth_connector.communication.xd.storedquery;

import java.util.List;

import org.ehealth_connector.common.Code;
import org.ehealth_connector.common.utils.XdsMetadataUtil;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.DocumentEntryType;

/**
 * Represents a query to get folders and its contents (associations, documents)
 * from an XDS Registry.
 */
public class GetFolderAndContentsQuery extends AbstractStoredQuery {

	/**
	 * Constructor. All arrays of codes are interpreted with as a disjunction (OR -
	 * semantics).
	 *
	 * @param folderId             id of the folder (either uniqueId or entryUUID)
	 *                             (required)
	 * @param isUUID               set to true if folderID is the entryUUID
	 *                             (internal registry identifier) of the folder and
	 *                             set to false if it is the uniqueID (external to
	 *                             registry) of the folder. In most user cases, this
	 *                             should be set to false
	 * @param formatCodes          array of formatCodes to query for (can be null)
	 * @param confidentialityCodes array of confidentiality codes to query for (can
	 *                             be null)
	 */
	public GetFolderAndContentsQuery(String folderId, boolean isUUID, Code[] formatCodes, Code[] confidentialityCodes) {

		var query = new org.openehealth.ipf.commons.ihe.xds.core.requests.query.GetFolderAndContentsQuery();

		if (isUUID) {
			query.setUuid(folderId);
		} else {
			query.setUniqueId(folderId);
		}

		query.setFormatCodes(XdsMetadataUtil.convertEhcCodeToCode(formatCodes));
		query.setConfidentialityCodes(XdsMetadataUtil.convertEhcCodeToQueryListCode(confidentialityCodes));

		setIpfStoredQuery(query);
	}

	/**
	 * Constructor that allows for the addition of a homeCommunityId to the query to
	 * support the XCA (Cross Community Access) profile extension of this query.
	 *
	 * @param folderId             id of the folder (either uniqueId or entryUUID)
	 *                             (required)
	 * @param isUUID               set to true if folderID is the entryUUID
	 *                             (internal registry identifier) of the folder and
	 *                             set to false if it is the uniqueID (external to
	 *                             registry) of the folder. In most user cases, this
	 *                             should be set to false
	 * @param formatCodes          array of formatCodes to query for (can be null)
	 * @param confidentialityCodes array of confidentiality codes to query for (can
	 *                             be null)
	 * @param homeCommunityId      this is the id of the home community as specified
	 *                             by the XCA profile. Value may be null or empty,
	 *                             in which case it is not added to the query.
	 */
	public GetFolderAndContentsQuery(String folderId, boolean isUUID, Code[] formatCodes, Code[] confidentialityCodes,
			String homeCommunityId) {

		var query = new org.openehealth.ipf.commons.ihe.xds.core.requests.query.GetFolderAndContentsQuery();

		if (isUUID) {
			query.setUuid(folderId);
		} else {
			query.setUniqueId(folderId);
		}

		query.setFormatCodes(XdsMetadataUtil.convertEhcCodeToCode(formatCodes));
		query.setConfidentialityCodes(XdsMetadataUtil.convertEhcCodeToQueryListCode(confidentialityCodes));
		query.setHomeCommunityId(homeCommunityId);

		setIpfStoredQuery(query);

	}

	/**
	 * Constructor that allows for the addition of a homeCommunityId to the query to
	 * support the XCA profile extension of this query.
	 *
	 * @param folderId             id of the folder (either uniqueId or entryUUID)
	 *                             (required)
	 * @param isUUID               set to true if folderID is the entryUUID
	 *                             (internal registry identifier) of the folder and
	 *                             set to false if it is the uniqueID (external to
	 *                             registry) of the folder. In most user cases, this
	 *                             should be set to false
	 * @param formatCodes          array of formatCodes to query for (can be null)
	 * @param confidentialityCodes array of confidentiality codes to query for (can
	 *                             be null)
	 * @param homeCommunityId      this is the id of the home community as specified
	 *                             by the XCA profile. Value may be null or empty,
	 *                             in which case it is not added to the query.
	 * @param objectType           for the OnDemand document feature. Tells whether
	 *                             you want static docs, on demand docs, or both
	 */
	public GetFolderAndContentsQuery(String folderId, boolean isUUID, Code[] formatCodes, Code[] confidentialityCodes,
			String homeCommunityId, DocumentEntryType objectType) {
		var query = new org.openehealth.ipf.commons.ihe.xds.core.requests.query.GetFolderAndContentsQuery();

		if (isUUID) {
			query.setUuid(folderId);
		} else {
			query.setUniqueId(folderId);
		}

		query.setFormatCodes(XdsMetadataUtil.convertEhcCodeToCode(formatCodes));
		query.setConfidentialityCodes(XdsMetadataUtil.convertEhcCodeToQueryListCode(confidentialityCodes));
		query.setHomeCommunityId(homeCommunityId);
		query.setDocumentEntryTypes(List.of(objectType));

		setIpfStoredQuery(query);
	}

	/**
	 * Adds an additional disjunctive clause of confidentiality codes to the query.
	 * Per IHE 2008-2009 ITI CP 228, codes with in the parameter will be interpreted
	 * with OR semantics. The resultant disjunctive clause will be AND-ed together
	 * with any confidentialityCode clauses previously added. Calling this method
	 * sequentially will result in the AND-ing of multiple clauses.
	 *
	 * @param confidentialityCodes array of confidentialityCodes, interpreted as a
	 *                             disjunctive clause in which each
	 *                             confidentialityCodes[i] will be a literal of that
	 *                             clause, to add to the query.
	 */
	public void addConfidentialityCodes(Code[] confidentialityCodes) {

		var query = (org.openehealth.ipf.commons.ihe.xds.core.requests.query.GetFolderAndContentsQuery) getIpfQuery();

		query.getConfidentialityCodes().getOuterList().add(XdsMetadataUtil.convertEhcCodeToCode(confidentialityCodes));
	}

}
