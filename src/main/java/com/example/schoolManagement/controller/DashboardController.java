package com.example.schoolManagement.controller;

import com.example.schoolManagement.database.StudentDAO;
import com.example.schoolManagement.database.TeacherDAO;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

import java.util.Map;

public class DashboardController {

    @FXML
    public Label totalTeachers;
    @FXML
    public Label totalStudents;
    @FXML
    public Text totalIncome;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private ToggleButton toggleDataButton;

    StudentDAO studentDAO = new StudentDAO();
    TeacherDAO teacherDAO = new TeacherDAO();


    @FXML
    public void initialize() {

        updateCountStudent(studentDAO.getTotalStudents());
        updateCountTeacher(teacherDAO.getTotalTeachers());

        // Set up the axes for the BarChart
        CategoryAxis xAxis = (CategoryAxis) barChart.getXAxis();
        NumberAxis yAxis = (NumberAxis) barChart.getYAxis();

        xAxis.setLabel("Classes");
        yAxis.setLabel("Count");

        XYChart.Series<String, Number> students = new XYChart.Series<>();
        students.setName("Students Count");

        Map<String, Integer> studentCountByClass = studentDAO.getStudentCountByClass();

        for (Map.Entry<String, Integer> entry : studentCountByClass.entrySet()) {
            students.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        XYChart.Series<String, Number> teachers = new XYChart.Series<>();
        teachers.setName("Teachers Count");

        Map<String, Integer> teacherCountByClass = teacherDAO.getTeacherCountByClass();

        for (Map.Entry<String, Integer> entry : teacherCountByClass.entrySet()) {
            teachers.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        barChart.getData().addAll(students, teachers);

    }

    public void updateCountStudent(int studentCount) {
        totalStudents.setText(String.valueOf(studentCount));
    }

    public void updateCountTeacher(int teacherCount) {
        System.out.println(teacherCount);
        totalTeachers.setText(String.valueOf(teacherCount));
    }
}


