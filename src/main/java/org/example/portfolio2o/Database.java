package org.example.portfolio2o;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String URL = "jdbc:sqlite:portfolio2db";
    private Connection conn;

    public Database() {
        try {
            this.conn = DriverManager.getConnection(URL);
            if (conn != null && !conn.isClosed()) {
                System.out.println("Connection to SQLite has been established successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to establish database connection: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection to SQLite has been closed.");
            } catch (SQLException e) {
                System.out.println("Failed to close database connection: " + e.getMessage());
            }
        }
    }

    public List<String> fetchPrograms() {
        return fetchData("SELECT program_name FROM bachelor_programs");
    }

    public List<String> fetchCoursesForProgram(String programName) {
        String sql = "SELECT course_name FROM Course WHERE program_name = ?";
        return fetchDataWithFilter(sql, programName);
    }

    public List<String> fetchProjectsForProgram(String programName) {
        String sql = "SELECT project_name FROM Project WHERE program_name = ?";
        return fetchDataWithFilter(sql, programName);
    }

    public List<String> fetchElectives() {
        return fetchData("SELECT elective_name FROM Elective");
    }

    private List<String> fetchData(String query) {
        List<String> data = new ArrayList<>();
        if (conn == null) {
            return data;
        }
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                data.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
        return data;
    }

    private List<String> fetchDataWithFilter(String query, String filter) {
        List<String> data = new ArrayList<>();
        if (conn == null) {
            return data;
        }
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, filter);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                data.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching data with filter: " + e.getMessage());
        }
        return data;
    }

    public int getECTSForItem(String itemName, String tableName) {
        String sql = String.format("SELECT ects FROM %s WHERE %s_name = ?", tableName, tableName.toLowerCase());
        int ects = 0;
        if (conn == null) {
            return ects;
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, itemName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                ects = rs.getInt("ects");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching ECTS: " + e.getMessage());
        }
        return ects;
    }

    public void saveSelectedItem(String itemName, String category, int studentId) {
        String sql = "";
        switch (category) {
            case "Elective":
                sql = "INSERT INTO SelectedElective (student_id, elective_id) VALUES (?, (SELECT elective_id FROM Elective WHERE elective_name = ?))";
                break;
            case "Course":
                sql = "INSERT INTO SelectedCourse (student_id, course_id) VALUES (?, (SELECT course_id FROM Course WHERE course_name = ?))";
                break;
            case "Project":
                sql = "INSERT INTO SelectedProject (student_id, project_id) VALUES (?, (SELECT project_id FROM Project WHERE project_name = ?))";
                break;
            case "Program":
                sql = "INSERT INTO SelectedProgram (student_id, program_id) VALUES (?, (SELECT program_id FROM bachelor_programs WHERE program_name = ?))";
                break;
            default:
                return;
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.setString(2, itemName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving selected item: " + e.getMessage());
        }
    }
}
