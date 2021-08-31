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
package org.ehealth_connector.cda.ch.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

import org.ehealth_connector.cda.ch.edes.CdaChEdesCtnn;
import org.ehealth_connector.cda.ch.edes.CdaChEdesEdpn;
import org.ehealth_connector.cda.ch.lab.lrph.CdaChLrph;
import org.ehealth_connector.cda.ch.lab.lrqc.CdaChLrqc;
import org.ehealth_connector.cda.ch.lab.lrtp.CdaChLrtp;
import org.ehealth_connector.cda.ch.vacd.v140.CdaChVacd;
import org.ehealth_connector.cda.ch.vacd.v140.enums.SectionsVacd;
import org.ehealth_connector.cda.utils.CdaUtilMdhtTest;
import org.ehealth_connector.common.enums.CodeSystems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.DocumentRoot;
import org.openhealthtools.mdht.uml.cda.EntryRelationship;
import org.openhealthtools.mdht.uml.hl7.datatypes.ED;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.TEL;

/**
 * Test of CdaChUtil
 *
 */
public class CdaChUtilTest extends CdaUtilMdhtTest {
	private SectionsVacd testPrefix;

	@Override
	@BeforeEach
	public void setUp() throws Exception {
		super.setUp();
		testPrefix = SectionsVacd.ALLERGIES_REACTIONS;
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.cda.utils.CdaUtilMdht#createUniqueIiFromString(java.lang.String)}
	 * .
	 */
	@Test
	public void testCreateUuidVacd() {
		final II ref = CdaChUtil.createUniqueIiFromString(testId);

		assertEquals(testId, ref.getExtension());
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.cda.utils.CdaUtilMdht#createUniqueIiFromString(org.ehealth_connector.common.mdht.Identificator)}
	 * .
	 */
	@Test
	public void testCreateUuidVacdIdentificator() {
		final II ref = CdaChUtil.createUniqueIiFromIdentificator(testIdentiicator);
		assertEquals(CodeSystems.GLN.getCodeSystemId(), ref.getRoot());
		assertEquals(testId, ref.getExtension());
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.cda.ch.utils#loadEdesCtnnFromFile(java.lang.String)}
	 * .
	 *
	 * @throws Exception
	 */
	@Test
	public void testLoadEdesCtnnFromFile() throws Exception {
		final URL test1Url = new URL(
				this.getClass().getResource("/cda/EdesCtnn-cda.xml").toString());
		final File testFile = new File(URLDecoder.decode(test1Url.getPath(), "UTF-8"));
		final CdaChEdesCtnn document = CdaChUtil.loadEdesCtnnFromFile(testFile.getAbsolutePath());
		assertNotNull(document);
		final DocumentRoot docRoot = document.getDocRoot();
		assertNotNull(docRoot);
		final ClinicalDocument clinicalDocument = docRoot.getClinicalDocument();
		assertNotNull(clinicalDocument);
		final II clDocId = clinicalDocument.getId();
		assertNotNull(clDocId);
		assertEquals("2.16.756.5.30.1.1.1.1", clDocId.getRoot());
		assertEquals("9611bcd2-760a-2576-c6ec-468faf7a0249", clDocId.getExtension());
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.cda.ch.utils#loadEdesEdpnFromFile(java.lang.String)}
	 * .
	 *
	 * @throws Exception
	 */
	@Test
	public void testLoadEdesEdpnFromFile() throws Exception {
		final URL test1Url = new URL(
				this.getClass().getResource("/cda/EdesEdpn-cda.xml").toString());
		final File testFile = new File(URLDecoder.decode(test1Url.getPath(), "UTF-8"));
		final CdaChEdesEdpn document = CdaChUtil.loadEdesEdpnFromFile(testFile.getAbsolutePath());
		assertNotNull(document);
		final DocumentRoot docRoot = document.getDocRoot();
		assertNotNull(docRoot);
		final ClinicalDocument clinicalDocument = docRoot.getClinicalDocument();
		assertNotNull(clinicalDocument);
		final II clDocId = clinicalDocument.getId();
		assertNotNull(clDocId);
		assertEquals("2.16.756.5.30.1.1.1.1", clDocId.getRoot());
		assertEquals("9611b2e6-760a-2576-cb4c-468faf7a0249", clDocId.getExtension());
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.cda.ch.utils#loadLrphFromFile(java.lang.String)}
	 * .
	 *
	 * @throws Exception
	 */
	@Test
	public void testLoadLrphFromFile() throws Exception {
		final URL test1Url = new URL(this.getClass().getResource("/cda/Lrph-cda.xml").toString());
		final File testFile = new File(URLDecoder.decode(test1Url.getPath(), "UTF-8"));
		final CdaChLrph document = CdaChUtil.loadLrphFromFile(testFile.getAbsolutePath());
		assertNotNull(document);
		final DocumentRoot docRoot = document.getDocRoot();
		assertNotNull(docRoot);
		final ClinicalDocument clinicalDocument = docRoot.getClinicalDocument();
		assertNotNull(clinicalDocument);
		final II clDocId = clinicalDocument.getId();
		assertNotNull(clDocId);
		assertEquals("2.16.756.5.30.1.1.1.1", clDocId.getRoot());
		assertEquals("96124e36-760a-2576-fc1f-468faf7a0249", clDocId.getExtension());
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.cda.ch.utils#loadLrqcFromFile(java.lang.String)}
	 * .
	 *
	 * @throws Exception
	 */
	@Test
	public void testLoadLrqcFromFile() throws Exception {
		final URL test1Url = new URL(this.getClass().getResource("/cda/Lrqc-cda.xml").toString());
		final File testFile = new File(URLDecoder.decode(test1Url.getPath(), "UTF-8"));
		final CdaChLrqc document = CdaChUtil.loadLrqcFromFile(testFile.getAbsolutePath());
		assertNotNull(document);
		final DocumentRoot docRoot = document.getDocRoot();
		assertNotNull(docRoot);
		final ClinicalDocument clinicalDocument = docRoot.getClinicalDocument();
		assertNotNull(clinicalDocument);
		final II clDocId = clinicalDocument.getId();
		assertNotNull(clDocId);
		assertEquals("2.16.756.5.30.1.1.1.1", clDocId.getRoot());
		assertEquals("9612b29a-760a-2576-3413-468faf7a0249", clDocId.getExtension());
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.cda.ch.utils#loadLrtpFromFile(java.lang.String)}
	 * .
	 *
	 * @throws Exception
	 */
	@Test
	public void testLoadLrtpFromFile() throws Exception {
		final URL test1Url = new URL(this.getClass().getResource("/cda/Lrtp-cda.xml").toString());
		final File testFile = new File(URLDecoder.decode(test1Url.getPath(), "UTF-8"));
		final CdaChLrtp document = CdaChUtil.loadLrtpFromFile(testFile.getAbsolutePath());
		assertNotNull(document);
		final DocumentRoot docRoot = document.getDocRoot();
		assertNotNull(docRoot);
		final ClinicalDocument clinicalDocument = docRoot.getClinicalDocument();
		assertNotNull(clinicalDocument);
		final II clDocId = clinicalDocument.getId();
		assertNotNull(clDocId);
		assertEquals("2.16.756.5.30.1.1.1.1", clDocId.getRoot());
		assertEquals("961338c8-760a-2576-68e6-468faf7a0249", clDocId.getExtension());
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.cda.ch.utils#loadVacdFromFile(java.lang.String)}
	 * .
	 *
	 * @throws Exception
	 */
	@Test
	public void testLoadVacdFromFile() throws Exception {
		final URL test1Url = new URL(
				this.getClass().getResource("/cda/vacDoc_Test1.xml").toString());
		final File testFile = new File(URLDecoder.decode(test1Url.getPath(), "UTF-8"));
		final CdaChVacd document = CdaChUtil.loadVacdFromFile(testFile.getAbsolutePath());
		assertNotNull(document);
		final DocumentRoot docRoot = document.getDocRoot();
		assertNotNull(docRoot);
		final ClinicalDocument clinicalDocument = docRoot.getClinicalDocument();
		assertNotNull(clinicalDocument);
		final II clDocId = clinicalDocument.getId();
		assertNotNull(clDocId);
		assertEquals("2.16.756.5.30.1.1.1.1", clDocId.getRoot());
		assertEquals("51bc6c3a-51b8-1950-1c21-fb8d9b73235b", clDocId.getExtension());
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.cda.ch.utils#loadVacdFromStream(java.io.InputStream)}
	 * .
	 *
	 * @throws Exception
	 */
	@Test
	public void testLoadVacdFromStream() throws Exception {

		// InputStream testStream =
		// this.getClass().getResourceAsStream("/cda/vacDoc_Test2.xml");
		// final CdaChVacd document = CdaChUtil.loadVacdFromStream(testStream);
		final URL test1Url = new URL(
				this.getClass().getResource("/cda/vacDoc_Test2.xml").toString());
		final File testFile = new File(URLDecoder.decode(test1Url.getPath(), "UTF-8"));
		final CdaChVacd document = CdaChUtil.loadVacdFromFile(testFile.getAbsolutePath());
		assertNotNull(document);
		final DocumentRoot docRoot = document.getDocRoot();
		assertNotNull(docRoot);
		final ClinicalDocument clinicalDocument = docRoot.getClinicalDocument();
		assertNotNull(clinicalDocument);
		final II clDocId = clinicalDocument.getId();
		assertNotNull(clDocId);
		assertEquals("2.16.756.5.30.1.1.1.1", clDocId.getRoot());
		assertEquals("51c4d94c-51c2-1950-aeba-13b0b1958ec8", clDocId.getExtension());
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.cda.utils.CdaUtilMdht#updateRefIfComment(org.openhealthtools.mdht.uml.cda.EntryRelationship, int, int, org.ehealth_connector.cda.ch.enums.SectionsVacd)}
	 * .
	 */
	@Test
	public void testUpdateRefIfCommentEntryRelationshipIntIntSectionsVacd() {
		final EntryRelationship ref = CdaChUtil.updateRefIfComment(false, testEr, testI, testJ,
				testPrefix);
		assertNotNull(ref);
		assertTrue(ref.getInversionInd());
		assertNotNull(ref.getAct());
		final ED ed = ref.getAct().getText();
		assertNotNull(ed);
		assertEquals(testText, ed.getText());
		final TEL refRef = ed.getReference();
		assertNotNull(refRef);
		final String refVal = refRef.getValue();
		assertTrue(refVal.startsWith("#"));
		assertTrue(refVal.endsWith("1"));
	}

	/**
	 * Test method for
	 * {@link org.ehealth_connector.cda.utils.CdaUtilMdht#updateRefIfComment(org.openhealthtools.mdht.uml.cda.EntryRelationship, java.lang.String, org.ehealth_connector.cda.ch.enums.SectionsVacd)}
	 * .
	 */
	@Test
	public void testUpdateRefIfCommentEntryRelationshipStringSectionsVacd() {
		final EntryRelationship ref = CdaChUtil.updateRefIfComment(false, testEr, testRef,
				testPrefix);
		assertNotNull(ref);
		assertTrue(ref.getInversionInd());

	}

}
