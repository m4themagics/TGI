package com.myapp.dao;

import com.myapp.model.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private Connection connection;

    // Constructor
    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    // Load courses from database
    public List<Course> loadCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Course");

        // map SQL result set to list of Course objects
        while (resultSet.next()) {
            Course course = new Course();
            course.setId(resultSet.getInt("id"));
            course.setName(resultSet.getString("name"));
            course.setHours(resultSet.getInt("hours"));
            courses.add(course);
        }

        return courses;
    }
}
