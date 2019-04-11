package com.csci360.electionapp.view;

import com.csci360.electionapp.model.Ballot;
import com.csci360.electionapp.view.votingCheckBoxes;
import com.csci360.electionapp.controller.VoterController;
import com.csci360.electionapp.model.database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class finalVoteView {
	database db = new database();
	Connection conn;
	
	@FXML
	public Label S1;
    @FXML
    public Label S2;
    @FXML
    public Label S3;
    @FXML
    public Label S4;
    //Button variable for submitting final choices (review page)
    @FXML
    public Button finalSubmit;

    // Button variable for canceling review choices (review page)
    @FXML
    public Button finalCancel;
    
    //VoterController votingPerson;
    
    public String uname;
	
    Ballot ballot;
    
    public void initialize() {
    	//won't let me call votingCheckBoxes without method being static but that
    	//causes more problems for other methods
    	
    	/*
    	String cat1 = votingCheckBoxes.getCat1();
    	S1.setText(cat1);
    	S2.setText(votingCheckBoxes.getCat2());
    	S3.setText(votingCheckBoxes.getCat3());
    	S4.setText(votingCheckBoxes.getCat4());
    	*/
    	
        
    }

	
	public void finalCancel(ActionEvent event) throws IOException {
		String logName = "log.txt";
    	BufferedWriter writer1 = new BufferedWriter(new FileWriter(logName, true));
    	String str = LocalDateTime.now() + "\nChange Button choosen, going back to voting page\n" + uname + "\n\n";
        writer1.write(str);
        writer1.close();
    	System.out.println("Cancel chosen, going back to voting page");

        // Get the current window and close it
        Stage stage = (Stage) finalCancel.getScene().getWindow();
        stage.close();
    }
    public void finalSubmit(ActionEvent event) throws IOException,SQLException {
    	
    	String logName = "log.txt";
    	BufferedWriter writer1 = new BufferedWriter(new FileWriter(logName, true));
    	String log = LocalDateTime.now()+ "\nVote Cast\n" + uname + "\n\n";
    	writer1.write(log);
    	writer1.close();
    	conn = db.getConnection();
    	
    	db.addToBallots(ballot, conn);
    	db.addToCandidates(ballot, conn);
    	db.setStatus(uname, conn);
    	
    	String fileName = "backupBallot.txt";
    	String str = ballot.getCat1Results() + ", " + ballot.getCat2Results() +", " + ballot.getCat3Results() + ", " + ballot.getCat4Results() + "\n";
    	BufferedWriter writer2 = new BufferedWriter(new FileWriter(fileName, true));
        writer2.append(str);
         
        writer2.close();

        // Might incorporate title and resourceName
        // into a class creation method...
        String resourceName = "endPage.fxml";
        String title = "Thank You";

        // FXMLLoader variable to grab the endPage.fxml file.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resourceName));

        // Store endPage.fxml into Parent variable
        Parent root = fxmlLoader.load();

        // Create a new stage and initialize the modality
        // set the opacity to 1 and set the title and show
        // root as the scene.
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void transferMessage(Ballot ballot, String username) {
    	this.ballot = ballot;
    	S1.setText(ballot.getCat1Results());
    	S2.setText(ballot.getCat2Results());
    	S3.setText(ballot.getCat3Results());
    	S4.setText(ballot.getCat4Results());
    	uname = username;
    }
}
