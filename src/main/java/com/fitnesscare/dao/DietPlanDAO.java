package com.fitnesscare.dao;

import com.fitnesscare.model.DietPlan;
import com.fitnesscare.model.MemberDietPlan;
import com.fitnesscare.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Diet Plan operations.
 */
public class DietPlanDAO {

    public DietPlan findById(long id) throws SQLException {
        String sql = "SELECT * FROM diet_plan WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    public List<DietPlan> findAll() throws SQLException {
        List<DietPlan> list = new ArrayList<>();
        String sql = "SELECT * FROM diet_plan ORDER BY created_at DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public List<DietPlan> search(String keyword) throws SQLException {
        List<DietPlan> list = new ArrayList<>();
        String sql = "SELECT * FROM diet_plan WHERE plan_name LIKE ? OR goal LIKE ? ORDER BY created_at DESC";
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

    public boolean insert(DietPlan plan) throws SQLException {
        String sql = "INSERT INTO diet_plan (plan_name, goal, breakfast, mid_morning_meal, lunch, evening_snack, dinner, additional_instructions, created_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, plan.getPlanName());
            ps.setString(2, plan.getGoal());
            ps.setString(3, plan.getBreakfast());
            ps.setString(4, plan.getMidMorningMeal());
            ps.setString(5, plan.getLunch());
            ps.setString(6, plan.getEveningSnack());
            ps.setString(7, plan.getDinner());
            ps.setString(8, plan.getAdditionalInstructions());
            ps.setString(9, plan.getCreatedBy());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(DietPlan plan) throws SQLException {
        String sql = "UPDATE diet_plan SET plan_name = ?, goal = ?, breakfast = ?, mid_morning_meal = ?, lunch = ?, evening_snack = ?, dinner = ?, additional_instructions = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, plan.getPlanName());
            ps.setString(2, plan.getGoal());
            ps.setString(3, plan.getBreakfast());
            ps.setString(4, plan.getMidMorningMeal());
            ps.setString(5, plan.getLunch());
            ps.setString(6, plan.getEveningSnack());
            ps.setString(7, plan.getDinner());
            ps.setString(8, plan.getAdditionalInstructions());
            ps.setLong(9, plan.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(long id) throws SQLException {
        String sql = "DELETE FROM diet_plan WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public int count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM diet_plan";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    // ----- Member Diet Plan Assignment -----

    public boolean assignPlanToMember(String memberId, long dietPlanId) throws SQLException {
        // Use REPLACE to handle unique constraint (one plan per member)
        String sql = "REPLACE INTO member_diet_plan (member_id, diet_plan_id) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, memberId);
            ps.setLong(2, dietPlanId);
            return ps.executeUpdate() > 0;
        }
    }

    public DietPlan findPlanByMemberId(String memberId) throws SQLException {
        String sql = "SELECT dp.* FROM diet_plan dp JOIN member_diet_plan mdp ON dp.id = mdp.diet_plan_id WHERE mdp.member_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    public List<MemberDietPlan> findAllAssignments() throws SQLException {
        List<MemberDietPlan> list = new ArrayList<>();
        String sql = "SELECT mdp.*, u.name AS member_name, dp.plan_name FROM member_diet_plan mdp " +
                     "JOIN user_gym u ON mdp.member_id = u.id " +
                     "JOIN diet_plan dp ON mdp.diet_plan_id = dp.id " +
                     "ORDER BY mdp.assigned_at DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                MemberDietPlan mdp = new MemberDietPlan();
                mdp.setId(rs.getLong("id"));
                mdp.setMemberId(rs.getString("member_id"));
                mdp.setDietPlanId(rs.getLong("diet_plan_id"));
                mdp.setAssignedAt(rs.getTimestamp("assigned_at"));
                mdp.setMemberName(rs.getString("member_name"));
                mdp.setPlanName(rs.getString("plan_name"));
                list.add(mdp);
            }
        }
        return list;
    }

    public int countAssignments() throws SQLException {
        String sql = "SELECT COUNT(*) FROM member_diet_plan";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    private DietPlan mapRow(ResultSet rs) throws SQLException {
        DietPlan dp = new DietPlan();
        dp.setId(rs.getLong("id"));
        dp.setPlanName(rs.getString("plan_name"));
        dp.setGoal(rs.getString("goal"));
        dp.setBreakfast(rs.getString("breakfast"));
        dp.setMidMorningMeal(rs.getString("mid_morning_meal"));
        dp.setLunch(rs.getString("lunch"));
        dp.setEveningSnack(rs.getString("evening_snack"));
        dp.setDinner(rs.getString("dinner"));
        dp.setAdditionalInstructions(rs.getString("additional_instructions"));
        dp.setCreatedBy(rs.getString("created_by"));
        dp.setCreatedAt(rs.getTimestamp("created_at"));
        dp.setUpdatedAt(rs.getTimestamp("updated_at"));
        return dp;
    }
}
