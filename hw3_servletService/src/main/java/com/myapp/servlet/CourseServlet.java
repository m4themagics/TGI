package com.myapp.servlet;

import com.myapp.dao.StudentDAO;
import com.myapp.model.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CourseServlet", value = "/course")
public class CourseServlet extends HttpServlet {
    private StudentDAO studentDAO;

    public void init() {
        this.studentDAO = new StudentDAO();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Student> students = studentDAO.loadStudents();
            request.setAttribute("students", students);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            if (dispatcher != null) {
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("error", "An error occurred while processing your request.");
            RequestDispatcher errorDispatcher = request.getRequestDispatcher("error.jsp");
            if (errorDispatcher != null) {
                errorDispatcher.forward(request, response);
            }
        }
    }
}