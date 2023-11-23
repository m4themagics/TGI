package com.myapp.service;

import com.myapp.entity.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentService {
    @Transactional
    List<Student> getAllStudents();

    @Transactional
    Student getStudent(int id);

    @Transactional
    void saveStudent(Student student);

    @Transactional
    void deleteStudent(int id);

    @Transactional
    void addCourse(int studentId, int courseId);

    @Transactional
    void removeCourse(int studentId, int courseId);
}
