// TeacherDAO.java
package com.example.schoolManagement.database;

import com.example.schoolManagement.models.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    public void addTeacher(Teacher teacher) {
        String query = "INSERT INTO teachers (id, name, gender, subject, classLevel, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, teacher.getId());
            pstmt.setString(2, teacher.getName());
            pstmt.setString(3, teacher.getGender());
            pstmt.setString(4, teacher.getSubject());
            pstmt.setString(5, teacher.getClassLevel());
            pstmt.setString(6, teacher.getEmail());
            pstmt.setString(7, teacher.getPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM teachers";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("subject"),
                        rs.getString("classLevel"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public void updateTeacher(Teacher teacher) {
        String sql = "UPDATE teachers SET name = ?, gender = ?, subject = ?, class = ?, email = ?, phone = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:school.db");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, teacher.getName());
            pstmt.setString(2, teacher.getGender());
            pstmt.setString(3, teacher.getSubject());
            pstmt.setString(4, teacher.getClassLevel());
            pstmt.setString(5, teacher.getEmail());
            pstmt.setString(6, teacher.getPhone());
            pstmt.setInt(7, teacher.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
