package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App for Couture Rental.
 */
public class App extends Application {

    /**
     * This method is called when the JavaFX application starts.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(final Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("LogIn.fxml"));
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * The entry point of the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(final String[] args) {
        launch();
    }
}