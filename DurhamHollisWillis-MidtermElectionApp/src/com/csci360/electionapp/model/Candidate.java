package com.csci360.electionapp.model;

public class Candidate {

	private String firstName;
	private String lastName;
	private String category;
	
	public Candidate(String firstName, String lastName, String category) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setCategory(category);
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
	
	public String getName() {
		String name = this.getFirstName() +  " " + this.getLastName();
		return name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
