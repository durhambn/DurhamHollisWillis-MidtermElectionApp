package com.csci360.electionapp.testing;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.csci360.electionapp.controller.VoterController;
import com.csci360.electionapp.controller.registrationController;


public class junitTesting {
	
	private VoterController votingPerson;
	String Fname = "Brandi";
	String Lname = "Durham";
	String mth = "10";
	String day = "31";
	String year = "1997";
	String ssn = "1234";
	String pw = "5678";
	
    @Before
	public void setUp() {
    	votingPerson = registrationController.createVoter(Fname, Lname, mth, day, year, ssn, pw);
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
	
	
}
