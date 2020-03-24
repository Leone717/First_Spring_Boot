# SpringBoot Part 4

## External configuration (application.properties), Annotations

- we check __application.properties__
- __porperty file: they are important, because we don't code fix, important datas. It includes database access, username, password.__
- it can be __config.conf, properties.prop, xy.info__ etc.
- independent part of the application, we can change code but we don't have to modify this file
- we can modify password, username but you don't have to deal with Java code  
- you have to hide in the structure, user don't see
- the application searching this file:

#### External Configurational Hiearchy(from top to bottom)

Terminal parameters   
JNDI attributes (java:comp/env)   
Java System properties(System.getProprties())  
OS environemnt variables  
Profile based configuration   
Apllication.proprties(besides .java file)  
Application.proprties(inside of the .jar file)

- open the file, __Application.proprties__:

```
server.port = 9001

common.database.username = admin
common.database.password = password
commond.database.url = 127.0.0.1

common.emailserver.url = 127.0.0.1
```

- create __application.yaml__ in rsc/main/resources:
```
common:
    database: admin
      username: admin
      password: password
      url: 127.0.0.1.
    emailserver:
      url: 127.0.01
```
- you have to choose one, because SpringBoot mix them if there are 2 configuration files (delete yaml),
- Application.proprties is stronger
- delete the reading list from ElsoSpringApplication:
```Java
String[] beanArray = ct.getBeanDefinitionNames();
		Arrays.parallelSort(beanArray);

		for(String name : beanArray){
			System.out.println(name);
		}
```
- check: localhost:9001
- start __other server with different port__ in CMD:

```
cd C:\Users\user\Documents\workspace-sts-3.9.12.RELEASE\ElsoSpring
mvn package
cd target
-java -jar Dserver.port=8090 elsospring-0.0.1-SNAPSHOT.jar
```
Console is stronger.

- add @Value in HomeController:

```Java
package com.elsospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elsospring.service.SpyService;

@RestController
public class HomeController {

	private SpyService spyService;

  @Value("${HomeController.msg}"))
	private String message;

	@Autowired
	public void setSpyService(SpyService spyService) {
		this.spyService = spyService;
	}

	@RequestMapping("/")
	public String index() {
		return message;
	}
}
```
- in application.proprties:

```
server.port = 9001

common.database.username = admin
common.database.password = password
commond.database.url = 127.0.0.1

common.emailserver.url = 127.0.0.1

HomeController.msg = ${random.value}
```
- run the server:  message in the browser

#### Group External Configuration

- rewrite Person.class:

```Java
package com.elsospring;

public class Person {
private String firstName;
private String lastName;
private int age;
private String address;
private String phoneNumber;

public Person() {

}

public String getFirstName() {
  return firstName;
}

public void setFirstName(String firstName) {
  this.firstName = firstName;
}

public String getLastName() {
  return lastName;
}

public void setLastName(String lastName) {
  this.lastName = lastName;
}

public int getAge() {
  return age;
}

public void setAge(int age) {
  this.age = age;
}

public String getAddress() {
  return address;
}

public void setAddress(String address) {
  this.address = address;
}

public String getPhoneNumber() {
  return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
  this.phoneNumber = phoneNumber;
}

public Person(String firstName, String lastName, int age, String address, String phoneNumber) {
  super();
  this.firstName = firstName;
  this.lastName = lastName;
  this.age = age;
  this.address = address;
  this.phoneNumber = phoneNumber;
}

}
```
- rewrite application.properties:

```
server.port = 9001

common.database.username = admin
common.database.password = password
commond.database.url = 127.0.0.1

common.emailserver.url = 127.0.0.1

HomeController.msg = ${random.int[0,100]}

person.first-name=Gyula       /
person.lastName=Nagy
person.age=21
person.address=Budapest
person.phooneNumber=00000000
```
We added pojo parameteres, class and __"."__ and variablest.
- we add __@Component__ to know Pojo in prop file:

```Java
@Component
public class Person {
	private String firstName;
}
```
- __delete the @Bean section__:

```Java
public class ElsoSpringApplication {

	@Bean
	public Person giveMeAPerson() {
		return new Person("Gyula", 20);
	}
}
  ```

  We allow @EnableAutoConfigurationProperties in ElsoSpringApplication to use the different files. __With this, we can link class variables with datas of application.properties.__

  This class observes the variables beginning with **"person"(prefix)**:

