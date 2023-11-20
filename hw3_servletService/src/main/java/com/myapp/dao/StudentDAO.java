package com.myapp.dao;

import com.myapp.Entity.Course;
import com.myapp.Entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private Connection connection;

    public StudentDAO() {
        this.connection = new DatabaseConnectionManager().getConnection();
    }

    public List<Student> loadStudents() throws SQLException {
        List<Student> studentEntities = new ArrayList<>();
        String query = "SELECT * FROM Student";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setCourses(loadStudentsCourses(student.getStudentId()));
                studentEntities.add(student);
            }
        }
        return studentEntities;
    }

    public void insertStudent(Student student) {
        String query = "INSERT INTO Student (student_id, student_name, age) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, student.getStudentId());
            stmt.setString(2, student.getName());
            stmt.setInt(3, student.getAge());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStudent(int id) {
        String query = "DELETE FROM Student WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudent(Student student) {
        String query = "UPDATE Student SET student_name = ?, age = ? WHERE student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, student.getName());
            stmt.setInt(2, student.getAge());
            stmt.setInt(3, student.getStudentId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Course> loadStudentsCourses(int studentId) throws SQLException {
        List<Course> cours = new ArrayList<>();
        String query = "SELECT c.course_id, c.course_name, c.hours, c.lecturer_id FROM Course c " +
                "INNER JOIN StudentCourse sc ON c.course_id = sc.course_id " +
                "WHERE sc.student_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setHours(rs.getInt("hours"));
                course.setLecturerId(rs.getInt("lecturer_id"));
                cours.add(course);
            }
        }
        return cours;
    }
}