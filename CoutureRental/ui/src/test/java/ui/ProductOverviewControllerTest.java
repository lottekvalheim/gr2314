package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for the ProductOverviewController using TestFX.
 */
@ExtendWith(ApplicationExtension.class)
public class ProductOverviewControllerTest extends ApplicationTest {

    private RemoteCoutureRentalAccess access;

    @BeforeEach
    public void setUp() {
        access = new RemoteCoutureRentalAccess();

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductOverview.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }

    /**
     * Test case for checking if the images are loaded.
     *
     * @param robot The FxRobot instance.
     */
    @Test
    public void testImageLoading(FxRobot robot) {
        ImageView img1 = robot.lookup("#img1").queryAs(ImageView.class);
        assertNotNull(img1.getImage(), "Image 1 not loaded.");


    }

    /**
     * Test case for checking button text after initialization.
     *
     * @param robot The FxRobot instance.
     */
    @Test
    public void testButtonTextAfterInitialization(FxRobot robot) {
        // Assuming you have initialized the buttons with certain text based on the data.
        Button dress1 = robot.lookup("#dress1").queryAs(Button.class);
        Boolean status = access.getAvailability("dress1");

        if (status.equals(true)) {
            assertEquals("AVAILABLE", dress1.getText(), "Button 1 text not as expected.");
        } else {
            assertEquals("RENTED", dress1.getText(), "Button 1 text not as expected.");
        }


    }

    /**
     * Test case for clicking a dress button and checking the result.
     *
     * @param robot The FxRobot instance.
     */
    @Test
    public void testButtonClick(FxRobot robot) {
        // Simulate a button click
        Button dress2 = robot.lookup("#dress2").queryAs(Button.class);

        Boolean status = access.getAvailability("dress2");
        robot.clickOn("#dress2");

        if (status.equals(true)) {
            assertEquals("RENTED", dress2.getText(), "Button 2 text not as expected.");
        } else {
            assertEquals("AVAILABLE", dress2.getText(), "Button 2 text not as expected.");
        }
    }

    /**
     * Test case for handling log out button click.
     *
     * @param robot The FxRobot instance.
     */
    @Test
    public void logOutClicked(FxRobot robot) {
        robot.clickOn("#logOut");

        AnchorPane logInPane = robot.lookup("#LogIn").query();
        assertNotNull(logInPane, "LogIn node not found in the scene.");
    }
}




