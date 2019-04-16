package com.csci360.electionapp.testing;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.csci360.electionapp.controller.VoterController;
import com.csci360.electionapp.controller.registrationController;
import com.csci360.electionapp.model.Ballot;


public class junitTesting {
	
	private VoterController votingPerson;
	String Fname = "Brandi";
	String Lname = "Durham";
	String mth = "10";
	String day = "31";
	String year = "1997";
	String ssn = "1234";
	String pw = "5678";
	private Ballot b;
	
    @Before
	public void setUp() throws SQLException {
    	votingPerson = registrationController.createVoter(Fname, Lname, mth, day, year, ssn, pw);
    	b = new Ballot();
    	b.setVoteCand1(false);
    	b.setVoteCand2(false);
    	b.setVoteCand3(true);
    	b.setVoteCand4(true);
    	b.setVoteCand5(false);
    	b.setVoteCand6(false);
    	b.setVoteCand7(true);
    	b.setVoteCand8(false);
    	b.setVoteCand9(false);
    	b.setVoteCand10(true);
    	b.setVoteCand11(false);
    	b.setVoteCand12(false);
    }
    
	@Test
	public void testGetFirstName() {
		assertEquals(Fname, votingPerson.getVoterFirstName());
	}
	
	@Test
	public void testGetLastName() {
		assertEquals(Lname, votingPerson.getVoterLastName());
	}
	
	@Test
	public void testGetBMth() {
		assertEquals(mth, votingPerson.getVoterBirthdayMth());
	}
	
	@Test
	public void testGetBDay() {
		assertEquals(day, votingPerson.getVoterBirthdayDay());
	}
	
	@Test
	public void testGetBYear() {
		assertEquals(year, votingPerson.getVoterBirthdayYear());
	}
	
	@Test
	public void testGetSsn() {
		assertEquals(ssn, votingPerson.getVoterSSN());
	}
	
	@Test
	public void testGetPw() {
		assertEquals(pw, votingPerson.getVoterPassword());
	}
	
	@Test
	public void testSetFirstName() {
		votingPerson.setVoterFirstName("Austin");
		assertEquals("Austin", votingPerson.getVoterFirstName());
	}
	
	@Test
	public void testSetLastName() {
		votingPerson.setVoterLastName("Hollis");
		assertEquals("Hollis", votingPerson.getVoterLastName());
	}
	
	@Test
	public void testSetBMth() {
		votingPerson.setBirthdayMth("1");
		assertEquals("1", votingPerson.getVoterBirthdayMth());
	}
	
	@Test
	public void testSetBDay() {
		votingPerson.setBirthdayDay("10");
		assertEquals("10", votingPerson.getVoterBirthdayDay());
	}
	
	@Test
	public void testSetBYear() {
		votingPerson.setBirthdayYear("1990");
		assertEquals("1990", votingPerson.getVoterBirthdayYear());
	}
	
	@Test
	public void testSetSsn() {
		votingPerson.setVoterSSN("1111");
		assertEquals("1111", votingPerson.getVoterSSN());
	}
	
	@Test
	public void testSetPw() {
		votingPerson.setVoterPassword("2222");
		assertEquals("2222", votingPerson.getVoterPassword());
	}
	
	@Test
	public void testSetBirthdayMth() {
		votingPerson.setBirthday("3", "10", "1998");
		assertEquals("3", votingPerson.getVoterBirthdayMth());
	}
	
	
	@Test
	public void testGetBDNum() {
		assertTrue(10 == votingPerson.getVoterBirthdayMonthNum());
	}
	
	@Test
	public void testGetBMNum() {
		assertTrue(31 == votingPerson.getVoterBirthdayDayNum());
	}
	
	@Test
	public void testGetBYNum() {
		assertTrue(1997 == votingPerson.getVoterBirthdayYearNum());
	}
	
	
	@Test
	public void testSetBirthdayDay() {
		votingPerson.setBirthday("3", "10", "1998");
		assertEquals("10", votingPerson.getVoterBirthdayDay());
	}
	
	@Test
	public void testSetBirthdayYear() {
		votingPerson.setBirthday("3", "10", "1998");
		assertEquals("1998", votingPerson.getVoterBirthdayYear());
	}
	
	@Test
	public void testDOBconvertion() {
		assertEquals("1997-10-31", votingPerson.convertDOB());
	}
	
	@Test
	public void testGetStatus() {
		assertEquals(false, votingPerson.getVoterStatus());
	}
	
	@Test
	public void testEligibility() {
		assertEquals(true, votingPerson.getVoterEligibility());
	}
	
	@Test
	public void testInEligibility() {
		votingPerson.setBirthdayYear("2005");
		assertEquals(false, votingPerson.getVoterEligibility());
	}
	
	@Test
	public void testSetUsername() throws SQLException {
		assertEquals("DurhamB", votingPerson.getVoterUsername());
	}
	
	@Test
	public void testGetCandidate1() {
		assertEquals("Leonardo DiCaprio", b.getCandidate1());
	}
	
	@Test
	public void testGetCandidate2() {
		assertEquals("Dom Cobb", b.getCandidate2());
	}
	
	@Test
	public void testGetCandidate3() {
		assertEquals("Harry Potter", b.getCandidate3());
	}
	
	@Test
	public void testGetCandidate4() {
		assertEquals("Nathan Drake", b.getCandidate4());
	}
	
	@Test
	public void testSetVoteCandidate1() {
		b.setVoteCand1(true);
		assertEquals(true, b.isVoteCand1());
	}
	
	@Test
	public void testSetVoteCandidate2() {
		b.setVoteCand2(false);
		assertEquals(false, b.isVoteCand2());
	}
	@Test
	public void testSetVoteCandidate3() {
		b.setVoteCand3(false);
		assertEquals(false, b.isVoteCand3());
	}
	@Test
	public void testSetVoteCandidate4() {
		b.setVoteCand4(false);
		assertEquals(false, b.isVoteCand4());
	}
	
	@Test
	public void testGetCat1Results() {
		assertEquals("Harry Potter", b.getCat1Results());
	}
	
	@Test
	public void testGetCat2Results() {
		assertEquals("Nathan Drake", b.getCat2Results());
	}
	
	@Test
	public void testGetCat3Results() {
		assertEquals("Jeff Riley", b.getCat3Results());
	}
	
	@Test
	public void testGetCat4Results() {
		assertEquals("Ellen Page", b.getCat4Results());
	}
	
}
