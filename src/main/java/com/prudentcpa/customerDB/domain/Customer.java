package com.prudentcpa.customerDB.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;

@Region("Customer")
public class Customer{
	
	@Id Long id;
	private String firstName;
	private String lastName;
	
	@PersistenceConstructor
	public Customer(Long id, String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	@Override
	public String toString() {
		return String.format("First Name:%s, Last Name:%s", this.firstName, this.lastName);
	}
}

