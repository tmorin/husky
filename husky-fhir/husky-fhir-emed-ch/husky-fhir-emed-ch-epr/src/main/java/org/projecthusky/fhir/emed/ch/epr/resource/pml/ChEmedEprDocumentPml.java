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
package org.projecthusky.fhir.emed.ch.epr.resource.pml;

import ca.uhn.fhir.model.api.annotation.ResourceDef;
import org.projecthusky.fhir.emed.ch.common.annotation.ExpectsValidResource;
import org.projecthusky.fhir.emed.ch.common.enums.EmedDocumentType;
import org.projecthusky.fhir.emed.ch.common.error.InvalidEmedContentException;
import org.projecthusky.fhir.emed.ch.epr.resource.ChEmedEprDocument;
import org.projecthusky.fhir.emed.ch.epr.resource.ChEmedEprEntry;

import java.io.Serial;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * The HAPI custom structure for the CH-EMED-EPR PML Document Bundle.
 *
 * @author Quentin Ligier
 **/
@ResourceDef(profile = "http://example.com/StructureDefinition/pmlDoc")
public class ChEmedEprDocumentPml extends ChEmedEprDocument {
    @Serial
    private static final long serialVersionUID = -1802412421572043644L;

    /**
     * Empty constructor for the parser.
     */
    public ChEmedEprDocumentPml() {
        super();
    }

    /**
     * Constructor that pre-populates fields.
     *
     * @param documentId The document ID.
     * @param timestamp  The document creation date.
     */
    public ChEmedEprDocumentPml(final UUID documentId,
                                final Instant timestamp) {
        super(documentId, timestamp);
    }

    @Override
    public EmedDocumentType getEmedType() {
        return EmedDocumentType.PML;
    }

    /**
     * Returns whether the composition entry exists.
     *
     * @return {@code true} if the composition entry exists, {@code false} otherwise.
     */
    public boolean hasCompositionEntry() {
        return this.getEntryByResourceType(ChEmedEprCompositionPml.class) != null;
    }

    /**
     * Returns the composition entry; if missing, it creates it.
     *
     * @return the composition entry.
     */
    public BundleEntryComponent getCompositionEntry() {
        var entry = this.getEntryByResourceType(ChEmedEprCompositionPml.class);
        if (entry == null) {
            entry = new BundleEntryComponent();
            this.getEntry().add(0, entry); // The composition shall go first
        }
        return entry;
    }

    /**
     * Returns the composition or throws.
     *
     * @return the composition.
     * @throws InvalidEmedContentException if the composition is missing.
     */
    @ExpectsValidResource
    public ChEmedEprCompositionPml resolveComposition() {
        final var entry = this.getEntryByResourceType(ChEmedEprCompositionPml.class);
        if (entry != null && entry.getResource() instanceof final ChEmedEprCompositionPml composition) {
            return composition;
        }
        throw new InvalidEmedContentException(
                "The ChEmedEprCompositionPml is missing in the document Bundle");
    }

    /**
     * Returns the list with medication statement, medication request, medication dispense and observation.
     *
     * @return the list with medication statement, medication request, medication dispense and observation.
     */
    @ExpectsValidResource
    public List<ChEmedEprEntry> resolveEntries() {
        final var resources = new ArrayList<ChEmedEprEntry>();
        for (final var entry : this.getEntry()) {
            final var resource = entry.getResource();

            if (resource instanceof ChEmedEprMedicationStatementPml medicationStatement) {
                resources.add(medicationStatement);
            } else if (resource instanceof ChEmedEprMedicationRequestPml medicationRequest) {
                resources.add(medicationRequest);
            } else if (resource instanceof ChEmedEprMedicationDispensePml medicationDispense) {
                resources.add(medicationDispense);
            } else if (resource instanceof ChEmedEprObservationPml observation) {
                resources.add(observation);
            }
        }
        return resources;
    }

    /**
     * Returns a list with ChEmedEprMedicationStatementPml resources.
     *
     * @return a list with ChEmedEprMedicationStatementPml resources.
     */
    public List<ChEmedEprMedicationStatementPml> resolveMedicationStatements() {
        return this.getEntryResourceByResourceType(ChEmedEprMedicationStatementPml.class);
    }

    /**
     * Returns a list with ChEmedEprMedicationRequestPml resources.
     *
     * @return a list with ChEmedEprMedicationRequestPml resources.
     */
    public List<ChEmedEprMedicationRequestPml> resolveMedicationRequests() {
        return this.getEntryResourceByResourceType(ChEmedEprMedicationRequestPml.class);
    }

    /**
     * Returns a list with ChEmedEprMedicationDispensePml resources.
     *
     * @return a list with ChEmedEprMedicationDispensePml resources.
     */
    public List<ChEmedEprMedicationDispensePml> resolveMedicationDispenses() {
        return this.getEntryResourceByResourceType(ChEmedEprMedicationDispensePml.class);
    }

    /**
     * Returns a list with ChEmedEprObservationPml resources.
     *
     * @return a list with ChEmedEprObservationPml resources.
     */
    public List<ChEmedEprObservationPml> resolveObservations() {
        return this.getEntryResourceByResourceType(ChEmedEprObservationPml.class);
    }

    /**
     * Sets the composition.
     *
     * @param composition The CH EMED Medication List Composition.
     * @return this.
     */
    public ChEmedEprDocumentPml setComposition(final ChEmedEprCompositionPml composition) {
        this.getCompositionEntry()
                .setFullUrl(composition.getIdentifier().getValue())
                .setResource(composition);
        return this;
    }

    /**
     * Adds a medication statement.
     *
     * @param medicationStatement a medication statement.
     * @return this.
     */
    public ChEmedEprDocumentPml addMedicationStatement(final ChEmedEprMedicationStatementPml medicationStatement) {
        this.addEntry()
                .setFullUrl(medicationStatement.getIdentifierFirstRep().getValue())
                .setResource(medicationStatement);
        return this;
    }

    /**
     * Adds a medication request.
     *
     * @param medicationRequest a medication request.
     * @return this.
     */
    public ChEmedEprDocumentPml addMedicationRequest(final ChEmedEprMedicationRequestPml medicationRequest) {
        this.addEntry()
                .setFullUrl(medicationRequest.getIdentifierFirstRep().getValue())
                .setResource(medicationRequest);
        return this;
    }

    /**
     * Adds a medication dispense.
     *
     * @param medicationDispense a medication dispense.
     * @return this.
     */
    public ChEmedEprDocumentPml addMedicationDispense(final ChEmedEprMedicationDispensePml medicationDispense) {
        this.addEntry()
                .setFullUrl(medicationDispense.getIdentifierFirstRep().getValue())
                .setResource(medicationDispense);
        return this;
    }

    /**
     * Adds an observation.
     *
     * @param observation an observation.
     * @return this.
     */
    public ChEmedEprDocumentPml addObservation(final ChEmedEprObservationPml observation) {
        this.addEntry()
                .setFullUrl(observation.getIdentifierFirstRep().getValue())
                .setResource(observation);
        return this;
    }
}
