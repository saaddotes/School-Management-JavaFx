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

        xAxis.setLabel("Class");
        yAxis.setLabel("Count");

        updateStudentCount();
    }

    @FXML
    private void handleToggleData() {
        if (toggleDataButton.isSelected()) {
            updateTeacherCount();
        } else {
            updateStudentCount();
        }
    }


    private void updateStudentCount() {
        Map<String, Integer> studentCountByClass = studentDAO.getStudentCountByClass();
        updateChart(studentCountByClass, "Student Count");
    }

    private void updateTeacherCount() {
        Map<String, Integer> teacherCountByClass = teacherDAO.getTeacherCountByClass();
        updateChart(teacherCountByClass, "Teacher Count");
    }

    private void updateChart(Map<String, Integer> dataMap, String seriesName) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(seriesName);

        for (Map.Entry<String, Integer> entry : dataMap.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        barChart.getData().clear();
        barChart.getData().add(series);
    }


    public void updateCountStudent(int studentCount) {
        totalStudents.setText("" + studentCount);
    }

    public void updateCountTeacher(int teacherCount) {
        System.out.println(teacherCount);
        totalTeachers.setText("" + teacherCount);
    }
}


