module com.example.schoolmanagment {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.schoolmanagment to javafx.fxml;
    exports com.example.schoolmanagment;
    exports com.example.schoolmanagment.models;
    opens com.example.schoolmanagment.models to javafx.fxml;
}