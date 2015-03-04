package org.ehealth_connector.cda.ch.enums;

import java.util.Arrays;

import org.ehealth_connector.common.Code;
import org.openhealthtools.mdht.uml.hl7.datatypes.CS;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;

/*
 *<div class="de">Zustand eines Leidens</div>
 *<div class="fr"></div>
 */
public enum ProblemConcernStatusCode {

	/** 
	 *<div class="de">Ein Leiden, das noch aktuell ist</div>
	 *<div class="fr">Affectation encore actuelle suspended</div>
	 */
	ACTIVE ("active", "active"),
	/** 
	 *<div class="de">Das Leiden wurde geheilt. Es wird nicht weiter behandelt. Der Eintrag wird nur noch zwecks Historisierung geführt.</div>
	 *<div class="fr">Affectation guérie. Traitement terminé. L’entrée n'est effectuée qu’afin de compléter l’historique.</div>
	 */
	COMPLETED ("completed", "completed"),
	/** 
	 *<div class="de">Ein Leiden, das nicht geheilt wurde, aber derzeit nicht aktuell ist</div>
	 *<div class="fr">Affectation guérie. Traitement terminé. L’entrée n'est effectuée qu’afin de compléter l’historique.</div>
	 */
	SUSPENDED ("suspended", "suspended"),
	/** 
	 *<div class="de">Ein Leiden, das nicht geheilt wurde, aber nicht mehr weiter behandelt wird. Dieser Wert kann z.B. dann eingesetzt werden, wenn der Patient gegen den ärztlichen Rat eine Behandlung abgebrochen hat.</div>
	 *<div class="fr">Affectation qui n’a pas été guérie, mais dont le traitement n’est plus poursuivi. Cette valeur peut par exemple être utilisée si le patient a interrompu un traitement contre l’avis du médecin.</div>
	 */
	ABORTED ("aborted", "aborted");
	public static final String ACTIVE_CODE="active";
	public static final String COMPLETED_CODE="completed";
	public static final String SUSPENDED_CODE="suspended";
	public static final String ABORTED_CODE="aborted";


	public static final String CODE_SYSTEM="2.16.840.1.113883.5.14";
	public static final String CODE_SYSTEM_NAME="ActStatus";


	public static ProblemConcernStatusCode getEnum(String code) {
		for (ProblemConcernStatusCode x : values()) {
			if (x.getCodeValue().equals(code)) {
				return x;
			}
		}
		return null;
	}
	private String code;

	private String displayName;

	ProblemConcernStatusCode (String code, String displayName) {
		this.code = code;
		this.displayName = displayName;
	}

	public Code getCode() {
		Code ehcCode = new Code(CODE_SYSTEM, code, displayName);
		return ehcCode;
	}

	public String getCodeSystemId() {
		return CODE_SYSTEM;
	}

	public String getCodeSystemName() {
		return CODE_SYSTEM_NAME;
	}

	public String getCodeValue() {
		return code;
	}

	public CS getCS() {
		CS cs = DatatypesFactory.eINSTANCE.createCS();
		cs.setCode(code);
		return cs;
	}

	public String getdisplayName() {
		return displayName;
	}

	public boolean isEnumOfValueSet(String enumStr) {
		return Arrays.asList(values()).contains(enumStr);
	}
	public boolean isInValueSet(String code) {
		for (ProblemConcernStatusCode x : values()) {
			if (x.getCodeValue().equals(code)) {
				return true;
			}
		}
		return false;
	}

}