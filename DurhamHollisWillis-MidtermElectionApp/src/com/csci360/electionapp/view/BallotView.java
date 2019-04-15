package com.csci360.electionapp.view;

import com.csci360.electionapp.model.Ballot;

public class BallotView {
	public void printBallotDetails(Ballot b) {
		System.out.println("Category 1 Vote: "+ b.getCat1Results());
		System.out.println("Category 2 Vote: " + b.getCat2Results());
		System.out.println("Category 3 Vote: " + b.getCat3Results());
		System.out.println("Category 4 Vote: " + b.getCat4Results());
	}
	
	
	//Do stuff with database stuff...???
}
