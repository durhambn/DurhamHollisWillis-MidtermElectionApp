package com.csci360.electionapp.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import com.csci360.electionapp.model.database;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import com.csci360.electionapp.controller.*;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import com.csci360.electionapp.model.*;

/**
 * 
 * @author Brandi Durham, Austin Hollis, Justin Willis
 *
 */
public class main extends Application {

	database db = new database();

	public Connection connectToDB() throws SQLException {
		return this.db.getConnection();
	}

	/**
	 * Method for starting screen
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Connection conn = connectToDB();
		db.createTables(conn);
		// Might incorporate title and resourceName
		// into a class creation method...
		String title = "Main Page";
		String resourceName = "Mainpage.fxml";

		setStage(title, resourceName);
	}

	// Button variable for registering page (registration page)
	@FXML
	public Button btnReg;

	// Button variable to close the screen (registration page)
	@FXML
	public Button closeButton;

	// Button variable for submitting registration information (registration page)
	@FXML
	public Button regSubmit;

	// Button variable for checking registration page (check registration page)
	@FXML
	public Button checkSubmit;

	// Button variable for submitting voting choices (vote page)
	@FXML
	public Button voteSubmit;

	// Button variable for submitting final choices (review page)
	@FXML
	public Button finalSubmit;

	// Button variable for canceling review choices (review page)
	@FXML
	public Button finalCancel;

	// Button variable for ending the session (thank you screen)
	@FXML
	public Button end;

	// Button variable for admin page (admin login page)
	@FXML
	public Button adminSubmit;

	// Button variable for voting login page submit (voterLogin page)
	@FXML
	public Button voteLoginSubmit;
	/**
	 * Text Fields for registration page
	 */

	// TextField variables used in
	// the registering process.
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField birthdayFieldMth;
	@FXML
	private TextField birthdayFieldDay;
	@FXML
	private TextField birthdayFieldYear;
	@FXML
	private TextField ssn;
	@FXML
	private TextField pswd;
	@FXML
	private TextField username;
	@FXML
	private TextField password;

	registrationController rc = new registrationController();

	// final DatePicker datePicker = new DatePicker();

