package com.csci360.electionapp.view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import com.csci360.electionapp.model.database;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import com.csci360.electionapp.controller.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.stage.Modality;
import javafx.stage.Stage;

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

	// Pop-up to fill all fields in the window
	public void fillFields(Button page) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(page.getScene().getWindow());
		alert.setTitle("Error");
		alert.setHeaderText("Not all fields are filled.");
		alert.setContentText("Please fill in all fields to register.");
		alert.showAndWait();
	}

	// Pop-up to input names correctly
	public void fixNames(Button page) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(page.getScene().getWindow());
		alert.setTitle("Error");
		alert.setHeaderText("First name and/or last name fields are not correct.");
		alert.setContentText("Only lowercase and uppercase letters will be accepted for names.");
		alert.showAndWait();
	}

	// Pop-up to fix birthday input fields
	public void fixBirth(Button page) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(page.getScene().getWindow());
		alert.setTitle("Error");
		alert.setHeaderText("Invalid date.");
		alert.setContentText("Text fields must match format of number length:\n" + "Month - MM\n" + "Day - DD\n"
				+ "Year - YYYY\n" + "(Note: must input numbers in order to be accepted)");
		alert.showAndWait();
	}

	// Pop-up to fix SSN input field
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
	 * @throws NoSuchAlgorithmException
	 */
	public void regSubmit(ActionEvent event) throws IOException, SQLException, NoSuchAlgorithmException {
		// Create variables for the creation of a voter object
		// from the text fields.
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String birthdayMth = birthdayFieldMth.getText();
		String birthdayDay = birthdayFieldDay.getText();
		String birthdayYear = birthdayFieldYear.getText();
		String ssnString = ssn.getText();
		String password = pswd.getText();

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
		else if (!birthdayMth.matches("0[0-9]|1[0-2]") || !birthdayDay.matches("0[1-9]|1[0-9]|2[0-9]|3[0-1]")
				|| !birthdayYear.matches("19[0-9][0-9]|20[0-1][0-8]")) {
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
		// Correct fields
		else {
			// Pass data to the controller by creating a registrationController object
			VoterController votingPerson = registrationController.createVoter(firstName, lastName, birthdayMth,
					birthdayDay, birthdayYear, ssnString, password);
			String addResult = registrationController.add(votingPerson, this.db);
			Alert result = new Alert(AlertType.INFORMATION);
			result.initOwner(regSubmit.getScene().getWindow());
			result.setTitle("Result");
			result.setHeaderText("Result");
			result.setContentText(addResult);

			result.showAndWait();

			// Print data to console.
			votingPerson.updateView();
			// Clear the text fields
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
		// File used to log data
		String fileName = "log.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

		// Create variables for the creation of a voter object
		// from the text fields.
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
		else if (!birthdayMth.matches("0[0-9]|1[0-2]") || !birthdayDay.matches("0[1-9]|1[0-9]|2[0-9]|3[0-1]")
				|| !birthdayYear.matches("19[0-9][0-9]|20[0-1][0-8]")) {
			fixBirth(checkSubmit);
		}
		// Incorrect SSN
		else if (!ssnString.matches("[0-9]{9}")) {
			fixSSN(checkSubmit);
		}
		// Correct fields
		else {
			// Pass data to the controller by creating a registrationController object
			String password = "";
			VoterController votingPerson = registrationController.createVoter(firstName, lastName, birthdayMth,
					birthdayDay, birthdayYear, ssnString, password);
			boolean addResult = votingPerson.getRegStatus(db);
			if (addResult) {
				// Pop-up saying they are already added
				String str = LocalDateTime.now() + "\nCheck registration - already registered\n"
						+ votingPerson.getVoterFirstName() + "\n" + votingPerson.getVoterLastName() + "\n"
						+ votingPerson.getVoterBirthday() + "\n\n";
				writer.write(str);
				writer.close();
				Alert resultTrue = new Alert(AlertType.INFORMATION);
				resultTrue.initOwner(checkSubmit.getScene().getWindow());
				resultTrue.setTitle("Result");
				resultTrue.setHeaderText("The voter is already registered to vote");
				resultTrue.setContentText("Please go to 'Vote!' Page to vote.");

				resultTrue.showAndWait();
			} else {
				// Pop-up saying they are not registered
				String str = LocalDateTime.now() + "\nCheck registration - not registered\n"
						+ votingPerson.getVoterFirstName() + "\n" + votingPerson.getVoterLastName() + "\n"
						+ votingPerson.getVoterBirthday() + "\n\n";
				writer.write(str);
				writer.close();
				Alert resultFalse = new Alert(AlertType.INFORMATION);
				resultFalse.initOwner(checkSubmit.getScene().getWindow());
				resultFalse.setTitle("Result");
				resultFalse.setHeaderText("The voter is not registered to vote");
				resultFalse.setContentText("Please go to 'Register to Vote' page to register to vote.");

				resultFalse.showAndWait();
			}

			// Clear the text fields
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
		String uname = username.getText();
		String pssw = password.getText();

		// File used to log data
		String fileName = "log.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

		// Fields are empty
		if (uname.isEmpty() || pssw.isEmpty()) {
			fillFields(voteLoginSubmit);
		}
		// Correct fields
		else {
			boolean result = db.checkUserLogin(uname, pssw, db.getConnection());
			// Correct login info
			if (result) {
				boolean hasVoted = db.getStatusToVote(db.getConnection(), uname);
				// Has not voted (begins voting process)
				if (!hasVoted) {
					java.util.Date created = db.getCreatedDate(db.getConnection(), uname);

					java.util.Date today = new java.util.Date();

					long period = Math.abs(today.getTime() - created.getTime());
					long diff = TimeUnit.HOURS.convert(period, TimeUnit.MILLISECONDS);
					// Checks if registered 24 hours ago
					if (diff >= 24) {

						String str = LocalDateTime.now() + "\nSuccessful Voter login\n" + uname + "\n\n";
						writer.write(str);
						writer.close();

						username.clear();
						password.clear();

						String resourceName = "votingPage.fxml";
						String title = "Voting Page";

						setStage(title, resourceName);
					}
					// Has not been 24 hours since registration
					else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.initOwner(voteLoginSubmit.getScene().getWindow());
						alert.setTitle("Error");
						alert.setHeaderText("Cannot Login");
						alert.setContentText("Cannot vote without 24 hours of registration");
						String str = LocalDateTime.now()
								+ "\nUnsuccessful Voter login - Cannot vote within 24 hours of registration\n" + uname
								+ "\n\n";
						writer.write(str);
						writer.close();
						alert.showAndWait();
					}
				}
				// Has already voted
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.initOwner(voteLoginSubmit.getScene().getWindow());
					alert.setTitle("Error");
					alert.setHeaderText("Cannot Login");
					alert.setContentText("Voter has already voted");
					String str = LocalDateTime.now() + "\nUnsuccessful Voter login - Voter has already voted\n" + uname
							+ "\n\n";
					writer.write(str);
					writer.close();
					alert.showAndWait();
				}

			}
			// Incorrect login info
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(voteLoginSubmit.getScene().getWindow());
				alert.setTitle("Error");
				alert.setHeaderText("Cannot Login");
				alert.setContentText("Incorrect Username/Password");
				String str = LocalDateTime.now() + "\nUnsuccessful Voter login - Incorrect Username/Password\n" + uname
						+ "\n\n";
				writer.write(str);
				writer.close();
				alert.showAndWait();
			}
		}
	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void adminSubmit(ActionEvent event) throws Exception {
		String uname = username.getText();
		String pssw = password.getText();
		String fileName = "log.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));

		// Fields are empty
		if (uname.isEmpty() || pssw.isEmpty()) {
			fillFields(adminSubmit);
		}
		// Correct fields
		else {
			boolean result = db.checkAdminLogin(uname, pssw, db.getConnection());
			// Correct login info
			if (result == true) {
				String str = LocalDateTime.now() + "\nAdmin Login successful\n" + uname + "\n\n";
				writer.write(str);
				writer.close();
				// Clear the text fields
				username.clear();
				password.clear();

				String resourceName = "adminInfo.fxml";
				String title = "Election Official Page";

				setStage(title, resourceName);
			} 
			// Incorrect login info
			else {
				String str = LocalDateTime.now() + "\nAdmin Login unsuccessful\n" + uname + "\n\n";
				writer.write(str);
				writer.close();
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