```Java
  @ConfigurationProperties(prefix = "person")
  @Component
  public class ElsoSpringApplication {

  	public static void main(String[] args) {
  		ApplicationContext ct = SpringApplication.run(ElsoSpringApplication.class, args);
  	}
  }
  ```

We add a dependency to pom.xml file(https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-configuration-processor/2.2.5.RELEASE):
```xml
<artifactId>spring-boot-devtools</artifactId>
<version>2.2.5.RELEASE</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-configuration-processor -->
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-configuration-processor</artifactId>
<version>2.2.5.RELEASE</version>
</dependency>
```
```Java
public class ElsoSpringApplication {

	public static void main(String[] args) {
		ApplicationContext ct = SpringApplication.run(ElsoSpringApplication.class, args);
		System.out.println(ct.getBean("person"));
	}
}
```

- Person.class-->right click/Source/Generate toString().../all Fields
- add Override
```Java
@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + "]";
	}
```
- change annotations:
```Java
@SpringBootApplication
//@Configuration
@EnableConfigurationProperties//***We join Person variables to application.proprties datas
//@ComponentScan
//@ConfigurationProperties
//@Component
public class ElsoSpringApplication {
}
```

```Java
@ConfigurationProperties(prefix ="person")//**observe peron variables
@Component
public class Person{}
```

__@EnableConfigurationProperties__//We join Person variables to application.proprties datas -->__@ConfigurationProperties__(prefix ="person")//observe peron variables

- run the project you see in the console:

```
2020-03-23 00:06:54.894  INFO 37276 --- [  restartedMain] com.elsospring.ElsoSpringApplication     : Started ElsoSpringApplication in 1.265 seconds (JVM running for 1.74)
Person [firstName=Gyula, lastName=Gyula haerh ae , age=21, address=Budapest, phoneNumber=null]
```
### Profiles

Imagine that, there is a game in the PC. If you playing and stop, and your friend comes after you to play, he won't continue your game ha start from the beginning. So this depends on the profile.

- make a new profile in application.proprties:

```
server.port = 9001

spring.profiles.active = dev
```
- after join this profile in Person.java:

```Java
package com.elsospring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix ="person")
@Component
public class Person {

	@Value("${spring.profiles.active}")
	private String selectedProfile;

	private String name;
	private int age;

	public Person() {

	}

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSelectedProfile() {
		return selectedProfile;
	}

	public void setSelectedProfile(String selectedProfile) {
		this.selectedProfile = selectedProfile;
	}
}
```
```
@Value("${spring.profiles.active}")
private String selectedProfile;
```
#### Tthe selectedProfile value gets the value from the application.properties.

- make new 2 flies in resources folder:
```
application-dev.properties
msg= Szia a DEV profilból!
application-prod.properties
msg=Szia a PROD profilból!
```

We told to Spring, the acitve profile is the dev profile. I have to make a getter and setter (valuse=msg) if I would like to inject this into the Person. (check application.properties)

- modify in the Person:

```Java
@Value("${spring.profiles.active}")
private String selectedProfile;

@Value("${msg}")
private String message;
```
__The selectedProfile gets the value of spring.profiles.active from application.proprties.__

- add getters and setters for the message
- make a toString() method with selectedProfile and message
- ElsoSpringApplication.java:

```Java
package com.elsospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@Configuration
//@ConfigurationProperties
//@ComponentScan
public class ElsoSpringApplication {

	public static void main(String[] args) {
		ApplicationContext ct = SpringApplication.run(ElsoSpringApplication.class, args);
		System.out.println(ct.getBean("person"));

	}

}
```
- delete 2 lines in HomeController:
```Java
@RestController
public class HomeController {

	private String message;

	@Autowired
	public void setSpyService(SpyService spyService) {
	}

	@RequestMapping("/")
	public String index() {
		return message;
	}
}
```


- run the application
- run applicaton by right __click/run as/run configurations -->profile --> dev/prod__ here you can test different profiles, here arguments you can add arguments
- delete this part from Pojo:

```Java
@Value("${spring.profiles.active}")
	private String selectedProfile;

@Value("${msg}")
```
