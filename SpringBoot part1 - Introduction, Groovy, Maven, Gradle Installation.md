# SpringBoot Part 1
## SpringBoot part1 - Introduction, Groovy, Maven, Gradle Installation

#### Introduction

- 2002 (Rod Johnson)
- free, open source
- IoC (Inversion of Control) - Dependency Injection
- REST Service

##### Java EE vs Spring Framework

|  | Java Enterprise Edition (Java EE)  | Spring Framework |
| ------ | ----------- |----------- |
| Dependency Injection   |CDI | Soring IoC Container
| Persistance |JPA | JPA, JDBC
| Transactions| JTA, EJB3 | JTA, JDBC, JPA
| Presentation framework| JSF | JSF, SPring MVC, Struts
| REST | JSF | Spring MVC REST, ....
| Security |JAX-RS | Spring Security
| Messaging |JMS/EJB 3  | JMS
| Testing |Arquillian | Spring Testing


##### Model-View-Controller (MVC)
 Loosely coupled(independent parts)

- __Model__
  + connection with database, stores data --> __Database__
- __View__
  + pictures, buttons, graphic elements, froms etc..
- __Controller__
  + makes calculation
  + respond to the request from the user, interactions
  + communication with the __Model__


##### The girl as object and the ball

  ```Java
  public class LittleGirl{         // ----->Class name

    private Ball ball = new Ball(); // ----->BALL PRIVATE VARIABLE
    public LittleGirl(){            // ----->Constructor
    }

    public void play(){           // ----->Interaction with the ball
      ball.playWithMe();
    }

    public String getBall(){      // ----->ball getter
      return this.ball;
    }

    public String setBall(Ball ball){ //ball setter
      this.ball = ball,
    }
  }
  ```
We can see, ther is 1 girl with 1 ball. If she leaves the park, she brings her ball too, this is the problem. Next time a second girl comes and bring a new ball.

We cannot use the same, first ball, every time creating a new one in the park. We want 1 ball in the park, and the children come to play, we give that(maybe they get 2 or 3 balls too) and if they finished, they leave the balls in the park. This is the goal.

The ball in the class, and  "Mr Container" is who gives the ball/s depends on the number of children. --> He gives these dependencies, we have less objects(balls) and there is much less place in the HEAP.

#### Inversion of Control (IoC)
- this method above
- we avoid that, more hundreds/thousands objects live in the HEAP
- we can observe how long time ball lives
- we can say to the ball it's lifecycle, change it for a new one in every day

#### Dependency Injection (DI)
- this is the implementation of the IoC

Inversion of Control  ------------>   Food  
Dependency Injection  ----------> Hamburger

#### Give Dependency
- there are 2 ways:

  |  | 1. option | 2. option |
  | ------ | ----------- |----------- |
  | Dependency Injection   | In constructor | In Setter method

1. option(Dependeny in constructor)

```Java
public class LittleGirl{         // ----->Class name

  private Ball ball;  // ->*** no initialization BALL PRIVATE VARIABLE
  public LittleGirl(){            // ----->Constructor
  }

  public LittleGirl(Ball ball){
    this ball = ball;            //***New Constructor with calling this, the object get a available dependency
  }

  public void play(){           // ----->Interaction with the ball
    ball.playWithMe();
  }
}
```

2. option(Dependency in setter)

```Java
public class LittleGirl{         // ----->Class name

  private Ball ball;  // ->*** no initialization BALL PRIVATE VARIABLE
  public LittleGirl(){            // ----->Constructor
  }

  public void play(){           // ----->Interaction with the ball
    ball.playWithMe();
  }

  public String getBall(){      // ----->ball getter
    return this.ball;
  }

  public String setBall(Ball ball){ //***ball setter, external dependency
    this.ball = ball,
  }
}
```

Spring ------------> Springboot  
Hypermarket -----> Online section

#### Advantages of Spring Boot

- fast making start project
- you don't have to know the full Spring
- esaily changing modules
- exact joining point
- it can make an internal running environment
- the final product is a .jar or .war file
- it gives a schema what is modifiable

