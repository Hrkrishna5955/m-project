![EU Regional Development Fund](docs/img/siva/EL_Regionaalarengu_Fond_horisontaalne-vaike.jpg)

# Signature Verification Service

[![SiVa CI with Maven](https://github.com/open-eid/siva/actions/workflows/siva-verify.yml/badge.svg?branch=master)](https://github.com/open-eid/siva/actions/workflows/siva-verify.yml)
[![GitHub license](https://img.shields.io/badge/license-EUPLv1.1-blue.svg)](https://raw.githubusercontent.com/open-eid/SiVa/develop/LICENSE.md)

SiVa is digital signature validation web service that provides JSON and SOAP API to validate following file types:

* Estonian DDOC containers
* Estonian BDOC containers with TimeMark and TimeStamp signatures
* Estonian ASiC-S containers with time stamp tokens
* ETSI standard based ASiC-E and ASiC-S containers
* ETSI standard based XAdES, CAdES and PAdES signatures
* ETSI standard based XAdES signatures with datafiles in hashcode form

### Libraries used in validation services

Below is list of Java libraries used for validation:

* [DigiDoc4J](https://github.com/open-eid/digidoc4j) - is used to validate DDOC and BDOC digital signature containers.
* [DigiDoc4J DSS fork](https://github.com/open-eid/sd-dss) - to validate all other digitally signed files.

## Requirements

These are minimum requirements to build and develop SiVa project:

* **git** - to easily download and update code. You can [download git here](https://git-scm.com/)
* **Java JDK 11** - to compile and run SiVa applications.
* **IDE** - to develop SiVa. We recommend to use [JetBrains IntelliJ](https://www.jetbrains.com/idea/)
* **2 GB of RAM** the RAM requirement is here because when building the project the integration tests take up a lot of memory
* Optionally You can also install **Maven** but it is not needed because SiVa project uses Maven wrapper to install maven

## How to build

### Using Maven Wrapper

Recommended way of building this project is using [Maven Wrapper](https://github.com/takari/maven-wrapper).
Run following command:

```bash
./mvnw clean install
```

Or create image for Docker:

```bash
./mvnw spring-boot:build-image -pl siva-parent/siva-webapp
```

## How-to run

### With docker

Before continuing, the [siva-demo-application](https://github.com/open-eid/SiVa-demo-application) docker image must be built and available on Docker as `siva-demo-application:latest`.

The following command will run siva-webapp along with siva-demo-application:

```
docker compose up
```

Now SiVa itself is accessible http://siva.localhost:8080/ and siva-demo-application http://siva-demo.localhost:9000/.

### Without docker

SiVa project compiles **2 fat executable JAR** files that You can run after successfully building the
project by issuing below command:

**Starting the SiVa REST and SOAP webservice. NB! X.X.X denotes the version you are running.**

```bash
java -jar siva-parent/siva-webapp/target/siva-webapp-X.X.X-exec.jar
```

The SiVa webapp by default runs on port **8080**.

Easiest way to test out validation is to [start SiVa Demo Application without docker](https://github.com/open-eid/SiVa-demo-application#without-docker).

## WAR and Tomcat setup for legacy systems

> **NOTE**: Each SiVa service **must** be deployed to separate instance of Tomcat to avoid Java JAR library version
> conflicts.

To build the WAR file use helper script with all the correct Maven parameters.

```bash
./build-war.sh
```

Copy built WAR file into Tomcat `webapps` directory and start the servlet container. NB! X.X.X denotes the version you are running.

```bash
cp siva-parent/siva-webapp/target/siva-webapp-X.X.X.war apache-tomcat-7.0.70/webapps
./apache-tomcat-7.0.77/bin/catalina.sh run
```

> **IMPORTANT** siva-webapp on startup creates `etc` directory where it copies the TSL validation certificates
> `siva-keystore.jks` (or `test-siva-keystore.jks` if `test` profile is used). Default location for this directory
> is application root or `$CATALINA_HOME`. To change this default behavior you should set environment variable
> `DSS_DATA_FOLDER`

### How-to set WAR deployed SiVa `application.properties`

SiVa override properties can be set using `application.properties` file. The file can locate anywhare in the host system.
To make properties file accessible for SiVa you need to create or edit `setenv.sh` placed inside `bin` directory.

Contents of the `setenv.sh` file should look like:

```bash
export CATALINA_OPTS="-Dspring.config.location=file:/path/to/application.properties"
```

## How-to run tests

Unit test are integral part of the SiVa code base. The tests are automatically executed every
time the application is built. The build will fail if any of the tests fail.

To execute the tests from command line after application is built use:

```bash
./mvnw verify
```

### How to run integration tests
Integration tests are disabled by default, but can be enabled with maven parameter `-DrunIntegrationTests=true`. 
Executing integration tests requires running SiVa Web application instance.

### How to run integration tests in docker
Before starting the docker instances, the `docker-compose.yaml` file must be modified. Add the following under the siva-webapp service to run the docker image with the test profile:

```
environment:
  - "SPRING_PROFILES_ACTIVE=test"
```

### How to run load tests

Load tests are disabled by default, but can be enabled with maven parameter `-DrunLoadTests=true`. When executing the load
tests, SiVa Web application has to be started before the tests are executed.

> **Note**: PDF load test files contain test certificates. In order for PDF load tests to succeed
> SiVa application should be started with test certificates preloaded.

To load trusted test certificates in addition to TSL, "test" spring profile should be activated at startup (NB! X.X.X denotes the version you are running), for example:

```bash
java -Dspring.profiles.active=test -jar siva-parent/siva-webapp/target/siva-webapp-X.X.X-exec.jar
```

To run load tests after unit tests in non GUI mode:

```bash
./mvnw verify -DrunLoadTests=true
```

To run load tests only:

```bash
./mvnw verify -DskipTests=true -DrunLoadTests=true
```

To run load tests with JMeter GUI execute the command in `Siva/siva-parent/siva-test/` folder:

```bash
mvn jmeter:gui  -DrunLoadTests=true
```

It is possible to configure following parameters in load test (given defaults are based on `../siva-test/pom.xml`):

  * `jmeter.host.name` - target webapp host against what the tests are executed, default is localhost
  * `jmeter.host.port` - target port of the webapp host , default is 8080
  * `jmeter.host.protocol` - target protocol , default is http
  * `jmeter.host.timeout` - response waiting timeout, default is 60000 (in milliseconds)
  * `jmeter.testfiles.dir` - directory of the test files, default is ${project.basedir}/src/test/jmeter/test-files
  * `jmeter.load.step.duration` - time how long the load is kept on each throuput level, default is 60 (in seconds)

These values can be set in three different ways:
  * In JMeter test plan - these settings will be used when JMeter GUI is used to run the tests
  * In `../siva-test/pom.xml` file - these settings will be used when the tests are run in non GUI mode
    and will overwrite the default values in test plans.
  * As parameters when executing the tests - These values have highest priority and will overwrite other default values.

To run the tests with modified parameters:

```bash
./mvnw verify -Drun.load.tests=true -Djmeter.host.port=9090
```

Test results will be available at `/siva-parent/siva-test/target/jmeter/results/reports/` folder

## Open source software used to build SiVa

Full list of open source Java libraries used to build SiVa can be found in our
[Open Source Software used page](OSS_USED.md)

## Documentation

Read [SiVa documentation](http://open-eid.github.io/SiVa/)
