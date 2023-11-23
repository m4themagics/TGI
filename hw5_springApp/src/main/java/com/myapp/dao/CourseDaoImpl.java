package com.myapp.dao;

import com.myapp.entity.Course;
import com.myapp.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    private final SessionFactory sessionFactory;

    public CourseDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Course> getAllCourses() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Course", Course.class).getResultList();
    }

    @Override
    public Course getCourse(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Course.class, id);
    }

    @Override
    public void saveCourse(Course course) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(course);
    }

    @Override
    public void deleteCourse(int id) {
        Session session = sessionFactory.getCurrentSession();
        Course course = session.get(Course.class, id);
        if (course != null) {
            session.delete(course);
        }
    }

    @Override
    public void addStudent(int studentId, int courseId) {
        Session session = sessionFactory.getCurrentSession();
        Course course = session.get(Course.class, courseId);
        Student student = session.get(Student.class, studentId);
        course.getCourseStudents().add(student);
    }

    @Override
    public void removeStudent(int studentId, int courseId) {
        Session session = sessionFactory.getCurrentSession();
        Course course = session.get(Course.class, courseId);
        Student student = session.get(Student.class, studentId);
        course.getCourseStudents().remove(student);
    }
}
