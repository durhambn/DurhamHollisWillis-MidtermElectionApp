package com.csci360.electionapp.model;

public class Ballot {
	
	// There are four categories.
	private int numCategories = 4;
	
	// Category Descriptions
	private String Category1 = "President";
	private String Category2 = "Vice President";
	private String Category3 = "Secretary";
	private String Category4 = "Treasurer";
	
	// Candidate names
	private Candidate Candidate1 = new Candidate("Leonardo", "DiCaprio", Category1);
	private Candidate Candidate2 = new Candidate("Dom", "Cobb", Category1);
	private Candidate Candidate3 = new Candidate("Harry", "Potter", Category1);
	private Candidate Candidate4 = new Candidate("Nathan", "Drake", Category2);
	private Candidate Candidate5 = new Candidate("Bob", "Barker", Category2);
	private Candidate Candidate6 = new Candidate("Steve", "Harvey", Category2);
	private Candidate Candidate7 = new Candidate("Jeff", "Riley", Category3);
	private Candidate Candidate8 = new Candidate("John", "Smith", Category3);
	private Candidate Candidate9 = new Candidate("Joseph", "Gordon-Levitt", Category3);
	private Candidate Candidate10 = new Candidate("Ellen", "Page", Category4);
	private Candidate Candidate11 = new Candidate("Tom", "Hardy", Category4);
	private Candidate Candidate12 = new Candidate("Ken", "Watanabe", Category4);
	
	// Initial voted for status is set to false for all candidates
	private boolean voteCand1 = false;
	private boolean voteCand2 = false;
	private boolean voteCand3 = false;
	private boolean voteCand4 = false;
	private boolean voteCand5 = false;
	private boolean voteCand6 = false;
	private boolean voteCand7 = false;
	private boolean voteCand8 = false;
	private boolean voteCand9 = false;
	private boolean voteCand10 = false;
	private boolean voteCand11 = false;
	private boolean voteCand12 = false;
	
