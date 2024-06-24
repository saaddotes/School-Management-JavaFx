package com.example.schoolManagement.controller;

import com.example.schoolManagement.database.StudentDAO;
import com.example.schoolManagement.database.TeacherDAO;
import com.example.schoolManagement.models.Student;
import com.example.schoolManagement.models.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Controller {

    public Text totalIncome;
    @FXML
    private TableView<Teacher> teacherTableView;

    @FXML
    private Button moreInfoBtn;
    @FXML
    private Button studentMoreInfoBtn;

    @FXML
    public TextField studentRollField;
    @FXML
    public Button teacherDeleteBtn;
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
    private TableColumn<Student, Integer> studentFeesColumn;
    @FXML
    private TableColumn<Student, Boolean> studentFeesStatusColumn;

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
    private Text studentWarningText;
    @FXML
    private Label totalStudents;
    @FXML
    private Label totalTeachers;

    @FXML
    private Button studentDeleteBtn;

    private ObservableList<Student> studentList;

    private StudentDAO studentDAO;
    private TeacherDAO teacherDAO;

    // Method to initialize the controller
    @FXML
    public void initialize() {
        // Initialize DAOs
        studentDAO = new StudentDAO();
        teacherDAO = new TeacherDAO();

        // Set up the axes for the BarChart
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

        // Initialize teacher list
        teacherList = FXCollections.observableArrayList();
        loadTeachersFromDatabase();

        // Set up the columns in the teacher table
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("classLevel"));
//        sectionColumn.setCellValueFactory(new PropertyValueFactory<>("section"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
//        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));

        // Initialize student list
        studentList = FXCollections.observableArrayList();
        loadStudentsFromDatabase();

        // Set up the columns in the student table
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

        tableView.setItems(teacherList);
        studentTableView.setItems(studentList);
        updateCounts();

        // Bind the delete button's disabled property to the selection model's selected item property
        studentDeleteBtn.disableProperty().bind(studentTableView.getSelectionModel().selectedItemProperty().isNull());
        teacherDeleteBtn.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
        moreInfoBtn.setDisable(true);
        studentMoreInfoBtn.setDisable(true);

        // Bind the button to enable based on selection in the table
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            moreInfoBtn.setDisable(newValue == null);
        });

        studentTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            studentMoreInfoBtn.setDisable(newValue == null);
        });


    }



    private void loadTeachersFromDatabase() {
        // Clear the current list
        teacherList.clear();

        // Load data from the database
        teacherList.addAll(teacherDAO.getAllTeachers());
    }

    private void loadStudentsFromDatabase() {
        // Clear the current list
        studentList.clear();

        // Load data from the database
        studentList.addAll(studentDAO.getAllStudents());
    }

    private void updateCounts() {
        int studentCount = studentDAO.getTotalStudents();
        int teacherCount = teacherDAO.getTotalTeachers();
        totalStudents.setText(String.valueOf(studentCount));
        totalTeachers.setText(String.valueOf(teacherCount));
    }


    @FXML
    private void addTeacher() {
        String id = idField.getText();
        String name = nameField.getText();
        String gender = genderMenu.getText();
        String subject = subjectField.getText();
        String classLevel = classMenu.getText();
//        String section = sectionMenu.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        if (name.isEmpty() || subject.isEmpty() || gender.equals("Gender")
                || classLevel.equals("Class")  || email.isEmpty()
                || phone.isEmpty() || id.isEmpty()) {
            warningText.setVisible(true);
        } else {
            warningText.setVisible(false);
            Teacher newTeacher = new Teacher(
                    Integer.parseInt(id),
                    name,
                    gender,
                    subject,
                    classLevel,
                    email,
                    phone
            );

            // Save to database
            teacherDAO.addTeacher(newTeacher);

            // Add to observable list
            teacherList.add(newTeacher);
            tableView.setItems(teacherList);
            clearFields();
            updateCounts();
        }
    }

    @FXML
    private void clearFields() {
        idField.clear();
        nameField.clear();
        genderMenu.setText("Gender");
        subjectField.clear();
//        dobField.clear();
        classMenu.setText("Class");
//        sectionMenu.setText("Section");
        emailField.clear();
        phoneField.clear();
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
        studentRollField.clear();
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
        int totalFees = 3000;
        String feesStatus = "Unpaid";

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
                    rollNumber,
                    totalFees,
                    feesStatus
            );


            // Save to database
            studentDAO.addStudent(newStudent);

            // Add to observable list
            studentList.add(newStudent);
            studentTableView.setItems(studentList);
            clearStudentFields();
            updateCounts();
        }
    }

    public void handleDeleteButtonAction(MouseEvent mouseEvent) {
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

            // Remove the student from the TableView
            studentTableView.getItems().remove(student);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void handleTeacherDelete(ActionEvent mouseEvent) {
        Teacher selectedTeacher = tableView.getSelectionModel().getSelectedItem();
        if (selectedTeacher != null) {
            deleteTeacher(selectedTeacher);
        }
    }

    private void deleteTeacher(Teacher selectedTeacher) {
        String sql = "DELETE FROM teachers WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:school.db");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, selectedTeacher.getId());
            pstmt.executeUpdate();

            // Remove the teacher from the TableView
            teacherList.remove(selectedTeacher);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleMoreInfo(ActionEvent event) {
        Teacher selectedTeacher = tableView.getSelectionModel().getSelectedItem();
        if (selectedTeacher != null) {
            showTeacherDetails(selectedTeacher);
        }
    }

    @FXML
    private void handleMoreInfoStudent(ActionEvent event) {
        Student selectedStudent = studentTableView.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            showStudentDetails(selectedStudent);
        }
    }

    private void showStudentDetails(Student student) {
        try {
            // Load the new window FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/schoolManagement/Fxml/student_details.fxml"));
            Parent root = loader.load();

            // Get the controller instance
            StudentDetailsController controller = loader.getController();

            // Pass the selected student to the controller
            controller.initData(student);

            // Create a new stage for the teacher details window
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Student Details");
            stage.setScene(new Scene(root));
            stage.showAndWait(); // Show and wait for it to be closed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showTeacherDetails(Teacher teacher) {
        try {
            // Load the new window FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/schoolManagement/Fxml/teacher_details.fxml"));
            Parent root = loader.load();

            // Get the controller instance
            TeacherDetailsController controller = loader.getController();

            // Pass the selected teacher to the controller
            controller.initData(teacher);

            // Create a new stage for the teacher details window
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Teacher Details");
            stage.setScene(new Scene(root));
            stage.showAndWait(); // Show and wait for it to be closed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
