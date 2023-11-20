package com.myapp.tests;

import com.myapp.Entity.Course;
import com.myapp.Entity.Student;
import com.myapp.dao.CourseDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseDAOTest {

    private CourseDAO courseDAO;

    @BeforeEach
    public void setUp() {
        courseDAO = new CourseDAO();
    }

    @Test
    public void testLoadCourses() throws SQLException {
        List<Course> cours = courseDAO.loadCourses();
        assertEquals(false, cours.isEmpty());
    }

    @Test
    public void testInsertCourse() {
        Course course = new Course();
        course.setId(5);
        course.setName("Test Course");
        course.setHours(10);
        course.setLecturerId(1);
        courseDAO.insertCourse(course);
    }

    @Test
    public void testDeleteCourse() {
        courseDAO.deleteCourse(1);
    }

    @Test
    public void testUpdateCourse() {
        Course course = new Course();
        course.setId(1);
        course.setName("Updated Course Name");
        course.setHours(15);
        course.setLecturerId(2);
        courseDAO.updateCourse(course);
    }

    @Test
    public void testLoadCourseStudents() throws SQLException {
        List<Student> studentEntities = courseDAO.loadCoursesStudents(2);
        assertEquals(false, studentEntities.isEmpty());
    }
}