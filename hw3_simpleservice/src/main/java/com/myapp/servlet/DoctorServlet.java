package com.myapp.servlet;

import com.myapp.dao.DoctorDAO;
import com.myapp.model.Doctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/mydoctor")
public class DoctorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DoctorServlet.class);
    private DoctorDAO doctorDAO;

    public DoctorServlet(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Doctor> doctorList = doctorDAO.getAllDoctors();
            request.setAttribute("doctors", doctorList);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException e) {
            logger.error("Error retrieving doctors", e);
            response.getWriter().write("Error retrieving doctors: " + e.getMessage());
        }
    }

    /*
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String doctorName = request.getParameter("doctorName");

        try {
            Doctor newDoctor = doctorDAO.createNewDoctor(doctorName);
            response.getWriter().write("New Doctor created: " + newDoctor.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Error creating new Doctor: " + e.getMessage());
        }
    } */
}