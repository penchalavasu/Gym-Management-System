package com.fitnesscare.dao;

import com.fitnesscare.model.Manager;
import com.fitnesscare.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Manager operations.
 */
public class ManagerDAO {

    public Manager findById(String managerId) throws SQLException {
        String sql = "SELECT managerid, password, name, gender, created_at FROM managerlogintable_gym WHERE managerid = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, managerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    public List<Manager> findAll() throws SQLException {
        List<Manager> list = new ArrayList<>();
        String sql = "SELECT managerid, password, name, gender, created_at FROM managerlogintable_gym ORDER BY created_at DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public List<Manager> search(String keyword) throws SQLException {
        List<Manager> list = new ArrayList<>();
        String sql = "SELECT managerid, password, name, gender, created_at FROM managerlogintable_gym WHERE managerid LIKE ? OR name LIKE ? ORDER BY created_at DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String pattern = "%" + keyword + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        }
        return list;
    }

    public boolean insert(Manager manager) throws SQLException {
        String sql = "INSERT INTO managerlogintable_gym (managerid, password, name, gender) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, manager.getManagerId());
            ps.setString(2, manager.getPassword());
            ps.setString(3, manager.getName());
            ps.setString(4, manager.getGender());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(Manager manager) throws SQLException {
        String sql = "UPDATE managerlogintable_gym SET name = ?, gender = ? WHERE managerid = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, manager.getName());
            ps.setString(2, manager.getGender());
            ps.setString(3, manager.getManagerId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updatePassword(String managerId, String hashedPassword) throws SQLException {
        String sql = "UPDATE managerlogintable_gym SET password = ? WHERE managerid = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hashedPassword);
            ps.setString(2, managerId);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(String managerId) throws SQLException {
        String sql = "DELETE FROM managerlogintable_gym WHERE managerid = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, managerId);
            return ps.executeUpdate() > 0;
        }
    }

    public int count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM managerlogintable_gym";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    public boolean existsById(String managerId) throws SQLException {
        String sql = "SELECT 1 FROM managerlogintable_gym WHERE managerid = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, managerId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    private Manager mapRow(ResultSet rs) throws SQLException {
        Manager m = new Manager();
        m.setManagerId(rs.getString("managerid"));
        m.setPassword(rs.getString("password"));
        m.setName(rs.getString("name"));
        m.setGender(rs.getString("gender"));
        m.setCreatedAt(rs.getTimestamp("created_at"));
        return m;
    }
}
