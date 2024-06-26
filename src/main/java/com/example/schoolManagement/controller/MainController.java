package com.example.schoolManagement.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane dashboardTab;

    @FXML
    private AnchorPane teachersTab;

    @FXML
    private AnchorPane studentsTab;

    @FXML
    public AnchorPane accountsTab;


    @FXML
    public void initialize() {
        loadFXML(dashboardTab, "/com/example/schoolManagement/fxml/DashboardTab.fxml");
        loadFXML(teachersTab, "/com/example/schoolManagement/fxml/TeachersTab.fxml");
        loadFXML(studentsTab, "/com/example/schoolManagement/fxml/StudentsTab.fxml");
        loadFXML(accountsTab, "/com/example/schoolManagement/fxml/AccountsTab.fxml");
    }

    private void loadFXML(AnchorPane pane, String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            pane.getChildren().add(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
