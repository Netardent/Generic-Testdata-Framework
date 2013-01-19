*Table of Contents*  

- [Generic-Testdata-Framework](#generic-testdata-framework)
	- [About this Document](#about-this-document)
	- [Download](#download)
	- [Components](#components)
- [Conceptual Usage Guide](#conceptual-usage-guide)
- [Technical Usage Guide](#technical-usage-guide)
	- [Usage](#usage)
		- [Argument File](#argument-file)
		- [Java](#java)
		- [Ant](#ant)
	- [Metadata, Templates & Configuration](#metadata-templates--configuration)
		- [Metadata](#metadata)
		- [Templates](#templates)
		- [Mapping CSV-Files to Metadata-Files](#mapping-csv-files-to-metadata-files)

- - -

Generic-Testdata-Framework
==========================

The _Generic Testdata Framework_ is designed to work on top of the 
[Robot Test Automation Framework](http://code.google.com/p/robotframework/).

The major goal of this framework is to enable better cooperation between technical team members and functional
specialists of a software development team. This means that:

1. Technical experts should write keywords and combine them to possible _Test Scenarios_.
2. Functional specialists should have an easy (not too technical) way of specifying new _Tests_ based on those existing scenarios.

Let's take a look at an example from an insurance company where customers have the possibility to enter data on their car
using a web application provided by the insurance company. Of course this should be thoroughly as mistakes here might
easily result in some unhappy customers.

One _Test Scenario_ here could be filling all possible fields with corresponding values (type of car, age of driver, etc.).
This needs to be technically enabled by implementing keywords and an order in which those keywords are executed. In 
addition at least one keyword would be required to check that the calculated result is correct. 

The _Tests_ now would fill this scenario with life. Probably an insurance company would have a quite big amount of
possible tests checking that various possible combinations are working. By using the _Generic Testdata Framework_
functional specialists are enabled to implement _Tests_ using some kind of GUI without the need to know anything
about the underlying technical details.

Actually the supported GUI is using Excel for editing and writing new _Tests_ for an existing _Test Scenario_. In the 
long run there will also be a web frontend, but as that one is work in progress it is not yet described here.


About this Document
-------------------

This document contains the complete documentation of the _Generic Testdata Framework_. It is divided
into the following main chapters:

- The chapter you are currently reading contains an introduction on the _Generic Testdata Framework_, the [download section](#download) and an overview on the available [components](#components).
- The - [Conceptual Usage Guide](#conceptual-usage-guide) describes the ideas and concepts behind this framework.
- The [Technical Usage Guide](#technical-usage-guide) describes how to implement tests using the _Generic Testdata Framework_ together with the _Robot Framework_.



Download
--------

The most recent version of the _Generic Testdata Framework_ can be downloaded from here:

[http://code.google.com/p/generic-testdata-framework/downloads/list](http://code.google.com/p/generic-testdata-framework/downloads/list)

The corresponding release notes for every version can be found [from this page](https://github.com/ThomasJaspers/Generic-Testdata-Framework/wiki/Releases).


Components
----------

The downloaded *robot_gtf* ZIP-file contains the following components:

1. JAR-file implementing the _Generic Testdata Framework_ core functionality.
2. An Excel-based example that can be used as a template for a new project.



Conceptual Usage Guide
======================

The following figure depicts pretty well the most basic concept and idea behind this framework. This is to have a 
clear division between the (technical) implementation of the tests and the (functional) implementation of individual
testcases. Using the differnt input files (input data) the _Generic Testdata Framework_ then generates complete
Robot Framework testsuite files.

![Conceptual Overview](https://raw.github.com/ThomasJaspers/Generic-Testdata-Framework/master/gtf/doc/ConceptualOverview.png)

Therefore so-called test scenarios are implemented using a template- and metadata-driven approach. Certainly it
makes sense to develop these test scenarios together with the functional experts or at least get the required
input. Then once the implementation is done different tests can be easily added by filling in the required
parameters into the different test scenarios. The following screenshot shows the Excel-file for one tests-file
provided in the example that comes with the _Generic Testdata Framework_.

![Excel Example](https://raw.github.com/ThomasJaspers/Generic-Testdata-Framework/master/gtf/doc/ExcelSample.png)

As can be seen from the screenshot in the Excel-Sheet it is possible to focus to a great extend on the tests.
Basically it is not at all possible to see how the tests are implemented in the end. It is mandatory to
define in each row the test scenario that should be used. Then the name of the test case and a description
can be given to have this information available later on in the Robot Framework report file. It would be also
possible to add Robot Framework tags this way. But none of this information is really mandatory beside the name
of the test scenario and the parameters to the test.

In order to allow the functional specialists to write new tests on their own it would be very beneficial (well,
basically mandatory) to define the available test scenarios somewhere together with the meaning of the used
parameters. On the other hand such a description would be anyway good to have to document the possible tests.
It can be seen from the screenshot that some color coding can be used in Excel to make the different purposes of
the different columns more clear. It is also possible to define comment lines by using a "##" in the beginning of
the first cell and to use empty lines for formatting purposes.


Technical Usage Guide
=====================

Usage
-----

The _Generic Testdata Framework_ is designed to be started from the commandline. 
There are two ways to do so that are supported out-of-the-box:

1. Using Java or
2. Using Ant (currently being implemented, not available yet).

Especially the Ant-Script that is bundled with the download package might be very useful as it does not only 
support starting the _Generic Testdata Framework_, but also the Robot Framework . 
No matter which way is used to start the tool, there is only one command line option available, which points 
to an argument-file that contains all required options. This way one can easily configure different 
startup-scenarios and share them easily (e.g. by checking the Argument-File into some version control system).

The following chapter is explaining the content of the Argument-File. Afterwards the startup using pure Java is explained 
and then the integration with Ant.

### Argument File

The argument file contains mandatory and optional parameters that must (can) be passed to the GTF-Tool.
Thus the only command line argument that is accepted is the path to such an argument-file. 
It can have any name, but it must have a proper syntax for [Java property files](http://en.wikipedia.org/wiki/.properties) 
(which is not too complicated too achieve ;)). The following list defines the possible arguments:

* **ConfigurationDirectory** - This is the directory that contains the metadata defintions as well as the template files. 
* **XlsDirectory** - This directory contains the Excel-Files that are used as an input to generate the individual Testsuite-Files containing then all the corresponding testcases from such a XSL-File.
* **TestsuiteDirectory** - The resulting Testsuite-Files are generated into this directory.
* **InputType** - Define the input type, currently supported XLS. If no input type is given XLS is assumed. This is designed for future use when also database will be supported as an input type.

The following shows an example of an argument file:

	ConfigurationDirectory = c:\gtf-sample\config  
	XlsDirectory = c:\gtf-sample\xsl
	TestsuiteDirectory = c:\gtf-sample\testsuite  

It should be noted that for the Configuration Directory this results in the following two directories:

* c:\gtf-sample\config\metadata
* c:\gtf-sample\config\template

The main idea of having all arguments bundled in one file is to be able to use the same argument file inside a 
team and project. Furthermore this makes it easily possible to have different ready-made files for different
environments (local, CI-environment, etc.).


### Java
To execute the _Generic Testdata Framework_ just issue the following command from the directory where the
__robot_gtf.jar__ is located (or add the corresponding path information).

	java -jar robot_gtf.jar sampleArguments.txt

The above example for starting the tool also assumes that the arguments-file is located in the same directory.
This is true for the example that is bundled together with the download. But of course you will adapt this with
the directory structure of your project. Nevertheless there is one recommendation regarding this which applies
to the Robot Framework as such as well: Always try to setup projects in a way that only relative path information
starting from a roo-directory is used. This makes it easier to share the project in the team and between different
environments without too big changes required.


### Ant

Please download and install Ant before continuing here.



Metadata, Templates & Configuration
-----------------------------------


To make use of the Generic Testdata Framework a certain amount of configuration is required. 
We need to pass some basic arguments to the tool. Then some configuration is needed to know how to glue the template files and the content from the CSV-files together.



The argument file contains mandatory and optional parameters that must (can) be passed to the GTF-Tool.
Thus the only command line argument that is accepted is the path to such an argument-file. It can have any name, but it must have a proper syntax for Java property files (which is not too complicated too achieve ;)). The following list defines the possible property values:

* **ConfigurationDirectory** - This is the directory that contains the metadata defintions as well as the template files. 
* **CsvDirectory** - This directory contains the CSV-Files that are used as an input to generate the individual Testsuite-Files containing then all the corresponding testcases from such a CSV-File.
* **XlsDirectory** - This directory contains the XLS-Files that are used as an input to generate the individual Testsuite-Files containing then all the corresponding testcases from such a XSL-File.
* **TestsuiteDirectory** - The resulting Testsuite-Files are generated into this directory.
* **InputType** - Define the input type, currently supported CSV and XLS. If no input type is given CSV is assumed.

The following shows an example of an argument file:

`ConfigurationDirectory = c:\gtf-sample\config`  
`CsvDirectory = c:\gtf-sample\csv`  
`TestsuiteDirectory = c:\gtf-sample\testsuite`  

It should be noted that for the Configuration Directory this results in the following two directories:

* c:\gtf-sample\config\metadata
* c:\gtf-sample\config\template


### Metadata

There must be one Metadata-File written for each kind of CSV-File that must be processed and converted to a corresponding Testsuite-File. Of course sevaral CSV-Files of the same type can be processed using the same Metadata-File. Metadata-Files are as well defined as Java property files. They have two different kind of entries. The first ones are those referring to the three types of template files to be used by defining the corresponding filesnames without path information. (The path is derived from the _Configuration Directory_ as described above.) Those are:

* **HeaderTemplateFileName** - Name of the Testsuite header template to be used.
* **FooterTemplateFileName** - Name of the Testsuite footer template to be used.
* **TestcaseTemplateFileName** - Name of the Testcase template file to be used.
* **TestsuiteFilePostfix** - Postfix to be used for generated testsuite files.

Now for the Testcase template files there is a need to define where the different elements from one line of a CSV-File are put to the Testcase template file. Therefore that file contains parameter definitions for example `%NAME%` and `%PHONE%`. Now we need to know which column in the CSV-File is the _name_ and which one the _phone_. Assuming we have _name_ in the first column and _phone_ in the second one we would have the following configuration:

`NAME = 1`  
`PHONE = 2`

Thus we simply need for each parameter definition in the Testcase template a corresponding entry in the mapping file to map this parameter to the corresponding column in the CSV-File. 


### Templates

The templates are basically parts of a Robot Framework testsuite. In principle it does not even matter which format is used here, but let's assume we are using HTML-format in the following.

For every kind of CSV-File we need the following three template files, which have already been mentioned in the metadata section:

* **Header-Template** - This template contains the header of the Testsuite. Typically it contains required imports of test-libraries and resource files and some more common Robot Framework configurations.
* **Footer-Template** - This template is only required to ensure the generated Testsuite file is valid. It might even only contain a closing HTML-tag.
* **Testcase-Template** - This is probably the most complicated template. It contains the definition of one kind of testcase using parameters in case of some real test values. This template is re-used for each line in a corresponding CSV-File to generate the tests inside the testsuite.

It is quite likely that the header and footer templates can be re-used for different CSV-Files by simply using the same names in the corresponding Metadata configuration.

The testcase template should be unique for each kind of CSV-File. It would make things easiest to define a high-level keyword in Robot for the execution of the specific tests that just gets the required parameters passed in as ... parameters. for example:

`Find Phone Number For Name %NAME% %PHONE%`  

Thus we have the _name_ as an input parameter and the _phone_ number we expect as an result both coming from one line in a CSV-File. But there is no intelligence here in the GTF-Tool on what the parameters are used for in the keywords and that's part of the beauty of it :-).


### Mapping CSV-Files to Metadata-Files

Ok, there is at least one question left. How does the GTF-Tool know which Metadata-File corresponds to which  CSV-Files. The answer: naming conventions.

Let's assume there is a CSV-File named: `phonefeature.csv` in the CSV-Directory. Then the corresponding metadata file (searched from the corresponding directory) is `phonefeature.properties`.

Of course now we would like to be able to match different CSV-Files of the same type to one Metadata-File. This is possible by using a prefix approach, thus the CSV-Files: `phonefeature_smoke.csv` and `phonefeature_full.csv` would both be mapped to make use of the Metadata-File `phonefeature.properties` by using the "_" as a delimiter for the common part of the name.

This would then result in the generation of two Testsuite-Files: `phonefeature_smoke.html` and `phonefeature_full.html` in the Testsuite directory defined in the Argument-File.

