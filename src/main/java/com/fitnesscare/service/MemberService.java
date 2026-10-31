package com.fitnesscare.service;

import com.fitnesscare.dao.MemberDAO;
import com.fitnesscare.model.Member;
import com.fitnesscare.util.PasswordUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Service for member management operations.
 */
public class MemberService {

    private final MemberDAO memberDAO = new MemberDAO();

    public Member getMemberById(String id) throws SQLException {
        return memberDAO.findById(id);
    }

    public List<Member> getAllMembers() throws SQLException {
        return memberDAO.findAll();
    }

    public List<Member> searchMembers(String keyword, String program, String gender) throws SQLException {
        return memberDAO.search(keyword, program, gender);
    }

    public List<Member> getRecentMembers(int limit) throws SQLException {
        return memberDAO.findRecent(limit);
    }

    /**
     * Register a new member with password hashing.
     */
    public boolean registerMember(Member member) throws SQLException {
        if (memberDAO.existsById(member.getId())) {
            return false; // duplicate ID
        }
        member.setPassword(PasswordUtil.hashPassword(member.getPassword()));
        return memberDAO.insert(member);
    }

    /**
     * Create a member (admin/manager use - password already provided).
     */
    public boolean createMember(Member member) throws SQLException {
        if (memberDAO.existsById(member.getId())) {
            return false;
        }
        member.setPassword(PasswordUtil.hashPassword(member.getPassword()));
        return memberDAO.insert(member);
    }

    public boolean updateMember(Member member) throws SQLException {
        return memberDAO.update(member);
    }

    public boolean updateMemberPassword(String memberId, String newPassword) throws SQLException {
        return memberDAO.updatePassword(memberId, PasswordUtil.hashPassword(newPassword));
    }

    public boolean deleteMember(String id) throws SQLException {
        return memberDAO.delete(id);
    }

    public int getMemberCount() throws SQLException {
        return memberDAO.count();
    }

    public boolean memberExists(String id) throws SQLException {
        return memberDAO.existsById(id);
    }
}
