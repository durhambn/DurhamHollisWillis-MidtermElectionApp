package com.csci360.electionapp.view;

import com.csci360.electionapp.model.Ballot;
import com.csci360.electionapp.view.votingCheckBoxes;
import com.csci360.electionapp.controller.VoterController;
import com.csci360.electionapp.model.database;

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
 // Button variable for submitting final choices (review page)
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
        System.out.println("Cancel chosen, going back to voting page");

        // Get the current window and close it
        Stage stage = (Stage) finalCancel.getScene().getWindow();
        stage.close();
    }
    public void finalSubmit(ActionEvent event) throws IOException,SQLException {

    	//unchecks all the boxes
    	//votingCheckBoxes.clear();
    	
    	//changes voted status to 1 somehow
    	//sends ballot information to database
    	
        // Not able to get the text value from the checkBox objects
        // may need to use controller class to grab and set data??
        /*
         * S1.setText(C1.getText()); S2.setText(C4.getText()); S3.setText(C7.getText());
         * S4.setText(C11.getText());
         * 
         * System.out.println(S1.getText()); System.out.println(S2.getText());
         * System.out.println(S3.getText()); System.out.println(S4.getText());
         */
    	
    	db.addToBallots(ballot, conn);
    	db.addToCandidates(ballot, conn);

    	conn = db.getConnection();

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
