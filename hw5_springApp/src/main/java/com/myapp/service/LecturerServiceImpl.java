package com.myapp.service;

import com.myapp.dao.LecturerDao;
import com.myapp.entity.Lecturer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LecturerServiceImpl implements LecturerService {

    private final LecturerDao lecturerDAO;

    public LecturerServiceImpl(LecturerDao lecturerDAO) {
        this.lecturerDAO = lecturerDAO;
    }

    @Override
    @Transactional
    public List<Lecturer> getAllLecturers() {
        return lecturerDAO.getAllLecturers();
    }

    @Override
    @Transactional
    public Lecturer getLecturer(int id) {
        return lecturerDAO.getLecturer(id);
    }

    @Override
    @Transactional
    public void saveLecturer(Lecturer lecturer) {
        lecturerDAO.saveLecturer(lecturer);
    }

    @Override
    @Transactional
    public void deleteLecturer(int id) {
        lecturerDAO.deleteLecturer(id);
    }

    @Override
    @Transactional
    public void addCourse(int lecturerId, int courseId) {
        lecturerDAO.addCourse(lecturerId, courseId);
    }

    @Override
    @Transactional
    public void removeCourse(int lecturerId, int courseId) {
        lecturerDAO.removeCourse(lecturerId, courseId);
    }
}
