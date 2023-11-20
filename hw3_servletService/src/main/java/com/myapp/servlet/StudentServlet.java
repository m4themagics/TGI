package com.myapp.servlet;

import com.myapp.Entity.Student;
import com.myapp.dao.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentServlet extends HttpServlet {

    private StudentDAO studentDAO;

    public StudentServlet() {
        this.studentDAO = new StudentDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Student> studentEntities = studentDAO.loadStudents();
            request.setAttribute("students", studentEntities);
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
                    String studentName = request.getParameter("studentName");
                    int age = Integer.parseInt(request.getParameter("age"));
                    String course = request.getParameter("course");

                    Student newStudent = new Student();
                    newStudent.setStudentName(studentName);
                    newStudent.setAge(age);

                    studentDAO.insertStudent(newStudent);
                    break;
                case "update":
                    String updatedStudentName = request.getParameter("studentName");
                    int updatedAge = Integer.parseInt(request.getParameter("age"));
                    String updatedCourse = request.getParameter("course");

                    Student updatedStudent = new Student();
                    updatedStudent.setStudentName(updatedStudentName);
                    updatedStudent.setAge(updatedAge);

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