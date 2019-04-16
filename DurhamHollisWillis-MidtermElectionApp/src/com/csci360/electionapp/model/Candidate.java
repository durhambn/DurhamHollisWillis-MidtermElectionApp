package com.csci360.electionapp.model;

public class Candidate {

	private String firstName;
	private String lastName;
	private String category;
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param category
	 */
	public Candidate(String firstName, String lastName, String category) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setCategory(category);
	}

	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		String name = this.getFirstName() +  " " + this.getLastName();
		return name;
	}

	/**
	 * 
	 * @return
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}
}