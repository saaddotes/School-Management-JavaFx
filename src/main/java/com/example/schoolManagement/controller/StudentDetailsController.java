package com.example.schoolManagement.controller;

import com.example.schoolManagement.database.StudentDAO;
import com.example.schoolManagement.models.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StudentDetailsController {

    @FXML
    private Label studentRollNumberLabel;
    @FXML
    private TextField studentNameField;
    @FXML
    private TextField studentFatherField;
    @FXML
    private TextField studentDobField;
    @FXML
    private TextField studentGenderField;
    @FXML
    private TextField studentClassLevelField;
    @FXML
    private TextField studentSectionField;
    @FXML
    private TextField studentEmailField;
    @FXML
    private TextField studentPhoneField;
    @FXML
    private TextField studentTotalFeesField;
    @FXML
    private TextField studentFeesStatusField;
    @FXML
    private Button studentEditButton;
    @FXML
    private Button studentUpdateButton;

    private Student student;

    public void initData(Student student) {
        this.student = student;
        studentRollNumberLabel.setText(student.getRollNumber());
        studentNameField.setText(student.getName());
        studentFatherField.setText(student.getFather());
        studentDobField.setText(student.getDob());
        studentGenderField.setText(student.getGender());
        studentClassLevelField.setText(student.getClassLevel());
        studentSectionField.setText(student.getSection());
        studentEmailField.setText(student.getEmail());
        studentPhoneField.setText(student.getPhone());
        studentTotalFeesField.setText(String.valueOf(student.getTotalFees()));
        studentFeesStatusField.setText(student.isFeesStatus());

        setFieldsEditable(false);
    }

    @FXML
    private void handleEditButtonAction() {
        setFieldsEditable(true);

        // Enable the Update button and disable the Edit button
        studentUpdateButton.setDisable(false);
        studentEditButton.setDisable(true);
    }

    @FXML
    private void handleUpdateButtonAction() {
        // Set the new values to the student object
        student.setName(studentNameField.getText());
        student.setFather(studentFatherField.getText());
        student.setDob(studentDobField.getText());
        student.setGender(studentGenderField.getText());
        student.setClassLevel(studentClassLevelField.getText());
        student.setSection(studentSectionField.getText());
        student.setEmail(studentEmailField.getText());
        student.setPhone(studentPhoneField.getText());
        student.setTotalFees(Integer.parseInt(studentTotalFeesField.getText()));
        student.setFeesStatus(studentFeesStatusField.getText());

        // Update the student in the database
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.updateStudent(student);

        // Disable text fields after updating
        setFieldsEditable(false);

        // Enable the Edit button and disable the Update button
        studentEditButton.setDisable(false);
        studentUpdateButton.setDisable(true);

        showAlert("Student details updated");
    }

    private void setFieldsEditable(boolean editable) {
        studentNameField.setEditable(editable);
        studentFatherField.setEditable(editable);
        studentDobField.setEditable(editable);
        studentGenderField.setEditable(editable);
        studentClassLevelField.setEditable(editable);
        studentSectionField.setEditable(editable);
        studentEmailField.setEditable(editable);
        studentPhoneField.setEditable(editable);
        studentTotalFeesField.setEditable(editable);
        studentFeesStatusField.setEditable(editable);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
