package com.myapp.servlet;

import com.myapp.dao.CourseDAO;
import com.myapp.model.Course;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CourseServlet extends HttpServlet {

    private CourseDAO courseDAO;

    public CourseServlet() {
        this.courseDAO = new CourseDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Course> courses = courseDAO.loadCourses();
            request.setAttribute("courses", courses);
            request.getRequestDispatcher("courses.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error loading courses", e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    int newcourseId = Integer.parseInt(request.getParameter("courseId"));
                    String courseName = request.getParameter("courseName");
                    int hours = Integer.parseInt(request.getParameter("hours"));
                    int lecturerId = Integer.parseInt(request.getParameter("lecturerId"));

                    Course newCourse = new Course();
                    newCourse.setId(newcourseId);
                    newCourse.setName(courseName);
                    newCourse.setHours(hours);
                    newCourse.setLecturerId(lecturerId);

                    courseDAO.insertCourse(newCourse);

                    break;
                case "update":
                    int courseId = Integer.parseInt(request.getParameter("courseId"));
                    String updatedCourseName = request.getParameter("courseName");
                    int updatedHours = Integer.parseInt(request.getParameter("hours"));
                    int updatedLecturerId = Integer.parseInt(request.getParameter("lecturerId"));
                    Course updatedCourse = new Course();
                    updatedCourse.setId(courseId);
                    updatedCourse.setName(updatedCourseName);
                    updatedCourse.setHours(updatedHours);
                    updatedCourse.setLecturerId(updatedLecturerId);
                    courseDAO.updateCourse(updatedCourse);

                    break;
                case "delete":
                    int courseIdToDelete = Integer.parseInt(request.getParameter("courseId"));
                    courseDAO.deleteCourse(courseIdToDelete);
                    break;
                default:
                    break;
            }
        }
        response.sendRedirect("courses");
    }
}