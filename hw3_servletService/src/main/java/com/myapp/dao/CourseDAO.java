package com.myapp.dao;

import com.myapp.Entity.Course;
import com.myapp.Entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseDAO {

    private Connection connection;

    public CourseDAO() {
        this.connection = new DatabaseConnectionManager().getConnection();
    }

    public List<Course> loadCourses() throws SQLException {
        Set<Course> cours = new HashSet<>();
        String query = "SELECT * FROM Course";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("id"));
                course.setCourseName(rs.getString("name"));
                course.setHours(rs.getInt("hours"));
                course.setCourseStudents(loadCoursesStudents(course.getCourseId()));
                cours.add(course);
            }
        }
        return cours;
    }

    public void insertCourse(Course course) {
        String query = "INSERT INTO Course (course_name, hours, lecturer_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, course.getCourseId());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getHours());
            stmt.setInt(4, course.getLecturerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCourse(int id) {
        String deleteCourseQuery = "DELETE FROM Course WHERE course_id = ?";
        String deleteStudentCourseQuery = "DELETE FROM StudentCourse WHERE course_id = ?";

        try (PreparedStatement deleteStudentCourseStmt = connection.prepareStatement(deleteStudentCourseQuery)) {
            deleteStudentCourseStmt.setInt(1, id);
            deleteStudentCourseStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting associated student-course records", e);
        }

        try (PreparedStatement deleteCourseStmt = connection.prepareStatement(deleteCourseQuery)) {
            deleteCourseStmt.setInt(1, id);
            deleteCourseStmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting course", e);
        }
    }

    public void updateCourse(Course course) {
        String query = "UPDATE Course SET course_name = ?, hours = ?, lecturer_id = ? WHERE course_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, course.getCourseName());
            stmt.setInt(2, course.getHours());
            stmt.setInt(3, course.getLecturerId());
            stmt.setInt(4, course.getCourseId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> loadCoursesStudents(int courseId) throws SQLException {
        List<Student> studentEntities = new ArrayList<>();
        String query = "SELECT s.student_id, s.student_name, s.age FROM Student s " +
                "INNER JOIN StudentCourse sc ON s.student_id = sc.student_id " +
                "WHERE sc.course_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("id"));
                student.setStudentName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                studentEntities.add(student);
            }
        }
        return studentEntities;
    }
}
