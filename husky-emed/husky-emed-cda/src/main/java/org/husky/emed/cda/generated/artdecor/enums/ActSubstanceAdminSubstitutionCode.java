/*
 * This code is made available under the terms of the Eclipse Public License v1.0
 * in the github project https://github.com/project-husky/husky there you also
 * find a list of the contributors and the license information.
 *
 * This project has been developed further and modified by the joined working group Husky
 * on the basis of the eHealth Connector opensource project from June 28, 2021,
 * whereas medshare GmbH is the initial and main contributor/author of the eHealth Connector.
 */
package org.husky.emed.cda.generated.artdecor.enums;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.Generated;

import org.husky.common.enums.CodeSystems;
import org.husky.common.enums.LanguageCode;
import org.husky.common.enums.ValueSetEnumInterface;

/**
 * Enumeration of ActSubstanceAdminSubstitutionCode values
 * <p>
 * EN: No designation found.<br>
 * DE: No designation found.<br>
 * FR: No designation found.<br>
 * IT: No designation found.<br>
 * <p>
 * Identifier: 2.16.756.5.30.1.1.11.81<br>
 * Effective date: 2020-07-10 00:52<br>
 * Version: 2020<br>
 * Status: DRAFT
 */
@Generated(value = "org.husky.codegenerator.ch.valuesets.UpdateValueSets", date = "2021-11-24")
public enum ActSubstanceAdminSubstitutionCode implements ValueSetEnumInterface {

    /**
     * EN: brand composition<br>
     * DE: Äquivalent, andere Marke<br>
     * FR: équivalent autre fabriquant<br>
     * IT: equivalente dello stesso produttore<br>
     */
    BRAND_COMPOSITION_L3("BC",
                         "2.16.840.1.113883.5.1070",
                         "brand composition",
                         "brand composition",
                         "Äquivalent, andere Marke",
                         "équivalent autre fabriquant",
                         "equivalente dello stesso produttore"),
    /**
     * EN: equivalent composition<br>
     * DE: äquivalente Zusammensetzung<br>
     * FR: composition équivalente<br>
     * IT: composizione simile<br>
     */
    EQUIVALENT_COMPOSITION_L2("EC",
                              "2.16.840.1.113883.5.1070",
                              "equivalent composition",
                              "equivalent composition",
                              "äquivalente Zusammensetzung",
                              "composition équivalente",
                              "composizione simile"),
    /**
     * EN: equivalent<br>
     * DE: Äquivalent<br>
     * FR: équivalent<br>
     * IT: equivalente<br>
     */
    EQUIVALENT_L1("E",
                  "2.16.840.1.113883.5.1070",
                  "equivalent",
                  "equivalent",
                  "Äquivalent",
                  "équivalent",
                  "equivalente"),
    /**
     * EN: formulary<br>
     */
    FORMULARY_L1("F",
                 "2.16.840.1.113883.5.1070",
                 "formulary",
                 "formulary",
                 "TOTRANSLATE",
                 "TOTRANSLATE",
                 "TOTRANSLATE"),
    /**
     * EN: generic composition<br>
     * DE: Äquivalent, Generikum<br>
     * FR: équivalent générique<br>
     * IT: composizione identica<br>
     */
    GENERIC_COMPOSITION_L3("G",
                           "2.16.840.1.113883.5.1070",
                           "generic composition",
                           "generic composition",
                           "Äquivalent, Generikum",
                           "équivalent générique",
                           "composizione identica"),
    /**
     * EN: none<br>
     * DE: nicht autorisiert<br>
     * FR: non autorisé<br>
     * IT: non autorizzato<br>
     */
    NONE_L1("N",
            "2.16.840.1.113883.5.1070",
            "none",
            "none",
            "nicht autorisiert",
            "non autorisé",
            "non autorizzato"),
    /**
     * EN: therapeutic alternative<br>
     * DE: therapeutische Alternative<br>
     * FR: alternative thérapeutique<br>
     * IT: alternativa terapeutica<br>
     */
    THERAPEUTIC_ALTERNATIVE_L2("TE",
                               "2.16.840.1.113883.5.1070",
                               "therapeutic alternative",
                               "therapeutic alternative",
                               "therapeutische Alternative",
                               "alternative thérapeutique",
                               "alternativa terapeutica"),
    /**
     * EN: therapeutic brand<br>
     * DE: therapeutische Alternative, andere Marke<br>
     * FR: lternative thérapeutique autre marque<br>
     * IT: alternativa terapeutica stesso produttore<br>
     */
    THERAPEUTIC_BRAND_L3("TB",
                         "2.16.840.1.113883.5.1070",
                         "therapeutic brand",
                         "therapeutic brand",
                         "therapeutische Alternative, andere Marke",
                         "lternative thérapeutique autre marque",
                         "alternativa terapeutica stesso produttore"),
    /**
     * EN: therapeutic generic<br>
     * DE: therapeutische Alternative, Generikum<br>
     * FR: alternative thérapeutique générique<br>
     * IT: stesso effetto terapeutico<br>
     */
    THERAPEUTIC_GENERIC_L3("TG",
                           "2.16.840.1.113883.5.1070",
                           "therapeutic generic",
                           "therapeutic generic",
                           "therapeutische Alternative, Generikum",
                           "alternative thérapeutique générique",
                           "stesso effetto terapeutico");

