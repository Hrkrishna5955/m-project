

## Test execution

Execution of tests in SiVa is divided in three categories: Automated, Automated SoapUI, Manual. The method is also indicated in test case description with TestType field.

### Execution of Automated type of tests

These tests are run automatically by Maven every time SiVa project is built. These tests must pass for the build to be successful. Its also possible to execute the tests separately with Maven wrapper using the command:

```bash
./mvnw verify
```

These tests are part of SiVa project and can also be executed using IntellJ IDEA. To do that, SiVa project must be loaded into IDEA. Tests are located in following packages:
  
  * ee.openeid.siva.integrationtest
  * ee.openeid.siva.resttest
  * ee.openeid.siva.soaptest

To run the tests in IDEA right click on package name and select "Run tests in". Its also possible to run individual tests by right clicking on specific test code and selecting "Run". 

### Execution of Automated SoapUI type of tests

The description how to execute the tests together with SoapUI project file to be used can be found in [SiVa GitHub](https://github.com/open-eid/SiVa/tree/master/test-helpers/XRoadSoapUITests)

### Execution of Manual type of tests

Most of the manual tests require that SiVa service is set up together with S[SiVa Demo Application](https://github.com/open-eid/SiVa-demo-application). The instructions how to set up SiVa are given in [System integrator's guide](http://open-eid.github.io/SiVa/siva3/systemintegrators_guide/)

Execution of manual tests depends on testable area. These tests can be divided into following categories:

  * Statistics tests - test data can be prepeared with exectuting ee.openeid.siva.manualtest package. It can be also generated by uploading files into SiVa Demo application. Results have to be verified in logs.
  * Report tests - test data can be prepeared with exectuting ee.openeid.siva.manualtest package. Results have to be verified manually.
  * Configuration tests - SiVa configuration files have to be modified by hand and service must be set up. Correct behavior of the service must be checked.
  * Other tests - tests are executed by loading files into SiVa Demo application and validating the results shown in the SiVa Demo application.

Files to use in manual tests can be found in [SiVa GitHub](https://github.com/open-eid/SiVa/tree/master/siva-parent/siva-test/src/test/resources)
  
## Integration Test introduction

This section of the document gives overview of Integration Testing carried out on SiVa web service and SiVa Demo application.

SiVa web service Integration Testing is using IO RestAssured library v3.3.0 to implement automatic checks for REST/SOAP based tests.

The testing of the SiVa web service is divided into sections based on the software architecture and functionalities provided to the users. The sections are:

  * REST API
  * SOAP API
  * DDOC container signature validation
  * BDOC container signature validation
  * ASICE container signature validation
  * ASIC-S container signature validation
  * PDF signature validation
  * XAdES hashcode validation

The goal is to focus testing on functionalities implemented in SiVa web service application. Functionalities provided by [Validation libraries](../overview/#Validation-libraries) are not explicitly tested.

In addition SiVa Demo Application is tested. These tests are carried out manually.

## Testing of REST API

The goal of the REST API testing is to check that the API is accepting the requests based on the specification and the output result is in correct format and has all the required elements.

### Validation request tests

Following areas are tested on input:

  * Wrong (not accepted) values in input parameters
  * Empty values in input paramters
  * Too many parameters
  * Too few parameters
  * Inconsistencies on stated parameters and actual data (wrong document type)
  * Case insensitivity on parameter names
  * Empty request


In all of the negative cases correctness of returned error message is checked.

Specific test cases and input files can be found in:

  * [ValidationRequestIT.java](../appendix/test_cases/#validationrequestitjava)
  * [DocumentFormatIT.java](../appendix/test_cases/#documentformatitjava)
  * [HashcodeValidationRequestIT.java](../appendix/test_cases/#hashcodevalidationrequestitjava)

### Get Data Files request tests

Following areas are tested on input:

  * Empty request
  * Empty values in input parameters
  * Too many parameters
  * Too few parameters
  * Changed order of parameters
  * Case insensitivity on parameter names
  * Inconsistencies on stated parameters and actual data

In all of the negative cases correctness of returned error message is checked.

Specific test cases and input files can be found in:

  * [GetDataFileRequestIT.java](../appendix/test_cases/#getdatafilerequestitjava)

### Validation report and report siganture tests

SiVa web service returns uniform Validation Report on all the supported document types. This also includes correct document types without actual signature (for example PDF document without signature).

Following areas are tested on output (Validation Report):

  * JSON structure on DDOC, BDOC, PDF, ASIC-E, ASIC-S, XAdES hashcode 
  * Presence of the mandatory elements on DDOC, BDOC, PDF, ASIC-E, ASIC-S, XAdES hashcode
  * Presence of optional elements on DDOC, BDOC, PDF, ASIC-E, ASIC-S, XAdES hashcode
  * Verification of expected values
  * JSON structure on containers without signatures

Specific test cases and input files can be found in:

  * [ValidationReportValueVerificationIT.java](../appendix/test_cases/#validationReportValueVerificationitjava)
  * [ReportSignatureManualIT.java](../appendix/test_cases/#reportSignatureManualitjava)
  * [DetailedReportValidationManualIT.java](../appendix/test_cases/#detailedReportValidationManualitjava)
  * [DiagnosticReportValidationManualIT.java](../appendix/test_cases/#diagnosticReportValidationManualitjava)
  
## Testing of SOAP API

The goal of the SOAP API testing is to check that the API is accepting the requests based on the specification and the output result (Validation Report) is in correct format and has all the required elements. In general the tests follow the same principles as with REST API.
Compatibility with X-Road security server is out of scope for these tests and will be covered in X-Road System Test plan.

### Validation request tests

Following areas are tested on input:

  * Wrong (not accepted) values in input parameters
  * Empty values in input paramters
  * Too many parameters
  * Too few parameters
  * Inconsistencies on stated parameters and actual data (wrong document type)
  * Case insensitivity on parameter names
  * Empty request


In all of the negative cases correctness of returned error message is checked.

Specific test cases and input files can be found in:

  * [SoapValidationRequestIT.java](../appendix/test_cases/#soapvalidationrequestitjava)
  * [SoapHashcodeValidationRequestIT.java](../appendix/test_cases/#soaphashcodevalidationrequestitjava)

### Get Data Files request tests

Following areas are tested on input:

  * Empty request
  * Empty values in input parameters
  * Too many parameters
  * Too few parameters
  * Changed order of parameters
  * Case insensitivity on parameter names
  * Inconsistencies on stated parameters and actual data

In all of the negative cases correctness of returned error message is checked.

Specific test cases and input files can be found in:

  * [SoapGetDataFileRequestIT.java](../appendix/test_cases/#soapgetdatafilerequestitjava)

### Validation report tests

SiVa web service returns uniform Validation Report on all the supported document types. This also includes correct document types without actual signature (for example PDF document without signature). However not all values may be present for all the document types.

Following areas are tested on output (Validation Report):

  * Presence of the mandatory elements on DDOC, BDOC, PDF, ASIC-S ASIC-E, XAdES hashcode
  * Presence of optional elements on DDOC, BDOC, PDF, ASIC-S, ASIC-E, XAdES hashcode and
  * Verification of expected values

Specific test cases and input files can be found in:

  * [SoapValidationReportValueIT.java](../appendix/test_cases/#soapvalidationreportvalueitjava)

### Get Data Files report tests

Following areas are tested on output:

  * Presence of the mandatory elements
  * Verification of expected values
  * Extraction of all data files

Specific test cases and input files can be found in:

  * [SoapGetDataFileReportIT.java](../appendix/test_cases/#soapgetdatafilereportitjava)

## Testing of DDOC container signature validation

The goal of the DDOC container signature validation testing is to check that the validation results given by JDigiDoc library are properly presented in validation report.

The testing of DDOC signatures consists of following main cases:

  * Containers with valid signature(s) are validated.
  * Containers with invalid signature(s) or no signature are validated
  * Containers sizes near maximum are validated
  * Containers with DDOC v1.0 - 1.3 are validated

Specific test cases and input files can be found in:

  * [DdocValidationFailIT.java](../appendix/test_cases/#ddocvalidationfailitjava)
  * [DdocValidationPassIT.java](../appendix/test_cases/#ddocvalidationpassitjava)
  * [LargeFileIT.java](../appendix/test_cases/#largefileitjava)

**What is not tested:**

  * Verification of different causes in container for invalid result is out of scope.

## Testing of BDOC container signature validation

The goal of the BDOC container signature validation testing is to check that the validation results given by DigiDoc4J library are properly presented in validation report.

The testing of BDOC container signatures consists of following main cases:

  * Containers with valid signature(s) are validated
  * Containers with invalid signature(s) or no signature are validated
  * Containers sizes near maximum are validated
  * Containers with baseline B, T, LT, LT_TM and LTA profile (files with BDOC extension)

Specific test cases and input files can be found in:

  * [BdocValidationFailIT.java](../appendix/test_cases/#bdocvalidationfailitjava)
  * [BdocValidationPassIT.java](../appendix/test_cases/#bdocvalidationpassitjava)
  * [LargeFileIT.java](../appendix/test_cases/#largefileitjava)

**What is not tested:**

  * Verification of different causes in container for invalid result is out of scope.
  
## Testing of ASIC-E container signature validation

The goal of the ASIC-E container signature validation testing is to check that the validation results given by DSS library are properly presented in validation report.

The testing of ASIC-E container signatures consists of following main cases:

  * Containers with valid signature(s) are validated
  * Containers with invalid signature(s) or no signature are validated
  * Containers with baseline B, T, LT and LTA profile

Specific test cases and input files can be found in:

  * [AsiceValidationFailIT.java](../appendix/test_cases/#asicevalidationfailitjava)
  * [AsiceValidationPassIT.java](../appendix/test_cases/#asicevalidationpassitjava)

**What is not tested:**

  * Verification of different causes in container for invalid result is out of scope.

## Testing of ASIC-S container signature validation

The goal of the ASIC-S container signature validation testing is to check that the validation results given by DSS library are properly presented in validation report.

The testing of ASIC-S container signatures consists of following main cases:

  * Containers with valid signature(s) are validated
  * Containers with invalid signature(s) or no signature are validated
  * Containers sizes near maximum are validated

Specific test cases and input files can be found in:

  * [AsicsValidationFailIT.java](../appendix/test_cases/#bdocvalidationfailitjava)
  * [AsicsValidationPassIT.java](../appendix/test_cases/#bdocvalidationpassitjava)

**What is not tested:**

  * Verification of different causes in container for invalid result is out of scope.

## Testing of XAdES hashcode signature validation

The goal of the XAdES hashcode signature validation testing is to check that the validation results given by DSS library are properly presented in validation report.

The testing of XAdES hashcode signatures consists of following main cases:

  * XAdES signatures with valid signature(s) are validated
  * XAdES signatures with invalid signature(s) or no signature are validated
  * XAdES signatures extracted from BDOC and ASIC-E
  * XAdES signatures with baseline B, T, LT and LTA profile

Specific test cases and input files can be found in:

  * [XadesHashcodeValidationFailIT.java](../appendix/test_cases/#xadeshashcodevalidationfailitjava)
  * [XadesHashcodeValidationPassIT.java](../appendix/test_cases/#xadeshashcodevalidationpassitjava)

**What is not tested:**

  * Verification of different causes in container for invalid result is out of scope.

## Testing of PDF signature validation

Portion of the validation rules for PDF documents are implemented in SiVa web apllication itself. Therefor different test area selection is used for PDF compared to other containers.

The testing of PDF signatures consists of following main cases:

  * Containers with invalid signature(s) (different reasons for failure) are validated
  * Containers with no signature are validated
  * Containers sizes near maximum are validated
  * Containers with different baseline profiles are validated
  * Containers with serial and parallel signatures are validated
  * Containers with different signature cryptocaphic algorithms are validated
  * Containers with OCSP values inside and outside bounds are validated
  * Containers with baseline B, T, LT and LTA profile
  

Specific test cases and input files can be found in:

  * [PdfBaselineProfileIT.java](../appendix/test_cases/#pdfbaselineprofileitjava)
  * [PdfSignatureCryptographicAlgorithmIT.java](../siva/appendix/test_cases/#pdfsignaturecryptographicalgorithmitjava)
  * [PdfValidationFailIT.java](../appendix/test_cases/#pdfvalidationfailitjava)
  * [PdfValidationPassIT.java](../appendix/test_cases/#pdfvalidationpassitjava)
  * [LargeFileIT.java](../appendix/test_cases/#largefileitjava)

## Testing of Data Files Extraction

The goal of the Data Files Extraction testing  is to check if right data files returned for DDOC container and error messages for other types of containers.

The testing of Data Files Extraction consists of following main cases:

  * Extracting the data files from valid DDOC container
  * Extracting the data files from Hashcoded DDOC  returns null for Base64 encoded String
  * Extracting the data files from DDOC container  with 12 different   types  of files
  * Extracting the data files from not DDOC container
  * Extracting the data files from DDOC container with wrong Document Type

Specific test cases and input files can be found in:

  * [DdocGetDataFilesIT.java](../appendix/test_cases/#ddocgetdatafilesitjava)

## Testing of user statistics

Testing of user statistics is carried out in combination of automatic data preparation and generation by integration tests and manual verification of the results.
SiVa supports the following way of gathering user statistics:

  * Validation results are printed to system log and can be gathered by any suitable means

Following areas are covered:

  * Statistics values are checked in log for all container types (this also includes parameters not present in validation report)
  * Valid and invalid signatures are validated
  * Error situations on signature validation (instead of validation report, error message is returned)
  
Specific test cases and input files can be found in:

  * [StatisticsToLogsManualIT.java](../appendix/test_cases/#statisticstologsmanualitjava)
  
## SiVa Demo Application tests

Testing of [SiVa Demo Application](https://github.com/open-eid/SiVa-demo-application) is done manually. The main cases are:

  * Cross browser usage (IE, Edge, Chrome, Firefox and Safari)
  * File upload (different sizes, suported and unsupported file types)
  * Displayment of Validation Report both for REST and SOAP
  * Layout of the page
  * Error representation

Sample test cases with input files can be found in:

  * [Demo Application Integration Test](appendix/test_cases#demo-application-integration-test) 
  
## System Test introduction

While Integration Tests were mostly carried out automatically then System Testing is mostly depending on manual testing.

System testing is carried out using two access points:

  * Testing through SiVa Demo Application
  * Testing through X-Road security server using SoapUI

!!! Note
 
	Testing through X-Road security server requires presence and configuration of X-Road security server to use SiVa service.  Tests are run using SoapUI that simulates request to X-Road security server.

## Testing through X-Road security server

Following areas are covered for document validation:

  * Validation of valid signature
  * Validation of invalid signatur
  * Validation that returns Soap error

All of the above test cases are run with BDOC, DDOC, PDF and ASIC-S containers. 

Following areas are covered for file extraction:

  * Extraction of data files from valid ddoc
  * Extraction of data files from invalid ddoc
  * Error response from data file extraction  

Tests along with test case descriptions are available for rerun in [github](https://github.com/open-eid/SiVa/tree/develop/test-helpers/XRoadSoapUITests).

Specific test cases and input files can be found in:

  * [X-Road Soap System Test](../appendix/test_cases/#x-road-soap-system-test)

## Configuration/administration testing

Following areas are covered:

  * SiVa Web Application configuration
  * Demo application configuration

Specific test cases can be found in:

  * [Configuration System Test](../appendix/test_cases/#configuration-system-test)  

## Load Test introduction

The goals of the load test was to:

  * Determine the throughput capabilities of a single Siva node and how it handles requests under increasing load.
  * Test whether the SiVa service throughput is horizontally scalable up to 50 requests per second.

Each container type was load-tested separately since the business logic and underlying mechanics for validating specific container types are vastly different.

Load tests were run in three stages – firstly a single Siva service node was tested to determine the baseline performance metrics. Second and third stage involved adding additional service node to previous setup and testing the horizontal scalability performance.
All three target Siva service nodes had identical virtual machine set-up. Virtual machines were installed on separate physical hardware.
Siva web service nodes on a target Linux virtual machine were packaged inside a Docker container (along with Java with 4 GB allocated for Heap). The test runner (JMeter plugin used by Maven) resided on a separate machine on local area network. Simple reverse proxy was used as a load balancer to distribute the load between nodes (using round robin algorithm).

Load testing is carried out on following environments:

  * System under test enviroment (processor: Intel(R) Xeon(R) CPU E5-2620 v3 @ 2.40GHz memory: 6GB (4GB allocated for Java heap))
  * Load balancer enviroment (processor: Intel(R) Xeon(R) CPU E5-2620 v3 @ 1.60GHz memory: 4GB)
  * Jmeter executer enviroment (processor: Intel(R) Xeon(R) CPU E5-2620 v2 @ 2.10GHz memory: 10GB)

Following test data is used in load test:

  * BDOC-TS file with two valid signatures (~100KB and 5MB)
  * BDOC-TM file with two valid signatures (~100KB and 5MB)
  * PDF file with two valid signatures (~200KB and 5MB)
  * DDOC file with two valid signatures (~300KB and 5MB)
  * ASIC-E X-Road container with one valid signature (~10KB)
  * ASIC-S file with two valid signatures (~20KB and 50KB)

Each of the files are validated through REST interface. SOAP interface is used with small files for a comparison. It is evaluated that the interface (REST or SOAP) do not play noticeable effect on overall results.

Each of the tested files follow the same test plan:

  * Five concurrent requests are made per second
  * This load is held for period of time
  * Concurrent requests are increased by five until 50 concurrent requests per second is achieved
  * Latency and throughput is measured on each concurrent request steps


