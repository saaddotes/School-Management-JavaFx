package com.example.schoolManagement.controller;

import com.example.schoolManagement.database.TeacherDAO;
import com.example.schoolManagement.models.Teacher;
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

public class TeachersController {

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
    private TableColumn<Teacher, String> emailColumn;
    @FXML
    private TableColumn<Teacher, String> phoneColumn;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private MenuButton genderMenu;
    @FXML
    private TextField subjectField;
    @FXML
    private MenuButton classMenu;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private Button teacherDeleteBtn;
    @FXML
    private Button moreInfoBtn;
    @FXML
    private Text warningText;

    private ObservableList<Teacher> teacherList;
    private TeacherDAO teacherDAO;

    private DashboardController dashboardController;

    @FXML
    public void initialize() {
        teacherDAO = new TeacherDAO();
        teacherList = FXCollections.observableArrayList();
        loadTeachersFromDatabase();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("classLevel"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tableView.setItems(teacherList);
//        updateCounts();

        teacherDeleteBtn.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
        moreInfoBtn.setDisable(true);

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> moreInfoBtn.setDisable(newValue == null));
    }


    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    private void loadTeachersFromDatabase() {
        teacherList.clear();
        teacherList.addAll(teacherDAO.getAllTeachers());
    }


    @FXML
    private void addTeacher() {
        String id = idField.getText();
        String name = nameField.getText();
        String gender = genderMenu.getText();
        String subject = subjectField.getText();
        String classLevel = classMenu.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        if (name.isEmpty() || subject.isEmpty() || gender.equals("Gender")
                || classLevel.equals("Class") || email.isEmpty()
                || phone.isEmpty() || id.isEmpty()) {
            warningText.setVisible(true);
            System.out.println("Wrong Data");
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

            teacherDAO.addTeacher(newTeacher);
            teacherList.add(newTeacher);
            tableView.setItems(teacherList);
            clearFields();

//            DashboardController dashboardController = new DashboardController();
//            dashboardController.updateCountTeacher(teacherDAO.getTotalTeachers());
      }
    }

    @FXML
    private void clearFields() {
        idField.clear();
        nameField.clear();
        genderMenu.setText("Gender");
        subjectField.clear();
        classMenu.setText("Class");
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

    public void handleTeacherDelete() {
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

            teacherList.remove(selectedTeacher);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleMoreInfo() {
        Teacher selectedTeacher = tableView.getSelectionModel().getSelectedItem();
        if (selectedTeacher != null) {
            showTeacherDetails(selectedTeacher);
        }
    }

    private void showTeacherDetails(Teacher teacher) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/schoolManagement/fxml/teacher_details.fxml"));
            Parent root = loader.load();
            TeacherDetailsController controller = loader.getController();
            controller.initData(teacher);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Teacher Details");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