    /**
     * EN: Code for brand composition<br>
     * DE: Code für Äquivalent, andere Marke<br>
     * FR: Code de équivalent autre fabriquant<br>
     * IT: Code per equivalente dello stesso produttore<br>
     */
    public static final String BRAND_COMPOSITION_L3_CODE = "BC";

    /**
     * EN: Code for equivalent composition<br>
     * DE: Code für äquivalente Zusammensetzung<br>
     * FR: Code de composition équivalente<br>
     * IT: Code per composizione simile<br>
     */
    public static final String EQUIVALENT_COMPOSITION_L2_CODE = "EC";

    /**
     * EN: Code for equivalent<br>
     * DE: Code für Äquivalent<br>
     * FR: Code de équivalent<br>
     * IT: Code per equivalente<br>
     */
    public static final String EQUIVALENT_L1_CODE = "E";

    /**
     * EN: Code for formulary<br>
     */
    public static final String FORMULARY_L1_CODE = "F";

    /**
     * EN: Code for generic composition<br>
     * DE: Code für Äquivalent, Generikum<br>
     * FR: Code de équivalent générique<br>
     * IT: Code per composizione identica<br>
     */
    public static final String GENERIC_COMPOSITION_L3_CODE = "G";

    /**
     * EN: Code for none<br>
     * DE: Code für nicht autorisiert<br>
     * FR: Code de non autorisé<br>
     * IT: Code per non autorizzato<br>
     */
    public static final String NONE_L1_CODE = "N";

    /**
     * EN: Code for therapeutic alternative<br>
     * DE: Code für therapeutische Alternative<br>
     * FR: Code de alternative thérapeutique<br>
     * IT: Code per alternativa terapeutica<br>
     */
    public static final String THERAPEUTIC_ALTERNATIVE_L2_CODE = "TE";

    /**
     * EN: Code for therapeutic brand<br>
     * DE: Code für therapeutische Alternative, andere Marke<br>
     * FR: Code de lternative thérapeutique autre marque<br>
     * IT: Code per alternativa terapeutica stesso produttore<br>
     */
    public static final String THERAPEUTIC_BRAND_L3_CODE = "TB";

    /**
     * EN: Code for therapeutic generic<br>
     * DE: Code für therapeutische Alternative, Generikum<br>
     * FR: Code de alternative thérapeutique générique<br>
     * IT: Code per stesso effetto terapeutico<br>
     */
    public static final String THERAPEUTIC_GENERIC_L3_CODE = "TG";

    /**
     * Identifier of the value set.
     */
    public static final String VALUE_SET_ID = "2.16.756.5.30.1.1.11.81";

    /**
     * Name of the value set.
     */
    public static final String VALUE_SET_NAME = "ActSubstanceAdminSubstitutionCode";

