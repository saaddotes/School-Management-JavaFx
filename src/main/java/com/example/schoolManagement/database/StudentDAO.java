package com.example.schoolManagement.database;

import com.example.schoolManagement.models.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDAO {

    private final String url = "jdbc:sqlite:school.db";

    public void addStudent(Student student) {
        String query = "INSERT INTO students (rollNumber, name, fatherName, dob, gender, classLevel, section, email, phone, totalFees, feesStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, student.getRollNumber());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getFather());
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

    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, fatherName = ?, dob = ?, gender = ?, classLevel = ?, section = ?, email = ?, phone = ?, totalFees = ?, feesStatus = ? WHERE rollNumber = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getFather());
            pstmt.setString(3, student.getDob());
            pstmt.setString(4, student.getGender());
            pstmt.setString(5, student.getClassLevel());
            pstmt.setString(6, student.getSection());
            pstmt.setString(7, student.getEmail());
            pstmt.setString(8, student.getPhone());
            pstmt.setInt(9, student.getTotalFees());
            pstmt.setString(10, student.isFeesStatus());
            pstmt.setString(11, student.getRollNumber());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> getStudentCountByClass() {
        Map<String, Integer> classData = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT classLevel, COUNT(*) AS count FROM students GROUP BY classLevel")) {

            while (rs.next()) {
                String classLevel = rs.getString("classLevel");
                int count = rs.getInt("count");
                classData.put(classLevel, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classData;
    }
}
