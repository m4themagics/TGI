package com.myapp.tests;

import com.myapp.Entity.Course;
import com.myapp.dao.CourseDAO;
import com.myapp.servlet.CourseServlet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CourseServletTest {

    private CourseServlet courseServlet;

    @Mock
    private CourseDAO courseDAO;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        courseServlet = new CourseServlet();
        Field field = CourseServlet.class.getDeclaredField("courseDAO");
        field.setAccessible(true);
        field.set(courseServlet, courseDAO);
    }

    @Test
    public void testDoGet() throws Exception {
        List<Course> cours = new ArrayList<>();
        when(courseDAO.loadCourses()).thenReturn(cours);
        when(request.getRequestDispatcher("courses.jsp")).thenReturn(requestDispatcher);

        courseServlet.doGet(request, response);

        verify(request).setAttribute("courses", cours);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void testDoPostAdd() throws Exception {
        when(request.getParameter("action")).thenReturn("add");
        when(request.getParameter("courseId")).thenReturn("1");
        when(request.getParameter("courseName")).thenReturn("Test Course");
        when(request.getParameter("hours")).thenReturn("10");
        when(request.getParameter("lecturerId")).thenReturn("1");

        courseServlet.doPost(request, response);

        verify(courseDAO).insertCourse(any(Course.class));
    }

    @Test
    public void testDoPostUpdate() throws Exception {
        when(request.getParameter("action")).thenReturn("update");
        when(request.getParameter("courseId")).thenReturn("1");
        when(request.getParameter("courseName")).thenReturn("Updated Course");
        when(request.getParameter("hours")).thenReturn("15");
        when(request.getParameter("lecturerId")).thenReturn("2");

        courseServlet.doPost(request, response);

        verify(courseDAO).updateCourse(any(Course.class));
    }

    @Test
    public void testDoPostDelete() throws Exception {
        when(request.getParameter("action")).thenReturn("delete");
        when(request.getParameter("courseId")).thenReturn("1");

        courseServlet.doPost(request, response);

        verify(courseDAO).deleteCourse(1);
    }
}