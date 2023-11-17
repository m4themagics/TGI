package com.myapp.servlet;

import com.myapp.dao.StudentDAO;
import com.myapp.model.Course;
import com.myapp.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StudentServletTest {

    private StudentDAO studentDAO;
    private StudentServlet studentServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        studentDAO = mock(StudentDAO.class);
        studentServlet = new StudentServlet();
        studentServlet.initDAO();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testDoGet() throws SQLException, ServletException, IOException {
        // Arrange
        List<Student> expectedStudents = Arrays.asList(new Student(), new Student());
        when(studentDAO.loadStudents(any())).thenReturn(expectedStudents);
        List<Course> expectedCourses = Arrays.asList(new Course(), new Course());
        when(studentDAO.loadCourses(any())).thenReturn(expectedCourses);

        // Act
        studentServlet.doGet(request, response);

        // Assert
        verify(request).setAttribute("students", expectedStudents);
        verify(request).setAttribute("courses", expectedCourses);
    }

    @Test
    public void testDoGetSQLException() throws SQLException, ServletException, IOException {
        // Arrange
        doThrow(new SQLException()).when(studentDAO).loadStudents(any());

        // Act & Assert
        try {
            studentServlet.doGet(request, response);
        } catch (ServletException e) {
            assertEquals(
                    "java.sql.SQLException",
                    e.getCause().getClass().getName());
        }
    }
}