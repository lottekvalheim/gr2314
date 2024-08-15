 package ui;

import persistence.Storage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import core.Dress;
import core.Dresses;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;





public class ProductOverviewController implements Initializable {



    /**
     * Images for the ProductOverview-scene.
     */
    @FXML
    private ImageView img1, img2, img3, img4, img5, img6, headerImage;


    /**
     * Buttons under the images in the ProductOverview-scene.
     */
    @FXML
    private Button dress1, dress2, dress3, dress4, dress5, dress6, logOut;

     /**
     * Header for the ProductOverview-scene.
     */
    @FXML
    private Label headerTxt;

    /**
     * Remote access.
     */
    private RemoteCoutureRentalAccess access = new RemoteCoutureRentalAccess();

    /**
     * Instance of storage.
     */
    private static Storage storage = new Storage();

    /**
     * A static list of all Button elements used in the controller.
     */
    private static List<Button> allButtons = new ArrayList<>();


    /**
     * Initializes the user interface components, setting images and text based on the stored data.
     * This method also handles any exceptions related to file access.
     *
     * @param location The URL location of the FXML file.
     * @param resources The ResourceBundle containing resources for the FXML file.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

        storage.initializeFile("DressStorage.json");

        try {
            if (allButtons.isEmpty()) {
                final int numOfButtons = 6;
                for (int i = 1; i <= numOfButtons; i++) {
                    Button button = (Button) this.getClass().getDeclaredField("dress" + i).get(this);
                    allButtons.add(button);
                }


            }
            img1.setImage(new Image(new FileInputStream("../pictures/img1.jpg")));
            img2.setImage(new Image(new FileInputStream("../pictures/img2.jpg")));
            img3.setImage(new Image(new FileInputStream("../pictures/img3.jpg")));
            img4.setImage(new Image(new FileInputStream("../pictures/img1.jpg")));
            img5.setImage(new Image(new FileInputStream("../pictures/img2.jpg")));
            img6.setImage(new Image(new FileInputStream("../pictures/img3.jpg")));
            headerImage.setImage(new Image(new FileInputStream("../pictures/header1.jpeg")));
            setText();



        } catch (FileNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();

        }
    }

//     }

    /**
     * Sets the text of the buttons based on the availability of the dresses.
     */
    public void setText() {
        Dresses dresses = access.getDresses();
        System.out.println(allButtons);

        for (Dress dress : dresses.getDresses()) {
            for (Button button : allButtons) {
                if (button.getId().equals(dress.getId())) {
                    Boolean isAvailable = access.getAvailability(dress.getId());

                    if (isAvailable != null) {
                        button.setText(isAvailable ? "AVAILABLE" : "RENTED");
                    } else {
                        // Handle the case where isAvailable is null
                        button.setText("UNKNOWN");
                    }
                    break;
                }
            }
        }
    }

    /**
     * Handles the button click event to toggle the availability status of a dress and update its text.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void toggleButton(final ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        access.getAvailability(clickedButton.getId());

        access.changeAvailability(clickedButton.getId());
        access.getAvailability(clickedButton.getId());
        Boolean isAvailable = access.getAvailability(clickedButton.getId());
        clickedButton.setText(isAvailable ? "AVAILABLE" : "RENTED");
    }

    /**
     * Handles the button click event to log out of the application.
     * @param event
     */
    @FXML
    private void logOut(final ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = (Stage) logOut.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }