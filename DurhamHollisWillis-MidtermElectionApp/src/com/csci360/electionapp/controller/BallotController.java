package com.csci360.electionapp.controller;

import com.csci360.electionapp.model.Ballot;
import com.csci360.electionapp.view.BallotView;

public class BallotController {

	private Ballot model;
	private BallotView view;
	
	public BallotController(Ballot model, BallotView view) {
		this.model = model;
		this.view = view;
	}
	
	public int getBallotNumCategories() {
		return model.getNumCategories();
	}
	
	public void setBallotNumCategories(int numCategories) {
		model.setNumCategories(numCategories);
	}
	
	public String getBallotCategory1() {
		return model.getCategory1();
	}
	
	public void setBallotCategory1(String Category1) {
		model.setCategory1(Category1);
	}
	
	public String getBallotCategory2() {
		return model.getCategory2();
	}
	
	public void setBallotCategory2(String Category2) {
		model.setCategory2(Category2);
	}
	
	public String getBallotCategory3() {
		return model.getCategory3();
	}
	
	public void setBallotCategory3(String Category3) {
		model.setCategory3(Category3);
	}
	
	public String getBallotCategory4() {
		return model.getCategory4();
	}
	
	public void setBallotCategory4(String Category4) {
		model.setCategory4(Category4);
	}
	
	public String getBallotCandidate1() {
		return model.getCandidate1();
	}
	
	public void setBallotCandidate1(String Candidate1) {
		model.setCandidate1(Candidate1);
	}
	
	public String getBallotCandidate2() {
		return model.getCandidate2();
	}
	
	public void setBallotCandidate2(String Candidate2) {
		model.setCandidate2(Candidate2);
	}
	
	public String getBallotCandidate3() {
		return model.getCandidate3();
	}
	
	public void setBallotCandidate3(String Candidate3) {
		model.setCandidate3(Candidate3);
	}
	
	public String getBallotCandidate4() {
		return model.getCandidate4();
	}
	
	public void setBallotCandidate4(String Candidate4) {
		model.setCandidate4(Candidate4);
	}
	
	public String getBallotCandidate5() {
		return model.getCandidate5();
	}
	
	public void setBallotCandidate5(String Candidate5) {
		model.setCandidate5(Candidate5);
	}
	
	public String getBallotCandidate6() {
		return model.getCandidate6();
	}
	
	public void setBallotCandidate6(String Candidate6) {
		model.setCandidate6(Candidate6);
	}
	
	public String getBallotCandidate7() {
		return model.getCandidate7();
	}
	
	public void setBallotCandidate7(String Candidate7) {
		model.setCandidate7(Candidate7);
	}
	
	public String getBallotCandidate8() {
		return model.getCandidate8();
	}
	
	public void setBallotCandidate8(String Candidate8) {
		model.setCandidate8(Candidate8);
	}
	
	public String getBallotCandidate9() {
		return model.getCandidate9();
	}
	
	public void setBallotCandidate9(String Candidate9) {
		model.setCandidate9(Candidate9);
	}
	
	public String getBallotCandidate10() {
		return model.getCandidate10();
	}
	
	public void setBallotCandidate10(String Candidate10) {
		model.setCandidate10(Candidate10);
	}
	
	public String getBallotCandidate11() {
		return model.getCandidate11();
	}
	
	public void setBallotCandidate11(String Candidate11) {
		model.setCandidate11(Candidate11);
	}
	
	public String getBallotCandidate12() {
		return model.getCandidate12();
	}
	
	public void setBallotCandidate12(String Candidate12) {
		model.setCandidate12(Candidate12);
	}
	
	public boolean isCand1() {
		return model.isVoteCand1();
	}

	public void setCand1(boolean voteCand1) {
		model.setVoteCand1(voteCand1);
	}

	public boolean isCand2() {
		return model.isVoteCand12();
	}

	public void setCand2(boolean voteCand2) {
		model.setVoteCand2(voteCand2);
	}

	public boolean isCand3() {
		return model.isVoteCand3();
	}

	public void setCand3(boolean voteCand3) {
		model.setVoteCand3(voteCand3);
	}

	public boolean isCand4() {
		return model.isVoteCand4();
	}

	public void setCand4(boolean voteCand4) {
		model.setVoteCand4(voteCand4);
	}

	public boolean isCand5() {
		return model.isVoteCand5();
	}

	public void setCand5(boolean voteCand5) {
		model.setVoteCand5(voteCand5);
	}

	public boolean isCand6() {
		return model.isVoteCand5();
	}

	public void setCand6(boolean voteCand6) {
		model.setVoteCand6(voteCand6);
	}

	public boolean isCand7() {
		return model.isVoteCand7();
	}

	public void setCand7(boolean voteCand7) {
		model.setVoteCand7(voteCand7);
	}

	public boolean isCand8() {
		return model.isVoteCand8();
	}

	public void setCand8(boolean voteCand8) {
		model.setVoteCand8(voteCand8);
	}

	public boolean isCand9() {
		return model.isVoteCand9();
	}

	public void setCand9(boolean voteCand9) {
		model.setVoteCand9(voteCand9);
	}

	public boolean isCand10() {
		return model.isVoteCand10();
	}

	public void setCand10(boolean voteCand10) {
		model.setVoteCand10(voteCand10);
	}

	public boolean isCand11() {
		return model.isVoteCand11();
	}

	public void setCand11(boolean voteCand11) {
		model.setVoteCand11(voteCand11);
	}

	public boolean isCand12() {
		return model.isVoteCand12();
	}

	public void setVoteCand12(boolean voteCand12) {
		model.setVoteCand12(voteCand12);
	}
}
