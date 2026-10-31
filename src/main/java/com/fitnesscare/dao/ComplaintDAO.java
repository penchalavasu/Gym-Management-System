package com.fitnesscare.dao;

import com.fitnesscare.model.Complaint;
import com.fitnesscare.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Complaint operations.
 */
public class ComplaintDAO {

    public Complaint findById(String id) throws SQLException {
        String sql = "SELECT * FROM complaint_gym WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    public List<Complaint> findAll() throws SQLException {
        List<Complaint> list = new ArrayList<>();
        String sql = "SELECT * FROM complaint_gym ORDER BY created_at DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public List<Complaint> findByMemberId(String memberId) throws SQLException {
        List<Complaint> list = new ArrayList<>();
        String sql = "SELECT * FROM complaint_gym WHERE member_id = ? ORDER BY created_at DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        }
        return list;
    }

    public List<Complaint> search(String keyword, String status) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM complaint_gym WHERE 1=1");
        List<String> params = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" AND (id LIKE ? OR member_id LIKE ? OR name LIKE ? OR subject LIKE ?)");
            String pattern = "%" + keyword.trim() + "%";
            params.add(pattern);
            params.add(pattern);
            params.add(pattern);
            params.add(pattern);
        }
        if (status != null && !status.trim().isEmpty()) {
            sql.append(" AND status = ?");
            params.add(status.trim());
        }
        sql.append(" ORDER BY created_at DESC");

        List<Complaint> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setString(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        }
        return list;
    }

    public List<Complaint> findRecent(int limit) throws SQLException {
        List<Complaint> list = new ArrayList<>();
        String sql = "SELECT * FROM complaint_gym ORDER BY created_at DESC LIMIT ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        }
        return list;
    }

    public boolean insert(Complaint complaint) throws SQLException {
        String sql = "INSERT INTO complaint_gym (id, member_id, name, subject, complaint, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, complaint.getId());
            ps.setString(2, complaint.getMemberId());
            ps.setString(3, complaint.getName());
            ps.setString(4, complaint.getSubject());
            ps.setString(5, complaint.getComplaint());
            ps.setString(6, complaint.getStatus());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateStatus(String id, String status) throws SQLException {
        String sql = "UPDATE complaint_gym SET status = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    public int count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM complaint_gym";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    public int countByStatus(String status) throws SQLException {
        String sql = "SELECT COUNT(*) FROM complaint_gym WHERE status = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    public int countByMemberId(String memberId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM complaint_gym WHERE member_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    /**
     * Generate next complaint ID.
     */
    public String generateNextId() throws SQLException {
        String sql = "SELECT id FROM complaint_gym ORDER BY id DESC LIMIT 1";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String lastId = rs.getString("id");
                if (lastId.startsWith("CMP")) {
                    int num = Integer.parseInt(lastId.substring(3)) + 1;
                    return String.format("CMP%03d", num);
                }
            }
        }
        return "CMP001";
    }

    private Complaint mapRow(ResultSet rs) throws SQLException {
        Complaint c = new Complaint();
        c.setId(rs.getString("id"));
        c.setMemberId(rs.getString("member_id"));
        c.setName(rs.getString("name"));
        c.setSubject(rs.getString("subject"));
        c.setComplaint(rs.getString("complaint"));
        c.setStatus(rs.getString("status"));
        c.setCreatedAt(rs.getTimestamp("created_at"));
        c.setUpdatedAt(rs.getTimestamp("updated_at"));
        return c;
    }
}
