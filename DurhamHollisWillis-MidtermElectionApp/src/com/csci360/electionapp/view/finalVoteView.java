package com.csci360.electionapp.view;

import com.csci360.electionapp.view.votingCheckBoxes;

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

public class finalVoteView {
	
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
    public void finalSubmit(ActionEvent event) throws IOException {

    	//unchecks all the boxes
    	//votingCheckBoxes.clear();
    	
        // Not able to get the text value from the checkBox objects
        // may need to use controller class to grab and set data??
        /*
         * S1.setText(C1.getText()); S2.setText(C4.getText()); S3.setText(C7.getText());
         * S4.setText(C11.getText());
         * 
         * System.out.println(S1.getText()); System.out.println(S2.getText());
         * System.out.println(S3.getText()); System.out.println(S4.getText());
         */

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
}
