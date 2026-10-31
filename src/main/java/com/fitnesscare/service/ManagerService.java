package com.fitnesscare.service;

import com.fitnesscare.dao.ManagerDAO;
import com.fitnesscare.model.Manager;
import com.fitnesscare.util.PasswordUtil;

import java.sql.SQLException;
import java.util.List;

public class ManagerService {

    private final ManagerDAO managerDAO = new ManagerDAO();

    public Manager getManagerById(String managerId) throws SQLException {
        return managerDAO.findById(managerId);
    }

    public List<Manager> getAllManagers() throws SQLException {
        return managerDAO.findAll();
    }

    public List<Manager> searchManagers(String keyword) throws SQLException {
        return managerDAO.search(keyword);
    }

    public boolean createManager(Manager manager) throws SQLException {
        if (managerDAO.existsById(manager.getManagerId())) {
            return false;
        }
        manager.setPassword(PasswordUtil.hashPassword(manager.getPassword()));
        return managerDAO.insert(manager);
    }

    public boolean updateManager(Manager manager) throws SQLException {
        return managerDAO.update(manager);
    }

    public boolean updateManagerPassword(String managerId, String newPassword) throws SQLException {
        return managerDAO.updatePassword(managerId, PasswordUtil.hashPassword(newPassword));
    }

    public boolean deleteManager(String managerId) throws SQLException {
        return managerDAO.delete(managerId);
    }

    public int getManagerCount() throws SQLException {
        return managerDAO.count();
    }

    public boolean managerExists(String managerId) throws SQLException {
        return managerDAO.existsById(managerId);
    }
}
