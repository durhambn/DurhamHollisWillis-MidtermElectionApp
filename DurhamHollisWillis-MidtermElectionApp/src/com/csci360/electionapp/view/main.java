package com.csci360.electionapp.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("Mainpage.fxml"));
    	//GridPane gridPane = new GridPane();
    	//gridPane.setMinSize(600, 400); 
    	Scene scene = new Scene(root, 600, 400);
    	stage.setTitle("Main Page");
    	stage.setScene(scene);
    	//scene.getStylesheets().add("/DurhamHollisWillis-MidtermElectionApp/src/com/csci360/electionapp/view/style.css");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}