	public Ballot() {
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumCategories() {
		return numCategories;
	}
	
	/**
	 * 
	 * @param numCategories
	 */
	public void setNumCategories(int numCategories) {
		this.numCategories = numCategories;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCategory1() {
		return Category1;
	}
	
	/**
	 * 
	 * @param Category1
	 */
	public void setCategory1(String Category1) {
		this.Category1 = Category1;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCategory2() {
		return Category2;
	}
	
	/**
	 * 
	 * @param Category2
	 */
	public void setCategory2(String Category2) {
		this.Category2 = Category2;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCategory3() {
		return Category3;
	}
	
	/**
	 * 
	 * @param Category3
	 */
	public void setCategory3(String Category3) {
		this.Category3 = Category3;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCategory4() {
		return Category4;
	}
	
	public void setCategory4(String Category4) {
		this.Category4 = Category4;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate1() {
		return this.Candidate1.getName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate1First() {
		return this.Candidate1.getFirstName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate2First() {
		return this.Candidate2.getFirstName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate3First() {
		return this.Candidate3.getFirstName();
	}
	/**
	 * 
	 * @return
	 */
	public String getCandidate4First() {
		return this.Candidate4.getFirstName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate5First() {
		return this.Candidate5.getFirstName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate6First() {
		return this.Candidate6.getFirstName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate7First() {
		return this.Candidate7.getFirstName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate8First() {
		return this.Candidate8.getFirstName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate9First() {
		return this.Candidate9.getFirstName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate10First() {
		return this.Candidate10.getFirstName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate11First() {
		return this.Candidate11.getFirstName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate12First() {
		return this.Candidate12.getFirstName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate1Last() {
		return this.Candidate1.getLastName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate2Last() {
		return this.Candidate2.getLastName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate3Last() {
		return this.Candidate3.getLastName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate4Last() {
		return this.Candidate4.getLastName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate5Last() {
		return this.Candidate5.getLastName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate6Last() {
		return this.Candidate6.getLastName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate7Last() {
		return this.Candidate7.getLastName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate8Last() {
		return this.Candidate8.getLastName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate9Last() {
		return this.Candidate9.getLastName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate10Last() {
		return this.Candidate10.getLastName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate11Last() {
		return this.Candidate11.getLastName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate12Last() {
		return this.Candidate12.getLastName();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate1Cat() {
		return this.Candidate1.getCategory();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate2Cat() {
		return this.Candidate2.getCategory();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate3Cat() {
		return this.Candidate3.getCategory();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate4Cat() {
		return this.Candidate4.getCategory();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate5Cat() {
		return this.Candidate5.getCategory();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate6Cat() {
		return this.Candidate6.getCategory();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate7Cat() {
		return this.Candidate7.getCategory();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate8Cat() {
		return this.Candidate8.getCategory();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate9Cat() {
		return this.Candidate9.getCategory();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate10Cat() {
		return this.Candidate10.getCategory();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate11Cat() {
		return this.Candidate11.getCategory();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate12Cat() {
		return this.Candidate12.getCategory();
	}
	
	/**
	 * 
	 * @param Candidate1
	 */
	public void setCandidate1(Candidate Candidate1) {
		this.Candidate1 = Candidate1;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate2() {
		return this.Candidate2.getName();
	}
	
	/**
	 * 
	 * @param Candidate2
	 */
	public void setCandidate2(Candidate Candidate2) {
		this.Candidate2 = Candidate2;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate3() {
		return this.Candidate3.getName();
	}
	
	/**
	 * 
	 * @param Candidate3
	 */
	public void setCandidate3(Candidate Candidate3) {
		this.Candidate3 = Candidate3;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate4() {
		return this.Candidate4.getName();
	}
	
	/**
	 * 
	 * @param Candidate4
	 */
	public void setCandidate4(Candidate Candidate4) {
		this.Candidate4 = Candidate4;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate5() {
		return this.Candidate5.getName();
	}
	
	/**
	 * 
	 * @param Candidate5
	 */
	public void setCandidate5(Candidate Candidate5) {
		this.Candidate5 = Candidate5;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate6() {
		return this.Candidate6.getName();
	}
	
	/**
	 * 
	 * @param Candidate6
	 */
	public void setCandidate6(Candidate Candidate6) {
		this.Candidate6 = Candidate6;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate7() {
		return this.Candidate7.getName();
	}
	
	/**
	 * 
	 * @param Candidate7
	 */
	public void setCandidate7(Candidate Candidate7) {
		this.Candidate7 = Candidate7;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate8() {
		return this.Candidate8.getName();
	}
	
	/**
	 * 
	 * @param Candidate8
	 */
	public void setCandidate8(Candidate Candidate8) {
		this.Candidate8 = Candidate8;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate9() {
		return this.Candidate9.getName();
	}
	
	/**
	 * 
	 * @param Candidate9
	 */
	public void setCandidate9(Candidate Candidate9) {
		this.Candidate9 = Candidate9;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate10() {
		return this.Candidate10.getName();
	}
	
	/**
	 * 
	 * @param Candidate10
	 */
	public void setCandidate10(Candidate Candidate10) {
		this.Candidate10 = Candidate10;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate11() {
		return this.Candidate11.getName();
	}
	
	/**
	 * 
	 * @param Candidate11
	 */
	public void setCandidate11(Candidate Candidate11) {
		this.Candidate11 = Candidate11;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCandidate12() {
		return this.Candidate12.getName();
	}
	
	/**
	 * 
	 * @param Candidate12
	 */
	public void setCandidate12(Candidate Candidate12) {
		this.Candidate12 = Candidate12;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isVoteCand1() {
		return voteCand1;
	}

	/**
	 * 
	 * @param voteCand1
	 */
	public void setVoteCand1(boolean voteCand1) {
		this.voteCand1 = voteCand1;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isVoteCand2() {
		return voteCand2;
	}

	/**
	 * 
	 * @param voteCand2
	 */
	public void setVoteCand2(boolean voteCand2) {
		this.voteCand2 = voteCand2;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isVoteCand3() {
		return voteCand3;
	}

	/**
	 * 
	 * @param voteCand3
	 */
	public void setVoteCand3(boolean voteCand3) {
		this.voteCand3 = voteCand3;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isVoteCand4() {
		return voteCand4;
	}

	/**
	 * 
	 * @param voteCand4
	 */
	public void setVoteCand4(boolean voteCand4) {
		this.voteCand4 = voteCand4;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isVoteCand5() {
		return voteCand5;
	}

	/**
	 * 
	 * @param voteCand5
	 */
	public void setVoteCand5(boolean voteCand5) {
		this.voteCand5 = voteCand5;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isVoteCand6() {
		return voteCand6;
	}

	/**
	 * 
	 * @param voteCand6
	 */
	public void setVoteCand6(boolean voteCand6) {
		this.voteCand6 = voteCand6;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isVoteCand7() {
		return voteCand7;
	}

	/**
	 * 
	 * @param voteCand7
	 */
	public void setVoteCand7(boolean voteCand7) {
		this.voteCand7 = voteCand7;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isVoteCand8() {
		return voteCand8;
	}

	/**
	 * 
	 * @param voteCand8
	 */
	public void setVoteCand8(boolean voteCand8) {
		this.voteCand8 = voteCand8;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isVoteCand9() {
		return voteCand9;
	}

	/**
	 * 
	 * @param voteCand9
	 */
	public void setVoteCand9(boolean voteCand9) {
		this.voteCand9 = voteCand9;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isVoteCand10() {
		return voteCand10;
	}

	/**
	 * 
	 * @param voteCand10
	 */
	public void setVoteCand10(boolean voteCand10) {
		this.voteCand10 = voteCand10;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isVoteCand11() {
		return voteCand11;
	}

	/**
	 * 
	 * @param voteCand11
	 */
	public void setVoteCand11(boolean voteCand11) {
		this.voteCand11 = voteCand11;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isVoteCand12() {
		return voteCand12;
	}

	/**
	 * 
	 * @param voteCand12
	 */
	public void setVoteCand12(boolean voteCand12) {
		this.voteCand12 = voteCand12;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCat1Results() {
		String cat1 = "";
		
		if(isVoteCand1()) {
			cat1 = getCandidate1();
		}
		
		if(isVoteCand2()) {
			cat1=getCandidate2();
		}
		
		if(isVoteCand3()) {
			cat1=getCandidate3();
		}
		
		return cat1;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCat2Results() {
		String cat2 = "";
		
		if(isVoteCand4()) {
			cat2=getCandidate4();
		}
		
		if(isVoteCand5()) {
			cat2=getCandidate5();
		}
		
		if(isVoteCand6()) {
			cat2=getCandidate6();
		}
		
		return cat2;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCat3Results() {
		String cat3 = "";
		
		if(isVoteCand7()) {
			cat3=getCandidate7();
		}
		
		if(isVoteCand8()) {
			cat3=getCandidate8();
		}
		
		if(isVoteCand9()) {
			cat3=getCandidate9();
		}
		
		return cat3;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCat4Results() {
		String cat4 = "";
		
		if(isVoteCand10()) {
			cat4=getCandidate10();
		}
		
		if(isVoteCand11()) {
			cat4=getCandidate11();
		}
		
		if(isVoteCand12()) {
			cat4=getCandidate12();
		}
		
		return cat4;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getResults() {
		String cat1 = "";
		String cat2 = "";
		String cat3 = "";
		String cat4 = "";
		String results="";
		
		if(isVoteCand1()) {
			cat1 = getCandidate1();
		}
		
		if(isVoteCand2()) {
			cat1=getCandidate2();
		}
		
		if(isVoteCand3()) {
			cat1=getCandidate3();
		}
		
		if(isVoteCand4()) {
			cat2=getCandidate4();
		}
		
		if(isVoteCand5()) {
			cat2=getCandidate5();
		}
		
		if(isVoteCand6()) {
			cat2=getCandidate6();
		}
		
		if(isVoteCand7()) {
			cat3=getCandidate7();
		}
		
		if(isVoteCand8()) {
			cat3=getCandidate8();
		}
		
		if(isVoteCand9()) {
			cat3=getCandidate9();
		}
		
		if(isVoteCand10()) {
			cat4=getCandidate10();
		}
		
		if(isVoteCand11()) {
			cat4=getCandidate11();
		}
		
		if(isVoteCand12()) {
			cat4=getCandidate12();
		}
		
		results="Category 1: " + cat1 + "\nCategory 2: " + cat2 + "\nCategory 3: " + cat3 + "\nCategory 4: " + cat4;
		
		return results;	
	}
}