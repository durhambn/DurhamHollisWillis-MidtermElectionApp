package com.csci360.electionapp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.csci360.electionapp.model.Voter;
import com.csci360.electionapp.model.database;
import com.csci360.electionapp.view.VoterView;

public class VoterController {

	private Voter model;
	private VoterView view;
	
	public VoterController(Voter model, VoterView view) {
		this.model = model;
		this.view = view;
	}
	
	public void setVoterFirstName(String firstName) {
		model.setFirstName(firstName);
	}
	
	public String getVoterFirstName() {
		return model.getFirstName();
	}
	
	public void setVoterLastName(String lastName) {
		model.setLastName(lastName);
	}
	
	public String getVoterLastName() {
		return model.getLastName();
	}
	
	public void setVoterBirthday(String birthday1, String birthday2, String birthday3) {
		model.setBirthday(birthday1, birthday2, birthday3);
	}
	
	public String getVoterBirthday() {
		return (model.getBirthdayMth() + "/" + model.getBirthdayDay() +"/" + model.getBirthdayYear());
	}
	
	public String getVoterBirthdayMth() {
		return model.getBirthdayMth();
	}
	
	public String getVoterBirthdayDay() {
		return model.getBirthdayDay();
	}
	
	public String getVoterBirthdayYear() {
		return model.getBirthdayYear();
	}
	
	public void setBirthday(String birthday2, String birthday3, String birthday4) {
		model.setBirthdayMth(birthday2);
		model.setBirthdayDay(birthday3);
		model.setBirthdayYear(birthday4);
	}
	
	public void setBirthdayDay(String birthday2) {
		model.setBirthdayDay(birthday2);
	}
	
	public void setBirthdayMth(String birthday3) {
		model.setBirthdayMth(birthday3);
	}
	
	public void setBirthdayYear(String birthday4) {
		model.setBirthdayYear(birthday4);
	}
	
	public Integer getVoterBirthdayMonthNum() {
		int mth = Integer.valueOf(model.getBirthdayMonthNum());
		System.out.println(mth);
		return mth;
	}
	public Integer getVoterBirthdayDayNum() {
		int day = Integer.valueOf(model.getBirthdayDayNum());
		System.out.println(day);
		return day;
	}
	public Integer getVoterBirthdayYearNum() {
		int year = Integer.valueOf(model.getBirthdayYearNum());
		System.out.println(year);
		return year;
	}
	
	public String convertDOB() {
		String DOB = this.getVoterBirthdayYear() + "-" + this.getVoterBirthdayMth() + "-" + this.getVoterBirthdayDay();
		return DOB;
	}
	
	public boolean getVoterStatus() {
		return model.getStatus();
	}
	
	public boolean getRegStatus(database db) throws IOException {
		return model.getRegistrationStatus(db);
	}
	
	public boolean getVoterEligibility() {
		return model.checkEligibility();
	}
	
	public void setVoterSSN(String SSN) {
		model.setSsn(SSN);
	}
	
	public String getVoterSSN() {
		return model.getSsn();
	}
	
	public void setVoterUsername(String username) throws SQLException {
		model.setUsername(getVoterFirstName(), getVoterLastName());
	}
	
	public String getVoterUsername() {
		return model.getUsername();
	}
	
	public void setVoterPassword(String password) {
		model.setPassword(password);
	}
	
	public String getVoterPassword() {
		return model.getPassword();
	}
	
	public Voter getVoterProfile() {
		return this.model;
	}
	
	public void updateView() {
		view.printVoterDetails(model.getFirstName(), model.getLastName(), model.getBirthday(), model.getSsn(), model.getPassword(), model.getUsername());
	}
	public String getTime() {
		LocalDateTime a = this.model.getRegDate();
		String regDateString = a.getYear() + "-"  + a.getMonthValue() + "-" + a.getDayOfMonth() + " " + a.getHour() + ":" + a.getMinute() + ":" + a.getSecond();
		return regDateString;
	}
	public void setTime(){
		this.model.setRegDate();
	}
}
