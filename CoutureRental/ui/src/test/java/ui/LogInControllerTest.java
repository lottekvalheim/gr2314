package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;


import core.User;
import core.Users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



/**
 * Test class for the LogInController.
 */
@ExtendWith(ApplicationExtension.class)
public class LogInControllerTest extends ApplicationTest {

    private RemoteCoutureRentalAccess access;
    private Users users;

    @BeforeEach
    public void setUp() {
        access = new RemoteCoutureRentalAccess();
        users = access.getUsers();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LogIn.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }

    /**
     * Test case for handling empty fields.
     *
     * @param robot The FxRobot instance.
     */
    @Test
    public void testEmptyFields(FxRobot robot) {
        robot.clickOn("#logInButton");

        robot.interact(() -> {
            assertEquals("please fill out username and password", robot.lookup("#errorLabel").queryAs(Label.class).getText());
        });
    }

    /**
     * Test case for a user that does not exist.
     *
     * @param robot The FxRobot instance.
     */
    @Test
    public void testUserDoesNotExist(FxRobot robot) {
        robot.clickOn("#userTxt").write("nonExistentUser");
        robot.clickOn("#passwordTxt").write("anyPassword");

        robot.clickOn("#logInButton");

        robot.interact(() -> {
            assertEquals("User does not exist", robot.lookup("#errorLabel").queryAs(Label.class).getText());
        });
    }

    /**
     * Test case for entering a wrong password.
     *
     * @param robot The FxRobot instance.
     */
    @Test
    public void testWrongPassword(FxRobot robot) {
        robot.interact(() -> {
            access.addUser(new User("test", "1234"));
        });

        robot.clickOn("#userTxt").write("test");
        robot.clickOn("#passwordTxt").write("12");

        robot.clickOn("#logInButton");

        assertEquals("The password is incorrect", robot.lookup("#errorLabel").queryAs(Label.class).getText());
    }

    /**
     * Test case for a successful login.
     *
     * @param robot The FxRobot instance.
     */
    @Test
    public void testSuccessfulLogin(FxRobot robot) {
        access.addUser(new User("testuser", "1234"));

        robot.clickOn("#userTxt").write("testuser");
        robot.clickOn("#passwordTxt").write("1234");

        robot.clickOn("#logInButton");

        AnchorPane productOverviewPane = robot.lookup("#ProductOverview").query();
        assertNotNull(productOverviewPane, "ProductOverview node not found in the scene.");
    }

    /**
     * Test case for clicking the "New User" button.
     *
     * @param robot The FxRobot instance.
     */
    @Test
    public void newUserClicked(FxRobot robot) {
        robot.clickOn("#newUserButton");

        AnchorPane makeUserPane = robot.lookup("#MakeUser").query();
        assertNotNull(makeUserPane, "MakeUser node not found in the scene.");
    }
}






