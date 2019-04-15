package com.csci360.electionapp.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.io.IOException;
import java.sql.Connection;
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
	private boolean status;
	private LocalDateTime regDate;
	
	database db = new database();
	Connection conn;
	
	public Voter(String firstName, String lastName, String birthday2, String birthday3, String birthday4, String ssn, String password) throws SQLException {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthday(birthday2, birthday3, birthday4);
		this.setSsn(ssn);
		this.setPassword(password);
		this.setUsername(firstName, lastName);
		this.status = false;
		this.setRegDate();
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
	
	public String getBirthdayMth() {
		return this.birthdayMth;
	}
	
	public String getBirthdayDay() {
		return this.birthdayDay;
	}
	
	public String getBirthdayYear() {
		return this.birthdayYear;
	}
	
	public void setBirthday(String birthday2, String birthday3, String birthday4) {
		this.birthdayMth = birthday2;
		this.birthdayDay = birthday3;
		this.birthdayYear = birthday4;
	}
	
	public void setBirthdayDay(String birthday2) {
		this.birthdayDay = birthday2;
	}
	
	public void setBirthdayMth(String birthday3) {
		this.birthdayMth = birthday3;
	}
	
	public void setBirthdayYear(String birthday4) {
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
	
	public void setUsername(String firstName, String lastName) throws SQLException {
		conn = db.getConnection();
		this.username = lastName + firstName.charAt(0);
		this.username = db.checkUsername(this.username, conn);
	}
	
	public String getUsername() {
		return username;
	}
	
	public boolean getStatus() {
		//calls database and returns if able to vote today
		if (this.status == true) {//call inDatabase(v)
			return true;
		}
		else {
			return false;
		}
	}
	
	public Integer getBirthdayMonthNum() {
		int mth = Integer.valueOf(birthdayMth);
		System.out.println(mth);
		return mth;
	}
	
	public Integer getBirthdayDayNum() {
		int day = Integer.valueOf(birthdayDay);
		System.out.println(day);
		return day;
	}
	
	public Integer getBirthdayYearNum() {
		int year = Integer.valueOf(birthdayYear);
		System.out.println(year);
		return year;
	}
	
	public boolean getRegistrationStatus(database db) throws IOException {
		//calls database and returns if in the database
		String social = this.getSsn();
		boolean result;
		try {
			if(database.checkVoters(social, db.getConnection())) {
				result=true;
				return result;
			}
			else {
				result=false;
				return result;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=false;
			return result;
			
		}
	}
	
	//@SuppressWarnings("deprecation")
	public boolean checkEligibility() {
		LocalDate today = LocalDate.now(); //todays date
		LocalDate birthday = LocalDate.of(getBirthdayYearNum(), getBirthdayMonthNum(), getBirthdayDayNum());
		//int todayYear = today.getYear();
		Period p = Period.between(birthday, today);
		//Date birthday = new Date(getBirthdayMonth(), getBirthdayDay(), getBirthdayYear());
		int age = p.getYears();//this.getBirthdayYearNum() - todayYear;
		System.out.println("Age: " + age);
		if(age < 18) {
			return false;
		}
		else {
			return true;
		}
	}

	public LocalDateTime getRegDate() {
		
		return regDate;
	}

	public void setRegDate() {
		LocalDateTime today = LocalDateTime.now();
		
		this.regDate = today;
	}
}
