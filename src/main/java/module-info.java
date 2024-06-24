module com.example.schoolmanagment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.schoolManagement to javafx.fxml;
    exports com.example.schoolManagement;
    exports com.example.schoolManagement.models;
    opens com.example.schoolManagement.models to javafx.fxml;
    exports com.example.schoolManagement.controller;
    opens com.example.schoolManagement.controller to javafx.fxml;
}