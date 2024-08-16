package org.projecthusky.fhir.emed.ch.epr.model.emediplan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.PrimitiveType;

import java.util.stream.Collectors;

/**
 * A postal address. This class is made to be embedded within other classes like {@link EMediplanPatient} and
 * {@link EMediplanHealthcareOrganization} that should use it as a {@link com.fasterxml.jackson.annotation.JsonUnwrapped}
 * property so that it is flattened on inclusion.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EMediplanPostalAddress {
    /**
     * All information at lower leven than the postal code, can be several lines.
     */
    protected String street;
    /**
     * Postcode.
     */
    @JsonProperty("zip")
    protected @Nullable String postalCode;
    protected String city;
    /**
     * Country. If the address is in Switzerland, this property does not need to be set, as it is assumed by default
     * that the address is in Switzerland.
     * Format: Alpha-2 code (ISO 3166 5Country Codes), e.g. {@code FR} for France.
     */
    protected @Nullable String country;

    public static EMediplanPostalAddress fromFhirAddress(final Address fhirAddress) {
        String street = null, postalCode = null, city = null, country = null;
        if (fhirAddress.hasLine())
            street = fhirAddress.getLine().stream().map(PrimitiveType::getValue).collect(Collectors.joining("\n"));
        if (fhirAddress.hasPostalCode()) postalCode = fhirAddress.getPostalCode();
        if (fhirAddress.hasCity()) city = fhirAddress.getCity();
        if (fhirAddress.hasCountryElement()) {
            final var countryElement = fhirAddress.getCountryElement();
            final var countryCode = countryElement.getExtensionByUrl("http://hl7.org/fhir/StructureDefinition/iso21090-SC-coding");
            if (countryCode != null && countryCode.hasValue() && countryCode.getValue() instanceof Coding countryCoding) {
                country = countryCoding.getCode();
            }
        }
        return new EMediplanPostalAddress(street, postalCode, city, country);
    }
}
