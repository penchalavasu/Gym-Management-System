package com.fitnesscare.dao;

import com.fitnesscare.model.ProgressRecord;
import com.fitnesscare.util.DBUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Progress Record operations.
 */
public class ProgressDAO {

    public ProgressRecord findById(long id) throws SQLException {
        String sql = "SELECT p.*, u.name AS member_name FROM progress_record p JOIN user_gym u ON p.member_id = u.id WHERE p.id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    public List<ProgressRecord> findByMemberId(String memberId) throws SQLException {
        List<ProgressRecord> list = new ArrayList<>();
        String sql = "SELECT p.*, u.name AS member_name FROM progress_record p JOIN user_gym u ON p.member_id = u.id WHERE p.member_id = ? ORDER BY p.record_date DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        }
        return list;
    }

    public List<ProgressRecord> findAll() throws SQLException {
        List<ProgressRecord> list = new ArrayList<>();
        String sql = "SELECT p.*, u.name AS member_name FROM progress_record p JOIN user_gym u ON p.member_id = u.id ORDER BY p.record_date DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public List<ProgressRecord> search(String memberId, String bmiStatus, String dateFrom, String dateTo) throws SQLException {
        StringBuilder sql = new StringBuilder(
            "SELECT p.*, u.name AS member_name FROM progress_record p JOIN user_gym u ON p.member_id = u.id WHERE 1=1"
        );
        List<Object> params = new ArrayList<>();

        if (memberId != null && !memberId.trim().isEmpty()) {
            sql.append(" AND (p.member_id LIKE ? OR u.name LIKE ?)");
            String pattern = "%" + memberId.trim() + "%";
            params.add(pattern);
            params.add(pattern);
        }
        if (bmiStatus != null && !bmiStatus.trim().isEmpty()) {
            sql.append(" AND p.bmi_status = ?");
            params.add(bmiStatus.trim());
        }
        if (dateFrom != null && !dateFrom.trim().isEmpty()) {
            sql.append(" AND p.record_date >= ?");
            params.add(dateFrom.trim());
        }
        if (dateTo != null && !dateTo.trim().isEmpty()) {
            sql.append(" AND p.record_date <= ?");
            params.add(dateTo.trim());
        }
        sql.append(" ORDER BY p.record_date DESC");

        List<ProgressRecord> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        }
        return list;
    }

    public ProgressRecord findLatestByMemberId(String memberId) throws SQLException {
        String sql = "SELECT p.*, u.name AS member_name FROM progress_record p JOIN user_gym u ON p.member_id = u.id WHERE p.member_id = ? ORDER BY p.record_date DESC LIMIT 1";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    public boolean insert(ProgressRecord record) throws SQLException {
        String sql = "INSERT INTO progress_record (member_id, weight_kg, height_cm, bmi, bmi_status, record_date, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, record.getMemberId());
            ps.setBigDecimal(2, record.getWeightKg());
            ps.setBigDecimal(3, record.getHeightCm());
            ps.setBigDecimal(4, record.getBmi());
            ps.setString(5, record.getBmiStatus());
            ps.setDate(6, record.getRecordDate());
            ps.setString(7, record.getNotes());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(long id) throws SQLException {
        String sql = "DELETE FROM progress_record WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public int count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM progress_record";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    public int countByMemberId(String memberId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM progress_record WHERE member_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    private ProgressRecord mapRow(ResultSet rs) throws SQLException {
        ProgressRecord pr = new ProgressRecord();
        pr.setId(rs.getLong("id"));
        pr.setMemberId(rs.getString("member_id"));
        pr.setWeightKg(rs.getBigDecimal("weight_kg"));
        pr.setHeightCm(rs.getBigDecimal("height_cm"));
        pr.setBmi(rs.getBigDecimal("bmi"));
        pr.setBmiStatus(rs.getString("bmi_status"));
        pr.setRecordDate(rs.getDate("record_date"));
        pr.setNotes(rs.getString("notes"));
        pr.setCreatedAt(rs.getTimestamp("created_at"));
        try { pr.setMemberName(rs.getString("member_name")); } catch (SQLException e) { /* not joined */ }
        return pr;
    }
}
