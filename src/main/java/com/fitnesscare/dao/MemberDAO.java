package com.fitnesscare.dao;

import com.fitnesscare.model.Member;
import com.fitnesscare.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Member operations.
 */
public class MemberDAO {

    public Member findById(String id) throws SQLException {
        String sql = "SELECT id, name, password, gender, program, fitness_goal, age, created_at, updated_at FROM user_gym WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    public List<Member> findAll() throws SQLException {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT id, name, password, gender, program, fitness_goal, age, created_at, updated_at FROM user_gym ORDER BY created_at DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    public List<Member> search(String keyword, String program, String gender) throws SQLException {
        StringBuilder sql = new StringBuilder(
            "SELECT id, name, password, gender, program, fitness_goal, age, created_at, updated_at FROM user_gym WHERE 1=1"
        );
        List<String> params = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" AND (id LIKE ? OR name LIKE ?)");
            params.add("%" + keyword.trim() + "%");
            params.add("%" + keyword.trim() + "%");
        }
        if (program != null && !program.trim().isEmpty()) {
            sql.append(" AND program = ?");
            params.add(program.trim());
        }
        if (gender != null && !gender.trim().isEmpty()) {
            sql.append(" AND gender = ?");
            params.add(gender.trim());
        }
        sql.append(" ORDER BY created_at DESC");

        List<Member> list = new ArrayList<>();
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

    public List<Member> findRecent(int limit) throws SQLException {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT id, name, password, gender, program, fitness_goal, age, created_at, updated_at FROM user_gym ORDER BY created_at DESC LIMIT ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
        }
        return list;
    }

    public boolean insert(Member member) throws SQLException {
        String sql = "INSERT INTO user_gym (id, name, password, gender, program, fitness_goal, age) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, member.getId());
            ps.setString(2, member.getName());
            ps.setString(3, member.getPassword());
            ps.setString(4, member.getGender());
            ps.setString(5, member.getProgram());
            ps.setString(6, member.getFitnessGoal());
            ps.setInt(7, member.getAge());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean update(Member member) throws SQLException {
        String sql = "UPDATE user_gym SET name = ?, gender = ?, program = ?, fitness_goal = ?, age = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, member.getName());
            ps.setString(2, member.getGender());
            ps.setString(3, member.getProgram());
            ps.setString(4, member.getFitnessGoal());
            ps.setInt(5, member.getAge());
            ps.setString(6, member.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updatePassword(String memberId, String hashedPassword) throws SQLException {
        String sql = "UPDATE user_gym SET password = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hashedPassword);
            ps.setString(2, memberId);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM user_gym WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public int count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM user_gym";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    public boolean existsById(String id) throws SQLException {
        String sql = "SELECT 1 FROM user_gym WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    private Member mapRow(ResultSet rs) throws SQLException {
        Member m = new Member();
        m.setId(rs.getString("id"));
        m.setName(rs.getString("name"));
        m.setPassword(rs.getString("password"));
        m.setGender(rs.getString("gender"));
        m.setProgram(rs.getString("program"));
        m.setFitnessGoal(rs.getString("fitness_goal"));
        m.setAge(rs.getInt("age"));
        m.setCreatedAt(rs.getTimestamp("created_at"));
        m.setUpdatedAt(rs.getTimestamp("updated_at"));
        return m;
    }
}
