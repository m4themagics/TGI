package com.myapp.servlet;

import com.myapp.dao.StudentDAO;
import com.myapp.model.Course;
import com.myapp.model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentServlet extends HttpServlet {

    private StudentDAO studentDAO;

    public StudentServlet() {
        this.studentDAO = new StudentDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Student> students = studentDAO.loadStudents();
            request.setAttribute("students", students);
            request.getRequestDispatcher("students.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error loading students", e);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    int studentId = Integer.parseInt(request.getParameter("studentId"));
                    String studentName = request.getParameter("studentName");
                    int age = Integer.parseInt(request.getParameter("age"));
                    String course = request.getParameter("course");

                    Student newStudent = new Student();
                    newStudent.setId(studentId);
                    newStudent.setName(studentName);
                    newStudent.setAge(age);
                    List<Course> courses = new ArrayList<>();
                    Course newCourse = new Course();
                    newCourse.setName(course);
                    courses.add(newCourse);
                    newStudent.setCourses(courses);

                    studentDAO.insertStudent(newStudent);
                    break;
                case "update":
                    int studentIdToUpdate = Integer.parseInt(request.getParameter("studentId"));
                    String updatedStudentName = request.getParameter("studentName");
                    int updatedAge = Integer.parseInt(request.getParameter("age"));
                    String updatedCourse = request.getParameter("course");

                    Student updatedStudent = new Student();
                    updatedStudent.setId(studentIdToUpdate);
                    updatedStudent.setName(updatedStudentName);
                    updatedStudent.setAge(updatedAge);
                    List<Course> updatedCourses = new ArrayList<>();
                    Course updatedCourseObj = new Course();
                    updatedCourseObj.setName(updatedCourse);
                    updatedCourses.add(updatedCourseObj);
                    updatedStudent.setCourses(updatedCourses);

                    studentDAO.updateStudent(updatedStudent);
                    break;
                case "delete":
                    int studentIdToDelete = Integer.parseInt(request.getParameter("studentId"));
                    studentDAO.deleteStudent(studentIdToDelete);
                    break;
                default:
                    break;
            }
        }
        response.sendRedirect("students");
    }
}