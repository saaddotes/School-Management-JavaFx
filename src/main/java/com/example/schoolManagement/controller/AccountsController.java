package com.example.schoolManagement.controller;

import com.example.schoolManagement.database.StudentDAO;
import com.example.schoolManagement.models.Student;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class AccountsController {

    @FXML
    private TextField idField;
    @FXML
    private HBox detailsHBox;
    @FXML
    private Text studentNameText;
    @FXML
    private Text fatherNameText;
    @FXML
    private Text classText;
    @FXML
    private Text sectionText;
    @FXML
    private Text totalFeesText;
    @FXML
    private Text dueDateText;
    @FXML
    private Text statusText;

    private Student studentPro;
    private StudentDAO studentDAO;

    @FXML
    public void initialize() {
        studentDAO = new StudentDAO();
        detailsHBox.setVisible(false);
    }

    @FXML
    private void handleGenerateVoucher() {
        String id = idField.getText();
        if (id.isEmpty()) {
            showAlert("Roll Number is required");
            return;
        }

        Student student = studentDAO.getStudentById(id);
        if (student == null) {
            showAlert("No student found with the given Roll Number");
            return;
        }

        studentPro = student;

        studentNameText.setText(student.getName());
        fatherNameText.setText(student.getFather());
        classText.setText(student.getClassLevel());
        sectionText.setText(student.getSection());
        totalFeesText.setText(String.valueOf(student.getTotalFees()));
        dueDateText.setText("27 June 2024");
        statusText.setText(student.isFeesStatus());

        detailsHBox.setVisible(true);
    }

    @FXML
    private void handlePay() {
        if (studentPro != null) {
            if (studentPro.isFeesStatus().equals("Paid")) {
                showAlert("Already Paid");
            } else {
                studentPro.setFeesStatus("Paid");
                studentDAO.updateStudent(studentPro);
                statusText.setText(studentPro.isFeesStatus());
                showAlert("Payment Successful");
            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
