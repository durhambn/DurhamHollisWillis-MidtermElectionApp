package com.csci360.electionapp.model;

public class Ballot {
	
	// There are four categories.
	private int numCategories = 4;
	
	// Get categories from database maybe?
	private String Category1;
	private String Category2;
	private String Category3;
	private String Category4;
	
	// Get Candidates from database maybe?
	private String Candidate1;
	private String Candidate2;
	private String Candidate3;
	private String Candidate4;
	private String Candidate5;
	private String Candidate6;
	private String Candidate7;
	private String Candidate8;
	private String Candidate9;
	private String Candidate10;
	private String Candidate11;
	private String Candidate12;
	
	public Ballot(int numCategories, String Category1, String Category2, String Category3, 
			String Category4, String Candidate1, String Candidate2, String Candidate3, 
			String Candidate4, String Candidate5, String Candidate6, String Candidate7, 
			String Candidate8, String Candidate9, String Candidate10, String Candidate11, 
			String Candidate12) {
		
		this.numCategories = numCategories;
		
		this.Category1 = Category1;
		this.Category2 = Category2;
		this.Category3 = Category3;
		this.Category4 = Category4;
		
		this.Candidate1 = Candidate1;
		this.Candidate2 = Candidate2;
		this.Candidate3 = Candidate3;
		this.Candidate4 = Candidate4;
		this.Candidate5 = Candidate5;
		this.Candidate6 = Candidate6;
		this.Candidate7 = Candidate7;
		this.Candidate8 = Candidate8;
		this.Candidate9 = Candidate9;
		this.Candidate10 = Candidate10;
		this.Candidate11 = Candidate11;
		this.Candidate12 = Candidate12;
	}
	
	public int getNumCategories() {
		return numCategories;
	}
	
	public void setNumCategories(int numCategories) {
		this.numCategories = numCategories;
	}
	
	public String getCategory1() {
		return Category1;
	}
	
	public void setCategory1(String Category1) {
		this.Category1 = Category1;
	}
	
	public String getCategory2() {
		return Category2;
	}
	
	public void setCategory2(String Category2) {
		this.Category2 = Category2;
	}
	
	public String getCategory3() {
		return Category3;
	}
	
	public void setCategory3(String Category3) {
		this.Category3 = Category3;
	}
	
	public String getCategory4() {
		return Category4;
	}
	
	public void setCategory4(String Category4) {
		this.Category4 = Category4;
	}
	
	public String getCandidate1() {
		return Candidate1;
	}
	
	public void setCandidate1(String Candidate1) {
		this.Candidate1 = Candidate1;
	}
	
	public String getCandidate2() {
		return Candidate2;
	}
	
	public void setCandidate2(String Candidate2) {
		this.Candidate2 = Candidate2;
	}
	
	public String getCandidate3() {
		return Candidate3;
	}
	
	public void setCandidate3(String Candidate3) {
		this.Candidate3 = Candidate3;
	}
	
	public String getCandidate4() {
		return Candidate4;
	}
	
	public void setCandidate4(String Candidate4) {
		this.Candidate4 = Candidate4;
	}
	
	public String getCandidate5() {
		return Candidate5;
	}
	
	public void setCandidate5(String Candidate5) {
		this.Candidate5 = Candidate5;
	}
	
	public String getCandidate6() {
		return Candidate6;
	}
	
	public void setCandidate6(String Candidate6) {
		this.Candidate6 = Candidate6;
	}
	
	public String getCandidate7() {
		return Candidate7;
	}
	
	public void setCandidate7(String Candidate7) {
		this.Candidate7 = Candidate7;
	}
	
	public String getCandidate8() {
		return Candidate8;
	}
	
	public void setCandidate8(String Candidate8) {
		this.Candidate8 = Candidate8;
	}
	
	public String getCandidate9() {
		return Candidate9;
	}
	
	public void setCandidate9(String Candidate9) {
		this.Candidate9 = Candidate9;
	}
	
	public String getCandidate10() {
		return Candidate10;
	}
	
	public void setCandidate10(String Candidate10) {
		this.Candidate10 = Candidate10;
	}
	
	public String getCandidate11() {
		return Candidate11;
	}
	
	public void setCandidate11(String Candidate11) {
		this.Candidate11 = Candidate11;
	}
	
	public String getCandidate12() {
		return Candidate12;
	}
	
	public void setCandidate12(String Candidate12) {
		this.Candidate12 = Candidate12;
	}
	
}