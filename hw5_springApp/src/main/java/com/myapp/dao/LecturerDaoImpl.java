package com.myapp.dao;

import com.myapp.entity.Course;
import com.myapp.entity.Lecturer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LecturerDaoImpl implements LecturerDao {

    private final SessionFactory sessionFactory;

    public LecturerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Lecturer", Lecturer.class).getResultList();
    }

    @Override
    public Lecturer getLecturer(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Lecturer.class, id);
    }

    @Override
    public void saveLecturer(Lecturer lecturer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(lecturer);
    }

    @Override
    public void deleteLecturer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Lecturer lecturer = session.get(Lecturer.class, id);
        if (lecturer != null) {
            session.delete(lecturer);
        }
    }

    @Override
    public void addCourse(int lecturerId, int courseId) {
        Session session = sessionFactory.getCurrentSession();
        Lecturer lecturer = session.get(Lecturer.class, lecturerId);
        Course course = session.get(Course.class, courseId);
        lecturer.getLecturerCourses().add(course);
    }

    @Override
    public void removeCourse(int lecturerId, int courseId) {
        Session session = sessionFactory.getCurrentSession();
        Lecturer lecturer = session.get(Lecturer.class, lecturerId);
        Course course = session.get(Course.class, courseId);
        lecturer.getLecturerCourses().remove(course);
    }
}