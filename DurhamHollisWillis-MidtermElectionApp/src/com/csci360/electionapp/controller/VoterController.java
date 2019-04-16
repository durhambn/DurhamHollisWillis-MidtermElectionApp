package com.csci360.electionapp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.csci360.electionapp.model.Voter;
import com.csci360.electionapp.model.database;
import com.csci360.electionapp.view.VoterView;

public class VoterController {
	
	// View and Model for voter
	// Voter.java
	private Voter model;
	// VoterView.java
	private VoterView view;
	
	/**
	 * 
	 * @param model
	 * @param view
	 */
	public VoterController(Voter model, VoterView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * 
	 * @param firstName
	 */
	public void setVoterFirstName(String firstName) {
		model.setFirstName(firstName);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getVoterFirstName() {
		return model.getFirstName();
	}
	
	/**
	 * 
	 * @param lastName
	 */
	public void setVoterLastName(String lastName) {
		model.setLastName(lastName);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getVoterLastName() {
		return model.getLastName();
	}
	
	/**
	 * 
	 * @param birthday1 - month
	 * @param birthday2 - day
	 * @param birthday3 - year
	 */
	public void setVoterBirthday(String birthday1, String birthday2, String birthday3) {
		model.setBirthday(birthday1, birthday2, birthday3);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getVoterBirthday() {
		return (model.getBirthdayMth() + "/" + model.getBirthdayDay() +"/" + model.getBirthdayYear());
	}
	
	/**
	 * 
	 * @return
	 */
	public String getVoterBirthdayMth() {
		return model.getBirthdayMth();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getVoterBirthdayDay() {
		return model.getBirthdayDay();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getVoterBirthdayYear() {
		return model.getBirthdayYear();
	}
	
	/**
	 * 
	 * @param birthday1 - Day
	 * @param birthday2 - Month
	 * @param birthday3 - Year
	 */
	public void setBirthday(String birthday1, String birthday2, String birthday3) {
		model.setBirthdayMth(birthday1);
		model.setBirthdayDay(birthday2);
		model.setBirthdayYear(birthday3);
	}
	
	/**
	 * 
	 * @param birthday1 - Day
	 */
	public void setBirthdayDay(String birthday1) {
		model.setBirthdayDay(birthday1);
	}
	
	/**
	 * 
	 * @param birthday2
	 */
	public void setBirthdayMth(String birthday2) {
		model.setBirthdayMth(birthday2);
	}
	
	/**
	 * 
	 * @param birthday3
	 */
	public void setBirthdayYear(String birthday3) {
		model.setBirthdayYear(birthday3);
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer getVoterBirthdayMonthNum() {
		int mth = Integer.valueOf(model.getBirthdayMonthNum());
		return mth;
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer getVoterBirthdayDayNum() {
		int day = Integer.valueOf(model.getBirthdayDayNum());
		return day;
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer getVoterBirthdayYearNum() {
		int year = Integer.valueOf(model.getBirthdayYearNum());
		return year;
	}
	
	/**
	 * 
	 * @return
	 */
	public String convertDOB() {
		String DOB = this.getVoterBirthdayYear() + "-" + this.getVoterBirthdayMth() + "-" + this.getVoterBirthdayDay();
		return DOB;
	}
	
	//if they have voted or not
	/**
	 * 
	 * @return
	 */
	public boolean getVoterStatus() {
		return model.getStatus();
	}
	
	//checks if they have registered
	/**
	 * 
	 * @param db
	 * @return
	 * @throws IOException
	 */
	public boolean getRegStatus(database db) throws IOException {
		return model.getRegistrationStatus(db);
	}
	
	//checks if they are over 18
	/**
	 * 
	 * @return
	 */
	public boolean getVoterEligibility() {
		return model.checkEligibility();
	}
	
	/**
	 * 
	 * @param SSN
	 */
	public void setVoterSSN(String SSN) {
		model.setSsn(SSN);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getVoterSSN() {
		return model.getSsn();
	}
	
	/**
	 * 
	 * @param username
	 * @throws SQLException
	 */
	public void setVoterUsername(String username) throws SQLException {
		model.setUsername(getVoterFirstName(), getVoterLastName());
	}
	
	/**
	 * 
	 * @return
	 */
	public String getVoterUsername() {
		return model.getUsername();
	}
	
	/**
	 * 
	 * @param password
	 */
	public void setVoterPassword(String password) {
		model.setPassword(password);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getVoterPassword() {
		return model.getPassword();
	}
	
	/**
	 * 
	 * @return
	 */
	public Voter getVoterProfile() {
		return this.model;
	}
	
	public void updateView() {
		view.printVoterDetails(model.getFirstName(), model.getLastName(), model.getBirthday(), model.getSsn(), model.getPassword(), model.getUsername());
	}
	
	//gets the timestamp of when they registered
	/**
	 * 
	 * @return
	 */
	public String getTime() {
		LocalDateTime a = this.model.getRegDate();
		String regDateString = a.getYear() + "-"  + a.getMonthValue() + "-" + a.getDayOfMonth() + " " + a.getHour() + ":" + a.getMinute() + ":" + a.getSecond();
		return regDateString;
	}
	
	public void setTime(){
		this.model.setRegDate();
	}
}
