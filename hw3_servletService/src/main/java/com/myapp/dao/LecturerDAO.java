package com.myapp.dao;

import com.myapp.model.Lecturer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LecturerDAO {
    private final Connection connection;

    public LecturerDAO() {
        this.connection = new DatabaseConnectionManager().getConnection();
    }

    public List<Lecturer> loadLecturers() {
        List<Lecturer> lecturers = new ArrayList<>();
        String query = "SELECT * FROM Lecturer";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lecturer lecturer = new Lecturer();
                lecturer.setId(rs.getInt("id"));
                lecturer.setName(rs.getString("name"));
                lecturer.setSpecialty(rs.getString("specialty"));
                lecturers.add(lecturer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lecturers;
    }

    public void insertLecturer(Lecturer lecturer) {
        String query = "INSERT INTO Lecturer (id, name, specialty) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, lecturer.getId());
            stmt.setString(2, lecturer.getName());
            stmt.setString(3, lecturer.getSpecialty());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteLecturer(int id) {
        String checkQuery = "SELECT * FROM course WHERE lecturer_id = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                throw new RuntimeException("Cannot delete lecturer with associated courses");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String deleteQuery = "DELETE FROM Lecturer WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteQuery)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateLecturer(Lecturer lecturer) {
        String query = "UPDATE Lecturer SET name = ?, specialty = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, lecturer.getName());
            stmt.setString(2, lecturer.getSpecialty());
            stmt.setInt(3, lecturer.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

// insertLecturer, deleteLecturer, updateLecturer