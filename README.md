#Table of Contents#  *generated with [DocToc](http://doctoc.herokuapp.com/)*

- [Generic-Testdata-Framework](#generic-testdata-framework)
  - [About this Document](#about-this-document)
	- [Download](#download)
	- [Components](#components)
- [Technical Usage Guide](#technical-usage-guide)
- [Conceptual Usage Guide](#conceptual-usage-guide)

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


Download
--------


Components
----------



Technical Usage Guide
=====================




Conceptual Usage Guide
======================



















