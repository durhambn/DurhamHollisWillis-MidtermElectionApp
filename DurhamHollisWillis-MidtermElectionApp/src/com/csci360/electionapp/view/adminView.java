package com.csci360.electionapp.view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.csci360.electionapp.model.Ballot;
import com.csci360.electionapp.model.database;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class adminView {

	database db = new database();
	
    @FXML
    private Label cand1;
    @FXML
    private Label cand2;
    @FXML
    private Label cand3;
    @FXML
    private Label cand4;
    @FXML
    private Label cand5;
    @FXML
    private Label cand6;
    @FXML
    private Label cand7;
    @FXML
    private Label cand8;
    @FXML
    private Label cand9;
    @FXML
    private Label cand10;
    @FXML
    private Label cand11;
    @FXML
    private Label cand12;
    
   
    @FXML
    private Label cand1votes;
    @FXML
    private Label cand2votes;
    @FXML
    private Label cand3votes;
    @FXML
    private Label cand4votes;
    @FXML
    private Label cand5votes;
    @FXML
    private Label cand6votes;
    @FXML
    private Label cand7votes;
    @FXML
    private Label cand8votes;
    @FXML
    private Label cand9votes;
    @FXML
    private Label cand10votes;
    @FXML
    private Label cand11votes;
    @FXML
    private Label cand12votes;
    
    @FXML
    private Label V1;
    @FXML
    public Label V2;
    
   @FXML
   private Button closeButton;
   @FXML
   private Button reTally;
   @FXML
   private Button add;
   @FXML
   private Button delete;
   
   Connection conn;

	public void initialize() throws SQLException {
		
		Ballot ballot = new Ballot();
		conn = db.getConnection();
		
		cand1.setText(ballot.getCandidate1());
		cand2.setText(ballot.getCandidate2());
		cand3.setText(ballot.getCandidate3());
		cand4.setText(ballot.getCandidate4());
		cand5.setText(ballot.getCandidate5());
		cand6.setText(ballot.getCandidate6());
		cand7.setText(ballot.getCandidate7());
		cand8.setText(ballot.getCandidate8());
		cand9.setText(ballot.getCandidate9());
		cand10.setText(ballot.getCandidate10());
		cand11.setText(ballot.getCandidate11());
		cand12.setText(ballot.getCandidate12());
		
		V1.setText(String.valueOf(db.getRegVoters(conn)));
		V2.setText(String.valueOf(db.getNumBallots(conn)));
		
		
		cand1votes.setText(String.valueOf(db.getCandVotes(conn, "1")));
		cand2votes.setText(String.valueOf(db.getCandVotes(conn, "2")));
		cand3votes.setText(String.valueOf(db.getCandVotes(conn, "3")));
		cand4votes.setText(String.valueOf(db.getCandVotes(conn, "4")));
		cand5votes.setText(String.valueOf(db.getCandVotes(conn, "5")));
		cand6votes.setText(String.valueOf(db.getCandVotes(conn, "6")));
		cand7votes.setText(String.valueOf(db.getCandVotes(conn, "7")));
		cand8votes.setText(String.valueOf(db.getCandVotes(conn, "8")));
		cand9votes.setText(String.valueOf(db.getCandVotes(conn, "9")));
		cand10votes.setText(String.valueOf(db.getCandVotes(conn, "10")));
		cand11votes.setText(String.valueOf(db.getCandVotes(conn, "11")));
		cand12votes.setText(String.valueOf(db.getCandVotes(conn, "12")));
		
	}
	public void btnClose_clicked(ActionEvent event) throws IOException {
        // Get the current window and close it
		System.out.println("Admin logged out");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }

	public void reTally(ActionEvent event) throws IOException, SQLException{
		int numBallots = db.getNumBallots(conn);
		String ballots = String.valueOf(numBallots);
		String v2 = V2.getText();
		System.out.println(ballots);
		System.out.println(v2);
		if (db.countVotes(conn) && (ballots.equals(v2))) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(reTally.getScene().getWindow());
			alert.setTitle("Re-Tally");
			alert.setHeaderText("Re-Tally Votes");
			alert.setContentText("Information on page in up to date.");
			alert.showAndWait();
			System.out.println("Re-tallying votes");
			
			String fileName = "log.txt";
		    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
		    String str = LocalDateTime.now() + "\nRe-tally votes\n\n";
		    writer.append(str);
		     
		    writer.close();
			initialize();
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(reTally.getScene().getWindow());
			alert.setTitle("Re-Tally");
			alert.setHeaderText("Re-Tally Votes");
			alert.setContentText("The backupBallot file doesn't match database.");
			alert.showAndWait();
		}
		
	}
	public void addTest(ActionEvent event) throws SQLException, NoSuchAlgorithmException, IOException {
		db.addTester(conn);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(reTally.getScene().getWindow());
		alert.setTitle("ADD TESTER");
		alert.setHeaderText("Added Tester User");
		alert.setContentText("The tester's username is: testerUser \npassword: Test123*");
		alert.showAndWait();
	}
	public void deleteTest(ActionEvent event) throws SQLException {
		db.deleteTester(conn);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(reTally.getScene().getWindow());
		alert.setTitle("DELETE TESTER");
		alert.setHeaderText("Deleted Tester User");
		//alert.setContentText("The tester's username is: testerUser \npassword: Test123*");
		alert.showAndWait();
	}
}
