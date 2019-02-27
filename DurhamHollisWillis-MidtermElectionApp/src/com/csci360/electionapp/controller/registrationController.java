package com.csci360.electionapp.controller;

import com.csci360.electionapp.model.Voter;
import com.csci360.electionapp.view.*;

/**
 * 
 * Controller class for Registration
 * button of application
 *
 */
public class registrationController {
	// Voter object that will be created
	private Voter v;
	
	// ??? what goes here.
	public registrationController() {
		
	}
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param birthday
	 * @param ssn
	 * @param password
	 * @return the controller
	 * 
	 */
	public static VoterController createVoter(String firstName, String lastName, String birthday, String ssn, String password) {
		// Creates a new voter object
		Voter v = new Voter(firstName, lastName, birthday, ssn, password);
		
		// Creates a new voter view object
		VoterView view = new VoterView();
		
		// Creates a new voter controller object with access to the
		// voter object itself and a way to view the voter data.
		VoterController controller = new VoterController(v, view);
		return controller;
	}
	
}
