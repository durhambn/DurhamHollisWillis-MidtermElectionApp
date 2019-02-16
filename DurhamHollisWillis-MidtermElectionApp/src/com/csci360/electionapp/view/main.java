package com.csci360.electionapp.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;



public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml")); 
    	Scene scene = new Scene(root);
    	stage.setTitle("Main Page");
    	stage.setScene(scene);
        stage.show();
       
        }
    @FXML
    public Button btnReg;
    @FXML
    public Button closeButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextField ssn;
    @FXML
    private TextField pswd;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    

    @FXML
    public void regButtonClicked() throws Exception{
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registration.fxml"));
       Parent root = fxmlLoader.load();
       Stage stage = new Stage();
       stage.initModality(Modality.APPLICATION_MODAL);
       stage.setOpacity(1);
       stage.setTitle("Registration Page");
       stage.setScene(new Scene (root));
       stage.showAndWait();
 
    }
    public void checkButtonClicked() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkStatus.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Check Registration Status Page");
        stage.setScene(new Scene (root));
        stage.showAndWait();
  
     }
    public void voteButtonClicked() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("votingPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Voting Page");
        stage.setScene(new Scene (root));
        stage.showAndWait();
  
     }
    public void adminButtonClicked() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Election Official Page");
        stage.setScene(new Scene (root));
        stage.showAndWait();
  
     }
    public void btnClose_clicked(ActionEvent event) throws IOException {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

    }
    @FXML
    public Button regSubmit;
    public void regSubmit(ActionEvent event) throws IOException{
    	System.out.print(firstNameField.getText() + "\n");
    	System.out.print(lastNameField.getText() + "\n");
    	System.out.print(birthdayField.getText() + "\n");
    	System.out.print(ssn.getText() + "\n");
    	System.out.print(pswd.getText() + "\n");
    	firstNameField.clear();
    	lastNameField.clear();
    	birthdayField.clear();
    	ssn.clear();
    	pswd.clear();
    	Stage stage = (Stage) regSubmit.getScene().getWindow();
    	stage.close();
    }
   
    @FXML
    public Button checkSubmit;
    public void checkSubmit(ActionEvent event) throws IOException{
    	System.out.print(firstNameField.getText() + "\n");
    	System.out.print(lastNameField.getText() + "\n");
    	System.out.print(birthdayField.getText() + "\n");
    	System.out.print(ssn.getText() + "\n");
    	firstNameField.clear();
    	lastNameField.clear();
    	birthdayField.clear();
    	ssn.clear();
    	Stage stage = (Stage) checkSubmit.getScene().getWindow();
    	stage.close();
    }
    @FXML
    public Button adminSubmit;
    public void adminSubmit(ActionEvent event) throws IOException{
    	System.out.print(username.getText() + "\n");
    	username.clear();
    	password.clear();
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminInfo.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Election Official Page");
        stage.setScene(new Scene (root));
        stage.showAndWait();   	
    }
    @FXML
    public Button voteSubmit;
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
    public Label S1;
    @FXML 
    public Label S2;
    @FXML 
    public Label S3;
    @FXML 
    public Label S4;

    public void voteSubmit(ActionEvent event) throws IOException{
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
    	/*
    	String vote1 = C1.getText();
    	String vote2 = C4.getText();
    	String vote3 = C7.getText();
    	String vote4 = C11.getText();
    	S1.setText(vote1);
    	S2.setText(vote2);
    	S3.setText(vote3);
    	S4.setText(vote4);
    	*/
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("finalSubmit.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Vote Confirmation Page");
        stage.setScene(new Scene (root));
        stage.showAndWait();
        
    }
    @FXML
    public Button finalSubmit;
    public void finalSubmit(ActionEvent event) throws IOException{
    	/*
    	S1.setText(C1.getText());
    	S2.setText(C4.getText());
    	S3.setText(C7.getText());
    	S4.setText(C11.getText());
    	*/
    	
    	System.out.println(S1.getText());
    	System.out.println(S2.getText());
    	System.out.println(S3.getText());
    	System.out.println(S4.getText());
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("endPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Thank You");
        stage.setScene(new Scene (root));
        stage.show();    	
    }
    @FXML
    public Button finalCancel;
    public void finalCancel(ActionEvent event) throws IOException{
    	System.out.println("Cancel chosen, going back to voting page");
    	Stage stage = (Stage) finalCancel.getScene().getWindow();
    	stage.close();
    }
    @FXML
    public Button end;
    public void closeApp(ActionEvent event) throws IOException{
    	System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}