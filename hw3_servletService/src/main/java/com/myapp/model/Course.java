package com.myapp.model;

import java.util.List;
import java.util.Objects;

public class Course {
    private int id;
    private String name;
    private int hours;
    private int lecturerId;
    private List<Student> students;
    private Lecturer lecturer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && hours == course.hours && lecturerId == course.lecturerId
                && Objects.equals(name, course.name) && Objects.equals(students, course.students)
                && Objects.equals(lecturer, course.lecturer);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + hours;
        result = 31 * result + lecturerId;
        result = 31 * result + (students != null ? students.hashCode() : 0);
        result = 31 * result + (lecturer != null ? lecturer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Course {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", lecturerId=" + lecturerId +
                ", lecturer=" + (lecturer == null ? "null" : lecturer.toString()) +
                ", students=" + (students == null ? "null" : students.toString()) +
                '}';
    }
}