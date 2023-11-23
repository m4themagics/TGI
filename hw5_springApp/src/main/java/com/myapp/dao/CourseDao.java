package com.myapp.dao;

import com.myapp.entity.Course;

import java.util.List;

public interface CourseDao {
    List<Course> getAllCourses();

    Course getCourse(int id);

    void saveCourse(Course course);

    void deleteCourse(int id);

    void removeStudent(int studentId, int courseId);

    void addStudent(int studentId, int courseId);
}
