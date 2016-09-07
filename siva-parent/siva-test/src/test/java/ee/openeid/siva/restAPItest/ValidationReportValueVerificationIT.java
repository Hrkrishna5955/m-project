package ee.openeid.siva.restAPItest;


import ee.openeid.siva.integrationtest.SiVaRestTests;
import ee.openeid.siva.integrationtest.configuration.IntegrationTest;
import org.apache.commons.codec.binary.Base64;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Category(IntegrationTest.class)

public class ValidationReportValueVerificationIT extends SiVaRestTests{

    @Before
    public void DirectoryBackToDefault() {
        setTestFilesDirectory(DEFAULT_TEST_FILES_DIRECTORY);
    }

    private static final String DEFAULT_TEST_FILES_DIRECTORY = "bdoc/live/timemark/";

    private String testFilesDirectory = DEFAULT_TEST_FILES_DIRECTORY;

    public void setTestFilesDirectory(String testFilesDirectory) {
        this.testFilesDirectory = testFilesDirectory;
    }

    /***
     *
     * TestCaseID: Bdoc-ValidationReportVerification-1
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: Verification of values in Validation Report XAdES_BASELINE_LT_TM, QES, FullSignatureScope
     *
     * Expected Result: All required elements are present and meet the expected values.
     *
     * File: Valid_ID_sig.bdoc
     *
     ***/
    @Test
    public void bdocCorrectValuesArePresentValidLtTmSignature() {
        post(validationRequestFor("Valid_ID_sig.bdoc"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchema.json"))
                .body("signatures[0].id", Matchers.is("S0"))
                .body("signatures[0].signatureFormat", Matchers.is("XAdES_BASELINE_LT_TM"))
                .body("signatures[0].signatureLevel", Matchers.is("QES"))
                .body("signatures[0].signedBy", Matchers.is("NURM,AARE,38211015222"))
                .body("signatures[0].indication", Matchers.is("TOTAL-PASSED"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes[0].name", Matchers.is("Proov.txt"))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is("FullSignatureScope"))
                .body("signatures[0].signatureScopes[0].content", Matchers.is("Full document"))
                .body("signatures[0].claimedSigningTime", Matchers.is("2016-05-12T10:09:09Z"))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is("2016-05-12T10:09:20Z"))
                .body("signatureForm", Matchers.is("ASiC_E"))
                .body("documentName", Matchers.is("Valid_ID_sig.bdoc"))
                .body("validSignaturesCount", Matchers.is(1))
                .body("signaturesCount", Matchers.is(1));
    }

    /***
     *
     * TestCaseID: Bdoc-ValidationReportVerification-2
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: Verification of values in Validation Report XAdES_BASELINE_LT, QES, FullSignatureScope
     *
     * Expected Result: All required elements are present and meet the expected values.
     *
     * File: 23635_bdoc_ts_OCSP_random_nonce.bdoc
     *
     ***/
    @Test
    public void bdocCorrectValuesArePresentValidLtSignature() {
        setTestFilesDirectory("bdoc/live/timestamp/");
        post(validationRequestFor("23635_bdoc_ts_OCSP_random_nonce.bdoc"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchema.json"))
                .body("signatures[0].id", Matchers.is("S0"))
                .body("signatures[0].signatureFormat", Matchers.is("XAdES_BASELINE_LT"))
                .body("signatures[0].signatureLevel", Matchers.is("QES"))
                .body("signatures[0].signedBy", Matchers.is("SINIVEE,VEIKO,36706020210"))
                .body("signatures[0].indication", Matchers.is("TOTAL-PASSED"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes[0].name", Matchers.is("Makefile"))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is("FullSignatureScope"))
                .body("signatures[0].signatureScopes[0].content", Matchers.is("Full document"))
                .body("signatures[0].claimedSigningTime", Matchers.is("2015-11-20T08:32:39Z"))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is("2015-11-20T08:32:42Z"))
                .body("signatureForm", Matchers.is("ASiC_E"))
                .body("documentName", Matchers.is("23635_bdoc_ts_OCSP_random_nonce.bdoc"))
                .body("validSignaturesCount", Matchers.is(1))
                .body("signaturesCount", Matchers.is(1));
    }

    /***
     *
     * TestCaseID: Bdoc-ValidationReportVerification-3
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: Verification of values in Validation Report XAdES_BASELINE_LT, AdES, FullSignatureScope, warning
     *
     * Expected Result: All required elements are present and meet the expected values.
     *
     * File: 23154_test1-old-sig-sigat-NOK-prodat-OK-1.bdoc
     *
     ***/
    @Test @Ignore //TODO: this is not proper AdES!
    public void bdocCorrectValuesArePresentValidLtSignatureAdesWarning() {
        setTestFilesDirectory("bdoc/test/timemark/");
        post(validationRequestFor("23154_test1-old-sig-sigat-NOK-prodat-OK-1.bdoc"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchema.json"))
                .body("signatures[0].id", Matchers.is("S0"))
                .body("signatures[0].signatureFormat", Matchers.is("XAdES_BASELINE_LT_TM"))
                .body("signatures[0].signatureLevel", Matchers.is("AdES"))
                .body("signatures[0].signedBy", Matchers.is("SINIVEE,VEIKO,36706020210"))
                .body("signatures[0].indication", Matchers.is("TOTAL-PASSED"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes[0].name", Matchers.is("build.xml"))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is("FullSignatureScope"))
                .body("signatures[0].signatureScopes[0].content", Matchers.is("Full document"))
                .body("signatures[0].claimedSigningTime", Matchers.is("2014-07-11T14:10:07Z"))
                .body("signatures[0].warnings[0].description", Matchers.is("The certificate is not supported by SSCD!"))
                .body("signatures[0].info.bestSignatureTime", Matchers.is("2011-10-15T14:59:35Z"))
                .body("signatureForm", Matchers.is("ASiC_E"))
                .body("documentName", Matchers.is("23154_test1-old-sig-sigat-NOK-prodat-OK-1.bdoc"))
                .body("validSignaturesCount", Matchers.is(1))
                .body("signaturesCount", Matchers.is(1));
    }

     /***
     *
     * TestCaseID: Bdoc-ValidationReportVerification-5
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: Verification of values in Validation Report XAdES_BASELINE_LT-TM, AdESqc
     *
     * Expected Result: All required elements are present and meet the expected values.
     *
     * File: 23200_weakdigest-wrong-nonce.asice
     *
     ***/
    @Test
    public void bdocCorrectValuesArePresentInvalidLtSignatureAdesqc() {
        setTestFilesDirectory("bdoc/test/timemark/");
        post(validationRequestFor("23200_weakdigest-wrong-nonce.asice"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchema.json"))
                .body("signatures[0].id", Matchers.is("S0"))
                .body("signatures[0].signatureFormat", Matchers.is("XAdES_BASELINE_LT_TM"))
                .body("signatures[0].signatureLevel", Matchers.is("AdESqc"))
                .body("signatures[0].signedBy", Matchers.is("ŽAIKOVSKI,IGOR,37101010021"))
                .body("signatures[0].indication", Matchers.is("TOTAL-FAILED"))
                .body("signatures[0].subIndication", Matchers.is("")) //TODO: VAL-242
                .body("signatures[0].errors[0].content", Matchers.is("Nonce is invalid"))
                .body("signatures[0].signatureScopes[0].name", Matchers.is("test.txt"))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is("FullSignatureScope"))
                .body("signatures[0].signatureScopes[0].content", Matchers.is("Full document"))
                .body("signatures[0].claimedSigningTime", Matchers.is("2013-10-24T13:12:36Z"))
                .body("signatures[0].warnings[0].description", Matchers.is("The certificate is not supported by SSCD!"))
                .body("signatures[0].info.bestSignatureTime", Matchers.is("2013-10-24T13:14:50Z"))
                .body("signatureForm", Matchers.is("ASiC_E"))
                .body("documentName", Matchers.is("23200_weakdigest-wrong-nonce.asice"))
                .body("validSignaturesCount", Matchers.is(0))
                .body("signaturesCount", Matchers.is(1));
    }

    /***
     *
     * TestCaseID: Bdoc-ValidationReportVerification-6
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: JSON structure has all elements (Bdoc valid multiple signatures)
     *
     * Expected Result: All required elements are present according to SimpleReportSchema.json
     *
     * File: Baltic MoU digital signing_EST_LT_LV.bdoc
     *
     ***/
    @Test @Ignore//TODO: VAL-244 was found with Valid_IDCard_MobID_signatures.bdoc file in addition to this file.
    public void bdocAllElementsArePresentValidMultipleSignatures() {
        post(validationRequestFor("Baltic MoU digital signing_EST_LT_LV.bdoc"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchema.json"))
                .body("signatures[0].id", Matchers.is("S0"))
                .body("signatures[0].signatureFormat", Matchers.is("XAdES_BASELINE_LT_TM"))
                .body("signatures[0].signatureLevel", Matchers.is("QES"))
                .body("signatures[0].signedBy", Matchers.is("MICHAL,KRISTEN,37507120348"))
                .body("signatures[0].indication", Matchers.is("TOTAL-PASSED"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes[0].name", Matchers.is("Baltic MoU digital signing_04112015_0.docx"))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is("FullSignatureScope"))
                .body("signatures[0].signatureScopes[0].content", Matchers.is("Full document"))
                .body("signatures[0].claimedSigningTime", Matchers.is("2015-11-04T10:24:11Z"))
                .body("signatures[0].warnings[0].description", Matchers.is("The certificate is not supported by SSCD!"))
                .body("signatures[0].info.bestSignatureTime", Matchers.is("2015-11-04T10:24:20Z"))
                .body("signatureForm", Matchers.is("ASiC_E"))
                .body("documentName", Matchers.is("Baltic MoU digital signing_EST_LT_LV.bdoc"))
                .body("validSignaturesCount", Matchers.is(3))
                .body("signaturesCount", Matchers.is(3));
    }

    /***
     *
     * TestCaseID: Bdoc-ValidationReportVerification-7
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: JSON structure has all elements (Bdoc indeterminate status)
     *
     * Expected Result: All required elements are present according to BdocDocSimpleReportSchema.json
     *
     * File: test1-bdoc-unknown.bdoc
     *
     ***/
    @Test @Ignore //TODO: Need a testfile with indeterminate status. With new DSS version indeterminate seems to be changed to total fail.
    public void bdocAllElementsArePresentIndeterminateSignature() {
        post(validationRequestFor("test1-bdoc-unknown.bdoc"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchema.json"))
                .body("signatures[0].id", Matchers.is("S0"))
                .body("signatures[0].signatureFormat", Matchers.is(""))
                .body("signatures[0].signatureLevel", Matchers.is(""))
                .body("signatures[0].signedBy", Matchers.is(""))
                .body("signatures[0].indication", Matchers.is("INDETERMINATE"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes[0].name", Matchers.is(""))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is("FullSignatureScope"))
                .body("signatures[0].signatureScopes[0].content", Matchers.is("Full document"))
                .body("signatures[0].claimedSigningTime", Matchers.is(""))
                .body("signatures[0].warnings[0].description", Matchers.is(""))
                .body("signatures[0].info.bestSignatureTime", Matchers.is(""))
                .body("signatureForm", Matchers.is("ASiC_E"))
                .body("documentName", Matchers.is(""))
                .body("validSignaturesCount", Matchers.is(3))
                .body("signaturesCount", Matchers.is(3));
    }

    /***
     *
     * TestCaseID: Bdoc-ValidationReportVerification-8
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title:  Bdoc report with no signatures
     *
     * Expected Result: Report is returned with required elements
     *
     * File:BdocContainerNoSignature.bdoc
     *
     ***/
    @Test
    public void bdocNoSignature() {
        setTestFilesDirectory("document_format_test_files/");
        post(validationRequestFor("BdocContainerNoSignature.bdoc"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchema.json"))
                .body("signatures", Matchers.hasSize(0))
                .body("signatureForm", Matchers.is("ASiC_E"))
                .body("documentName", Matchers.is("BdocContainerNoSignature.bdoc"))
                .body("validSignaturesCount", Matchers.is(0))
                .body("signaturesCount", Matchers.is(0));
    }

    /***
     *
     * TestCaseID: Pdf-ValidationReportVerification-1
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: JSON structure has all elements (Pdf valid single signature)
     *
     * Expected Result: All required elements are present according to SimpleReportSchema.json
     *
     * File: hellopades-lt-sha256-ec256.pdf
     *
     ***/
    @Test
    public void pdfAllElementsArePresentValidSignature() {
        setTestFilesDirectory("pdf/signature_cryptographic_algorithm_test_files/");
        post(validationRequestFor("hellopades-lt-sha256-ec256.pdf"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchema.json"))
                .body("signatures[0].id", Matchers.is("id-85aeeca3c7135efa1c42b69dfcba898c780e552845d04b66868301a5cf0ed8ba"))
                .body("signatures[0].signatureFormat", Matchers.is("PAdES_BASELINE_LT"))
                .body("signatures[0].signatureLevel", Matchers.is("QES"))
                .body("signatures[0].signedBy", Matchers.is("Veiko Sinivee"))
                .body("signatures[0].indication", Matchers.is("TOTAL-PASSED"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes[0].name", Matchers.is("PDF previous version #1"))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is("PdfByteRangeSignatureScope"))
                .body("signatures[0].signatureScopes[0].content", Matchers.is("The document byte range: [0, 14153, 52047, 491]"))
                .body("signatures[0].claimedSigningTime", Matchers.is("2015-08-25T10:15:06Z"))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is("2015-08-25T09:15:06Z"))
                .body("signatureForm", Matchers.is("PAdES"))
                .body("documentName", Matchers.is("hellopades-lt-sha256-ec256.pdf"))
                .body("validSignaturesCount", Matchers.is(1))
                .body("signaturesCount", Matchers.is(1));
    }

    /***
     *
     * TestCaseID: Pdf-ValidationReportVerification-2
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: JSON structure has all elements (Pdf valid Multiple signatures)
     *
     * Expected Result: All required elements are present according to SimpleReportSchema.json
     *
     * File:
     *
     ***/
    @Test
    public void pdfAllElementsArePresentValidmultipleSignatures() {
        setTestFilesDirectory("pdf/baseline_profile_test_files/");
        post(validationRequestFor("pades_lt_two_valid_sig.pdf"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchema.json"))
                .body("signatures[1].id", Matchers.is("id-4a63aba993298157bf1df8d29e14241654991a12c73036390788f2a46af27865"))
                .body("signatures[1].signatureFormat", Matchers.is("PAdES_BASELINE_LT"))
                .body("signatures[1].signatureLevel", Matchers.is("QES"))
                .body("signatures[1].signedBy", Matchers.is("VOLL,ANDRES,39004170346"))
                .body("signatures[1].indication", Matchers.is("TOTAL-PASSED"))
                .body("signatures[1].subIndication", Matchers.is(""))
                .body("signatures[1].errors", Matchers.hasSize(0))
                .body("signatures[1].signatureScopes[0].name", Matchers.is("Full PDF"))
                .body("signatures[1].signatureScopes[0].scope", Matchers.is("FullSignatureScope"))
                .body("signatures[1].signatureScopes[0].content", Matchers.is("Full document"))
                .body("signatures[1].claimedSigningTime", Matchers.is("2016-06-27T09:59:37Z"))
                .body("signatures[1].warnings", Matchers.hasSize(0))
                .body("signatures[1].info.bestSignatureTime", Matchers.is("2016-06-27T09:59:48Z"))
                .body("signatureForm", Matchers.is("PAdES"))
                .body("documentName", Matchers.is("pades_lt_two_valid_sig.pdf"))
                .body("validSignaturesCount", Matchers.is(2))
                .body("signaturesCount", Matchers.is(2));
    }

    /***
     *
     * TestCaseID: Pdf-ValidationReportVerification-3
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: JSON structure has all elements (Pdf invalid signature)
     *
     * Expected Result: All required elements are present according to SimpleReportSchema.json
     *
     * File: hellopades-lt-b.pdf
     *
     ***/
    @Test @Ignore //TODO: VAL-242
    public void pdfAllElementsArePresentInvalidSignature() {
        setTestFilesDirectory("pdf/baseline_profile_test_files/");
        post(validationRequestFor("hellopades-lt-b.pdf"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchema.json"))
                .body("signatures[1].id", Matchers.is("id-74acbd70b729fd9bd372ae39ecb3ba688f6be35189324449a69368e9f2fe61ca"))
                .body("signatures[1].signatureFormat", Matchers.is("PAdES_BASELINE_B"))
                .body("signatures[1].signatureLevel", Matchers.is("QES"))
                .body("signatures[1].signedBy", Matchers.is("SINIVEE,VEIKO,36706020210"))
                .body("signatures[1].indication", Matchers.is("TOTAL-FAILED"))
                .body("signatures[1].subIndication", Matchers.is(""))
                .body("signatures[1].errors[0].content", Matchers.is("The expected format is not found!"))
                .body("signatures[1].signatureScopes[0].name", Matchers.is("Full PDF"))
                .body("signatures[1].signatureScopes[0].scope", Matchers.is("FullSignatureScope"))
                .body("signatures[1].signatureScopes[0].content", Matchers.is("Full document"))
                .body("signatures[1].claimedSigningTime", Matchers.is("2015-08-23T05:10:15Z"))
                .body("signatures[1].warnings", Matchers.hasSize(0))
                .body("signatures[1].info.bestSignatureTime", Matchers.is(""))
                .body("signatureForm", Matchers.is("PAdES"))
                .body("documentName", Matchers.is("hellopades-lt-b.pdf"))
                .body("validSignaturesCount", Matchers.is(1))
                .body("signaturesCount", Matchers.is(2));
    }

    /***
     *
     * TestCaseID: Pdf-ValidationReportVerification-4
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: JSON structure has all elements (Pdf indeterminate status)
     *
     * Expected Result: All required elements are present according to SimpleReportSchema.json
     *
     * File: hellopades-lt-rsa1024-sha1-expired.pdf
     *
     ***/
    @Test
    public void pdfAllElementsArePresentIndeterminateSignature() {
        setTestFilesDirectory("pdf/signing_certifacte_test_files/");
        post(validationRequestFor("hellopades-lt-rsa1024-sha1-expired.pdf"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchema.json"))
                .body("signatures[0].id", Matchers.is("id-cbcdd80dbccaf1f0d536ada0e425d7bb780e552845d04b66868301a5cf0ed8ba"))
                .body("signatures[0].signatureFormat", Matchers.is("PAdES_BASELINE_LT"))
                .body("signatures[0].signatureLevel", Matchers.is("AdES"))
                .body("signatures[0].signedBy", Matchers.is("SINIVEE,VEIKO,36706020210"))
                .body("signatures[0].indication", Matchers.is("INDETERMINATE"))
                .body("signatures[0].subIndication", Matchers.is("OUT_OF_BOUNDS_NO_POE"))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes[0].name", Matchers.is("PDF previous version #1"))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is("PdfByteRangeSignatureScope"))
                .body("signatures[0].signatureScopes[0].content", Matchers.is("The document byte range: [0, 14153, 52047, 491]"))
                .body("signatures[0].claimedSigningTime", Matchers.is("2012-01-24T11:08:15Z"))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is("2015-08-24T10:08:25Z"))
                .body("signatureForm", Matchers.is("PAdES"))
                .body("documentName", Matchers.is("hellopades-lt-rsa1024-sha1-expired.pdf"))
                .body("validSignaturesCount", Matchers.is(0))
                .body("signaturesCount", Matchers.is(1));
    }

    /***
     *
     * TestCaseID: Pdf-ValidationReportVerification-5
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title:  Pdf report with no signatures
     *
     * Expected Result: Report is returned with required elements
     *
     * File: PdfNoSignature.pdf
     *
     ***/
    @Test
    public void pdfNoSignature() {
        setTestFilesDirectory("document_format_test_files/");
        post(validationRequestFor("PdfNoSignature.pdf"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchema.json"))
                .body("signatures", Matchers.hasSize(0))
                .body("signatureForm", Matchers.is("PAdES"))
                .body("documentName", Matchers.is("PdfNoSignature.pdf"))
                .body("validSignaturesCount", Matchers.is(0))
                .body("signaturesCount", Matchers.is(0));
    }

    /***
     *
     * TestCaseID: Ddoc-ValidationReportVerification-1
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: JSON structure has all elements (ddoc valid single signature)
     *
     * Expected Result: All required elements are present according to SimpleReportSchema.json
     *
     * File: DIGIDOC-XML1.3.ddoc
     *
     ***/
    @Test
    public void ddocAllElementsArePresentValidSignature() {
        setTestFilesDirectory("ddoc/live/timemark/");
        post(validationRequestFor("DIGIDOC-XML1.3.ddoc"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchemaDdoc.json"))
                .body("signatures[0].id", Matchers.is("S0"))
                .body("signatures[0].signatureFormat", Matchers.is("DIGIDOC_XML_1.3"))
                .body("signatures[0].signatureLevel", Matchers.is(""))
                .body("signatures[0].signedBy", Matchers.is("LUKIN,LIISA,47710110274"))
                .body("signatures[0].indication", Matchers.is("TOTAL-PASSED"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes[0].name", Matchers.is("Glitter-rock-4_gallery.jpg"))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is("FullSignatureScope"))
                .body("signatures[0].signatureScopes[0].content", Matchers.is("Full document"))
                .body("signatures[0].claimedSigningTime", Matchers.is("2012-10-03T07:46:31Z"))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is(""))
                .body("signatureForm", Matchers.is("DIGIDOC_XML_1.3"))
                .body("documentName", Matchers.is("DIGIDOC-XML1.3.ddoc"))
                .body("validSignaturesCount", Matchers.is(1))
                .body("signaturesCount", Matchers.is(1));
    }

    /***
     *
     * TestCaseID: Ddoc-ValidationReportVerification-2
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: JSON structure has all elements (ddoc invalid signature)
     *
     * Expected Result: All required elements are present according to SimpleReportSchema.json
     *
     * File: test1-ddoc-revoked.ddoc
     *
     ***/
    @Test
    public void ddocAllElementsArePresentInvalidSignature() {
        setTestFilesDirectory("ddoc/live/timemark/");
        post(validationRequestFor("test1-ddoc-revoked.ddoc"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchemaDdoc.json"))
                .body("signatures[0].id", Matchers.is("S0"))
                .body("signatures[0].signatureFormat", Matchers.is("DIGIDOC_XML_1.3"))
                .body("signatures[0].signatureLevel", Matchers.is(""))
                .body("signatures[0].signedBy", Matchers.is("SINIVEE,VEIKO,36706020210"))
                .body("signatures[0].indication", Matchers.is("TOTAL-FAILED"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors[0].content", Matchers.is("70ee.sk.digidoc.DigiDocException; nested exception is: \n\tERROR: 117 - No certificate for responder: 'byName: C=EE,O=AS Sertifitseerimiskeskus,OU=OCSP,CN=TEST of SK OCSP RESPONDER 2011,E=pki@sk.ee' found in local certificate store!"))
                .body("signatures[0].signatureScopes[0].name", Matchers.is("build.xml"))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is("FullSignatureScope"))
                .body("signatures[0].signatureScopes[0].content", Matchers.is("Full document"))
                .body("signatures[0].claimedSigningTime", Matchers.is("2013-05-17T12:15:08Z"))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is(""))
                .body("signatureForm", Matchers.is("DIGIDOC_XML_1.3"))
                .body("documentName", Matchers.is("test1-ddoc-revoked.ddoc"))
                .body("validSignaturesCount", Matchers.is(0))
                .body("signaturesCount", Matchers.is(1));
    }

    /***
     *
     * TestCaseID: Ddoc-ValidationReportVerification-3
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: JSON structure has all elements (ddoc indeterminate status)
     *
     * Expected Result: All required elements are present according to SimpleReportSchema.json
     *
     * File: test1-ddoc-unknown.ddoc
     *
     ***/
    @Test @Ignore //TODO: We are getting total-fail on all the files we expect to be indeterminate
    public void ddocAllElementsArePresentIndeterminateSignature() {
        setTestFilesDirectory("ddoc/live/timemark/");
        post(validationRequestFor("test1-ddoc-unknown.ddoc"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchemaDdoc.json"))
                .body("signatures[0].id", Matchers.is("S0"))
                .body("signatures[0].signatureFormat", Matchers.is("DIGIDOC_XML_1.3"))
                .body("signatures[0].signatureLevel", Matchers.is(""))
                .body("signatures[0].signedBy", Matchers.is(""))
                .body("signatures[0].indication", Matchers.is("INDETERMINATE"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors[0].content", Matchers.is(""))
                .body("signatures[0].signatureScopes[0].name", Matchers.is(""))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is(""))
                .body("signatures[0].signatureScopes[0].content", Matchers.is("Full document"))
                .body("signatures[0].claimedSigningTime", Matchers.is(""))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is(""))
                .body("signatureForm", Matchers.is("DIGIDOC_XML_1.3"))
                .body("documentName", Matchers.is("test1-ddoc-unknown.ddoc"))
                .body("validSignaturesCount", Matchers.is(0))
                .body("signaturesCount", Matchers.is(1));
    }

    /***
     *
     * TestCaseID: Ddoc-ValidationReportVerification-4
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: Check for optional warning element
     *
     * Expected Result: Warning element is present
     *
     * File:
     *
     ***/
    @Test @Ignore //TODO: File is needed!
    public void ddocOptionalWarningElementIsPresent() {
        setTestFilesDirectory("ddoc/live/timemark/");
        post(validationRequestFor("NeedFile.ddoc"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchemaDdoc.json"))
                .body("signatures[0].id", Matchers.is("S0"))
                .body("signatures[0].signatureFormat", Matchers.is("DIGIDOC_XML_1.3"))
                .body("signatures[0].signatureLevel", Matchers.is(""))
                .body("signatures[0].signedBy", Matchers.is(""))
                .body("signatures[0].indication", Matchers.is("INDETERMINATE"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors[0].content", Matchers.is(""))
                .body("signatures[0].signatureScopes[0].name", Matchers.is(""))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is(""))
                .body("signatures[0].signatureScopes[0].content", Matchers.is("Full document"))
                .body("signatures[0].claimedSigningTime", Matchers.is(""))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is(""))
                .body("signatureForm", Matchers.is("DIGIDOC_XML_1.3"))
                .body("documentName", Matchers.is("test1-ddoc-unknown.ddoc"))
                .body("validSignaturesCount", Matchers.is(0))
                .body("signaturesCount", Matchers.is(1));
    }

    /***
     *
     * TestCaseID: Ddoc-ValidationReportVerification-5
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title:  Ddoc report with no signatures
     *
     * Expected Result: Report is returned with required elements
     *
     * File: DdocContainerNoSignature.ddoc
     *
     ***/
    @Test
    public void ddocNoSignature() {
        setTestFilesDirectory("document_format_test_files/");
        post(validationRequestFor("DdocContainerNoSignature.ddoc"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchema.json"))
                .body("signatures", Matchers.hasSize(0))
                .body("signatureForm", Matchers.is("DIGIDOC_XML_1.3"))
                .body("documentName", Matchers.is("DdocContainerNoSignature.ddoc"))
                .body("validSignaturesCount", Matchers.is(0))
                .body("signaturesCount", Matchers.is(0));
    }

    /***
     *
     * TestCaseID: Ddoc-ValidationReportVerification-6
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: Verification of values in Validation Report, xml v1.0, checks for missing info
     *
     * Expected Result: All required elements are present and meet the expected values and other values are empty as expected.
     *
     * File: SK-XML1.0.ddoc
     *
     ***/
    @Test @Ignore //TODO: VAL-238
    public void ddocCorrectValuesArePresentV1_0() {
        setTestFilesDirectory("ddoc/live/timemark/");
        post(validationRequestFor("SK-XML1.0.ddoc"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchemaDdoc.json"))
                .body("signatures[0].id", Matchers.is("S0"))
                .body("signatures[0].signatureFormat", Matchers.is("SK_XML_1.0"))
                .body("signatures[0].signatureLevel", Matchers.is(""))
                .body("signatures[0].signedBy", Matchers.is("ANSIP,ANDRUS,35610012722"))
                .body("signatures[0].indication", Matchers.is("TOTAL-PASSED"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes[0].name", Matchers.is("Tartu ja Tallinna koostooleping.doc"))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is(""))
                .body("signatures[0].signatureScopes[0].content", Matchers.is("Full document"))
                .body("signatures[0].claimedSigningTime", Matchers.is("2002-10-07T12:10:19Z"))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is(""))
                .body("signatureForm", Matchers.is("DIGIDOC_XML_1.0"))
                .body("documentName", Matchers.is("SK-XML1.0.ddoc"))
                .body("validSignaturesCount", Matchers.is(2))
                .body("signaturesCount", Matchers.is(2));
    }

    /***
     *
     * TestCaseID: Ddoc-ValidationReportVerification-7
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: Verification of values in Validation Report, xml v1.1, checks for missing info
     *
     * Expected Result: All required elements are present and meet the expected values and other values are empty as expected.
     *
     * File: Igasugust1.1.ddoc
     *
     ***/
    @Test
    public void ddocCorrectValuesArePresentV1_1() {
        setTestFilesDirectory("ddoc/live/timemark/");
        post(validationRequestFor("igasugust1.1.ddoc"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchemaDdoc.json"))
                .body("signatures[0].id", Matchers.is("S0"))
                .body("signatures[0].signatureFormat", Matchers.is("DIGIDOC_XML_1.1"))
                .body("signatures[0].signatureLevel", Matchers.is(""))
                .body("signatures[0].signedBy", Matchers.is("SOONSEIN,SIMMO,38508134916"))
                .body("signatures[0].indication", Matchers.is("TOTAL-PASSED"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes[1].name", Matchers.is("Testilood20070320.doc"))
                .body("signatures[0].signatureScopes[1].scope", Matchers.is("FullSignatureScope"))
                .body("signatures[0].signatureScopes[1].content", Matchers.is("Full document"))
                .body("signatures[0].claimedSigningTime", Matchers.is("2009-06-01T10:42:19Z"))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is(""))
                .body("signatureForm", Matchers.is("DIGIDOC_XML_1.1"))
                .body("documentName", Matchers.is("igasugust1.1.ddoc"))
                .body("validSignaturesCount", Matchers.is(3))
                .body("signaturesCount", Matchers.is(3));
    }

    /***
     *
     * TestCaseID: Ddoc-ValidationReportVerification-8
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: Verification of values in Validation Report, xml v1.2, checks for missing info
     *
     * Expected Result: All required elements are present and meet the expected values and other values are empty as expected.
     *
     * File: Igasugust1.2.ddoc
     *
     ***/
    @Test
    public void ddocCorrectValuesArePresentV1_2() {
        setTestFilesDirectory("ddoc/live/timemark/");
        post(validationRequestFor("igasugust1.2.ddoc"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchemaDdoc.json"))
                .body("signatures[0].id", Matchers.is("S0"))
                .body("signatures[0].signatureFormat", Matchers.is("DIGIDOC_XML_1.2"))
                .body("signatures[0].signatureLevel", Matchers.is(""))
                .body("signatures[0].signedBy", Matchers.is("SOONSEIN,SIMMO,38508134916"))
                .body("signatures[0].indication", Matchers.is("TOTAL-PASSED"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes[1].name", Matchers.is("Testilood20070320.doc"))
                .body("signatures[0].signatureScopes[1].scope", Matchers.is("FullSignatureScope"))
                .body("signatures[0].signatureScopes[1].content", Matchers.is("Full document"))
                .body("signatures[0].claimedSigningTime", Matchers.is("2009-06-01T10:45:44Z"))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is(""))
                .body("signatureForm", Matchers.is("DIGIDOC_XML_1.2"))
                .body("documentName", Matchers.is("igasugust1.2.ddoc"))
                .body("validSignaturesCount", Matchers.is(3))
                .body("signaturesCount", Matchers.is(3));
    }

    /***
     *
     * TestCaseID: Ddoc-ValidationReportVerification-9
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: Verification of values in Validation Report, xml v1.3, checks for missing info
     *
     * Expected Result: All required elements are present and meet the expected values and other values are empty as expected.
     *
     * File: Igasugust1.3.ddoc
     *
     ***/
    @Test
    public void ddocCorrectValuesArePresentV1_3() {
        setTestFilesDirectory("ddoc/live/timemark/");
        post(validationRequestFor("igasugust1.3.ddoc"))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchemaDdoc.json"))
                .body("signatures[0].id", Matchers.is("S0"))
                .body("signatures[0].signatureFormat", Matchers.is("DIGIDOC_XML_1.3"))
                .body("signatures[0].signatureLevel", Matchers.is(""))
                .body("signatures[0].signedBy", Matchers.is("SOONSEIN,SIMMO,38508134916"))
                .body("signatures[0].indication", Matchers.is("TOTAL-PASSED"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes[1].name", Matchers.is("Testilood20070320.doc"))
                .body("signatures[0].signatureScopes[1].scope", Matchers.is("FullSignatureScope"))
                .body("signatures[0].signatureScopes[1].content", Matchers.is("Full document"))
                .body("signatures[0].claimedSigningTime", Matchers.is("2009-06-01T10:46:37Z"))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is(""))
                .body("signatureForm", Matchers.is("DIGIDOC_XML_1.3"))
                .body("documentName", Matchers.is("igasugust1.3.ddoc"))
                .body("validSignaturesCount", Matchers.is(3))
                .body("signaturesCount", Matchers.is(3));
    }

    /***
     *
     * TestCaseID: Xroad-ValidationReportVerification-1
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: Verification of values in Validation Report, xroad-simple container
     *
     * Expected Result: Report is returned with required elements
     *
     * File: xroad-simple.asice
     *
     ***/
    @Test @Ignore //TODO: VAL-315
    public void xroadAllElementsArePresentValidSimpleSignature() {
        setTestFilesDirectory("xroad/");
        String encodedString = Base64.encodeBase64String(readFileFromTestResources("xroad-simple.asice"));
        post(validationRequestWithValidKeys(encodedString, "xroad-simple.asice", "xroad", ""))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchemaXroad.json"))
                .body("signatures[0].id", Matchers.is("signature"))
                .body("signatures[0].signatureFormat", Matchers.is("XAdES_BASELINE_LT"))
                .body("signatures[0].signatureLevel", Matchers.is(""))
                .body("signatures[0].signedBy", Matchers.is("Riigi Infosüsteemi Amet"))
                .body("signatures[0].indication", Matchers.is("TOTAL-PASSED"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes.name", Matchers.is("somename"))
                .body("signatures[0].signatureScopes.scope", Matchers.is(""))
                .body("signatures[0].signatureScopes.content", Matchers.is(""))
                .body("signatures[0].claimedSigningTime", Matchers.is(""))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is("2016-04-27T12:15:42Z"))
                .body("signatureForm", Matchers.is("ASIC_E"))
                .body("documentName", Matchers.is("xroad-simple.asice"))
                .body("validSignaturesCount", Matchers.is(1))
                .body("signaturesCount", Matchers.is(1));
    }

    /***
     *
     * TestCaseID: Xroad-ValidationReportVerification-2
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: Verification of values in Validation Report, xroad-batchsignature container
     *
     * Expected Result: Report is returned with required elements
     *
     * File: xroad-batchsignature.asice
     *
     ***/
    @Test @Ignore //TODO: VAL-315
    public void xroadAllElementsArePresentValidBatchSignature() {
        setTestFilesDirectory("xroad/");
        String encodedString = Base64.encodeBase64String(readFileFromTestResources("xroad-batchsignature.asice"));
        post(validationRequestWithValidKeys(encodedString, "xroad-batchsignature.asice", "xroad", ""))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchemaXroad.json"))
                .body("signatures[0].id", Matchers.is("signature"))
                .body("signatures[0].signatureFormat", Matchers.is("XAdES_BASELINE_B"))
                .body("signatures[0].signatureLevel", Matchers.is(""))
                .body("signatures[0].signedBy", Matchers.is("Riigi Infosüsteemi Amet"))
                .body("signatures[0].indication", Matchers.is("TOTAL-PASSED"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes[0].name", Matchers.is("somename"))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is(""))
                .body("signatures[0].signatureScopes[0].content", Matchers.is(""))
                .body("signatures[0].claimedSigningTime", Matchers.is(""))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is("2016-04-27T12:26:53Z"))
                .body("signatureForm", Matchers.is("ASiC_E_batchsignature"))
                .body("documentName", Matchers.is("xroad-batchsignature.asice"))
                .body("validSignaturesCount", Matchers.is(1))
                .body("signaturesCount", Matchers.is(1));
    }

    /***
     *
     * TestCaseID: Xroad-ValidationReportVerification-3
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: Verification of values in Validation Report, xroad-attachment container
     *
     * Expected Result: Report is returned with required elements
     *
     * File: xroad-attachment.asice
     *
     ***/
    @Test @Ignore //TODO: VAL-315
    public void xroadAllElementsArePresentValidAttachmentSignature() {
        setTestFilesDirectory("xroad/");
        String encodedString = Base64.encodeBase64String(readFileFromTestResources("xroad-attachment.asice"));
        post(validationRequestWithValidKeys(encodedString, "xroad-attachment.asice", "xroad", ""))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchemaXroad.json"))
                .body("signatures[0].id", Matchers.is("signature"))
                .body("signatures[0].signatureFormat", Matchers.is("XAdES_BASELINE_B"))
                .body("signatures[0].signatureLevel", Matchers.is(""))
                .body("signatures[0].signedBy", Matchers.is("Riigi Infosüsteemi Amet"))
                .body("signatures[0].indication", Matchers.is("TOTAL-PASSED"))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes[0].name", Matchers.is("somename"))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is(""))
                .body("signatures[0].signatureScopes[0].content", Matchers.is(""))
                .body("signatures[0].claimedSigningTime", Matchers.is(""))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is("2016-04-27T12:30:10Z"))
                .body("signatureForm", Matchers.is("ASiC_E_batchsignature"))
                .body("documentName", Matchers.is("xroad-attachment.asice"))
                .body("validSignaturesCount", Matchers.is(1))
                .body("signaturesCount", Matchers.is(1));
    }

    /***
     *
     * TestCaseID: Xroad-ValidationReportVerification-4
     *
     * TestType: Automated
     *
     * RequirementID: http://open-eid.github.io/SiVa/siva/interface_description/
     *
     * Title: Verification of values in Validation Report with invalid xroad container
     *
     * Expected Result: Report is returned with required elements
     *
     * File: xroad-attachment.asice
     *
     ***/
    @Test @Ignore //TODO: VAL-323
    public void xroadAllElementsArePresentInvalidSignature() {
        setTestFilesDirectory("xroad/");
        String encodedString = Base64.encodeBase64String(readFileFromTestResources("invalid-digest.asice"));
        post(validationRequestWithValidKeys(encodedString, "invalid-digest.asice", "xroad", ""))
                .then()
                .body(matchesJsonSchemaInClasspath("SimpleReportSchemaXroad.json"))
                .body("signatures[0].id", Matchers.is("signature"))
                .body("signatures[0].signatureFormat", Matchers.is("XAdES_BASELINE_LT"))
                .body("signatures[0].signatureLevel", Matchers.is(""))
                .body("signatures[0].signedBy", Matchers.is("Riigi Infosüsteemi Amet"))
                .body("signatures[0].indication", Matchers.is(""))
                .body("signatures[0].subIndication", Matchers.is(""))
                .body("signatures[0].errors", Matchers.hasSize(0))
                .body("signatures[0].signatureScopes[0].name", Matchers.is("somename"))
                .body("signatures[0].signatureScopes[0].scope", Matchers.is(""))
                .body("signatures[0].signatureScopes[0].content", Matchers.is(""))
                .body("signatures[0].claimedSigningTime", Matchers.is(""))
                .body("signatures[0].warnings", Matchers.hasSize(0))
                .body("signatures[0].info.bestSignatureTime", Matchers.is("2016-04-27T12:30:10Z"))
                .body("signatureForm", Matchers.is("ASiC_E_batchsignature"))
                .body("documentName", Matchers.is("invalid-digest.asice"))
                .body("validSignaturesCount", Matchers.is(1))
                .body("signaturesCount", Matchers.is(1));
    }

    @Override
    protected String getTestFilesDirectory() {
        return testFilesDirectory;
    }
}