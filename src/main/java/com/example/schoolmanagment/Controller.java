package com.example.schoolmanagment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.Random;

public class Controller {
    @FXML
    public TextField studentRollField;
    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private TableView<Teacher> tableView;
    @FXML
    private TableColumn<Teacher, Integer> idColumn;
    @FXML
    private TableColumn<Teacher, String> nameColumn;
    @FXML
    private TableColumn<Teacher, String> genderColumn;
    @FXML
    private TableColumn<Teacher, String> subjectColumn;
    @FXML
    private TableColumn<Teacher, Integer> classColumn;
    @FXML
    private TableColumn<Teacher, String> sectionColumn;
    @FXML
    private TableColumn<Teacher, String> emailColumn;
    @FXML
    private TableColumn<Teacher, String> phoneColumn;
    @FXML
    private TableColumn<Teacher, String> actionColumn;

    @FXML
    private TextField nameField;
    @FXML
    private TextField subjectField;
    @FXML
    private TextField dobField;
    @FXML
    private SplitMenuButton genderMenu;
    @FXML
    private SplitMenuButton classMenu;
    @FXML
    private SplitMenuButton sectionMenu;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField idField;
    @FXML
    private Text warningText;

    private ObservableList<Teacher> teacherList;


    // Student fields
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
    private TextField rollNumberField;
    @FXML
    private Text studentWarningText;

    private ObservableList<Student> studentList;

    // Method to initialize the controller
    @FXML
    public void initialize() {
        // Set up the axes
        CategoryAxis xAxis = (CategoryAxis) barChart.getXAxis();
        NumberAxis yAxis = (NumberAxis) barChart.getYAxis();

        xAxis.setLabel("Month");
        yAxis.setLabel("Amount (Rs)");

        // Create a series for monthly fees
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Monthly Fees");

        // Add data to the series
        series.getData().add(new XYChart.Data<>("January", 2300));
        series.getData().add(new XYChart.Data<>("February", 2100));
        series.getData().add(new XYChart.Data<>("March", 2500));
        series.getData().add(new XYChart.Data<>("April", 2700));
        series.getData().add(new XYChart.Data<>("May", 2600));
        series.getData().add(new XYChart.Data<>("June", 2400));
        series.getData().add(new XYChart.Data<>("July", 2800));
        series.getData().add(new XYChart.Data<>("August", 3000));
        series.getData().add(new XYChart.Data<>("September", 2900));
        series.getData().add(new XYChart.Data<>("October", 3100));
        series.getData().add(new XYChart.Data<>("November", 3300));
        series.getData().add(new XYChart.Data<>("December", 3500));

        // Add the series to the BarChart
        barChart.getData().add(series);


        teacherList = FXCollections.observableArrayList();

        // Set up the columns in the table
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("classLevel"));
        sectionColumn.setCellValueFactory(new PropertyValueFactory<>("section"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));

        // Add 30 teachers initially
        for (int i = 1; i <= 10; i++) {
            teacherList.add(new Teacher(
                    i,
                    "Teacher " + i,
                    i % 2 == 0 ? "Male" : "Female",
                    "Subject " + i,
                    i % 10 + 1,
                    "A",
                    "teacher" + i + "@school.com",
                    "1234567890"
            ));
        }

        tableView.setItems(teacherList);

        studentList = FXCollections.observableArrayList();

        // Set up the columns in the student table
        rollNumberColumn.setCellValueFactory(new PropertyValueFactory<>("rollNumber"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        studentGenderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        studentClassColumn.setCellValueFactory(new PropertyValueFactory<>("classLevel"));
        studentSectionColumn.setCellValueFactory(new PropertyValueFactory<>("section"));
        studentEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        studentPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        // Add initial students if needed

        for (int i = 1; i <= 10; i++) {
            studentList.add(new Student(
                    "Student" + 1,
                    "Student " + i,
                    "DOb" + i ,
                    "male",
                    "Class 3",
                    "Subject " + i,
                    "A",
                    "teacher" + i + "@school.com",
                    1234
            ));
        }

        studentTableView.setItems(studentList);
    }




    @FXML
    private void addTeacher() {
        String name = nameField.getText();
        String subject = subjectField.getText();
        String dob = dobField.getText();
        String gender = genderMenu.getText();
        String classLevel = classMenu.getText();
        String section = sectionMenu.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String id = idField.getText();


        if (name.isEmpty() || subject.isEmpty() || dob.isEmpty() || gender.equals("Gender")
                || classLevel.equals("Class") || section.equals("Section") || email.isEmpty()
                || phone.isEmpty() || id.isEmpty()) {
            warningText.setVisible(true);
        } else {
            warningText.setVisible(false);
            Teacher newTeacher = new Teacher(
                    Integer.parseInt(id),
                    name,
                    gender,
                    subject,
                    Integer.parseInt(classLevel),
                    section,
                    email,
                    phone
            );
            teacherList.add(newTeacher);
            tableView.setItems(teacherList);
            clearFields();
        }
    }


    @FXML
    private void clearFields() {
        nameField.clear();
        subjectField.clear();
        dobField.clear();
        genderMenu.setText("Gender");
        classMenu.setText("Class");
        sectionMenu.setText("Section");
        emailField.clear();
        phoneField.clear();
        idField.clear();
        warningText.setVisible(false);
    }

    @FXML
    private void setGender(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        genderMenu.setText(menuItem.getText());
    }

    @FXML
    private void setClass(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        classMenu.setText(menuItem.getText());
    }

    @FXML
    private void setSection(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        sectionMenu.setText(menuItem.getText());
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
        rollNumberField.clear();
        studentWarningText.setVisible(false);
    }

    @FXML
    private void addStudent() {
        String studentname = studentNameField.getText();
        String fatherName = fatherNameField.getText();
        String dob = studentDobField.getText();
        String gender = studentGenderMenu.getText();
        String classLevel = studentClassMenu.getText();
        String section = studentSectionMenu.getText();
        String email = studentEmailField.getText();
        String phone = studentPhoneField.getText();
        String rollNumber = studentRollField.getText();

        if (studentname.isEmpty() || fatherName.isEmpty() || dob.isEmpty() || gender.equals("Gender")
                || classLevel.equals("Class") || section.equals("Section") || email.isEmpty()
                || phone.isEmpty() || rollNumber.isEmpty()) {
            studentWarningText.setVisible(true);
        } else {
            studentWarningText.setVisible(false);
            Student newStudent = new Student(
                    studentname,
                    fatherName,
                    dob,
                    gender,
                    classLevel,
                    section,
                    email,
                    phone,
                    Integer.parseInt(rollNumber)
            );
            studentList.add(newStudent);
            studentTableView.setItems(studentList);
            clearStudentFields();
        }
    }
}







