package com.myapp.dao;

import com.myapp.entity.Course;
import com.myapp.entity.Lecturer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LecturerDaoImplTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    private LecturerDaoImpl lecturerDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        lecturerDao = new LecturerDaoImpl(sessionFactory);
    }

    @Test
    public void testGetAllLecturers() {
        List<Lecturer> expectedLecturers = Arrays.asList(new Lecturer(), new Lecturer());
        Query<Lecturer> query = mock(Query.class);
        when(session.createQuery("from Lecturer", Lecturer.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(expectedLecturers);

        List<Lecturer> actualLecturers = lecturerDao.getAllLecturers();

        assertEquals(expectedLecturers, actualLecturers);
    }

    @Test
    public void testGetLecturer() {
        Lecturer expectedLecturer = new Lecturer();
        when(session.get(Lecturer.class, 1)).thenReturn(expectedLecturer);

        Lecturer actualLecturer = lecturerDao.getLecturer(1);

        assertEquals(expectedLecturer, actualLecturer);
    }

    @Test
    public void testSaveLecturer() {
        Lecturer lecturer = new Lecturer();

        lecturerDao.saveLecturer(lecturer);

        verify(session).saveOrUpdate(lecturer);
    }

    @Test
    public void testDeleteLecturer() {
        Lecturer lecturer = new Lecturer();
        when(session.get(Lecturer.class, 1)).thenReturn(lecturer);

        lecturerDao.deleteLecturer(1);

        verify(session).delete(lecturer);
    }

    @Test
    public void testAddCourse() {
        Lecturer lecturer = new Lecturer();
        Course course = new Course();
        when(session.get(Lecturer.class, 1)).thenReturn(lecturer);
        when(session.get(Course.class, 2)).thenReturn(course);

        lecturerDao.addCourse(1, 2);

        verify(session).get(Lecturer.class, 1);
        verify(session).get(Course.class, 2);
        assertTrue(lecturer.getLecturerCourses().contains(course));
    }

    @Test
    public void testRemoveCourse() {
        Lecturer lecturer = new Lecturer();
        Course course = new Course();
        lecturer.getLecturerCourses().add(course);
        when(session.get(Lecturer.class, 1)).thenReturn(lecturer);
        when(session.get(Course.class, 2)).thenReturn(course);

        lecturerDao.removeCourse(1, 2);

        verify(session).get(Lecturer.class, 1);
        verify(session).get(Course.class, 2);
        assertFalse(lecturer.getLecturerCourses().contains(course));
    }
}