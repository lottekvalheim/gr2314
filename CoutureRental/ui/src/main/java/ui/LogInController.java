package ui;


import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import core.Users;
import persistence.Storage;


/**

* Images for the ProductOverview-scene.

*/

public class LogInController implements Initializable {

/**

* Images for the LogIn-scene.

*/

@FXML

private ImageView promdress;

/**

* buttons for the LogIn-scene.

*/

@FXML

private Button logInButton, newUserButton;

/**

* labels for the LogIn-scene.

*/

@FXML

private Label errorLabel;

/**

* textfields for the LogIn-scene.

*/
@FXML
private TextField userTxt, passwordTxt;

/**
 * Instance of filestorage.
 */
 private Storage persistence = new Storage();

 /**
  * Remote access.
  */
 private RemoteCoutureRentalAccess access = new RemoteCoutureRentalAccess();


/**
* Initializes the user interface components, setting images and text based on the stored data.

* This method also handles any exceptions related to file access.

*

* @param location The URL location of the FXML file.

* @param resources The ResourceBundle containing resources for the FXML file.

*/
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        try {

            System.out.println("jeg er i login");

            promdress.setImage(new Image(new FileInputStream("../pictures/promdress.jpeg")));





            } catch (FileNotFoundException e) {

            e.printStackTrace();

            }


    }

    /**
     * Handles the button click event to toggle the availability status of a dress and update its text.
     *
     * @param event The ActionEvent triggered by the button click.
     * @throws IOException
     */
    @FXML
    private void newUserClicked(final ActionEvent event) {

       loadAndShowScene("MakeUser.fxml");

    }

    /**
     * Handles the button click event for the Login functionality.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void logInClicked(final ActionEvent event) {

        String username = userTxt.getText();
        String password = passwordTxt.getText();


        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("please fill out username and password");
        } else {
            Users users = access.getUsers();
            System.out.println(users);

            if (users.checkUsername(username)) {
                if (!users.comparePassword(username, password)) {
                    errorLabel.setText("The password is incorrect");
                } else {
                    loadAndShowScene("ProductOverview.fxml");
                }
            } else {
                errorLabel.setText("User does not exist");
            }

        }

    }

    /**
     * Loads and shows the specified FXML scene.
     *
     * @param fxmlFileName The name of the FXML file to load and display.
     */
    private void loadAndShowScene(final String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) logInButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }







}
