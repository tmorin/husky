/*******************************************************************************
 *
 * The authorship of this code and the accompanying materials is held by
 * medshare GmbH, Switzerland. All rights reserved.
 * http://medshare.net
 *
 * Project Team: https://sourceforge.net/p/ehealthconnector/wiki/Team/
 *
 * This code is are made available under the terms of the
 * Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the
 * Creative Commons Attribution-ShareAlike 3.0 Switzerland License.
 *
 * Year of publication: 2014
 *
 *******************************************************************************/

package org.ehc.common;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.ehc.cda.ch.enums.CodeSystems;
import org.ehc.common.Address;
import org.ehc.common.DateUtil;
import org.ehc.common.Identificator;
import org.ehc.common.Name;
import org.ehc.common.Util;
import org.ehc.common.ConvenienceUtilsEnums.KnownOID;
import org.ehc.common.ConvenienceUtilsEnums.UseCode;
import org.openhealthtools.ihe.xds.metadata.AuthorType;
import org.openhealthtools.mdht.uml.cda.AssignedAuthor;
import org.openhealthtools.mdht.uml.cda.CDAFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.CE;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;


/**
 * Ein Autor (meist ein Arzt)
 */
public class Author {
	org.openhealthtools.mdht.uml.cda.Author mAuthor;

	/**
	 * Erstellt ein eHealthconnector-Author Objekt mittels eines MDHT-Author
	 * Objekts
	 * 
	 * @param authorMdht
	 */
	public Author(org.openhealthtools.mdht.uml.cda.Author authorMdht) {
		this.mAuthor = authorMdht;
	}
	
	//TODO Hier wäre es evtl. sinnvoller für einen Arzt eine eigene Klasse zu erstellen, in der die GLN Verpflichtend anzugeben ist. Beim Aufruf des VACD-Konstruktors wird dann eben diese Klasse dem Dokument hinzugefügt (daraus kann dann auch gleich die ID für den Custodian generiert werden).

	/**
	 * Erstellt einen neuen Autor (Dieser Konstruktor wird oft gebraucht für
	 * Behandelnde)
	 * 
	 * @param name
	 *            Name
	 * @param gln
	 *            Global Location Number (GLN)
	 */
	public Author(Name name, String gln) {
		// Create and fill Person Name and GLN
		II id = DatatypesFactory.eINSTANCE.createII();
		id.setRoot(ConvenienceUtilsEnums.knownOID(KnownOID.GLN));
		id.setExtension(gln);

		mAuthor = CDAFactory.eINSTANCE.createAuthor();
		org.openhealthtools.mdht.uml.cda.Person asPers = CDAFactory.eINSTANCE
				.createPerson();

		AssignedAuthor asAuth = CDAFactory.eINSTANCE.createAssignedAuthor();
		asAuth.setAssignedPerson(asPers);
		asAuth.getIds().add(id);

		mAuthor.setAssignedAuthor(asAuth);
		
		// add functionCode and time
		mAuthor.setFunctionCode(createFunctionCode());
		mAuthor.setTime(DateUtil.nowAsTS());
		this.addName(name);
	}
	
	private CE createFunctionCode() {
		CE ce = DatatypesFactory.eINSTANCE.createCE();
		ce.setCode("221");
		ce.setCodeSystem("2.16.840.1.113883.2.9.6.2.7");
		ce.setCodeSystemName("ISCO-08");
		ce.setDisplayName("Medical doctors");
		return ce;
	}

	/**
	 * @param iAuthor
	 */
	public Author(AuthorType iAuthor) {
		// System.out.println(iAuthor.getAuthorPerson().getFamilyName());
	}

	/**
	 * Weist dem Autor eine Postadresse zu
	 * 
	 * @param address
	 *            Die Postadresse des Autors
	 */
	public void addAddress(Address address) {
		mAuthor.getAssignedAuthor().getAddrs().add(address);
	}

