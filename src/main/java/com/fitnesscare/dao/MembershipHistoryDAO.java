package com.fitnesscare.dao;

import com.fitnesscare.model.MembershipHistory;
import com.fitnesscare.util.DBUtil;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Persistence for membership periods. Renewals insert a new row, preserving old rows. */
public class MembershipHistoryDAO {
    public List<MembershipHistory> findByMemberId(String memberId) throws SQLException {
        String sql = "SELECT id, member_id, plan_name, start_date, end_date, " +
                "CASE WHEN end_date < CURRENT_DATE AND status='ACTIVE' THEN 'EXPIRED' ELSE status END AS effective_status, created_at " +
                "FROM membership_history WHERE member_id=? ORDER BY start_date DESC, id DESC";
        List<MembershipHistory> rows = new ArrayList<>();
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) rows.add(mapRow(rs));
            }
        }
        return rows;
    }

    public boolean add(String memberId, String planName, LocalDate startDate, LocalDate endDate) throws SQLException {
        String sql = "INSERT INTO membership_history(member_id,plan_name,start_date,end_date,status) VALUES(?,?,?,?, 'ACTIVE')";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, memberId); ps.setString(2, planName);
            ps.setDate(3, Date.valueOf(startDate)); ps.setDate(4, Date.valueOf(endDate));
            return ps.executeUpdate() == 1;
        }
    }

    public boolean markNotRenewed(long id, String memberId) throws SQLException {
        String sql = "UPDATE membership_history SET status='NOT_RENEWED' WHERE id=? AND member_id=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id); ps.setString(2, memberId); return ps.executeUpdate() == 1;
        }
    }

    private MembershipHistory mapRow(ResultSet rs) throws SQLException {
        MembershipHistory m = new MembershipHistory();
        m.setId(rs.getLong("id")); m.setMemberId(rs.getString("member_id"));
        m.setPlanName(rs.getString("plan_name"));
        m.setStartDate(rs.getDate("start_date").toLocalDate());
        m.setEndDate(rs.getDate("end_date").toLocalDate());
        m.setStatus(rs.getString("effective_status")); m.setCreatedAt(rs.getTimestamp("created_at"));
        return m;
    }
}
