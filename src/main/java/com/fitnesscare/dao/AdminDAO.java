package com.fitnesscare.dao;

import com.fitnesscare.model.Admin;
import com.fitnesscare.util.DBUtil;

import java.sql.*;

/**
 * Data Access Object for Admin operations.
 */
public class AdminDAO {

    /**
     * Find admin by ID.
     */
    public Admin findById(String id) throws SQLException {
        String sql = "SELECT id, password, name, created_at FROM admin_gym WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    /**
     * Update admin profile.
     */
    public boolean update(Admin admin) throws SQLException {
        String sql = "UPDATE admin_gym SET name = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getId());
            return ps.executeUpdate() > 0;
        }
    }

    private Admin mapRow(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setId(rs.getString("id"));
        admin.setPassword(rs.getString("password"));
        admin.setName(rs.getString("name"));
        admin.setCreatedAt(rs.getTimestamp("created_at"));
        return admin;
    }
}
