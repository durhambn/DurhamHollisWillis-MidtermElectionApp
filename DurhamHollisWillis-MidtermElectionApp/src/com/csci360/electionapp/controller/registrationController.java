package com.csci360.electionapp.controller;

import com.csci360.electionapp.model.Voter;
import com.csci360.electionapp.view.*;

public class registrationController {

	/** private String firstName;
	private String lastName;
	private String birthday;
	private String ssn;
	private String password; **/
	private Voter v;
	
	public registrationController() {
		
	}
	
	public static VoterController createVoter(String firstName, String lastName, String birthday, String ssn, String password) {
		Voter v = new Voter(firstName, lastName, birthday, ssn, password);
		VoterView view = new VoterView();
		VoterController controller = new VoterController(v, view);
		return controller;
	}
	
}
