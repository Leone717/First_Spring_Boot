# SpringBoot part 5

## Classes, @SpringBootApplication

Our current Person Class:

```Java
package com.elsospring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix ="person")
@Component
public class Person {


	private String message;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
```

-  make Beans in ElsoSpringApplication:
```Java
@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
public class ElsoSpringApplication {

  @Bean(name = "gyula")
	@Profile("dev")
	public Person giveMeADevPerson() {
		return new Person("dev");
	}

	@Bean(name = "gyula")
	@Profile("prod")
	public Person giveMeAProdPerson() {
		return new Person("prod");
	}

	public static void main(String[] args) {
		ApplicationContext ct = SpringApplication.run(ElsoSpringApplication.class, args);
		System.out.println(ct.getBean("gyula"));
		}
	}
}
```

- make toString() in Person with only message parameter and modify the constructor:

```Java
@package com.elsospring;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix ="person")
@Component
public class Person {


	private String message;

	private String name;
	private int age;

	public Person() {

	}

	public Person(String message) {
		super();
		this.message = message;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Person [message=" + message + "]";
	}
}
```
- run, you get: Person [message=dev] because of applcation.properties

#### Auto Configuration
__@ComponentScan:__: it says, where it searches the Beans. It searches in certain packages.  
__@Configuration__: with this the class can write into the "book".  __The class defines components.__   
__@EnableAutoConfiguration__: It signs to SpringBoots, please set everythin independently. (in metafolder, you can find Spring.factory folder)

- right clock on the project, properties and copy the path intp CMD:

```
cd C:\Users\Mike\Documents\workspace-sts-3.9.12.RELEASE\ElsoSpring
mvn clean install
```
- check the folder in file explorer
```
(C:\Users\Mike\Documents\workspace-sts 3.9.12.RELEASE\ElsoSpring\target)
elsospring-0.0.1-SNAPSHOT.jar
```
- REWRITE to elsospring-0.0.1-SNAPSHOT.zip
- open the zip file
- unpack this file
- go into:
```
C:\Users\Mike\Documents\workspace-sts-3.9.12.RELEASE\ElsoSpring\target\elsospring-0.0.1-SNAPSHOT\BOOT-INF\lib
spring-boot-2.2.5.RELEASE.jar
```
- rewerite this to .zip and inside you can find spring.factories and you can find other references inside of this file  
