package com.myapp.dao;

import com.myapp.model.Course;
import com.myapp.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private Connection connection;

    public CourseDAO() {
        this.connection = new DatabaseConnectionManager().getConnection();
    }

    public List<Course> loadCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Course";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setHours(rs.getInt("hours"));
                course.setStudents(loadCourseStudents(connection, course.getId()));
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
