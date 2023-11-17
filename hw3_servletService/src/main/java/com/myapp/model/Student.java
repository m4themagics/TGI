package com.myapp.model;

import java.util.List;

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
    public String toString() {
        return "Student {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", courses=" + (courses == null ? "null" : courses.toString()) +
                '}';
    }
}
