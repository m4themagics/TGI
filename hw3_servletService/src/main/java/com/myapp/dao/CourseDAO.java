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
                course.setStudents(loadCoursesStudents(course.getId()));
                courses.add(course);
            }
        }
        return courses;
    }

    public void insertCourse(Course course) {
        String query = "INSERT INTO Course (id, name, hours, lecturer_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, course.getId());
            stmt.setString(2, course.getName());
            stmt.setInt(3, course.getHours());
            stmt.setInt(4, course.getLecturerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCourse(int id) {
        String deleteCourseQuery = "DELETE FROM Course WHERE id = ?";
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
        String query = "UPDATE Course SET name = ?, hours = ?, lecturer_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getHours());
            stmt.setInt(3, course.getLecturerId());
            stmt.setInt(4, course.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> loadCoursesStudents(int courseId) throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT s.id, s.name, s.age FROM Student s " +
                "INNER JOIN StudentCourse sc ON s.id = sc.student_id " +
                "WHERE sc.course_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
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
