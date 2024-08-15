package ui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import core.Users;

import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import javafx.scene.Node;

@ExtendWith(ApplicationExtension.class)
public class MakeUserControllerTest {

    private Stage stage;
    private FXMLLoader loader;


    @Start
    private void start(Stage stage) throws Exception {
        this.stage = stage;
        loader = new FXMLLoader(getClass().getResource("MakeUser.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.show();
        this.stage.toFront();
    }

    @AfterEach
    void tearDown(FxRobot robot) throws Exception {
        Platform.runLater(() -> {
            stage.close();
        });
        FxToolkit.cleanupStages();
    }

    @Test
    void testBackToLogin(FxRobot robot) {
        robot.clickOn("#back");
        WaitForAsyncUtils.waitForFxEvents();
        Node loginButton = robot.lookup("#logInButton").query();
        assertNotNull(loginButton, "Login button should be present, indicating the scene has changed to login");
    }





    @Test
    void testRegisterUserOnlyName(FxRobot robot) {
        robot.clickOn("#userTxt").write("newUser");
        robot.interact(() -> {
            Button register = robot.lookup("#register").queryAs(Button.class);
            register.fire();
        });
        WaitForAsyncUtils.waitForFxEvents();
        Label label = robot.lookup("#errorLabel").queryAs(Label.class);
        assertEquals("Please fill out username and password", label.getText());
    }



    @Test
    void testRegisterExistingUser(FxRobot robot) {
        robot.clickOn("#userTxt").write("Lotte");
        robot.clickOn("#passwordTxt").write("1234");
        robot.interact(() -> {
            Button register = robot.lookup("#register").queryAs(Button.class);
            register.fire();
        });
        Label label = robot.lookup("#errorLabel").queryAs(Label.class);
        assertEquals("User already exists", label.getText());
    }

    @Test
    void testRegisterWithEmptyFields(FxRobot robot) {
        robot.interact(() -> {
            Button register = robot.lookup("#register").queryAs(Button.class);
            register.fire();
        });
        WaitForAsyncUtils.waitForFxEvents();
        Label label = robot.lookup("#errorLabel").queryAs(Label.class);
        assertEquals("Please fill out username and password", label.getText());
    }

    @Test
    void testRegisterUser(FxRobot robot) {


        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String newUsername = "nyBruker_" + timestamp;
        String newPassword = "nyttPassord";


        robot.clickOn("#userTxt").write(newUsername);
        robot.clickOn("#passwordTxt").write(newPassword);
        robot.clickOn("#register");

        Node loginButton = robot.lookup("#logInButton").query();
        assertNotNull(loginButton, "Login button should be present, indicating the scene has changed to login");
    }

}
