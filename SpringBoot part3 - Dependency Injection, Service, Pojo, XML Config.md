# Springboot part 3

## Dependency Injection, Service, Pojo, XML Config

1. solution - __@Autowired__ simple injection

```Java
package com.elsospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
```Java
@RestController
public class HomeController {
	@Autowired
	private SpyGirl spicy = new SpyGirl();

	@RequestMapping("/")
	public String index() {
		return "Sziasztok Lányok, Fiúk";
	}
}
```
With @Autowired helps, Spring make Controller and inject the object into. This can be session scope or prototype too.

Disadvantage is hard to test.

2. solution - __setter__ injection

```Java
package com.elsospring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	private SpyGirl spicy = new SpyGirl();

	@Autowired
	public void setSpicy(SpyGirl spicy) {
		this.spicy = spicy;
	}

	@RequestMapping("/")
	public String index() {
		return "Sziasztok Lányok, Fiúk";
	}
}
```
3. soltuion - __constructor__ injection

```Java
package com.elsospring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	private SpyGirl spicy;

	@Autowired
	public HomeController(SpyGirl spicy) {
		this.spicy = spicy;
	}

	@RequestMapping("/")
	public String index() {
		return spicy.iSaySomething();
	}
}
```

## Service layer

- delete SpyGirl from HomeController:

```Java
package com.elsospring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


	@RequestMapping("/")
	public String index() {
		return "Bármi";
	}
}
```


We made a databox as "ct" in ElsoSpringApplication. We know "Mr. Container" have a list abount __Beans__ and we read this list.

```Java
package com.elsospring;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
public class ElsoSpringApplication {

	public static void main(String[] args) {
		ApplicationContext ct = SpringApplication.run(ElsoSpringApplication.class, args);

		String[] beanArray = ct.getBeanDefinitionNames();
		Arrays.parallelSort(beanArray);

		for(String name : beanArray){
			System.out.println(name);
		}
	}
}
```
- run the program, we found the beans list:
```
homeController  
httpRequestHandlerAdapter  
parameterNamesModule  
preserveErrorControllerTargetClassPostProcessor  
propertySourcesPlaceholderConfigurer  
requestContextFilter
requestMappingHandlerAdapter
requestMappingHandlerMapping
resourceHandlerMapping
restTemplateBuilder
restartingClassPathChangedEventListener
routerFunctionMapping
server-org.springframework.boot.autoconfigure.web.ServerProperties
servletWebServerFactoryCustomizer
simpleControllerHandlerAdapter
spring.devtools
standardJacksonObjectMapperBuilderCustomizer
stringHttpMessageConverter
taskExecutorBuilder
taskSchedulerBuilder
tomcatServletWebServerFactory
tomcatServletWebServerFactoryCustomizer
tomcatWebServerFactoryCustomizer
viewControllerHandlerMapping
viewResolver
webServerFactoryCustomizerBeanPostProcessor
websocketServletWebServerCustomizer
welcomePageHandlerMapping
```

**@ComponentScan**: it observes the all classes in the package, searching annotations. Because of this we find this list above.
**@Configuration**: It signs, certain class is the information source of Bean classes. From this class the context of the "Mr. Continer"'s book.

- make a new package as __com.spy__ and cut into the SpyGirl
- restart the application, we won't find spygirl on the list becuase there is in independent package
- __after restarting, tehere is no spygirl on the list, becuase Mr. Container has no access for this package__
- we can reach this package, if __sign, this is a bean__

Eclipse/Window/Preferences/Text editors/Keys --> unbind alt + b to use "{ in the text editor

- cut back the spygirl in the first package, delete the new one
- __rename SpyGirl as SpyService__

##Spring Boot structure
```
  View
  - HTML5 + CSS + JS

            Controller
            - traffic manager

                        Service
                        - calculations
                        - business logics
                                            Model
                                            - storing datas
```
- modify:

```Java
package com.elsospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	private SpyService spyService;

	@Autowired
	public void setSpyService(SpyService spyService) {
		this.spyService = spyService;
	}

	@RequestMapping("/")
	public String index() {
		return spyService.iSaySomething();
	}
}
```
HomeController -->@RestController  --> **traffic manager**  
SpyService --> @Service --> **calculations, business logics**

We can add a name to @Service:
```Java
package com.elsospring;

import org.springframework.stereotype.Service;

@Service("Spying")
public class SpyService {

	public String iSaySomething(){
	return "Én egy kém vagyok";
	}
}
```
- start application, Spying is the first element of the list because of the Capital letter

__Rule__  
We store Service and Controller in independent packages. However the @ComponentScan in the @SpringBootApplication search components in certain package, we make a subpackage.

- make new packages in src/main/java and make put them classes to be separate:
```
com.elsospring --> ElsoSpringApplication.java
com.elsospring.controller --> HomeController.java
com.elsospring.service --> SpyService.java
```

#### Pojo

- make Person class in com.elsospring:

```Java
package com.elsospring;

public class Person {
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
}
```
The Person class doesn't have annotation so __SpringBoot doesn't see.__  
If we add annotation, that is __injection.__

- we serach a class, where is __@Configuration__:

```Java
package com.elsospring;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
public class ElsoSpringApplication {

	@Bean
	public Person giveMeAPerson() {
		return new Person("Gyula", 20);
	}
	public static void main(String[] args) {
		ApplicationContext ct = SpringApplication.run(ElsoSpringApplication.class, args);

		String[] beanArray = ct.getBeanDefinitionNames();
		Arrays.parallelSort(beanArray);

		for(String name : beanArray){
			System.out.println(name);
		}
	}
}
```
This means, this class is __Bean source(by @Configuration). Becuse of this, I can say, I define a bean by @Bean annotation. This give back a Person as bean.__ This is not simple Person class!

__We added name and age for the Person. If I run, I will find giveMeAPerson in the list because of the @Bean annotation.__

#### XML Annotations(older mode for dependency injection)

- we store dependencies in xml file
- https://www.tutorialspoint.com/spring/spring_applicationcontext_container.htm
- it is good to know
- it doesn't ask "Bean list" but also "helloworld" bean. It saves in an object and with this use getMessage() method

```xml
<?xml version = "1.0" encoding = "UTF-8"?>

<beans xmlns = "http://www.springframework.org/schema/beans"
   xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation = "http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

   <bean id = "helloWorld" class = "com.tutorialspoint.HelloWorld">
      <property name = "message" value = "Hello World!"/>
   </bean>

</beans>
```
We crate a bean tag and what is the it's id and which class includes.  It can give a property name which means in a superclass there is a name variable and you ask, while injection it does with this name and value. Sometimes the xml solution is easier to test.
