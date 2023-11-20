package com.myapp.servlet;

import com.myapp.Entity.Lecturer;
import com.myapp.dao.LecturerDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LecturerServlet extends HttpServlet {
    private LecturerDAO lecturerDAO;

    public void init() {
        lecturerDAO = new LecturerDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Lecturer> lecturerEntities = lecturerDAO.loadLecturers();
        request.setAttribute("lecturers", lecturerEntities);
        RequestDispatcher dispatcher = request.getRequestDispatcher("lecturers.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    String name = request.getParameter("name");
                    String specialty = request.getParameter("specialty");
                    Lecturer newLecturer = new Lecturer();
                    newLecturer.setLecturerName(name);
                    newLecturer.setSpecialty(specialty);
                    lecturerDAO.insertLecturer(newLecturer);
                    break;
                case "update":
                    String updatedName = request.getParameter("name");
                    String updatedSpecialty = request.getParameter("specialty");
                    Lecturer updatedLecturer = new Lecturer();
                    updatedLecturer.setLecturerName(updatedName);
                    updatedLecturer.setSpecialty(updatedSpecialty);
                    lecturerDAO.updateLecturer(updatedLecturer);
                    break;
                case "delete":
                    int lecturerId = Integer.parseInt(request.getParameter("id"));
                    lecturerDAO.deleteLecturer(lecturerId);
                    break;
                default:
                    break;
            }
        }

        response.sendRedirect("lecturers");
    }
}