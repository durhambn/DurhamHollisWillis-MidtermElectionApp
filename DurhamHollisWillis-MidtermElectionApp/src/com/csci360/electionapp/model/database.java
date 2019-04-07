package com.csci360.electionapp.model;
import com.csci360.electionapp.controller.VoterController;
import com.csci360.electionapp.model.Voter;
import com.csci360.electionapp.view.*;
import com.csci360.electionapp.model.Ballot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * This class demonstrates how to connect to MySQL and run some basic commands.
 * 
 * In order to use this, you have to download the Connector/J driver and add
 * its .jar file to your build path.  You can find it here:
 * 
 * http://dev.mysql.com/downloads/connector/j/
 * 
 * You will see the following exception if it's not in your class path:
 * 
 * java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/
 * 
 * To add it to your class path:
 * 1. Right click on your project
 * 2. Go to Build Path -> Add External Archives...
 * 3. Select the file mysql-connector-java-5.1.24-bin.jar
 *    NOTE: If you have a different version of the .jar file, the name may be
 *    a little different.
 *    
 * The user name and password are both "root", which should be correct if you followed
 * the advice in the MySQL tutorial. If you want to use different credentials, you can
 * change them below. 
 * 
 * You will get the following exception if the credentials are wrong:
 * 
 * java.sql.SQLException: Access denied for user 'userName'@'localhost' (using password: YES)
 * 
 * You will instead get the following exception if MySQL isn't installed, isn't
 * running, or if your serverName or portNumber are wrong:
 * 
 * java.net.ConnectException: Connection refused
 */
public class database {

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "root";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "election_database";
	