#### Web application vs Webservice
```
            Webservice   <----------------------------- Database
              |-  receives/sends datas(xml,jason etc.)
              V
Web Application                 |                     |
-webpage, dinamic datas         |                     |
- HTML5 + CSS                   |                     |
        |                       |                     |
        |                       |                     |
        V                       V                     V
     Client 1                Client 2               Client 3
   (PC/browser)            (PC, console)          (smartphone)
```

The important thing, the Webservice asks datas from the Database. __All of clients reach Database accross the Webservice by authentication.__

__Webservice serves clients, web application serves the users.__  


__Webservice__: a service, which can answer xml or jason responses to request. The client/applications uses webservices.

__Client__: interacting with the users. For example IOS, Android

__Spring Boot CLI__: the Spring Boot's tool, where we can make projects.

The Spring Boot Project includes __pom.xml. This file includes the all of dependencies, for example JDPC package, iText package etc__.Based on this, the __Maven__ downloads this and copies to the right place so we don't have to download and copy manually. It is perfect to create __microservices.__

```   


Ordering-Webservice<---Database------>Character-managing webservice
    | xml         |                       |               |xml
    |json         |                       |               |json
    V             V                       V               V
Webapplication   Webapplication       Client 3        Client4         
- HTML5 + CSS    - HTML5 + CSS        (PC)          (smartphone)          
        |             |                                         
        |             |                                          
        V             V                               
     Client 1      Client 2                      
   (PC/browser)    (PC/browser)          
```
SOAP: it is a protocol  
REST: it is not protocol   
Two ot them is a method, we we can ask datas from the server.   
webserbice --->Webapplication

__Loosely coupled:__ they are independent part, the advantage this, we can tast all small part independently. Other advantage, you can manage the business logic: you don't have to share the full model with the team, just certain part.

## Apache Groovy

Apache Groovy is a powerful, optionally typed and dynamic language, with static-typing and static compilation capabilities, for the Java platform aimed at improving developer productivity thanks to a concise, familiar and easy to learn syntax. It integrates smoothly with any Java program, and immediately delivers to your application powerful features, including scripting capabilities, Domain-Specific Language authoring, runtime and compile-time meta-programming and functional programming.

- download
- test it in CMD:
  ```
  groovy -v
  ```
- C:\Program Files\Groovy ---> unpack there
- add groovy-3.0.2 to GROOVY_HOME in system variables

__% in system variables__

- you can use % sign in system variables:
```
%GROOVY_HOME%  ---> this is a reference to the name
```
- test it in CMD:
  ```
  groovy -v
  ```

- path ---> groovy/bin
- check: groovy -v : Groovy Version: 3.0.2 JVM: 1.8.0_231 Vendor: Oracle Corporation OS: Windows 10

Additional (start Tomcat/manager app button/long in/ deploy button--> we can deploy a .war file here
With this process we can unpack the file into a new folder.)

## Maven & Gradle*

