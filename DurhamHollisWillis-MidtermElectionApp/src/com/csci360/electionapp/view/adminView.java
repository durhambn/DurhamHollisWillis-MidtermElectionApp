package com.csci360.electionapp.view;

import java.io.IOException;

import com.csci360.electionapp.model.Ballot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class adminView {

    @FXML
    public Label cand1;
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
   private Button closeButton;

	public void initialize() {
		
		Ballot ballot = new Ballot();
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
	}
	public void btnClose_clicked(ActionEvent event) throws IOException {
        // Get the current window and close it
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }
}
