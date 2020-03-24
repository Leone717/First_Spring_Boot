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