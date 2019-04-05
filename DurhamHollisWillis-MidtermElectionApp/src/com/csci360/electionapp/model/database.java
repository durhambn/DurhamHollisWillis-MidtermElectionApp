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
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
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
		String s1 = "CREATE TABLE IF NOT EXISTS VOTERS(" + "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(256) NOT NULL, "
				+ "last_name varchar(256) NOT NULL, date_of_birth date NOT NULL, ssn varchar(128) NOT NULL, username varchar(256) NOT NULL, "
				+ "password varchar(256) NOT NULL, created time NOT NULL, status boolean NOT NULL DEFAULT 0, CONSTRAINT Unique_ssn UNIQUE KEY(ssn));";
		String s2 = "CREATE TABLE IF NOT EXISTS ADMIN(" + "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(256) NOT NULL, "
				+ "last_name varchar(256) NOT NULL, username varchar(256) NOT NULL, password varchar(256) NOT NULL" + ", "
						+ "CONSTRAINT Unique_user UNIQUE KEY(username));";
		String s3 = "CREATE TABLE IF NOT EXISTS CANDIDATES(" + "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,name varchar(256) NOT NULL, "
				+ "last_name varchar(256) NOT NULL, category varchar(256) NOT NULL, votes INTEGER NOT NULL DEFAULT 0" + ");";
		String s4 = "CREATE TABLE IF NOT EXISTS BALLOT(" + "id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, category1 boolean NOT NULL DEFAULT 0, "
				+ "category2 boolean NOT NULL DEFAULT 0,category3 boolean NOT NULL DEFAULT 0, category4 boolean NOT NULL DEFAULT 0" + ");";
		
		s.addBatch(s1);
		s.addBatch(s2);  
		s.addBatch(s3);
		s.addBatch(s4);
		s.executeBatch();
		initialCandidates(conn);
		initialAdmin(conn);
	}
	
	public void addToVoters(VoterController voter, Connection conn) throws SQLException {
		String query =" insert into VOTERS (name, last_name, date_of_birth, ssn, username, password, created)" + " values (?, ?, ?, ?, ?, ?, ?)";
		
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
	
	public static boolean checkVoters(String social, Connection conn) throws SQLException {
		boolean result;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ssn FROM VOTERS WHERE ssn=" + social + ";");
		if(rs.next()) {
			result = true;
			return result;
		}
		else {
			result = false;
			return result;
		}
	}
	
	public static boolean checkUserLogin(String user, String pass, Connection conn) throws SQLException {
		boolean result;
		String query = "SELECT * FROM VOTERS WHERE username='" + user + "' AND password='" + pass + "'";
		PreparedStatement stmt = conn.prepareStatement(query);
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
		String query = "SELECT * FROM ADMIN WHERE username='" + user + "' AND password='" + pass + "'";
		PreparedStatement stmt = conn.prepareStatement(query);
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
	
	public int getRegVoters(Connection conn) throws SQLException{
		String query = "SELECT COUNT(*) FROM VOTERS";
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		//System.out.println("rows: "+count);
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
	
	public int getCandVotes(Connection conn, String candID) throws SQLException{
		String query="SELECT votes FROM CANDIDATES WHERE id=" + candID;
		PreparedStatement stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		return count;
	}
	
	/**
	 * Connect to the DB and do some stuff
	 */
	public static void main(String[] args) {
		database app = new database();
		app.run();
	}
}