package com.myapp.service;

import com.myapp.dao.StudentDao;
import com.myapp.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    @Transactional
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    @Transactional
    public Student getStudent(int id) {
        return studentDao.getStudent(id);
    }

    @Override
    @Transactional
    public void saveStudent(Student student) {
        studentDao.saveStudent(student);
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {
        studentDao.deleteStudent(id);
    }

    @Override
    @Transactional
    public void addCourse(int studentId, int courseId) {
        studentDao.addCourse(studentId, courseId);
    }

    @Override
    @Transactional
    public void removeCourse(int studentId, int courseId) {
        studentDao.removeCourse(studentId, courseId);
    }
}
