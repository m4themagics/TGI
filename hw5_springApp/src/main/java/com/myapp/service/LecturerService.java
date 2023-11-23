package com.myapp.service;

import com.myapp.entity.Lecturer;

import java.util.List;

public interface LecturerService {

    List<Lecturer> getAllLecturers();

    Lecturer getLecturer(int id);

    void saveLecturer(Lecturer lecturer);

    void deleteLecturer(int id);

    void addCourse(int lecturerId, int courseId);

    void removeCourse(int lecturerId, int courseId);
}
