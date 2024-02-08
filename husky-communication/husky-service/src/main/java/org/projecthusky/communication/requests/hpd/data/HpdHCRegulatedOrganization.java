/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.projecthusky.communication.requests.hpd.data;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.openehealth.ipf.commons.ihe.hpd.stub.dsmlv2.AddRequest;
import org.projecthusky.communication.enums.HpdAttributeName;
import org.projecthusky.communication.utils.HpdUtils;

/**
 * Class used for creating the HPD Organizational Provider Attributes
 *
 * Refer to the following documentation for a detailed description of the mandatory fields
 * <a href="https://www.ihe.net/uploadedFiles/Documents/ITI/IHE_ITI_Suppl_HPD.pdf"></a>
 * <a href="https://www.fedlex.admin.ch/filestore/fedlex.data.admin.ch/eli/oce/2022/39/de/pdf-a/fedlex-data-admin-ch-eli-oce-2022-39-de-pdf-a.pdf></a>
 *
 */
@Getter
@ToString
@Slf4j
public class HpdHCRegulatedOrganization extends HpdEntity {
  private final List<String> businessCategory;
  private final List<String> hcRegisteredName;
  private final List<String> organizations;

  public HpdHCRegulatedOrganization(String dn, String uid, List<String> hcIdentifier, List<String> objectClass,
      Map<HpdAttributeName, List<String>> optionalAttributes, List<String> businessCategory, List<String> hcRegisteredName,
      List<String> organizations) {
    super(dn, uid, hcIdentifier, objectClass, optionalAttributes);
    this.businessCategory = businessCategory;
    this.hcRegisteredName = hcRegisteredName;
    this.organizations = organizations;
  }

  @Override
  public AddRequest createAddRequest() {
    AddRequest addRequest = new AddRequest();
    addRequest.setDn(getDistinguishedName());

    HpdUtils.addHpdAttributes(addRequest, HpdAttributeName.BUSINESS_CATEGORY, businessCategory);
    HpdUtils.addHpdAttributes(addRequest, HpdAttributeName.HC_REGISTERED_NAME, hcRegisteredName);
    HpdUtils.addHpdAttributes(addRequest, HpdAttributeName.HC_IDENTIFIER, getHcIdentifier());
    HpdUtils.addHpdAttributes(addRequest, HpdAttributeName.ORGANIZATION, organizations);
    HpdUtils.addHpdAttribute(addRequest, HpdAttributeName.UID, getUid());
    HpdUtils.addHpdAttributes(addRequest, HpdAttributeName.OBJECT_CLASS, getObjectClass());

    HpdUtils.setOptionalAttributes(addRequest, getOptionalAttributes());

    return addRequest;
  }

  @Override
  public boolean isValid() {
    super.isValid();
    if (HpdUtils.hasNullOrBlank(businessCategory, hcRegisteredName, organizations)
        || organizations.isEmpty()
        || businessCategory.isEmpty()
        || hcRegisteredName.isEmpty()) {
      log.error("Mandatory validation constraints have not been fulfilled!");
      return false;
    }
    return true;
  }
}