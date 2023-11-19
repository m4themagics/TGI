package com.myapp.dao;

import com.myapp.model.Course;
import com.myapp.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final Connection connection;

    public StudentDAO() {
        this.connection = new DatabaseConnectionManager().getConnection();
    }

    public List<Student> loadStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Student";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setCourses(loadStudentCourses(connection, student.getId()));
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
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
}