	// Set the stage to show whatever page is inputted
	public void setStage(String title, String resourceName) throws Exception {
		// FXMLLoader variable to grab the registration.fxml file.
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resourceName));

		// Store the registration.fxml file into root as a "Parent"
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

	public void fillFields(Button page) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(page.getScene().getWindow());
		alert.setTitle("Error");
		alert.setHeaderText("Not all fields are filled.");
		alert.setContentText("Please fill in all fields to register.");
		alert.showAndWait();
	}

	public void fixNames(Button page) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(page.getScene().getWindow());
		alert.setTitle("Error");
		alert.setHeaderText("First name and/or last name fields are not correct.");
		alert.setContentText("Only lowercase and uppercase letters will be accepted for names.");
		alert.showAndWait();
	}

	// Check birthday fields
	public void fixBirth(Button page) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(page.getScene().getWindow());
		alert.setTitle("Error");
		alert.setHeaderText("Incorrect length of dates");
		alert.setContentText("Text fields must match format of number length:\n" + "Month - MM\n" + "Day - DD\n"
				+ "Year - YYYY\n" + "(Note: must input numbers in order to be accepted)");
		alert.showAndWait();
	}

	// Check SSN
	public void fixSSN(Button page) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(page.getScene().getWindow());
		alert.setTitle("Error");
		alert.setHeaderText("Incorrect social security number.");
		alert.setContentText("Social security number must be a series of 9 numbers.");
		alert.showAndWait();
	}

	/**
	 * 
	 * @throws Exception
	 */
	// Method used if the register button is clicked
	@FXML
	public void regButtonClicked() throws Exception {
		// Might incorporate title and resourceName
		// into a class creation method...
		String title = "Registration Page";
		String resourceName = "registration.fxml";

		setStage(title, resourceName);
	}

	/**
	 * 
	 * @throws Exception
	 */
	// Method used if the check registration button is clicked
	public void checkButtonClicked() throws Exception {
		// Might incorporate title and resourceName
		// into a class creation method...
		String title = "Check registration Status Page";
		String resourceName = "checkStatus.fxml";

		setStage(title, resourceName);
	}

	/**
	 * 
	 * @throws Exception
	 */
	// Method used if the vote button is clicked
	public void voteButtonClicked() throws Exception {
		// Might incorporate title and resourceName
		// into a class creation method...
		// votingPage.fxml "Voting Page"
		String resourceName = "voterLogin.fxml";
		String title = "Voter Login";

		setStage(title, resourceName);
	}

	/**
	 * 
	 * @throws Exception
	 */
	// Method used if the admin login button is clicked
	public void adminButtonClicked() throws Exception {
		// Might incorporate title and resourceName
		// into a class creation method...
		String resourceName = "admin.fxml";
		String title = "Election Official Page";

		setStage(title, resourceName);
	}

	/**
	 * 
	 * @param event
	 * @throws IOException
	 */
	// Method used when a close button has been clicked
	public void btnClose_clicked(ActionEvent event) throws IOException {
		// Get the current window and close it
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	/**
	 * 
	 * @param event
	 * @throws IOException
	 * @throws SQLException
	 */
	public void regSubmit(ActionEvent event) throws IOException, SQLException {
		// Print statements for testing purposes (remove later)
		System.out.print(firstNameField.getText() + "\n");
		System.out.print(lastNameField.getText() + "\n");
		System.out.print(birthdayFieldMth.getText() + "\n");
		System.out.print(birthdayFieldDay.getText() + "\n");
		System.out.print(birthdayFieldYear.getText() + "\n");
		System.out.print(ssn.getText() + "\n");
		System.out.print(pswd.getText() + "\n");

		// Create variables for the creation of a voter object
		// from the text fields.
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		// LocalDate birthday = datePicker.getValue();
		String birthdayMth = birthdayFieldMth.getText();
		String birthdayDay = birthdayFieldDay.getText();
		String birthdayYear = birthdayFieldYear.getText();
		String ssnString = ssn.getText();
		String password = pswd.getText();

		// if(birthdayMth<0 || birthdayMth>12 )
		// Need to make sure date is real

		// Password check for validation
		final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!~<>,;:_=?*+#.\"&�%�()\\|\\[\\]\\-\\$\\^\\@\\/]).{8,40}$";
		final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

		// Fields are empty
		if ((firstName.isEmpty()) || (lastName.isEmpty()) || (birthdayMth.isEmpty()) || (birthdayDay.isEmpty())
				|| (birthdayYear.isEmpty()) || (ssnString.isEmpty()) || (password.isEmpty())) {
			fillFields(regSubmit);
		}
		// Name fields not correct
		else if (!firstName.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")) {
			fixNames(regSubmit);
		}
		// Date fields do not match required format
		else if (!birthdayMth.matches("[0-9]{2}") || !birthdayDay.matches("[0-9]{2}")
				|| !birthdayYear.matches("[0-9]{4}")) {
			fixBirth(regSubmit);
		}
		// Incorrect SSN
		else if (!ssnString.matches("[0-9]{9}")) {
			fixSSN(regSubmit);
		} 
		// Incorrect password
		else if (!(PASSWORD_PATTERN.matcher(password).matches())) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(regSubmit.getScene().getWindow());
			alert.setTitle("Error");
			alert.setHeaderText("Invalid password.");
			alert.setContentText(
					"The following requirements must be met for a valid password:\n\n" + "- Length of 8-40 characters\n"
							+ "- At least 1 uppercase letter [A-Z]\n" + "- At least 1 lowercase letter [a-z]\n"
							+ "- At least 1 number [0-9]\n" + "- At least 1 special character");
			alert.showAndWait();
		}
		// Correct data
		else {
			// Pass data to the controller by creating a registrationController object
			VoterController votingPerson = registrationController.createVoter(firstName, lastName, birthdayMth,
					birthdayDay, birthdayYear, ssnString, password);
			String addResult = registrationController.add(votingPerson, this.db);
			System.out.println("The result from adding person to the database is: " + addResult);
			System.out.println("The time the voter registered: " + votingPerson.getTime());
			Alert result = new Alert(AlertType.INFORMATION);
			result.initOwner(regSubmit.getScene().getWindow());
			result.setTitle("Result");
			result.setHeaderText("Result");
			result.setContentText(addResult);
			result.setContentText("Your username is: " + votingPerson.getVoterUsername());

			result.showAndWait();

			// Print data to console.
			votingPerson.updateView();
			// Clear the text fields
			// (may need to do this after storing somewhere?)
			firstNameField.clear();
			lastNameField.clear();
			birthdayFieldMth.clear();
			birthdayFieldDay.clear();
			birthdayFieldYear.clear();
			ssn.clear();
			pswd.clear();

			// Get the current window and close it.
			Stage stage = (Stage) regSubmit.getScene().getWindow();
			stage.close();
		}
	}

	/**
	 * 
	 * @param event
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void checkSubmit(ActionEvent event) throws IOException, SQLException {
		// Print statements for testing purposes (remove later)
		System.out.print(firstNameField.getText() + "\n");
		System.out.print(lastNameField.getText() + "\n");
		System.out.print(birthdayFieldMth.getText() + "\n");
		System.out.print(birthdayFieldDay.getText() + "\n");
		System.out.print(birthdayFieldYear.getText() + "\n");
		System.out.print(ssn.getText() + "\n");

		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String birthdayMth = birthdayFieldMth.getText();
		String birthdayDay = birthdayFieldDay.getText();
		String birthdayYear = birthdayFieldYear.getText();
		String ssnString = ssn.getText();

		// Fields are empty
		if ((firstName.isEmpty()) || (lastName.isEmpty()) || (birthdayMth.isEmpty()) || (birthdayDay.isEmpty())
				|| (birthdayYear.isEmpty()) || (ssnString.isEmpty())) {
			fillFields(checkSubmit);
		}
		// Name fields not correct
		else if (!firstName.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")) {
			fixNames(checkSubmit);
		}
		// Date fields do not match required format
		else if (!birthdayMth.matches("[0-9]{2}") || !birthdayDay.matches("[0-9]{2}")
				|| !birthdayYear.matches("[0-9]{4}")) {
			fixBirth(checkSubmit);
		}
		// Incorrect SSN
		else if (!ssnString.matches("[0-9]{9}")) {
			fixSSN(checkSubmit);
		}
		else {
			// Pass data to the controller by creating a registrationController object
			String password = "";
			VoterController votingPerson = registrationController.createVoter(firstName, lastName, birthdayMth,
					birthdayDay, birthdayYear, ssnString, password);
			boolean addResult = votingPerson.getRegStatus(db);
			if (addResult) {
				// popup saying they are already added
				System.out.println("The voter is already registered to vote");
				Alert resultTrue = new Alert(AlertType.INFORMATION);
				resultTrue.initOwner(checkSubmit.getScene().getWindow());
				resultTrue.setTitle("Result");
				resultTrue.setHeaderText("The voter is already registered to vote");
				resultTrue.setContentText("Please go to 'Vote!' Page to vote.");

				resultTrue.showAndWait();
			} else {
				// popup saying they are not registered
				System.out.println("The voter is not registered to vote");
				Alert resultFalse = new Alert(AlertType.INFORMATION);
				resultFalse.initOwner(checkSubmit.getScene().getWindow());
				resultFalse.setTitle("Result");
				resultFalse.setHeaderText("The voter is not registered to vote");
				resultFalse.setContentText("Please go to 'Register to Vote' page to register to vote.");

				resultFalse.showAndWait();
			}
			// System.out.println("The result from adding person to the database is: " +
			// addResult);
			// Print data to console.
			votingPerson.updateView();
			// Clear the text fields
			// (may need to do this after storing somewhere?)
			firstNameField.clear();
			lastNameField.clear();
			birthdayFieldMth.clear();
			birthdayFieldDay.clear();
			birthdayFieldYear.clear();
			ssn.clear();

			// Get the current window and close it.
			Stage stage = (Stage) checkSubmit.getScene().getWindow();
			stage.close();
		}

	}

	// voter login submit method
	// click submit and goes to voting page
	/**
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void voteLoginSubmit(ActionEvent event) throws Exception {
		System.out.print(username.getText() + "\n");
		String uname = username.getText();
		String pssw = password.getText();

		if (uname.isEmpty() || pssw.isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(voteLoginSubmit.getScene().getWindow());
			alert.setTitle("Error");
			alert.setHeaderText("Not all fields are filled.");
			alert.setContentText("Please fill in all fields to register.");

			alert.showAndWait();
		} else {
			boolean result = db.checkUserLogin(uname, pssw, db.getConnection());
			// call check if user registered more than 24 hours ago
			boolean hasVoted = db.getStatusToVote(db.getConnection(), uname);
			java.util.Date created = db.getCreatedDate(db.getConnection(), uname);
			System.out.println(created);

			java.util.Date today = new java.util.Date();

			long period = Math.abs(today.getTime() - created.getTime());
			// System.out.println(period);
			long diff = TimeUnit.HOURS.convert(period, TimeUnit.MILLISECONDS);
			// System.out.println(diff);

			if (result && !hasVoted && (diff >= 24)) {
				username.clear();
				password.clear();

				// Might incorporate title and resourceName
				// into a class creation method...
				String resourceName = "votingPage.fxml";
				String title = "Voting Page";
				
				// FXMLLoader variable to grab the registration.fxml file.
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resourceName));

				// Store the registration.fxml file into root as a "Parent"
				Parent root = fxmlLoader.load();

				votingCheckBoxes sceneController = fxmlLoader.getController();
                sceneController.initialize(uname);
				// Create a new stage and initialize the modality
				// set the opacity to 1 and set the title and show
				// root as the scene.
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setOpacity(1);
				stage.setTitle(title);
				stage.setScene(new Scene(root));
				stage.showAndWait();
				
			} else {
				// PoP uP
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(voteLoginSubmit.getScene().getWindow());
				alert.setTitle("Error");
				alert.setHeaderText("Cannot Login");
				if (result == false) {
					alert.setContentText("Incorrect Username/Password");
				} else if (hasVoted == true) {
					alert.setContentText("Voter has already voted");
				} else if (diff < 24) {
					alert.setContentText("Cannot vote without 24 hours of registration");
				}
				alert.showAndWait();
				username.clear();
				password.clear();
			}
		}
	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void adminSubmit(ActionEvent event) throws Exception {
		// Print statements for testing purposes (remove later)
		System.out.print(username.getText() + "\n");
		String uname = username.getText();
		String pssw = password.getText();

		if (uname.isEmpty() || pssw.isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(adminSubmit.getScene().getWindow());
			alert.setTitle("Error");
			alert.setHeaderText("Not all fields are filled.");
			alert.setContentText("Please fill in all fields to register.");

			alert.showAndWait();
		} else {
			boolean result = db.checkAdminLogin(uname, pssw, db.getConnection());
			if (result == true) {

				// Clear the text fields
				// (may need to clear this after storing the data somewhere?)
				username.clear();
				password.clear();

				// Might incorporate title and resourceName
				// into a class creation method...
				String resourceName = "adminInfo.fxml";
				String title = "Election Official Page";

				setStage(title, resourceName);
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(adminSubmit.getScene().getWindow());
				alert.setTitle("Error");
				alert.setHeaderText("Incorrect Username/Password");
				alert.setContentText("Please try again.");
				alert.showAndWait();
				username.clear();
				password.clear();
			}
		}
	}

	/**
	 * 
	 * @param event
	 * @throws IOException
	 */
	public void closeApp(ActionEvent event) throws IOException {
		// If application is closed exit the program
		System.exit(0);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
