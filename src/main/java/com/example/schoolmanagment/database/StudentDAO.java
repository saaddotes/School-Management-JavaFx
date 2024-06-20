package com.example.schoolmanagment.database;

import com.example.schoolmanagment.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public void addStudent(Student student) {
        String query = "INSERT INTO students (rollNumber, name, fatherName,dob, gender, classLevel, section, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, student.getRollNumber());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getFatherName());
            pstmt.setString(4, student.getDob());
            pstmt.setString(5, student.getGender());
            pstmt.setString(6, student.getClassLevel());
            pstmt.setString(7, student.getSection());
            pstmt.setString(8, student.getEmail());
            pstmt.setString(9, student.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getString("name"),
                        rs.getString("fatherName"),
                        rs.getString("dob"),
                        rs.getString("gender"),
                        rs.getString("classLevel"),
                        rs.getString("section"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("rollNumber")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
