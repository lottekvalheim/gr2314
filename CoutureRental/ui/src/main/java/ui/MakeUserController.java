package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import core.User;

import core.Users;



public class MakeUserController {

    /**
     * Button to register user, and button for return to login.
     */
    @FXML
    private Button register, back;

    /**
     * Label to reveal possible errors.
     */
    @FXML
    private Label errorLabel;

    /**
     * Username and password from user.
     */
    @FXML
    private TextField passwordTxt, userTxt;

    /**
     * Instance of api.
     */
    private RemoteCoutureRentalAccess access = new RemoteCoutureRentalAccess();


    /**
     * Return to log in.
     * @param event
     */
    public void backToLogin(final ActionEvent event) {
        loadAndShowScene("LogIn.fxml");
    }

    /**
     * Register user.
     * @param event
     */
    public void registerUser(final ActionEvent event) {
        System.out.println("jeg er i registerer bruker");

        String username = userTxt.getText();
        String password = passwordTxt.getText();
        User user = new User(username, password);
        Users users = access.getUsers();
        System.out.println("klarer å lese");
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please fill out username and password");
        } else if (users != null) {
            System.out.println("er i else if______________________");
            if (users.checkUsername(username)) {
                errorLabel.setText("User already exists");
            } else {
                System.out.println("skal legge til bruker");


                access.addUser(user);
                loadAndShowScene("LogIn.fxml");
            }
        } else {
             access.addUser(user);
        }
    }

    /**
     * Load and show scene.
     * @param fxmlFileName
     */
    private void loadAndShowScene(final String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
