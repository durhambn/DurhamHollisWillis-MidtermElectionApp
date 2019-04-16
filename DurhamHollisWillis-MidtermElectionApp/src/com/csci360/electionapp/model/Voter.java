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
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param birthday1
	 * @param birthday2
	 * @param birthday3
	 * @param ssn
	 * @param password
	 * @throws SQLException
	 */
	public Voter(String firstName, String lastName, String birthday1, String birthday2, String birthday3, String ssn, String password) throws SQLException {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthday(birthday1, birthday2, birthday3);
		this.setSsn(ssn);
		this.setPassword(password);
		this.setUsername(firstName, lastName);
		this.status = false;
		this.setRegDate();
	}

	/**
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getBirthday() {
		return (birthdayMth + "/" + birthdayDay +"/" + birthdayYear);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getBirthdayMth() {
		return this.birthdayMth;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getBirthdayDay() {
		return this.birthdayDay;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getBirthdayYear() {
		return this.birthdayYear;
	}
	
	/**
	 * 
	 * @param birthday1
	 * @param birthday2
	 * @param birthday3
	 */
	public void setBirthday(String birthday1, String birthday2, String birthday3) {
		this.birthdayMth = birthday1;
		this.birthdayDay = birthday2;
		this.birthdayYear = birthday3;
	}
	
	/**
	 * 
	 * @param birthday1
	 */
	public void setBirthdayDay(String birthday1) {
		this.birthdayDay = birthday1;
	}
	
	/**
	 * 
	 * @param birthday2
	 */
	public void setBirthdayMth(String birthday2) {
		this.birthdayMth = birthday2;
	}
	
	/**
	 * 
	 * @param birthday3
	 */
	public void setBirthdayYear(String birthday3) {
		this.birthdayYear = birthday3;
	}

	/**
	 * 
	 * @return
	 */
	public String getSsn() {
		return ssn;
	}

	/**
	 * 
	 * @param ssn
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @throws SQLException
	 */
	public void setUsername(String firstName, String lastName) throws SQLException {
		conn = db.getConnection();
		this.username = lastName + firstName.charAt(0);
		this.username = db.checkUsername(this.username, conn);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	
	//checks if they've voted
	/**
	 * 
	 * @return
	 */
	public boolean getStatus() {
		//calls database and returns if able to vote today
		if (this.status == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer getBirthdayMonthNum() {
		int mth = Integer.valueOf(birthdayMth);
		return mth;
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer getBirthdayDayNum() {
		int day = Integer.valueOf(birthdayDay);
		return day;
	}
	
	/**
	 * 
	 * @return
	 */
	public Integer getBirthdayYearNum() {
		int year = Integer.valueOf(birthdayYear);
		return year;
	}
	
	//check if registered
	/**
	 * 
	 * @param db
	 * @return
	 * @throws IOException
	 */
	public boolean getRegistrationStatus(database db) throws IOException {
		//calls database and returns if in the database
		String social = this.getSsn();
		boolean result;
		try {
			if(database.checkVoters(this.firstName, this.lastName, social, db.getConnection())) {
				result=true;
				return result;
			}
			else {
				result=false;
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result=false;
			return result;	
		}
	}
	
	//check over 18
	/**
	 * 
	 * @return
	 */
	public boolean checkEligibility() {
		// Get Today's date
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(getBirthdayYearNum(), getBirthdayMonthNum(), getBirthdayDayNum());
		Period p = Period.between(birthday, today);
		int age = p.getYears();
		if(age < 18) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * 
	 * @return
	 */
	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate() {
		LocalDateTime today = LocalDateTime.now();
		this.regDate = today;
	}
}