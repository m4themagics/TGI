package com.myapp.dao;

import com.myapp.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents();

    Student getStudent(int id);

    void saveStudent(Student student);

    void deleteStudent(int id);

    void removeCourse(int studentId, int courseId);

    void addCourse(int studentId, int courseId);

}
