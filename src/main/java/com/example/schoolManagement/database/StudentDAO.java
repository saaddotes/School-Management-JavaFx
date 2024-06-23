package com.example.schoolManagement.database;

import com.example.schoolManagement.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public void addStudent(Student student) {
        String query = "INSERT INTO students (rollNumber, name, fatherName,dob, gender, classLevel, section, email, phone,totalFees, feesStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, student.getRollNumber());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getFatherName());
            pstmt.setString(4, student.getDob());
            pstmt.setString(5, student.getGender());
            pstmt.setString(6, student.getClassLevel());
            pstmt.setString(7, student.getSection());
            pstmt.setString(8, student.getEmail());
            pstmt.setString(9, student.getPhone());
            pstmt.setInt(10, student.getTotalFees());
            pstmt.setString(11, student.isFeesStatus());


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
                        rs.getString("rollNumber"),
                        rs.getInt("totalFees"),
                        rs.getString("feesStatus")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public int getTotalStudents() {
        String query = "SELECT COUNT(*) AS total FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalTeachers() {
        String query = "SELECT COUNT(*) AS total FROM teachers";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}