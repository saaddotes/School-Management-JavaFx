package com.example.schoolManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.example.schoolManagement.database.DatabaseConnection;
import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Initialize database school.db
        DatabaseConnection.createNewDatabase();
        DatabaseConnection.getConnection();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/example/schoolManagement/Fxml/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 400);
        stage.setTitle("School Management");

        // Load and set the favicon
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/schoolManagement/icons/icon16.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/schoolManagement/icons/icon32.png"))));
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/schoolManagement/icons/icon48.png"))));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
