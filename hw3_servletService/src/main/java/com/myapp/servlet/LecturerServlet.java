package com.myapp.servlet;

import com.myapp.dao.LecturerDAO;
import com.myapp.model.Lecturer;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class LecturerServlet extends HttpServlet {
    private LecturerDAO lecturerDAO;

    public void init() {
        lecturerDAO = new LecturerDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Lecturer> lecturers = lecturerDAO.loadLecturers();
        request.setAttribute("lecturers", lecturers);
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
                    newLecturer.setName(name);
                    newLecturer.setSpecialty(specialty);
                    lecturerDAO.insertLecturer(newLecturer);
                    break;
                case "update":
                    int id = Integer.parseInt(request.getParameter("id"));
                    String updatedName = request.getParameter("name");
                    String updatedSpecialty = request.getParameter("specialty");
                    Lecturer updatedLecturer = new Lecturer();
                    updatedLecturer.setId(id);
                    updatedLecturer.setName(updatedName);
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