package com.csci360.electionapp.model;

public class Voter {
	
	private String firstName;
	private String lastName;
	private String birthday;
	private String ssn;
	private String password;
	private String username;
	
	public Voter(String firstName, String lastName, String birthday, String ssn, String password) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthday(birthday);
		this.setSsn(ssn);
		this.setPassword(password);
		this.setUsername(firstName, lastName);
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
	
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsername(String firstName, String lastName) {
		this.username = lastName + firstName.charAt(0);
	}
	
	public String getUsername() {
		return username;
	}
}
