package com.csci360.electionapp.model;
import com.csci360.electionapp.controller.VoterController;
import com.csci360.electionapp.controller.registrationController;
import com.csci360.electionapp.security.*;
import com.csci360.electionapp.model.Ballot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
	private final String password = "sqlserver";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "election_database";
	
	
	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		//Create a null connection to build up on
		Connection conn = null;
		//Create a Properties variable to build up on
		Properties connectionProps = new Properties();
		//Set user as this.userName; set password as this.password;
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
		//Add property of creating the database if it does not already exist
		connectionProps.put("createDatabaseIfNotExist", "true");
		//Combine the connection properties and final information from above.
		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName, connectionProps);
		return conn;
	}
	
	/**
	 * 
	 * @param conn
	 * @throws SQLException
	 * @throws IOException
	 */
	public void createTables(Connection conn) throws SQLException, IOException {
		//Create all tables on startup
		Statement s = conn.createStatement();
		//Create VOTERS table
		String s1 = "CREATE TABLE IF NOT EXISTS VOTERS(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(256) NOT NULL, "
				+ "last_name varchar(256) NOT NULL, date_of_birth date NOT NULL, ssn varchar(128) NOT NULL, username varchar(256) NOT NULL, "
				+ "password varchar(256) NOT NULL, created datetime NOT NULL, salt binary(16) NOT NULL, status boolean NOT NULL DEFAULT 0, "
				+ "CONSTRAINT Unique_ssn UNIQUE KEY(ssn));";
		//Create ADMIN table
		String s2 = "CREATE TABLE IF NOT EXISTS ADMIN(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(256) NOT NULL, "
				+ "last_name varchar(256) NOT NULL, username varchar(256) NOT NULL, password varchar(256) NOT NULL, CONSTRAINT Unique_user UNIQUE KEY(username));";
		//Create CANDIDATES table
		String s3 = "CREATE TABLE IF NOT EXISTS CANDIDATES(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,name varchar(256) NOT NULL, "
				+ "last_name varchar(256) NOT NULL, category varchar(256) NOT NULL, votes INTEGER NOT NULL DEFAULT 0, "
				+ "CONSTRAINT Unique_cand UNIQUE KEY(name,last_name,category));";
		//Create BALLOT table
		String s4 = "CREATE TABLE IF NOT EXISTS BALLOT(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, category1 varchar(256) NOT NULL, "
				+ "category2 varchar(256) NOT NULL,category3 varchar(256) NOT NULL, category4 varchar(256) NOT NULL);";
		//Add queries to a batch
		s.addBatch(s1);
		s.addBatch(s2);  
		s.addBatch(s3);
		s.addBatch(s4);
		//Execute the batch
		s.executeBatch();
		//Initialize candidates
		initialCandidates(conn);
		//Initialize Admins
		initialAdmin(conn);
	}
	
	/**
	 * 
	 * @param voter
	 * @param salt
	 * @param conn
	 * @throws SQLException
	 */
	public void addToVoters(VoterController voter, byte[] salt, Connection conn) throws SQLException {
		//Add a new voter with the given information
		String query = " INSERT INTO VOTERS (name, last_name, date_of_birth, ssn, username, password, created, salt)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		//Set values accordingly
		preparedStmt.setString(1, voter.getVoterFirstName());
		preparedStmt.setString(2,  voter.getVoterLastName());
		preparedStmt.setString(3, voter.convertDOB());
		preparedStmt.setString(4,  voter.getVoterSSN());
		preparedStmt.setString(5,  voter.getVoterUsername());
		preparedStmt.setString(6,  voter.getVoterPassword());
		preparedStmt.setString(7, voter.getTime());
		preparedStmt.setBytes(8, salt);
		//Execute statement
		preparedStmt.execute();
		//Close connection
	    conn.close();
	}
	
	/**
	 * 
	 * @param ballot
	 * @param conn
	 * @throws SQLException
	 */
	public void addToCandidates(Ballot ballot, Connection conn) throws SQLException {
		//Split the first and last names apart into two strings
		String query1 = "SELECT votes FROM CANDIDATES WHERE name=? AND last_name=? AND category=?;";		
		PreparedStatement ps1 = conn.prepareStatement(query1);
		//Get category 1 winner
		String cat1 = ballot.getCat1Results();
		//Split the first and last names apart into two strings
		String[] winner1 = cat1.split("\\s+");
		String winner1First = winner1[0];
		String winner1Last = winner1[1];
		//Set values accordingly
		ps1.setString(1, winner1First);
		ps1.setString(2, winner1Last);
		ps1.setString(3, ballot.getCategory1());
		ResultSet rs1 = ps1.executeQuery();
		rs1.next();
		//Increase candidate votes by 1 and update with an update query
		int cat1Votes = rs1.getInt("votes") + 1;
		String finalquery1 = "UPDATE CANDIDATES set votes=? WHERE name=? AND last_name=? AND category=?;";
		PreparedStatement stmt1 = conn.prepareStatement(finalquery1);
		//Set the proper values with proper information
		stmt1.setInt(1, cat1Votes);
		stmt1.setString(2, winner1First);
		stmt1.setString(3, winner1Last);
		stmt1.setString(4, ballot.getCategory1());
		stmt1.executeUpdate();
		//Close the first statement
		ps1.close();
		//Grab votes from candidate with given name and category
		String query2 = "SELECT votes FROM CANDIDATES WHERE name=? AND last_name=? AND category=?;";
		PreparedStatement ps2 = conn.prepareStatement(query2);
		String cat2 = ballot.getCat2Results();
		//Split the first and last names apart into two strings
		String[] winner2 = cat2.split("\\s+");
		String winner2First = winner2[0];
		String winner2Last = winner2[1];
		//Set the proper values with proper information
		ps2.setString(1, winner2First);
		ps2.setString(2, winner2Last);
		ps2.setString(3, ballot.getCategory2());
		ResultSet rs2 = ps2.executeQuery();
		rs2.next();
		//Increase votes by 1
		int cat2Votes = rs2.getInt("votes") + 1;
		String finalquery2 = "UPDATE CANDIDATES set votes=? WHERE name=? AND last_name=? AND category=?;";
		PreparedStatement stmt2 = conn.prepareStatement(finalquery2);
		//Set proper values with proper information
		stmt2.setInt(1, cat2Votes);
		stmt2.setString(2, winner2First);
		stmt2.setString(3, winner2Last);
		stmt2.setString(4, ballot.getCategory2());
		stmt2.executeUpdate();
		//Close the second statement
		ps2.close();
		//Grab votes from candidate with given name and category
		String query3 = "SELECT votes FROM CANDIDATES WHERE name=? AND last_name=? AND category=?;";
		PreparedStatement ps3 = conn.prepareStatement(query3);
		String cat3 = ballot.getCat3Results();
		//Split the first and last names apart into two strings
		String[] winner3 = cat3.split("\\s+");
		String winner3First = winner3[0];
		String winner3Last = winner3[1];
		//Set proper values with proper information
		ps3.setString(1, winner3First);
		ps3.setString(2, winner3Last);
		ps3.setString(3, ballot.getCategory3());
		ResultSet rs3 = ps3.executeQuery();
		rs3.next();
		//Increase votes by 1 and update
		int cat3Votes = rs3.getInt("votes") + 1;
		//Update votes for candidate
		String finalquery3 = "UPDATE CANDIDATES set votes=? WHERE name=? AND last_name=? AND category=?;";
		PreparedStatement stmt3 = conn.prepareStatement(finalquery3);
		//Set proper values with proper information
		stmt3.setInt(1, cat3Votes);
		stmt3.setString(2, winner3First);
		stmt3.setString(3, winner3Last);
		stmt3.setString(4, ballot.getCategory3());
		stmt3.executeUpdate();
		//Close the third statement
		ps3.close();
		//Set up and run query 4 for the fourth category that is available; Grab the votes for the proper candidate
		String query4 = "SELECT votes FROM CANDIDATES WHERE name=? AND last_name=? AND category=?;";
		PreparedStatement ps4 = conn.prepareStatement(query4);
		//Grab category 4 result winner
		String cat4 = ballot.getCat4Results();
		//Split the first and last names apart into two strings
		String[] winner4 = cat4.split("\\s+");
		String winner4First = winner4[0];
		String winner4Last = winner4[1];
		//Set proper values with proper information
		ps4.setString(1, winner4First);
		ps4.setString(2, winner4Last);
		ps4.setString(3, ballot.getCategory4());
		ResultSet rs4 = ps4.executeQuery();
		rs4.next();
		//Increase votes for candidate by 1
		int cat4Votes = rs4.getInt("votes") + 1;
		String finalquery4 = "UPDATE CANDIDATES set votes=? WHERE name=? AND last_name=? AND category=?;";
		PreparedStatement stmt4 = conn.prepareStatement(finalquery4);
		//Set proper values with proper information
		stmt4.setInt(1, cat4Votes);
		stmt4.setString(2, winner4First);
		stmt4.setString(3, winner4Last);
		stmt4.setString(4, ballot.getCategory4());
		stmt4.executeUpdate();
		//Close statement 4
		ps4.close();
	}
	
	/**
	 * 
	 * @param ballot
	 * @param conn
	 * @throws SQLException
	 */
	public void addToBallots(Ballot ballot, Connection conn) throws SQLException {
		//Insert a new ballot
		String query = "INSERT INTO BALLOT(category1, category2, category3, category4) VALUES(?,?,?,?);";
		//Set statement with proper values needed and run the query
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, ballot.getCat1Results());
		ps.setString(2, ballot.getCat2Results());
		ps.setString(3, ballot.getCat3Results());
		ps.setString(4, ballot.getCat4Results());
		ps.executeUpdate();
	}
	
	/**
	 * 
	 * @param social
	 * @param conn
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static boolean checkVoters(String social, Connection conn) throws SQLException, IOException {
		boolean result;
		//Encrypt the social security number for security reasons
		String encryptedSocial = Security.encrypt(social);
		//Grab all ssn from the voters that match the given ssn
		String query = "SELECT ssn FROM VOTERS WHERE ssn=?;";
		//Set up the statement to be ran
		PreparedStatement stmt = conn.prepareStatement(query);
		//Database stores encrypted ssn's so comparing encrypted values is the way to go
		stmt.setString(1, encryptedSocial);
		ResultSet rs = stmt.executeQuery();
		//If a result set is not null that means the ssn is already in use
		if(rs.next()) {
			result = true;
			return result;
		}
		else {
			result = false;
			return result;
		}
	}
	
	/**
	 * 
	 * @param username
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public String checkUsername(String username, Connection conn) throws SQLException {
		String newUsername = username;
		String usernameNum = "";
		boolean uniqueUser = false;
		//Two queries needed for repeated query calls until unique user is found
		String query = "SELECT username FROM VOTERS WHERE username=?;";
		String query1 = "SELECT username FROM VOTERS WHERE username=?;";
		//Set up the first query - query
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		//If the query returns a result set that is not null it is not a unique username
		if(rs.next()) {
			int count = 0;
			//Call repeatedly until a unique user is found
			while(uniqueUser != true) {
				newUsername = username;
				//Cast count integer to a string to concatenate to the username
				usernameNum = newUsername + Integer.toString(count);
				//Set up and execute the next query - query1
				PreparedStatement stmt1 = conn.prepareStatement(query1);
				stmt1.setString(1, usernameNum);
				ResultSet rs1 = stmt1.executeQuery();
				//If there is a result set a unique user is not found yet
				if(rs1.next()) {
					uniqueUser = false;
				}
				//Else a unique user is found
				else {
					uniqueUser = true;
				}
				count++;
			}
			newUsername = usernameNum;
		}
		return newUsername;
	}
	
	/**
	 * 
	 * @param user
	 * @param pass
	 * @param conn
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static boolean checkUserLogin(String user, String pass, Connection conn) throws SQLException, IOException {
		//Create variables for the matching user login info and byte array for the salt check
		boolean result;
		byte[] salt;
		//Grab passwords and salt of the given username
		String query = "SELECT password, salt FROM VOTERS WHERE username=?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, user);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			//Hash the plain-text password given and compare the hashes of the given username
			String hashedPass = rs.getString(1);
			salt = rs.getBytes(2);
			result = Security.comparePasswords(pass, salt, hashedPass);
		}
		//Return the boolean value
		else {
			result = false;
		}
		return result;
	}
	
	/**
	 * 
	 * @param user
	 * @param pass
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static boolean checkAdminLogin(String user, String pass, Connection conn) throws SQLException {
		boolean result;
		//Grab all Admin users with the specified information
		String query = "SELECT * FROM ADMIN WHERE username=? AND password=?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		//Set user to the 1st unknown value
		stmt.setString(1, user);
		//Set pass to the 2nd unknown value
		stmt.setString(2, pass);
		//Grab a result set of the query executed
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			result = true;
		}
		else {
			result = false;
		}
		return result;
	}
	
	/**
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public boolean countVotes(Connection conn) throws SQLException, IOException {
		boolean votesMatch = false;
		//Call getNumBallots to get total number of ballots cast
		int voteCount = getNumBallots(conn);
		int lines = 0;
		//Set up a file to buffer read from
		File tempFile = new File("backupBallot.txt");
		//Check if the file even exists
		boolean exists = tempFile.exists();
		if(exists) {
			BufferedReader reader = new BufferedReader(new FileReader("backupBallot.txt"));
			while (reader.readLine() != null) {
				lines++;
			}
			//Close the reader
			reader.close();
			//If the two numbers match then the votes are up to date
			if(voteCount == lines) {
				votesMatch = true;
			}
			return votesMatch;
		}
		else {
			return true;
		}
	}
	
	/**
	 * 
	 * @param conn
	 * @throws SQLException
	 * @throws IOException
	 */
	public void initialAdmin(Connection conn) throws SQLException, IOException {
		//Create an admin entry in the ADMIN table if it does not already exist
		String query = "INSERT IGNORE INTO ADMIN(name, last_name, username, password)" + " VALUES(?, ?, ?, ?);";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		int count = 0;
		//Read the file
		FileReader file = new FileReader("./src/com/csci360/electionapp/model/admin.txt");
		//BufferRead everything from the file
	    BufferedReader reader = new BufferedReader(file);
	    //Set a string array with size of 5 (name, last_name, username, password)
	    String[] admin = new String[5];
	    String key = "";
	    String line = reader.readLine();
	    //Iterate through the reader until EOF (end of file)
	    while (line != null) {
	    	key = line;
	        line = reader.readLine();
	        admin[count] = key;
	        count++;
	    }
	    count = 1;
	    int i = 0;
	    //Set the query values with the information in the admin string array
	    while(i < admin.length-1) {
	    	preparedStmt.setString(count, admin[i]);
	    	count++;
	    	i++;
	    }
		//Execute the statement; close the reader; close the connection
		preparedStmt.execute();
		reader.close();
		conn.close();
	}
	
	/**
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	public void initialCandidates(Connection conn) throws SQLException {
		//Create a new ballot to hard set the candidate names
		Ballot ballot = new Ballot();
		Statement s = conn.createStatement();
		//All string queries for all the candidates
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
		//Add the queries to a batch and run all of them
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
	
	/**
	 * 
	 * @param username
	 * @param conn
	 * @throws SQLException
	 */
	public void setStatus(String username, Connection conn) throws SQLException {
		//Update voters status that have voted to prevent double voting
		String query = "UPDATE VOTERS SET status=1 WHERE username=?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, username);
		//Execute the update query
		stmt.executeUpdate();
		conn.close();
	}
	
	/**
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public int getRegVoters(Connection conn) throws SQLException {
		//Counts all entries in VOTERS table
		String query = "SELECT COUNT(*) FROM VOTERS;";
		PreparedStatement stmt = conn.prepareStatement(query);
		//Run the query store the results into a result set
		ResultSet rs = stmt.executeQuery();
		//Set cursor to the next line of the result set
		rs.next();
		//Get the integer of the 1st column of the result set
		int count = rs.getInt(1);
		return count;
	}

	/**
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public int getNumBallots(Connection conn) throws SQLException {
		//Counts all entries in BALLOT table
		String query= "SELECT COUNT(*) FROM BALLOT";
		PreparedStatement stmt = conn.prepareStatement(query);
		//Run the query store the results into a result set
		ResultSet rs = stmt.executeQuery();
		//Set cursor to the next line of the result set
		rs.next();
		//Get the integer of the 1st column of the result set
		int count = rs.getInt(1);
		return count;
	}
	
	/**
	 * 
	 * @param conn
	 * @param candID
	 * @return
	 * @throws SQLException
	 */
	public int getCandVotes(Connection conn, String candID) throws SQLException {
		//Grab all the candidate votes
		String query="SELECT votes FROM CANDIDATES WHERE id=?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, candID);
		ResultSet rs = stmt.executeQuery();
		//Set cursor to next line of the result set
		rs.next();
		//Grab the integer value from the result set
		int count = rs.getInt(1);
		return count;
	}
	
	//1=true, has voted; 0=false, hasn't voted
	/**
	 * 
	 * @param conn
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public boolean getStatusToVote(Connection conn, String username) throws SQLException {
		//Checks whether or not a user has voted or not
		boolean canVote;
		String query="SELECT status FROM VOTERS WHERE username=?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, username);
		//Store the results into a set to grab later
		ResultSet rs = stmt.executeQuery();
		//Move cursor to the first entry of the result set
		rs.next();
		//Grabs the boolean result from the result set
		canVote = rs.getBoolean(1);
		return canVote;
	}
	
	/**
	 * 
	 * @param conn
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public java.util.Date getCreatedDate(Connection conn, String username) throws SQLException {
		//Grabs the date that the entry with the given username was registered to vote
		String query="SELECT created FROM VOTERS WHERE username=?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		//Set the value for username in the PreparedStatement to username
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		//Create and return a Timestamp database object
		Timestamp created = rs.getTimestamp("created");
		return created;
	}
	
	/**
	 * 
	 * @param conn
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public void addTester(Connection conn) throws SQLException, NoSuchAlgorithmException, IOException {
		database db = new database();
		//Information for the test voter
		VoterController v = registrationController.createVoter("Tester", "Tester", "10", "01", "1990", "269715403", "TestUser123*");
		//Call add function of registration controller
		registrationController.add(v,db);
		//Updating testUser for voting purposes
		String query = "UPDATE VOTERS SET created='2019-04-05 17:21:49', username='testUser' WHERE username='testerT';";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.execute();
	}
	
	/**
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	public void deleteTester(Connection conn) throws SQLException {
		//Deletes testUser from database
		String query = "DELETE FROM VOTERS WHERE username=?;";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, "testUser");
		stmt.executeUpdate();
	}
}