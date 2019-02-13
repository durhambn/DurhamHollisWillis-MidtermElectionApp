package com.csci360.electionapp.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    	Scene scene = new Scene(root, 600, 400);
    	stage.setTitle("Main Page");
    	stage.setScene(scene);
        stage.show();
       
        }
    @FXML
    public Button btnReg;
    @FXML
    public Button closeButton;

    @FXML
    public void regButtonClicked() throws Exception{
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registration.fxml"));
       Parent root = fxmlLoader.load();
       Stage stage = new Stage();
       stage.initModality(Modality.APPLICATION_MODAL);
       stage.setOpacity(1);
       stage.setTitle("Registration Page");
       stage.setScene(new Scene (root, 600, 450));
       stage.showAndWait();
 
    }
    @FXML
    public void checkButtonClicked() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkStatus.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Check Registration Status Page");
        stage.setScene(new Scene (root, 600, 450));
        stage.showAndWait();
  
     }
    @FXML
    public void voteButtonClicked() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("votingPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Voting Page");
        stage.setScene(new Scene (root, 600, 450));
        stage.showAndWait();
  
     }
    @FXML
    public void adminButtonClicked() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("Election Official Page");
        stage.setScene(new Scene (root, 600, 450));
        stage.showAndWait();
  
     }
    @FXML
    public void btnClose_clicked(ActionEvent event) throws IOException {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

}
    
        
    

    public static void main(String[] args) {
        launch(args);
    }
}