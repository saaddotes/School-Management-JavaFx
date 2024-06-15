module com.example.schoolmanagment {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.schoolmanagment to javafx.fxml;
    exports com.example.schoolmanagment;
}