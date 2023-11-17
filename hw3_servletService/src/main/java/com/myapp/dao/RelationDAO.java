package com.myapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelationDAO {

    private Connection connection;

    // Constructor
    public RelationDAO(Connection connection) {
        this.connection = connection;
    }

    // Load course-student relations from database
    public Map<Integer, List<Integer>> loadRelations() throws SQLException {
        Map<Integer, List<Integer>> relations = new HashMap<>();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM StudentCourse");

        // map SQL result set to map of relation objects
        while (resultSet.next()) {
            int studentId = resultSet.getInt("student_id");
            int courseId = resultSet.getInt("course_id");
            relations.computeIfAbsent(studentId, k -> new ArrayList<>()).add(courseId);
        }

        return relations;
    }
}
