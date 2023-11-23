package com.myapp.service;

import com.myapp.entity.Course;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseService {
    @Transactional
    List<Course> getAllCourses();

    @Transactional
    Course getCourse(int id);

    @Transactional
    void saveCourse(Course course);

    @Transactional
    void deleteCourse(int id);

    @Transactional
    void addStudent(int studentId, int courseId);

    @Transactional
    void removeStudent(int studentId, int courseId);
}
