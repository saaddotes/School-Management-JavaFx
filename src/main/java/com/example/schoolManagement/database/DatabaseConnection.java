package com.example.schoolManagement.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:school.db";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            createTablesIfNotExist(conn);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }

    public static void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createTablesIfNotExist(Connection conn) {
        String sqlStudents = "CREATE TABLE IF NOT EXISTS students (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " fatherName text NOT NULL,\n"
                + " dob text NOT NULL,\n"
                + " gender text NOT NULL,\n"
                + " classLevel text NOT NULL,\n"
                + " section text NOT NULL,\n"
                + " email text NOT NULL,\n"
                + " phone text NOT NULL,\n"
                + " rollNumber integer NOT NULL,\n"
                + "totalFees integer NOT NULL,\n"
                + "feesStatus bool NOT NULL\n"
                + ");";

        String sqlTeachers = "CREATE TABLE IF NOT EXISTS teachers (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " gender text NOT NULL,\n"
                + " subject text NOT NULL,\n"
                + " classLevel integer NOT NULL,\n"
                + " email text NOT NULL,\n"
                + " phone text NOT NULL\n"
                + ");";


        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sqlStudents);
            stmt.execute(sqlTeachers);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
