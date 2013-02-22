package com.igate.dam.smooks.dto;

import java.io.Serializable;


public class Person implements Serializable  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}
	private String firstName="";
	private String lastName="";
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

}
