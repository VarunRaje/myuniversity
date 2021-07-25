# myuniversity
University Management App

This is a sample application to demonstate how to use SpringBoot to develope REST service. This application also contains the Use of H2 DB with Help of JPA Repository  which make this  App can we easily port with another DB.
This Application have below parts.
1. SpringBoot REST Application which have CRUD operation on student database.
2. Use of H2 in memory database and it configuration with JPA repository
3. Unit Test cases with Testng and H2 database.
4. Integration Test case with Cucumber and BDD approch
5. Unit test cases with Mockito and JUnit.


Table of contents
=================

<!--ts-->
* [Building Intructions](#installation)
* [Usage](#usage)
    * [Running REST Application standalone](#running)
    * [Running REST application with docker](#docker-run)
    * [Running Unit Test Cases](#unit-test-run)
    * [Running Integration Test cases](#integration-test-run)
<!--te-->

Building Intructions
============
Clone the above repositoty on your local ssystem

```bash
$ git clone https://github.com/VarunRaje/myuniversity.git
$ cd myuniversity
$ ./gradlew clean build
```


Usage
============

1. Running REST Application standalone

```bash
$ ./gradlew bootRun
```

2. REST application with docker

```bash
$ docker build -t myUniversity .
$  docker run -p 8080:8080 myuniversity
```

3. Running Unit Test Cases

```bash
$ ./gradlew test
```

4. Running Integration Tests

```bash
$ ./gradlew integrationTest
```