- __they are freamework, they can unpack .war .jar etc files__
- download Gradle(https://gradle.org/install/) __Installing manually__
- save it in your programs folder(C:\Program Files\Gradle\gradle-6.2.2)
- system variables:
```
 GRADLE_HOME--> C:\Program Files\Gradle\gradle-6.2.2
 path/ C:\Program Files\Gradle\gradle-6.2.2\bin
```
- check in CMD: gradle -v

## Install Spring Boot CLI

- download(__Manual Installation__, https://spring.io/projects/spring-boot#learn), save it in:
```
C:\Program Files\Spring_Boot\spring-2.1.14.BUILD-SNAPSHOT
```
- add system variables:
```
SPRING_HOME -->C:\Program Files\Spring_Boot\spring-2.1.14.BUILD-SNAPSHOT
path --> %SPRING_HOME%\bin or C:\Program Files\Spring_Boot\spring-2.1.14.BUILD-SNAPSHOT\bin
```
- test it: spring help

## Spring Boot CLI

tutorial: https://docs.spring.io/autorepo/docs/spring-boot/1.3.0.M5/reference/html/cli-using-the-cli.html

- in cmd make a new folder(ElsoGroovy) in C:\Program Files\Spring_Boot --> __right click, add access__
- in this folder, make a new file: hello.groovy

```Java
@RestController
class WebApplication {

  @RequestMapping("/")
  String home() {
      "Hello World!"
  }

}
```
__@RequestMapping("/")__: on the running server, it returns "Hello World" after ("/") address. This address can be modified.  
__@RestController__: it indicates, this is a special class, which can get requests and respond

- in CMD, in ElsoGroovy folder ask Spring to run:
```
spring run hello.groovy
```
Firstly, it downloads every dependency from the internet. After it runs a Tomcat Server.

- try in the browser: localhost:8080

It is more eaisier than creatinga Tomcat Server manually.

- __Changing port in CMD__(additional info)
```
spring run hello.groovy -- --server.port=9000
```

#### We have a Java Server, we don't make structure and configuration files and compile them, the Sring Boot helps us.


## Define the Spring Framework##

- go to start.spring.io
- you add **Maven project** with **Java**,  and **Spring Boot 2.1.4**,  **Artifact: ElsoJava** and click **Web dependency** --> Generate Button
- unpack into Springboot folder as ElsoJava directory
- inside this directory you find: __pom.xml__ open:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.14.BUILD-SNAPSHOT</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>ElsoJava</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>ElsoJava</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
```

It includes the all dependecies to run the Server. You can add more dependencies if you need.
- Spring_Boot folder -->add write access for users
- in Spring boot folder:
```
C:\Program Files\Spring_Boot
spring init
```
- check, there is generated ElsoJavaApplication.java
- we have this structure:
```
C:\Program Files\Spring_Boot\ElsoGroovy
C:\Program Files\Spring_Boot\ElsoJava
C:\Program Files\Spring_Boot\spring-2.1.14.BUILD-SNAPSHOT
C:\Program Files\Spring_Boot\demo.zip
```
- you get demo.zip in the folder, open itm check files

## Maven

- it is packaging framework
- __Maven__ uses __pom.xml__

#### Packaging Systems

- automatize tasks
- get the dependencies
- compile the source code to JVM understands
- creating -jar/.war files
- __Apache Ant, Apache Maven, Gradle__

__Why is important?__ Becuuse your time is more valuable as developer, they help to create server fastly.

```
Maven Central Repository (JDBC etc..)
|
V
Spring Boot Project
```
- open pom.xml
```xml
<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.14.BUILD-SNAPSHOT</version>
		<relativePath/> <!-- lookup parent from repository -->
</parent>
```
We ask Maven, dowlonad the this version from the internet. Maven has a Central Repository where uploaded some releases. The Springboot get a certain code package.

#### Metadatas
__All datas, which describes the project.__

- download Maven bin.zip, unpack--> C:\Program Files
- add access to this folder
- download STS IDE (SPRING TOOL SUITE: https://github.com/spring-projects/toolsuite-distribution/wiki/Spring-Tool-Suite-3)
- add in system variables:
```
MAVEN_HOME --> C:\Program Files\Maven\apache-maven-3.6.3
path --> C:\Program Files\Maven\apache-maven-3.6.3\bin
```
- check Maven:
```
mvn -v
```
- go to :
```
cd C:\Program Files\Spring_Boot\ElsoJava
mvn install
```
- you will find a __target folder__
- in this folder there is __ElsoJava-0.0.1-SNAPSHOT.jar__
- download and install __Eclipse__
- open:
```
C:\Users\Mike\Downloads\sts-bundle\sts-3.9.12.RELEASE
STS.exe
```
- in Eclipse: new/Spring Starter Project:
```
Name: ElsoSpring      /Maven, Jar, Java 8,
Group: com.elsospring
Version: 0.0.1-SNAPSHOT
Description:
Package: com.elsospring
```
Next window:
```
Spring Web
```

- Open ElsoSpringApplication in Eclipse

#### This is our starting point