	/**
	 * Weist dem Autor eine eMail Adresse zu
	 * 
	 * @param eMail
	 *            eMail Adresse
	 * @param usage
	 *            Verwendungszweck (Privat, Geschäft)
	 */
	public void addEMail(String eMail, UseCode usage) {
		// Auto-generated method stub

	}

	/**
	 * Weist dem Autor eine Faxnummer zu
	 * 
	 * @param faxNr
	 *            Faxnummer (nur internationale Rufnummer ohne Sonderzeichen
	 *            erlaubt). Beispiel: +41322345566
	 * @param usage
	 *            Verwendungszweck (Privat, Geschäft)
	 */
	public void addFax(String faxNr, UseCode usage) {
		// Auto-generated method stub

	}

	/**
	 * Weist dem Autoren eine ID zu
	 * 
	 * @param identificator
	 *            Kombination von eigentlicher ID und der OID der verwaltenden
	 *            Domäne
	 */
	public void addID(Identificator identificator) {
		mAuthor.getAssignedAuthor().getIds().add(identificator.getIi());
	}

	/**
	 * Weist dem Autor eine Telefonnummer zu
	 * 
	 * @param phoneNr
	 *            Telefonnummer (nur internationale Rufnummer ohne Sonderzeichen
	 *            erlaubt). Beispiel: +41322345566
	 * @param usage
	 *            Verwendungszweck (Privat, Geschäft, Mobil)
	 */
	public void addPhone(String phoneNr, UseCode usage) {
		// Auto-generated method stub

	}

	/**
	 * Weist dem Autor ein Fachgebiet zu (wird vor allem für Ärzte gebraucht)
	 * 
	 * @param speciality
	 *            Fachgebiet (z.B. Facharzt für Allgemeine Medizin)
	 */
	public void addSpeciality(String speciality) {
		// Auto-generated method stub
		// This is not a CDA-Element, but is important for the transmission of a
		// document with XDS!
	}

	/**
	 * Weist dem Autor eine Webseite zu
	 * 
	 * @param eMail
	 *            Webseite
	 * @param usage
	 *            Verwendungszweck (Privat, Geschäft)
	 */
	public void addWebsite(String eMail, UseCode usage) {
		// Auto-generated method stub

	}

	public void addName(Name name) {
		this.mAuthor.getAssignedAuthor().getAssignedPerson().getNames()
				.add(name.getPn());
	}

	public void addName(String familyName) {
		this.mAuthor.getAssignedAuthor().getAssignedPerson().getNames().get(0)
				.getFamilies().add(Util.createName(familyName));
	}

	public String getCompleteName() {
		//Search for the author name. If it isn´t there, try to use the organisation name.
	  if (mAuthor.getAssignedAuthor() != null) {
		  if (mAuthor.getAssignedAuthor().getAssignedPerson() != null) {
		    if (mAuthor.getAssignedAuthor().getAssignedPerson().getNames() != null) {
		      Name name = new Name(mAuthor.getAssignedAuthor().getAssignedPerson()
	                .getNames().get(0));
		        return name.getCompleteName();
		  }
		  else {
		    if (mAuthor.getAssignedAuthor().getRepresentedOrganization() != null) {
		      if (mAuthor.getAssignedAuthor().getRepresentedOrganization().getNames() != null) {
	            Name name = new Name(mAuthor.getAssignedAuthor().getRepresentedOrganization().getNames().get(0));
	            return name.getCompleteName();
		      }
		    }
		  }
		  }
		}
    return "";
	}

	public org.openhealthtools.mdht.uml.cda.Author getAuthorMdht() {
		return EcoreUtil.copy(this.mAuthor);
	}

	public String getGln() {
		// TODO Alle Ids durchsehen und die richtige (anhand der root-id)
		// zurückliefern.
		return mAuthor.getAssignedAuthor().getIds().get(0).getExtension();
	}

	public void setGln(String gln) {
		// TODO Construct Enums that deliver the real OIDs in Numbers (like in
		// hl7.vocab.PostalAdressUse). Replace the construction of the II in the
		// following line with this number.
		this.addID(new Identificator(CodeSystems.GLN.getCodeSystemId(), gln));
	}
}
