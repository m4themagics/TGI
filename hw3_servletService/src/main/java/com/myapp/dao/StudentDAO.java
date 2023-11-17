package com.myapp.dao;

import com.myapp.model.Course;
import com.myapp.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;

    public StudentDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // обработка исключения
        }
        return connection;
    }

    public List<Student> loadStudents(Connection conn) throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Student";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setCourses(loadStudentCourses(conn, student.getId()));
                students.add(student);
            }
        }
        return students;
    }

    public List<Course> loadCourses(Connection conn) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Course";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setHours(rs.getInt("hours"));
                course.setStudents(loadCourseStudents(conn, course.getId()));
                courses.add(course);
            }
        }
        return courses;
    }

    public List<Course> loadStudentCourses(Connection conn, int studentId) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT c.id, c.name, c.hours FROM Course c " +
                "INNER JOIN StudentCourse sc ON c.id = sc.course_id " +
                "WHERE sc.student_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setHours(rs.getInt("hours"));
                courses.add(course);
            }
        }
        return courses;
    }

    public List<Student> loadCourseStudents(Connection conn, int courseId) throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT s.id, s.name, s.age FROM Student s " +
                "INNER JOIN StudentCourse sc ON s.id = sc.student_id " +
                "WHERE sc.course_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                students.add(student);
            }
        }
        return students;
    }
}