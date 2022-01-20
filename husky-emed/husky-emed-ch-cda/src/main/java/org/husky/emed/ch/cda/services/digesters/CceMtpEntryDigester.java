/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 *
 */

/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.emed.ch.cda.services.digesters;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.husky.common.hl7cdar2.*;
import org.husky.common.utils.OptionalUtils;
import org.husky.emed.ch.cda.services.readers.AuthorReader;
import org.husky.emed.ch.cda.services.readers.SubAdmEntryReader;
import org.husky.emed.ch.cda.utils.IiUtils;
import org.husky.emed.ch.cda.utils.TemplateIds;
import org.husky.emed.ch.errors.InvalidEmedContentException;
import org.husky.emed.ch.models.common.AuthorDigest;
import org.husky.emed.ch.models.common.EmedReference;
import org.husky.emed.ch.models.entry.EmedMtpEntryDigest;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

/**
 * Creator of CDA-CH-EMED MTP item entry digests.
 *
 * @author Quentin Ligier
 */
@Component
public class CceMtpEntryDigester {

    /**
     * Creates a new {@link EmedMtpEntryDigest} from an MTP SubstanceAdministration element.
     *
     * @param substanceAdministration  The MTP SubstanceAdministration element.
     * @param mtpDocumentId            The MTP document ID.
     * @param mtpDocumentEffectiveTime The MTP document effective time.
     * @param parentDocumentAuthor     The parent document author (not the original document author).
     * @param parentSectionAuthor      The parent section author (not the original section author).
     * @return a digest of the element.
     */
    protected EmedMtpEntryDigest createDigest(final POCDMT000040SubstanceAdministration substanceAdministration,
                                              final String mtpDocumentId,
                                              final Instant mtpDocumentEffectiveTime,
                                              final AuthorDigest parentDocumentAuthor,
                                              final AuthorDigest parentSectionAuthor) throws InvalidEmedContentException {
        if (!TemplateIds.hasAllIds(TemplateIds.MTP_ENTRY, substanceAdministration.getTemplateId())) {
            throw new InvalidEmedContentException("The given substance administration is not an MTP item entry");
        }
        final var mtpEntry = new SubAdmEntryReader(Objects.requireNonNull(substanceAdministration));

        final AuthorDigest documentAuthor;
        final AuthorDigest sectionAuthor;
        if (substanceAdministration.getAuthor().size() == 2) {
            sectionAuthor = new AuthorReader(substanceAdministration.getAuthor().get(0)).toDigest();
            documentAuthor = new AuthorReader(substanceAdministration.getAuthor().get(1)).toDigest();
        } else if (substanceAdministration.getAuthor().size() == 1) {
            sectionAuthor = new AuthorReader(substanceAdministration.getAuthor().get(0)).toDigest();
            documentAuthor = sectionAuthor;
        } else {
            documentAuthor = parentDocumentAuthor;
            sectionAuthor = parentSectionAuthor;
        }

        return new EmedMtpEntryDigest(
                Objects.requireNonNull(mtpDocumentEffectiveTime),
                Objects.requireNonNull(mtpDocumentId),
                documentAuthor,
                sectionAuthor,
                IiUtils.getNormalizedUid(mtpEntry.getEntryId()),
                IiUtils.getNormalizedUid(mtpEntry.getEntryId()), // Use the MTP entry ID as treatment ID
                0,
                mtpEntry.getAnnotationComment().orElse(null),
                mtpEntry.getDosageInstructions(),
                mtpEntry.getManufacturedMaterialReader().toMedicationProduct(),
                mtpEntry.getRepeatNumber().orElse(null),
                mtpEntry.getRouteOfAdministration().orElse(null),
                this.getMtpStartTime(mtpEntry, mtpDocumentEffectiveTime),
                this.getMtpStopTime(mtpEntry),
                mtpEntry.getSubstitutionPermissions(),
                this.getReferenceToOriginalMtpEntry(substanceAdministration).orElse(null),
                mtpEntry.getTreatmentReason().orElse(null),
                mtpEntry.getPatientMedicationInstructions().orElse(null),
                mtpEntry.getFulfillmentInstructions().orElse(null)
        );
    }

    /**
     * Returns the starting time of the MTP item. It's the first known time from:
     * <li>The treatment period start time, defined by the first 'effectiveTime' of the MTP Item;
     * <li>The creationTime of the MTP document.
     *
     * @param mtpEntry                 The MTP item reader.
     * @param mtpDocumentEffectiveTime The MTP document effective time.
     * @return the starting time of the MTP item.
     */
    private Instant getMtpStartTime(final SubAdmEntryReader mtpEntry,
                                    final Instant mtpDocumentEffectiveTime) {
        return mtpEntry.getEffectiveStartTime().orElse(mtpDocumentEffectiveTime);
    }

    /**
     * Returns the ending time of the MTP item, if any. It's the first known date from:
     * <li>The treatment period end date, defined by the first 'effectiveTime' of the MTP Item;
     *
     * @param mtpEntry The MTP item reader.
     * @return the ending time of the MTP item or {@code null} if the MTP item is valid forever.
     */
    @Nullable
    private Instant getMtpStopTime(final SubAdmEntryReader mtpEntry) {
        return mtpEntry.getEffectiveStopTime().orElse(null);
    }

    /**
     * Finds the reference to the original MTP item if that's a consolidated MTP item entry.
     *
     * @param substanceAdministration The MTP item SubstanceAdministration.
     * @return an {@link Optional} that may contain a reference to the original MTP item entry.
     */
    private Optional<EmedReference> getReferenceToOriginalMtpEntry(final POCDMT000040SubstanceAdministration substanceAdministration) {
        var sa = substanceAdministration.getEntryRelationship().stream()
                .filter(entryRelationship -> entryRelationship.getTypeCode() == XActRelationshipEntryRelationship.REFR)
                .findAny()
                .map(POCDMT000040EntryRelationship::getSubstanceAdministration)
                .orElse(null);
        if (sa == null) {
            return Optional.empty();
        }
        final EmedReference reference = new EmedReference(
                Optional.of(sa.getReference())
                        .map(OptionalUtils::getListFirstElement)
                        .map(POCDMT000040Reference::getExternalDocument)
                        .map(POCDMT000040ExternalDocument::getId)
                        .map(OptionalUtils::getListFirstElement)
                        .map(II::getRoot)
                        .orElse(null),
                sa.getId().get(0).getRoot()
        );
        return Optional.of(reference);
    }
}
