package com.csci360.electionapp.view;


import java.io.IOException;


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

public class votingCheckBoxes {

	/**
     * CheckBox and Label variables for voting page
     */
	
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
    
    private final int maxNumSelected =  1;
    private boolean pSelected = false;
    private boolean vpSelected = false;
    private boolean sSelected = false;
    private boolean tSelected = false;
    
    public String cat1 = "";
	public String cat2 = "";
	public String cat3 = "";
	public String cat4 = "";
    
    public void initialize() {
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
    	//C1.setText(BallotController.getBallotCandidate1());
    	//C2.setText(BallotController.getBallotCandidate2());
    	//C3.setText(BallotController.getBallotCandidate1());
    	//C4.setText(BallotController.getBallotCandidate2());
    	//C5.setText(BallotController.getBallotCandidate1());
    	//C6.setText(BallotController.getBallotCandidate2());
    	//C7.setText(BallotController.getBallotCandidate1());
    	//C8.setText(BallotController.getBallotCandidate2());
    	//C9.setText(BallotController.getBallotCandidate1());
    	//C10.setText(BallotController.getBallotCandidate2());
    	//C11.setText(BallotController.getBallotCandidate1());
    	//C12.setText(BallotController.getBallotCandidate2());
    	
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
        

        voteSubmit.setDisable(true);

        numCheckBoxesSelectedPres.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            if (newSelectedCount.intValue() >= maxNumSelected) {
                unselectedPresident.forEach(cb -> cb.setDisable(true));
                //voteSubmit.setDisable(false);
                pSelected=true;
                if(pSelected && vpSelected && sSelected && tSelected) {
                	voteSubmit.setDisable(false);
                }
                else {
                	voteSubmit.setDisable(true);
                }
            } else {
                unselectedPresident.forEach(cb -> cb.setDisable(false));
                //voteSubmit.setDisable(true);
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
                //voteSubmit.setDisable(false);
                vpSelected=true;
                if(pSelected && vpSelected && sSelected && tSelected) {
                	voteSubmit.setDisable(false);
                }
                else {
                	voteSubmit.setDisable(true);
                }
            } else {
                unselectedVP.forEach(cb -> cb.setDisable(false));
                //voteSubmit.setDisable(true);
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
                //voteSubmit.setDisable(false);
                sSelected=true;
                if(pSelected && vpSelected && sSelected && tSelected) {
                	voteSubmit.setDisable(false);
                }
                else {
                	voteSubmit.setDisable(true);
                }
            } else {
                unselectedSecretary.forEach(cb -> cb.setDisable(false));
                //voteSubmit.setDisable(true);
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
                //voteSubmit.setDisable(false);
                tSelected=true;
                if(pSelected && vpSelected && sSelected && tSelected) {
                	voteSubmit.setDisable(false);
                }
                else {
                	voteSubmit.setDisable(true);
                }
            } else {
                unselectedTreasurer.forEach(cb -> cb.setDisable(false));
                //voteSubmit.setDisable(true);
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
    public void btnClose_clicked(ActionEvent event) throws IOException {
        // Get the current window and close it
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }
    
    /**
     * 
     * @param event
     * @throws IOException
     */
    
    public void voteSubmit(ActionEvent event) throws IOException {
        // Print statements for testing purposes (remove later)
    	
        System.out.println(C1.getText());
        System.out.println(C1.isSelected());
        System.out.println(C2.getText());
        System.out.println(C2.isSelected());
        System.out.println(C3.getText());
        System.out.println(C3.isSelected());
        System.out.println(C4.getText());
        System.out.println(C4.isSelected());
        System.out.println(C5.getText());
        System.out.println(C5.isSelected());
        System.out.println(C6.getText());
        System.out.println(C6.isSelected());
        System.out.println(C7.getText());
        System.out.println(C7.isSelected());
        System.out.println(C8.getText());
        System.out.println(C8.isSelected());
        System.out.println(C9.getText());
        System.out.println(C9.isSelected());
        System.out.println(C10.getText());
        System.out.println(C10.isSelected());
        System.out.println(C11.getText());
        System.out.println(C11.isSelected());
        System.out.println(C12.getText());
        System.out.println(C12.isSelected());
        
    	

        // Trying to store checkbox text into a variable to grab
        // Not working; may need to use Controller class??
        /*
         * String vote1 = C1.getText(); String vote2 = C4.getText(); String vote3 =
         * C7.getText(); String vote4 = C11.getText(); S1.setText(vote1);
         * S2.setText(vote2); S3.setText(vote3); S4.setText(vote4);
         */

        // Might incorporate title and resourceName
        // into a class creation method...
        String resourceName = "finalSubmit.fxml";
        String title = "Vote Confirmation Page";

        // FXMLLoader variable to grab the finalSubmit.fxml file.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resourceName));

        // Store finalSubmit.fxml into Parent variable
        Parent root = fxmlLoader.load();

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
    
    
    public String getCat1() {
    	if(C1.isSelected()) {
        	System.out.println(C1.getText());
        	cat1=(C1.getText());
        	}
        if(C2.isSelected()) {
        	System.out.println(C2.getText());
        	cat1=(C2.getText());
        	}
        if(C3.isSelected()) {
        	System.out.println(C3.getText());
        	cat1=(C3.getText());
        	}
        return cat1;
    }
    public String getCat2() {
    	if(C4.isSelected()) {
        	System.out.println(C4.getText());
        	cat2=(C4.getText());
        	}
        if(C5.isSelected()) {
        	System.out.println(C5.getText());
        	cat2=(C5.getText());
        	}
        if(C6.isSelected()) {
        	System.out.println(C6.getText());
        	cat2=(C6.getText());
        	}
        return cat2;
    }
    public String getCat3() {
        if(C7.isSelected()) {
        	System.out.println(C7.getText());
        	cat3=(C7.getText());
        	}
        if(C8.isSelected()) {
        	System.out.println(C8.getText());
        	cat3=(C8.getText());
        	}
        if(C9.isSelected()) {
        	System.out.println(C9.getText());
        	cat3=(C9.getText());
        	}
        return cat3;
    }
    public String getCat4() {
    	
        if(C10.isSelected()) {
        	System.out.println(C10.getText());
        	cat4=(C10.getText());
        	}
        if(C11.isSelected()) {
        	System.out.println(C11.getText());
        	cat4=(C11.getText());
        	}
        if(C12.isSelected()) {
        	System.out.println(C12.getText());
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