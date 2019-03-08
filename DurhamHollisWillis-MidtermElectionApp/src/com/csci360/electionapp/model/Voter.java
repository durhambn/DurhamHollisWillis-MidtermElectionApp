package com.csci360.electionapp.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

//voter person will have an object of ballot, set attributes set to null, vp.getBallot()
//connect to voting person
//ballots going to need to know how many categories 
public class Voter {
	
	private String firstName;
	private String lastName;
	private String birthdayMth;
	private String birthdayDay;
	private String birthdayYear;
	private String ssn;
	private String password;
	private String username;
	
	public Voter(String firstName, String lastName, String birthday2, String birthday3, String birthday4, String ssn, String password) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthday(birthday2, birthday3, birthday4);
		this.setSsn(ssn);
		this.setPassword(password);
		this.setUsername(firstName, lastName);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getBirthday() {
		return (birthdayMth + "/" + birthdayDay +"/" + birthdayYear);
	}

	public void setBirthday(String birthday2, String birthday3, String birthday4) {
		this.birthdayMth = birthday2;
		this.birthdayDay = birthday3;
		this.birthdayYear = birthday4;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsername(String firstName, String lastName) {
		this.username = lastName + firstName.charAt(0);
	}
	
	public String getUsername() {
		return username;
	}
	
	public boolean getStatus() {
		//calls database and returns if in the database
		if (true) {//call inDatabase(v)
			return true;
		}
		else
			return true;
	}
	private Integer getBirthdayMonth() {
		int mth = Integer.valueOf(birthdayMth);
		System.out.println(mth);
		return mth;
	}
	private Integer getBirthdayDay() {
		int day = Integer.valueOf(birthdayDay);
		System.out.println(day);
		return day;
	}
	private Integer getBirthdayYear() {
		int year = Integer.valueOf(birthdayYear);
		System.out.println(year);
		return year;
	}
	
	@SuppressWarnings("deprecation")
	public boolean checkEligibility() {
		Date today = new Date(); //todays date
		Date birthday = new Date(getBirthdayYear(), getBirthdayMonth(), getBirthdayDay());
		//Date birthday = new Date(getBirthdayMonth(), getBirthdayDay(), getBirthdayYear());
		int age = today.compareTo(birthday);
		System.out.println("Age: " + age);
		if(age < 18) {
			return false;
		}
		else {
		return true;
		}
	}
}
