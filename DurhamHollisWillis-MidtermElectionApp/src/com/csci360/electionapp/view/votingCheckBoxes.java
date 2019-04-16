package com.csci360.electionapp.view;


import java.io.IOException;


import com.csci360.electionapp.controller.VoterController;
import com.csci360.electionapp.model.Ballot;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.SQLException;

public class votingCheckBoxes {

	/**
     * CheckBox and Label variables for voting page
     */
	
	VoterController votingPerson;
	
	//check box initialization
	@FXML
	public CheckBox C1;
	@FXML
	public CheckBox C2;
	@FXML
	public CheckBox C3;
	@FXML
	public CheckBox C4;
	@FXML
	public CheckBox C5;
	@FXML
	public CheckBox C6;
	@FXML
	public CheckBox C7;
	@FXML
	public CheckBox C8;
	@FXML
	public CheckBox C9;
	@FXML
	public CheckBox C10;
	@FXML
	public CheckBox C11;
	@FXML
	public CheckBox C12;
	@FXML
	public Button closeButton;
	@FXML
	private Button voteSubmit;
	 
	    
	//this is making sets of checkboxes
	private ObservableSet<CheckBox> selectedPresident = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedPresident = FXCollections.observableSet();
    
    private ObservableSet<CheckBox> selectedVP = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedVP = FXCollections.observableSet();
    
    private ObservableSet<CheckBox> selectedSecretary = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedSecretary = FXCollections.observableSet();
    
    private ObservableSet<CheckBox> selectedTreasurer = FXCollections.observableSet();
    private ObservableSet<CheckBox> unselectedTreasurer = FXCollections.observableSet();
    
    private IntegerBinding numCheckBoxesSelectedPres = Bindings.size(selectedPresident);
    private IntegerBinding numCheckBoxesSelectedVP = Bindings.size(selectedVP);
    private IntegerBinding numCheckBoxesSelectedSec = Bindings.size(selectedSecretary);
    private IntegerBinding numCheckBoxesSelectedTres = Bindings.size(selectedTreasurer);
    
    //to select how many checkboxes can be select at one time
    private final int maxNumSelected =  1;
    
    //to make sure there is at least one selected
    private boolean pSelected = false;
    private boolean vpSelected = false;
    private boolean sSelected = false;
    private boolean tSelected = false;
    
    //to set which one is chosen
    public String cat1 = "";
	public String cat2 = "";
	public String cat3 = "";
	public String cat4 = "";
	Ballot ballot = new Ballot();
	String uname;
	
