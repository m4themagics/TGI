package com.myapp.dao;

import com.myapp.model.Doctor;
import com.myapp.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private final Connection connection;
    private PreparedStatement ps;

    public DoctorDAO() {
        this.connection = new DBConnection().conn();
    }

    public Doctor createNewDoctor(String name, String surname) throws SQLException {
        String sql = "INSERT INTO public.doctor(name, surname) VALUES (?,?)";
        ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ps.setString(2, surname);
        ps.executeUpdate();
        try (ResultSet resultSet = ps.getGeneratedKeys()) {
            if (resultSet.next()) {
                int generatedId = resultSet.getInt(1);
                return new Doctor(generatedId, name, surname);
            } else {
                throw new SQLException("Failed to retrieve generated key for Doctor");
            }
        }
    }

    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctor";

        ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            doctors.add(new Doctor(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("surname")));
        }
        return doctors;
    }
}