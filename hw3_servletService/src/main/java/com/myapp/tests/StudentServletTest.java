package com.myapp.tests;

import com.myapp.Entity.Student;
import com.myapp.dao.StudentDAO;
import com.myapp.servlet.StudentServlet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class StudentServletTest {

    @Mock
    private StudentDAO studentDAO;

    @InjectMocks
    private StudentServlet studentServlet;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        List<Student> studentEntities = new ArrayList<>();
        when(studentDAO.loadStudents()).thenReturn(studentEntities);
        when(request.getRequestDispatcher("students.jsp")).thenReturn(dispatcher);

        studentServlet.doGet(request, response);

        verify(request).setAttribute("students", studentEntities);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPostAdd() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("action")).thenReturn("add");
        when(request.getParameter("studentId")).thenReturn("1");
        when(request.getParameter("studentName")).thenReturn("John Doe");
        when(request.getParameter("age")).thenReturn("25");
        when(request.getParameter("course")).thenReturn("Math");

        studentServlet.doPost(request, response);

        verify(studentDAO).insertStudent(any(Student.class));
    }

    @Test
    public void testDoPostUpdate() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("action")).thenReturn("update");
        when(request.getParameter("studentId")).thenReturn("1");
        when(request.getParameter("studentName")).thenReturn("Updated Name");
        when(request.getParameter("age")).thenReturn("30");
        when(request.getParameter("course")).thenReturn("Physics");

        studentServlet.doPost(request, response);

        verify(studentDAO).updateStudent(any(Student.class));
    }

    @Test
    public void testDoPostDelete() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("action")).thenReturn("delete");
        when(request.getParameter("studentId")).thenReturn("1");

        studentServlet.doPost(request, response);

        verify(studentDAO).deleteStudent(1);
    }
}