package com.myapp.model;

import java.util.List;
import java.util.Objects;

public class Student {
    private int id;
    private String name;
    private int age;
    private List<Course> courses;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && age == student.age && Objects.equals(name, student.name) && Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", courses=" + (courses == null ? "null" : courses.toString()) +
                '}';
    }
}
