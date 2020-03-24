# Springboot part 2
## Use Springboot, Setting dependecies,  @Scopes, DevTools

We are in ElsoSpringApplication.java in Eclipse:

```Java
package com.elsospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElsoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElsoSpringApplication.class, args);
	}

}
```
This is our starting point. Firstly, the main method runs. We can find in the code an annotation, we will learn how can we make special Java blocks for SpringBoot.

__@SpringBootApplication__: it shows, this is a SpringBoot Application

### Dircetory structure of our ElsoSpring Project

```
ElsoSrping
    src/main/Java
        ElsoSpringApplication.java
    src/main/resources
        static
        templates
        application.properties
    ...
    JRE System Library
        ...
        servlet-api.jar
        ...
    pom.xml
    ...

```
__static__: we put here videos, pictures, mp3 and fix files, we won't modify them
__templates__: we put pages, html-s  
__application.properties__: when you start application, it gets parameters (port numbers, heap sizes etc.) from this file

__pon.xml__
- you find here the dependencies etc.
- you find a lot of dependencies in Maven

Spring boot has hierarchical strcture. It includes more directories, dependencie and a chain. These codes call each other and help you to code Java.

#### Run SpringBoot
- Elsospring right click/properties/copy directory in Eclipse  
- in CMD:
```
cd C:\Users\Mike\Documents\workspace-sts-3.9.12.RELEASE\ElsoSpring
mvn install
cd C:\Users\Mike\Documents\workspace-sts-3.9.12.RELEASE\ElsoSpring\target
java -jar elsospring-0.0.1-SNAPSHOT.jar
```

#### RestController

ElsoSpring/pom.xml:
```xml
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>

  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
    <exclusions>
      <exclusion>
        <groupId>org.junit.vintage</groupId>
        <artifactId>junit-vintage-engine</artifactId>
      </exclusion>
    </exclusions>
  </dependency>
</dependencies>
```
You can add here depedencies.

__@SpringBootApplication:  
@Configuration + @EnableAutoConfiguration + @ComponentScan__

- make a new Class as __HomeController__
- you shold know, after creating a new class you want to add annotation there for Sring
- add annotation:

```Java
package com.elsospring;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
}
```
Rescontroller as mailbox, we can add and take out messages.__The SpringBoot entitles the RestController's class to receive requests and answer.__:

```Java
package com.elsospring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@RequestMapping("/")
	public String index() {
		return "Szia Gyula";		
	}
}
```

With the **2. annotation(@RestController)** Springboot observes the "/" address and **1. annotation(@RestController)** it will be able to accept and send requests.

- run as Spring Boot App in Eclipse
- check localhost:8080, it works

However I __change__ the return statement:
```Java
return "Szia Gizi néni";
```
**The message won't change just if you Relaunch the ElsoSpring.**

#### Adding new dependency(DevTools)

- stop server, close Windows
- if you create new project you can add dependency (DevTools) in the Windows
- at our already existing project, we add the code from internet into __pom.xml__
- search google: devtools maven dependency(https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools/1.3.0.RELEASE)
- copy and paste here in pom.xml:

```xml
<scope>test</scope>
<exclusions>
  <exclusion>
    <groupId>org.junit.vintage</groupId>
    <artifactId>junit-vintage-engine</artifactId>
  </exclusion>
</exclusions>
</dependency>

<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools -->
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-devtools</artifactId>
<version>2.2.5.RELEASE</version>
</dependency>
</dependencies>
```
- run as Sring Boot App
- change return statement for: return "Szia Pali bácsi" and **save**

__DevTolls__: save time for you. After saving, restart the server for you. It knows this is developer environment, doesn't restart the Server in production environment.

__Integrated Development Enviroment__:  it notices if other developer uploaded a new code. For exmaple with Maven, it rebould itself.

#### Spring Bean Scopes

Next time if we write new clasess, we add to "Mr. Container"(fantasy name). Mr. Container has a book where follow, which object has to folow. __He knows, how make objects, observe them and destroy them.__

```Java
@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
public class ElsoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElsoSpringApplication.class, args);
	}
}
```
#### ApplicationContext = "Mr. Container"

__Spring Bean Scope: It determines the lifecycle of SpringBeans.__

- **singleton** - one instance
- **prototype** - always new instance
- **request** - new instance/request
- **session** - new instance/session - It **knows the user** so doesn't generate new instance if the user asks again.
- **globalSession**     

#### @Scope
- Create new class as SpyGirl
- with @Scope Mr. Container knows if somebody ask SpyGirl class, this request coming from the same user. If a new alien asks, it give back an other object.

```Java
package com.elsospring;

import org.springframework.context.annotation.Scope;

@Scope("session")
public class SpyGirl {
}
```

Here we can change the address: (we stay at the original, "/")

```Java
public class HomeController {
	@RequestMapping("/")
	public String index(){
  }
}
```

The controller receives the request and Spygirl has a method which can give back a String:

```Java
package com.elsospring;

import org.springframework.context.annotation.Scope;

@Scope("session")
public class SpyGirl {

	public String isaySomething(){
	return "Én egy kém vagyok";
	}
}
```
- make a new class variable as spicy:

```Java
package com.elsospring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	private SpyGirl spicy = new SpyGirl();

	@RequestMapping("/")
	public String index() {
		return "Sziasztok Lányok, Fiúk";
	}
}
```

__This is Title Coupled.__ Every time when you make Controller, it makes a new SpyGirl.

Becuase of we made Controller once time, and it is __singleton__, the spygirl exists once time. It doesn't work the Session. __No Dependency Injection"__
