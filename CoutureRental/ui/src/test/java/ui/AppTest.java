package ui;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AppTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        App app = new App();
        app.start(stage);
    }

    /**
     * Tests that the app starts properly.
     */
    @Test
    public void testAppStart() {
        assertNotNull(lookup("#passwordTxt").query());
        assertNotNull(lookup("#logInButton").query());
        assertNotNull(lookup("#promdress").query());
        assertNotNull(lookup("#userTxt").query());
    }
}

