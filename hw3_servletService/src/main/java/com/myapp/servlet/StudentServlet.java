package com.myapp.servlet;

import com.myapp.dao.StudentDAO;
import com.myapp.model.Course;
import com.myapp.model.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/student")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;


    public void init() {
        initDAO();
    }

    void initDAO() {
        String jdbcURL = "jdbc:postgresql://localhost:5432/Courses";
        String jdbcUsername = "postgres";
        String jdbcPassword = "suBa4net";
        this.studentDAO = new StudentDAO(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = studentDAO.getConnection()) {
            List<Student> students = studentDAO.loadStudents(conn);
            List<Course> courses = studentDAO.loadCourses(conn);
            request.setAttribute("students", students);
            request.setAttribute("courses", courses);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            if (dispatcher != null) {
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            // обработка исключения
        }
    }
}