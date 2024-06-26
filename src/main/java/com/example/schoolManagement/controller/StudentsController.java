package com.example.schoolManagement.controller;

import com.example.schoolManagement.database.StudentDAO;
import com.example.schoolManagement.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentsController {

    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private TableColumn<Student, Integer> rollNumberColumn;
    @FXML
    private TableColumn<Student, String> studentNameColumn;
    @FXML
    private TableColumn<Student, String> fatherNameColumn;
    @FXML
    private TableColumn<Student, String> studentGenderColumn;
    @FXML
    private TableColumn<Student, Integer> studentClassColumn;
    @FXML
    private TableColumn<Student, String> studentSectionColumn;
    @FXML
    private TableColumn<Student, String> studentEmailColumn;
    @FXML
    private TableColumn<Student, String> studentPhoneColumn;
    @FXML
    private TableColumn<Student, Integer> studentFeesColumn;
    @FXML
    private TableColumn<Student, Boolean> studentFeesStatusColumn;
    @FXML
    private TextField studentRollField;
    @FXML
    private TextField studentNameField;
    @FXML
    private TextField fatherNameField;
    @FXML
    private TextField studentDobField;
    @FXML
    private SplitMenuButton studentGenderMenu;
    @FXML
    private SplitMenuButton studentClassMenu;
    @FXML
    private SplitMenuButton studentSectionMenu;
    @FXML
    private TextField studentEmailField;
    @FXML
    private TextField studentPhoneField;
    @FXML
    private Button studentDeleteBtn;
    @FXML
    private Button studentMoreInfoBtn;
    @FXML
    private Text studentWarningText;
    @FXML
    private Label totalStudents;

    private final ObservableList<Student> studentList = FXCollections.observableArrayList();
    private StudentDAO studentDAO;

    @FXML
    public void initialize() {
        studentDAO = new StudentDAO();

        studentList.clear();
        studentList.addAll(studentDAO.getAllStudents());

        rollNumberColumn.setCellValueFactory(new PropertyValueFactory<>("rollNumber"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        fatherNameColumn.setCellValueFactory(new PropertyValueFactory<>("father"));
        studentGenderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        studentClassColumn.setCellValueFactory(new PropertyValueFactory<>("classLevel"));
        studentSectionColumn.setCellValueFactory(new PropertyValueFactory<>("section"));
        studentEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        studentPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        studentFeesColumn.setCellValueFactory(new PropertyValueFactory<>("totalFees"));
        studentFeesStatusColumn.setCellValueFactory(new PropertyValueFactory<>("feesStatus"));

        studentTableView.setItems(studentList);

        studentDeleteBtn.disableProperty().bind(studentTableView.getSelectionModel().selectedItemProperty().isNull());
        studentMoreInfoBtn.disableProperty().bind(studentTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    @FXML
    private void addStudent() {
        String studentName = studentNameField.getText();
        String fatherName = fatherNameField.getText();
        String dob = studentDobField.getText();
        String gender = studentGenderMenu.getText();
        String classLevel = studentClassMenu.getText();
        String section = studentSectionMenu.getText();
        String email = studentEmailField.getText();
        String phone = studentPhoneField.getText();
        String rollNumber = studentRollField.getText();
        int totalFees = 3000;
        String feesStatus = "Unpaid";

        if (studentName.isEmpty() || fatherName.isEmpty() || dob.isEmpty() || gender.equals("Gender")
                || classLevel.equals("Class") || section.equals("Section") || email.isEmpty()
                || phone.isEmpty() || rollNumber.isEmpty()) {
            studentWarningText.setVisible(true);
        } else {
            studentWarningText.setVisible(false);
            Student newStudent = new Student(
                    studentName,
                    fatherName,
                    dob,
                    gender,
                    classLevel,
                    section,
                    email,
                    phone,
                    rollNumber,
                    totalFees,
                    feesStatus
            );

            studentDAO.addStudent(newStudent);
            studentList.add(newStudent);
            studentTableView.setItems(studentList);
            clearStudentFields();
        }
    }

    @FXML
    private void clearStudentFields() {
        studentNameField.clear();
        fatherNameField.clear();
        studentDobField.clear();
        studentGenderMenu.setText("Gender");
        studentClassMenu.setText("Class");
        studentSectionMenu.setText("Section");
        studentEmailField.clear();
        studentPhoneField.clear();
        studentRollField.clear();
        studentWarningText.setVisible(false);
    }

    @FXML
    private void setStudentGender(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        studentGenderMenu.setText(menuItem.getText());
    }

    @FXML
    private void setStudentClass(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        studentClassMenu.setText(menuItem.getText());
    }

    @FXML
    private void setStudentSection(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        studentSectionMenu.setText(menuItem.getText());
    }

    public void handleDeleteButtonAction() {
        Student selectedStudent = studentTableView.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            deleteStudent(selectedStudent);
        }
    }

    private void deleteStudent(Student student) {
        String sql = "DELETE FROM students WHERE rollNumber = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:school.db");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, student.getRollNumber());
            pstmt.executeUpdate();
            studentList.remove(student);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleMoreInfoStudent() {
        Student selectedStudent = studentTableView.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            showStudentDetails(selectedStudent);
        }
    }

    private void showStudentDetails(Student student) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/schoolManagement/Fxml/Details_Student.fxml"));
            Parent root = loader.load();
            StudentDetailsController controller = loader.getController();
            controller.initData(student);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Student Details");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