	//initialize method being called when scene is loaded
	/**
	 * 
	 * @param username
	 */
    public void initialize(String username) {
    	uname = username;
    	
    	//makes sure all checkboxes are not selected when scene is loaded
    	C1.setSelected(false);
    	C2.setSelected(false);
    	C3.setSelected(false);
    	C4.setSelected(false);
    	C5.setSelected(false);
    	C6.setSelected(false);
    	C7.setSelected(false);
    	C8.setSelected(false);
    	C9.setSelected(false);
    	C10.setSelected(false);
    	C11.setSelected(false);
    	C12.setSelected(false);
    	
    	
    	//these will pull the candidates from the ballot and initialize names on page
    	C1.setText(ballot.getCandidate1());
    	C2.setText(ballot.getCandidate2());
    	C3.setText(ballot.getCandidate3());
    	C4.setText(ballot.getCandidate4());
    	C5.setText(ballot.getCandidate5());
    	C6.setText(ballot.getCandidate6());
    	C7.setText(ballot.getCandidate7());
    	C8.setText(ballot.getCandidate8());
    	C9.setText(ballot.getCandidate9());
    	C10.setText(ballot.getCandidate10());
    	C11.setText(ballot.getCandidate11());
    	C12.setText(ballot.getCandidate12());
    	
    	//these methods to configre the boxes
        configureCheckBoxP(C1);
        configureCheckBoxP(C2);
        configureCheckBoxP(C3);
        
        configureCheckBoxVP(C4);
        configureCheckBoxVP(C5);
        configureCheckBoxVP(C6);
        
        configureCheckBoxS(C7);
        configureCheckBoxS(C8);
        configureCheckBoxS(C9);
        
        configureCheckBoxT(C10);
        configureCheckBoxT(C11);
        configureCheckBoxT(C12);
        
        //disables the submit button is not every category has a selection
        voteSubmit.setDisable(true);

        //adds a listener to check when box is selected
        numCheckBoxesSelectedPres.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            if (newSelectedCount.intValue() >= maxNumSelected) {
                unselectedPresident.forEach(cb -> cb.setDisable(true));
                pSelected=true;
                if(pSelected && vpSelected && sSelected && tSelected) {
                	voteSubmit.setDisable(false);
                }
                else {
                	voteSubmit.setDisable(true);
                }
            } else {
                unselectedPresident.forEach(cb -> cb.setDisable(false));
                pSelected=false;
                if(pSelected && vpSelected && sSelected && tSelected) {
                	voteSubmit.setDisable(false);
                }
                else {
                	voteSubmit.setDisable(true);
                }
            }
        });
        
        numCheckBoxesSelectedVP.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            if (newSelectedCount.intValue() >= maxNumSelected) {
                unselectedVP.forEach(cb -> cb.setDisable(true));
                vpSelected=true;
                if(pSelected && vpSelected && sSelected && tSelected) {
                	voteSubmit.setDisable(false);
                }
                else {
                	voteSubmit.setDisable(true);
                }
            } else {
                unselectedVP.forEach(cb -> cb.setDisable(false));
                vpSelected=false;
                if(pSelected && vpSelected && sSelected && tSelected) {
                	voteSubmit.setDisable(false);
                }
                else {
                	voteSubmit.setDisable(true);
                }
            }
        });
        
        numCheckBoxesSelectedSec.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            if (newSelectedCount.intValue() >= maxNumSelected) {
                unselectedSecretary.forEach(cb -> cb.setDisable(true));
                sSelected=true;
                if(pSelected && vpSelected && sSelected && tSelected) {
                	voteSubmit.setDisable(false);
                }
                else {
                	voteSubmit.setDisable(true);
                }
            } else {
                unselectedSecretary.forEach(cb -> cb.setDisable(false));
                sSelected=false;
                if(pSelected && vpSelected && sSelected && tSelected) {
                	voteSubmit.setDisable(false);
                }
                else {
                	voteSubmit.setDisable(true);
                }
            }
        });
        
        numCheckBoxesSelectedTres.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            if (newSelectedCount.intValue() >= maxNumSelected) {
                unselectedTreasurer.forEach(cb -> cb.setDisable(true));
                tSelected=true;
                if(pSelected && vpSelected && sSelected && tSelected) {
                	voteSubmit.setDisable(false);
                }
                else {
                	voteSubmit.setDisable(true);
                }
            } else {
                unselectedTreasurer.forEach(cb -> cb.setDisable(false));
                tSelected=false;
                if(pSelected && vpSelected && sSelected && tSelected) {
                	voteSubmit.setDisable(false);
                }
                else {
                	voteSubmit.setDisable(true);
                }
            }
        });


    }
    //configures the checkboxes to add the lists
    /**
     * 
     * @param checkBox
     */
    public void configureCheckBoxP(CheckBox checkBox) {

        if (checkBox.isSelected()) {
            selectedPresident.add(checkBox);
        } else {
            unselectedPresident.add(checkBox);
        }

        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                unselectedPresident.remove(checkBox);
                selectedPresident.add(checkBox);
            } else {
                selectedPresident.remove(checkBox);
                unselectedPresident.add(checkBox);
            }

        });

    }
    
    /**
     * 
     * @param checkBox
     */
    private void configureCheckBoxVP(CheckBox checkBox) {

        if (checkBox.isSelected()) {
            selectedVP.add(checkBox);
        } else {
            unselectedVP.add(checkBox);
        }

        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
            	unselectedVP.remove(checkBox);
                selectedVP.add(checkBox);
            } else {
            	selectedVP.remove(checkBox);
            	unselectedVP.add(checkBox);
            }

        });

    }
    
    /**
     * 
     * @param checkBox
     */
    private void configureCheckBoxS(CheckBox checkBox) {

        if (checkBox.isSelected()) {
            selectedSecretary.add(checkBox);
        } else {
            unselectedSecretary.add(checkBox);
        }

        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
            	unselectedSecretary.remove(checkBox);
                selectedSecretary.add(checkBox);
            } else {
            	selectedSecretary.remove(checkBox);
            	unselectedSecretary.add(checkBox);
            }

        });
    }
    
    /**
     * 
     * @param checkBox
     */
    private void configureCheckBoxT(CheckBox checkBox) {

        if (checkBox.isSelected()) {
            selectedTreasurer.add(checkBox);
        } else {
            unselectedTreasurer.add(checkBox);
        }

        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
            	unselectedTreasurer.remove(checkBox);
                selectedTreasurer.add(checkBox);
            } else {
            	selectedTreasurer.remove(checkBox);
            	unselectedTreasurer.add(checkBox);
            }

        });

    }
    
    /**
     * 
     * @param event
     * @throws IOException
     */
    public void btnClose_clicked(ActionEvent event) throws IOException {
        // Get the current window and close it
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }
    
    /**
     * 
     * @param event
     * @throws IOException
     *  @throws SQLException 
     */
    
    public void voteSubmit(ActionEvent event) throws IOException, SQLException {
        //grab t/f from checkboxes and call ballot methods
        ballot.setVoteCand1(C1.isSelected());
        ballot.setVoteCand2(C2.isSelected());
        ballot.setVoteCand3(C3.isSelected());
        ballot.setVoteCand4(C4.isSelected());
        ballot.setVoteCand5(C5.isSelected());
        ballot.setVoteCand6(C6.isSelected());
        ballot.setVoteCand7(C7.isSelected());
        ballot.setVoteCand8(C8.isSelected());
        ballot.setVoteCand9(C9.isSelected());
        ballot.setVoteCand10(C10.isSelected());
        ballot.setVoteCand11(C11.isSelected());
        ballot.setVoteCand12(C12.isSelected());
        
        // Might incorporate title and resourceName
        // into a class creation method...
        String resourceName = "finalSubmit.fxml";
        String title = "Vote Confirmation Page";

        // FXMLLoader variable to grab the finalSubmit.fxml file.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resourceName));

        // Store finalSubmit.fxml into Parent variable
        Parent root = fxmlLoader.load();

        finalVoteView sceneController = fxmlLoader.getController();
        sceneController.transferMessage(ballot, uname);
        // Create a new stage and initialize the modality
        // set the opacity to 1 and set the title and show
        // root as the scene.
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    
    /**
     * 
     * @return
     */
    public String getCat1() {
    	if(C1.isSelected()) {
        	cat1=(C1.getText());
        	}
        if(C2.isSelected()) {
        	cat1=(C2.getText());
        	}
        if(C3.isSelected()) {
        	cat1=(C3.getText());
        	}
        return cat1;
    }
    
    /**
     * 
     * @return
     */
    public String getCat2() {
    	if(C4.isSelected()) {
        	cat2=(C4.getText());
        	}
        if(C5.isSelected()) {
        	cat2=(C5.getText());
        	}
        if(C6.isSelected()) {
        	cat2=(C6.getText());
        	}
        return cat2;
    }
    
    /**
     * 
     * @return
     */
    public String getCat3() {
        if(C7.isSelected()) {
        	cat3=(C7.getText());
        	}
        if(C8.isSelected()) {
        	cat3=(C8.getText());
        	}
        if(C9.isSelected()) {
        	cat3=(C9.getText());
        	}
        return cat3;
    }
    
    /**
     * 
     * @return
     */
    public String getCat4() {
        if(C10.isSelected()) {
        	cat4=(C10.getText());
        	}
        if(C11.isSelected()) {
        	cat4=(C11.getText());
        	}
        if(C12.isSelected()) {
        	cat4=(C12.getText());
        	}
        return cat4;
    }
    
    public void clear() {
    	C1.setSelected(false);
    	C2.setSelected(false);
    	C3.setSelected(false);
    	C4.setSelected(false);
    	C5.setSelected(false);
    	C6.setSelected(false);
    	C7.setSelected(false);
    	C8.setSelected(false);
    	C9.setSelected(false);
    	C10.setSelected(false);
    	C11.setSelected(false);
    	C12.setSelected(false);
    }
}