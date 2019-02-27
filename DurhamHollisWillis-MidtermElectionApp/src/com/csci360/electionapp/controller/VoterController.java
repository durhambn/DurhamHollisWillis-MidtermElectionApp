package com.csci360.electionapp.controller;

import com.csci360.electionapp.model.Voter;
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
	
	public void setVoterBirthday(String birthday) {
		model.setBirthday(birthday);
	}
	
	public String getVoterBirthday() {
		return model.getBirthday();
	}
	
	public void setVoterSSN(String SSN) {
		model.setSsn(SSN);
	}
	
	public String getVoterSSN() {
		return model.getSsn();
	}
	
	public void setVoterUsername(String username) {
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
	
	public void updateView() {
		view.printVoterDetails(model.getFirstName(), model.getLastName(), model.getBirthday(), model.getSsn(), model.getPassword(), model.getUsername());
	}
}
