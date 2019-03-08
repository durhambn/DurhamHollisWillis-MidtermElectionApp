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
}
