package com.fitnesscare.service;

import com.fitnesscare.dao.AdminDAO;
import com.fitnesscare.dao.ManagerDAO;
import com.fitnesscare.dao.MemberDAO;
import com.fitnesscare.model.Admin;
import com.fitnesscare.model.Manager;
import com.fitnesscare.model.Member;
import com.fitnesscare.util.PasswordUtil;

import java.sql.SQLException;

/**
 * Service for authentication operations.
 */
public class AuthService {

    private final AdminDAO adminDAO = new AdminDAO();
    private final ManagerDAO managerDAO = new ManagerDAO();
    private final MemberDAO memberDAO = new MemberDAO();

    /**
     * Authenticate admin by id and password.
     * @return Admin object if valid, null otherwise.
     */
    public Admin authenticateAdmin(String id, String password) throws SQLException {
        if (id == null || password == null) return null;
        Admin admin = adminDAO.findById(id.trim());
        if (admin != null && PasswordUtil.verifyPassword(password, admin.getPassword())) {
            return admin;
        }
        return null;
    }

    /**
     * Authenticate manager by id and password.
     */
    public Manager authenticateManager(String managerId, String password) throws SQLException {
        if (managerId == null || password == null) return null;
        Manager manager = managerDAO.findById(managerId.trim());
        if (manager != null && PasswordUtil.verifyPassword(password, manager.getPassword())) {
            return manager;
        }
        return null;
    }

    /**
     * Authenticate member by id and password.
     */
    public Member authenticateMember(String id, String password) throws SQLException {
        if (id == null || password == null) return null;
        Member member = memberDAO.findById(id.trim());
        if (member != null && PasswordUtil.verifyPassword(password, member.getPassword())) {
            return member;
        }
        return null;
    }
}
