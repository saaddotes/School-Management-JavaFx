package com.example.schoolManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.schoolManagement.database.DatabaseConnection;
import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Initialize database
        DatabaseConnection.createNewDatabase();
        DatabaseConnection.getConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("school.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 400);
        stage.setTitle("School Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
