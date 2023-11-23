package com.myapp.service;

import com.myapp.dao.CourseDao;
import com.myapp.entity.Course;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDAO;

    public CourseServiceImpl(CourseDao courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    @Transactional
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    @Override
    @Transactional
    public Course getCourse(int id) {
        return courseDAO.getCourse(id);
    }

    @Override
    @Transactional
    public void saveCourse(Course course) {
        courseDAO.saveCourse(course);
    }

    @Override
    @Transactional
    public void deleteCourse(int id) {
        courseDAO.deleteCourse(id);
    }

    @Override
    @Transactional
    public void addStudent(int courseId, int studentId) {
        courseDAO.addStudent(courseId, studentId);
    }

    @Override
    @Transactional
    public void removeStudent(int courseId, int studentId) {
        courseDAO.removeStudent(courseId, studentId);
    }
}