	/** The name of the table we are testing with */
	private final String tableName = "VOTERS";
	
	
	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
		//connectionProps.put("useSSL", "false");
		connectionProps.put("createDatabaseIfNotExist", "true");

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName, connectionProps);

		return conn;
	}
	
	/**
	 * Connect to MySQL and do some stuff.
	 */
	public void run() {

		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
		
	}
	
	public void createTables(Connection conn) throws SQLException {
		Statement s = conn.createStatement();
		String s1 = "CREATE TABLE IF NOT EXISTS VOTERS(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(256) NOT NULL, "
				+ "last_name varchar(256) NOT NULL, date_of_birth date NOT NULL, ssn varchar(128) NOT NULL, username varchar(256) NOT NULL, "
				+ "password varchar(256) NOT NULL, created datetime NOT NULL, status boolean NOT NULL DEFAULT 0, CONSTRAINT Unique_ssn UNIQUE KEY(ssn));";
		
		String s2 = "CREATE TABLE IF NOT EXISTS ADMIN(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(256) NOT NULL, "
				+ "last_name varchar(256) NOT NULL, username varchar(256) NOT NULL, password varchar(256) NOT NULL, CONSTRAINT Unique_user UNIQUE KEY(username));";
		
		String s3 = "CREATE TABLE IF NOT EXISTS CANDIDATES(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,name varchar(256) NOT NULL, "
				+ "last_name varchar(256) NOT NULL, category varchar(256) NOT NULL, votes INTEGER NOT NULL DEFAULT 0, "
				+ "CONSTRAINT Unique_cand UNIQUE KEY(name,last_name,category));";
		
		String s4 = "CREATE TABLE IF NOT EXISTS BALLOT(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, category1 varchar(256) NOT NULL, "
				+ "category2 varchar(256) NOT NULL,category3 varchar(256) NOT NULL, category4 varchar(256) NOT NULL);";
		
		s.addBatch(s1);
		s.addBatch(s2);  
		s.addBatch(s3);
		s.addBatch(s4);
		s.executeBatch();
		initialCandidates(conn);
		initialAdmin(conn);
	}
	
	public void addToVoters(VoterController voter, Connection conn) throws SQLException {
		String query = " INSERT INTO VOTERS (name, last_name, date_of_birth, ssn, username, password, created)" + " VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, voter.getVoterFirstName());
		preparedStmt.setString(2,  voter.getVoterLastName());
		preparedStmt.setString(3, voter.convertDOB());
		preparedStmt.setString(4,  voter.getVoterSSN());
		preparedStmt.setString(5,  voter.getVoterUsername());
		preparedStmt.setString(6,  voter.getVoterPassword());
		preparedStmt.setString(7, voter.getTime());
		
		preparedStmt.execute();
	      
	    conn.close();
	}
	
	public void addToCandidates(Ballot ballot, Connection conn) throws SQLException {
		String query1 = "SELECT votes FROM CANDIDATES WHERE name=? AND last_name=? AND category=?;";		
		PreparedStatement ps1 = conn.prepareStatement(query1);
		String cat1 = ballot.getCat1Results();
		String[] winner1 = cat1.split("\\s+");
		String winner1First = winner1[0];
		String winner1Last = winner1[1];
		ps1.setString(1, winner1First);
		ps1.setString(2, winner1Last);
		ps1.setString(3, ballot.getCategory1());
		ResultSet rs1 = ps1.executeQuery();
		ps1.close();
		int cat1Votes = rs1.getInt(1) + 1;
		String finalquery1 = "UPDATE CANDIDATES set votes=? WHERE name=? AND last_name=? AND category=?;";
		PreparedStatement stmt1 = conn.prepareStatement(finalquery1);
		stmt1.setInt(1, cat1Votes);
		stmt1.setString(2, winner1First);
		stmt1.setString(3, winner1Last);
		stmt1.setString(4, ballot.getCategory1());
		stmt1.executeQuery();
		
		String query2 = "SELECT votes FROM CANDIDATES WHERE name=? AND last_name=? AND category=?;";
		PreparedStatement ps2 = conn.prepareStatement(query2);
		String cat2 = ballot.getCat2Results();
		String[] winner2 = cat2.split("\\s+");
		String winner2First = winner2[0];
		String winner2Last = winner2[1];
		ps2.setString(1, winner2First);
		ps2.setString(2, winner2Last);
		ps2.setString(3, ballot.getCategory2());
		ResultSet rs2 = ps2.executeQuery();
		ps2.close();
		int cat2Votes = rs2.getInt(1) + 1;
		String finalquery2 = "UPDATE CANDIDATES set votes=? WHERE name=? AND last_name=? AND category=?;";
		PreparedStatement stmt2 = conn.prepareStatement(finalquery2);
		stmt2.setInt(1, cat2Votes);
		stmt2.setString(2, winner2First);
		stmt2.setString(3, winner2Last);
		stmt2.setString(4, ballot.getCategory2());
		stmt2.executeQuery();


		String query3 = "SELECT votes FROM CANDIDATES WHERE name=? AND last_name=? AND category=?;";
		PreparedStatement ps3 = conn.prepareStatement(query3);
		String cat3 = ballot.getCat3Results();
		String[] winner3 = cat3.split("\\s+");
		String winner3First = winner3[0];
		String winner3Last = winner3[1];
		ps3.setString(1, winner3First);
		ps3.setString(2, winner3Last);
		ps3.setString(3, ballot.getCategory3());
		ResultSet rs3 = ps3.executeQuery();
		ps3.close();
		int cat3Votes = rs3.getInt(1) + 1;
		String finalquery3 = "UPDATE CANDIDATES set votes=? WHERE name=? AND last_name=? AND category=?;";
		PreparedStatement stmt3 = conn.prepareStatement(finalquery3);
		stmt3.setInt(1, cat3Votes);
		stmt3.setString(2, winner3First);
		stmt3.setString(3, winner3Last);
		stmt3.setString(4, ballot.getCategory3());
		stmt3.executeQuery();

		
		String query4 = "SELECT votes FROM CANDIDATES WHERE name=? AND last_name=? AND category=?;";
		PreparedStatement ps4 = conn.prepareStatement(query4);
		String cat4 = ballot.getCat4Results();
		String[] winner4 = cat4.split("\\s+");
		String winner4First = winner4[0];
		String winner4Last = winner4[1];
		ps4.setString(1, winner4First);
		ps4.setString(2, winner4Last);
		ps4.setString(3, ballot.getCategory4());
		ResultSet rs4 = ps4.executeQuery();
		ps4.close();
		int cat4Votes = rs4.getInt(1) + 1;
		String finalquery4 = "UPDATE CANDIDATES set votes=? WHERE name=? AND last_name=? AND category=?;";
		PreparedStatement stmt4 = conn.prepareStatement(finalquery4);
		stmt4.setInt(1, cat4Votes);
		stmt4.setString(2, winner4First);
		stmt4.setString(3, winner4Last);
		stmt4.setString(4, ballot.getCategory4());
		stmt4.executeQuery();

		conn.close();
	}
	
	public void addToBallots(Ballot ballot, Connection conn) throws SQLException {
		String query = "INSERT INTO BALLOT(category1, category2, category3, category4) VALUES(?,?,?,?);";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, ballot.getCat1Results());
		ps.setString(2, ballot.getCat2Results());
		ps.setString(3, ballot.getCat3Results());
		ps.setString(4, ballot.getCategory4());
		ps.executeQuery();
		
	}
	//Need to fix and change Statement to a PreparedStatement
	//Refer to: https://alvinalexander.com/java/java-mysql-update-query-example
	public static boolean checkVoters(String social, Connection conn) throws SQLException {
		boolean result;
		String query = "SELECT ssn FROM VOTERS WHERE ssn=?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, social);
		ResultSet rs = stmt.executeQuery(query);
		if(rs.next()) {
			result = true;
			return result;
		}
		else {
			result = false;
			return result;
		}
	}
	
	public String checkUsername(String username, Connection conn) throws SQLException {
		String newUsername = username;
		boolean uniqueUser = true;
		String query = "SELECT username FROM VOTERS WHERE username=?;";
		String query1 = "SELECT username FROM VOTERS WHERE username=?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			String usernameNum = "";
			int count = 0;
			while(uniqueUser != true) {
				newUsername = username;
				usernameNum = newUsername + count;
				PreparedStatement stmt1 = conn.prepareStatement(query1);
				stmt1.setString(1, newUsername);
				uniqueUser = stmt1.execute(query1);
				count++;
			}
		}
		return newUsername;
	}
	
	public static boolean checkUserLogin(String user, String pass, Connection conn) throws SQLException {
		boolean result;
		String query = "SELECT * FROM VOTERS WHERE username=? AND password=?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, user);
		stmt.setString(2, pass);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			result = true;
		}
		else {
			result = false;
		}
		return result;
	}
	
	public static boolean checkAdminLogin(String user, String pass, Connection conn) throws SQLException {
		boolean result;
		String query = "SELECT * FROM ADMIN WHERE username=? AND password=?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, user);
		stmt.setString(2, pass);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			result = true;
		}
		else {
			result = false;
		}
		return result;
	}
	
	public void initialAdmin(Connection conn) throws SQLException {
		String query = "INSERT IGNORE INTO ADMIN(name, last_name, username, password)" + " VALUES(?, ?, ?, ?);";
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		
		preparedStmt.setString(1, "Admin");
		preparedStmt.setString(2, "Admin");
		preparedStmt.setString(3, "admin");
		preparedStmt.setString(4, "admin");
		
		preparedStmt.execute();
		
		conn.close();
	}
	
	//Need to fix Statement and change it into a PreparedStatement maybe?
	public void initialCandidates(Connection conn) throws SQLException {
		Ballot ballot = new Ballot();
		//String query = "INSERT IGNORE INTO CANDIDATES(name, last_name, category, votes)" + " VALUES(?, ?, ?);";
		Statement s = conn.createStatement();

		String s1 = "INSERT IGNORE INTO CANDIDATES(name, last_name, category) VALUES('" +
				ballot.getCandidate1First() + "', '" + ballot.getCandidate1Last() + "', '" + ballot.getCandidate1Cat() + "');";
			
		String s2 = "INSERT IGNORE INTO CANDIDATES(name, last_name, category) VALUES('" +
				ballot.getCandidate2First() + "', '" + ballot.getCandidate2Last() + "', '" + ballot.getCandidate2Cat() + "');";		
		String s3 = "INSERT IGNORE INTO CANDIDATES(name, last_name, category) VALUES('" +
				ballot.getCandidate3First() + "', '" + ballot.getCandidate3Last() + "', '" + ballot.getCandidate3Cat() + "');";
		String s4 = "INSERT IGNORE INTO CANDIDATES(name, last_name, category) VALUES('" +
				ballot.getCandidate4First() + "', '" + ballot.getCandidate4Last() + "', '" + ballot.getCandidate4Cat() + "');";
		String s5 = "INSERT IGNORE INTO CANDIDATES(name, last_name, category) VALUES('" +
				ballot.getCandidate5First() + "', '" + ballot.getCandidate5Last() + "', '" + ballot.getCandidate5Cat() + "');";
		String s6 = "INSERT IGNORE INTO CANDIDATES(name, last_name, category) VALUES('" +
				ballot.getCandidate6First() + "', '" + ballot.getCandidate6Last() + "', '" + ballot.getCandidate6Cat() + "');";
		String s7 = "INSERT IGNORE INTO CANDIDATES(name, last_name, category) VALUES('" +
				ballot.getCandidate7First() + "', '" + ballot.getCandidate7Last() + "', '" + ballot.getCandidate7Cat() + "');";
		String s8 = "INSERT IGNORE INTO CANDIDATES(name, last_name, category) VALUES('" +
				ballot.getCandidate8First() + "', '" + ballot.getCandidate8Last() + "', '" + ballot.getCandidate8Cat() + "');";
		String s9 = "INSERT IGNORE INTO CANDIDATES(name, last_name, category) VALUES('" +
				ballot.getCandidate9First() + "', '" + ballot.getCandidate9Last() + "', '" + ballot.getCandidate9Cat() + "');";
		String s10 = "INSERT IGNORE INTO CANDIDATES(name, last_name, category) VALUES('" +
				ballot.getCandidate10First() + "', '" + ballot.getCandidate10Last() + "', '" + ballot.getCandidate10Cat() + "');";
		String s11 = "INSERT IGNORE INTO CANDIDATES(name, last_name, category) VALUES('" +
				ballot.getCandidate11First() + "', '" + ballot.getCandidate11Last() + "', '" + ballot.getCandidate11Cat() + "');";
		String s12 = "INSERT IGNORE INTO CANDIDATES(name, last_name, category) VALUES('" +
				ballot.getCandidate12First() + "', '" + ballot.getCandidate12Last() + "', '" + ballot.getCandidate12Cat() + "');";
		
		s.addBatch(s1);
		s.addBatch(s2);  
		s.addBatch(s3);
		s.addBatch(s4);
		s.addBatch(s5);
		s.addBatch(s6);
		s.addBatch(s7);
		s.addBatch(s8);
		s.addBatch(s9);
		s.addBatch(s10);
		s.addBatch(s11);
		s.addBatch(s12);
		
		s.executeBatch();
	}
	
	public void setStatus(String username, Connection conn) throws SQLException {
		String query = "UPDATE VOTERS SET status=1 WHERE username=?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, username);
		stmt.executeQuery();
		conn.close();
		
	}
	
	public int getRegVoters(Connection conn) throws SQLException{
		String query = "SELECT COUNT(*) FROM VOTERS;";
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		return count;
		
	}

	public int getNumBallots(Connection conn) throws SQLException{
		String query= "SELECT COUNT(*) FROM BALLOT";
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		return count;
	}
	
	//Need to fix query maybe to account single quotes (See checkAdminLogin)
	public int getCandVotes(Connection conn, String candID) throws SQLException{
		String query="SELECT votes FROM CANDIDATES WHERE id=?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, candID);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		return count;
	}
	
	//1=true, has voted; 0=false, hasn't voted
	public boolean getStatusToVote(Connection conn, String username) throws SQLException{
		boolean canVote;
		String query="SELECT status FROM VOTERS WHERE username=?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		canVote=rs.getBoolean(1);
		return canVote;
	}
	
	public java.util.Date getCreatedDate(Connection conn, String username) throws SQLException{
		String query="SELECT created FROM VOTERS WHERE username=?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		Timestamp created = rs.getTimestamp("created");
		//java.sql.Date date = rs.getDate("created");
		//java.sql.Time time = rs.getTime("created");
		//java.util.Date dateTime = new java.util.Date(date.getTime());
		return created;
		
	}
	
	
	
	/**
	 * Connect to the DB and do some stuff
	 */
	public static void main(String[] args) {
		database app = new database();
		app.run();
	}
}