    /**
     * Identifier of the code system (all values share the same).
     */
    public static final String CODE_SYSTEM_ID = "2.16.840.1.113883.5.1070";

    /**
     * Gets the Enum with a given code.
     *
     * @param code The code value.
     * @return the enum value found or {@code null}.
     */
    public static ActSubstanceAdminSubstitutionCode getEnum(final String code) {
        for (final ActSubstanceAdminSubstitutionCode x : values()) {
            if (x.getCodeValue().equals(code)) {
                return x;
            }
        }
        return null;
    }

    /**
     * Checks if a given enum is part of this value set.
     *
     * @param enumName The name of the enum.
     * @return {@code true} if the name is found in this value set, {@code false} otherwise.
     */
    public static boolean isEnumOfValueSet(final String enumName) {
        if (enumName == null) {
            return false;
        }
        try {
            Enum.valueOf(ActSubstanceAdminSubstitutionCode.class,
                         enumName);
            return true;
        } catch (final IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Checks if a given code value is in this value set.
     *
     * @param codeValue The code value.
     * @return {@code true} if the value is found in this value set, {@code false} otherwise.
     */
    public static boolean isInValueSet(final String codeValue) {
        for (final ActSubstanceAdminSubstitutionCode x : values()) {
            if (x.getCodeValue().equals(codeValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Machine interpretable and (inside this class) unique code.
     */
    private String code;

    /**
     * Identifier of the referencing code system.
     */
    private String codeSystem;

    /**
     * The display names per language.
     */
    private Map<LanguageCode, String> displayNames;

    /**
     * Instantiates this enum with a given code and display names.
     *
     * @param code          The code value.
     * @param codeSystem    The code system (OID).
     * @param displayName   The default display name.
     * @param displayNameEn The display name in English.
     * @param displayNameDe The display name in German.
     * @param displayNameFr The display name in French.
     * @param displayNameIt The display name in Italian.
     */
    ActSubstanceAdminSubstitutionCode(final String code, final String codeSystem, final String displayName, final String displayNameEn, final String displayNameDe, final String displayNameFr, final String displayNameIt) {
        this.code = code;
        this.codeSystem = codeSystem;
        this.displayNames = new HashMap<>();
        this.displayNames.put(null,
                              displayName);
        this.displayNames.put(LanguageCode.ENGLISH,
                              displayNameEn);
        this.displayNames.put(LanguageCode.GERMAN,
                              displayNameDe);
        this.displayNames.put(LanguageCode.FRENCH,
                              displayNameFr);
        this.displayNames.put(LanguageCode.ITALIAN,
                              displayNameIt);
    }

    /**
     * Gets the code system identifier.
     *
     * @return the code system identifier.
     */
    @Override
    public String getCodeSystemId() {
        return this.codeSystem;
    }

    /**
     * Gets the code system name.
     *
     * @return the code system identifier.
     */
    @Override
    public String getCodeSystemName() {
        final CodeSystems cs = CodeSystems.getEnum(this.codeSystem);
        if (cs != null) {
            return cs.getCodeSystemName();
        }
        return "";
    }

    /**
     * Gets the code value as a string.
     *
     * @return the code value.
     */
    @Override
    public String getCodeValue() {
        return this.code;
    }

    /**
     * Gets the display name defined by the language param. If there is no english translation, the default display name
     *      is returned.
     *
     * @param languageCode The language code to get the display name for.
     * @return the display name in the desired language. if language not found, display name in german will be returned.
     */
    @Override
    public String getDisplayName(final LanguageCode languageCode) {
        final String displayName = this.displayNames.get(languageCode);
        if (displayName == null && languageCode == LanguageCode.ENGLISH) {
            return this.displayNames.get(null);
        }
        return displayName;
    }

    /**
     * Gets the value set identifier.
     *
     * @return the value set identifier.
     */
    @Override
    public String getValueSetId() {
        return VALUE_SET_ID;
    }

    /**
     * Gets the value set name.
     *
     * @return the value set name.
     */
    @Override
    public String getValueSetName() {
        return VALUE_SET_NAME;
    }
}
