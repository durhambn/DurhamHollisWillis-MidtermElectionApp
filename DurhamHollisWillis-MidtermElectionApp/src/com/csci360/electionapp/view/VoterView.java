package com.csci360.electionapp.view;

public class VoterView {
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param birthday
	 * @param ssn
	 * @param password
	 * @param username
	 */
	public void printVoterDetails(String firstName, String lastName, String birthday, String ssn, String password, String username) {
		System.out.println("Voter:");
		System.out.println("First Name: " + firstName);
		System.out.println("Last Name: " + lastName);
		System.out.println("Birthday: " + birthday);
		System.out.println("SSN: " + ssn);
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
	}
}