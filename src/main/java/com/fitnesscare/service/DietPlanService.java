package com.fitnesscare.service;

import com.fitnesscare.dao.DietPlanDAO;
import com.fitnesscare.model.DietPlan;
import com.fitnesscare.model.MemberDietPlan;

import java.sql.SQLException;
import java.util.List;

public class DietPlanService {

    private final DietPlanDAO dietPlanDAO = new DietPlanDAO();

    public DietPlan getDietPlanById(long id) throws SQLException {
        return dietPlanDAO.findById(id);
    }

    public List<DietPlan> getAllDietPlans() throws SQLException {
        return dietPlanDAO.findAll();
    }

    public List<DietPlan> searchDietPlans(String keyword) throws SQLException {
        return dietPlanDAO.search(keyword);
    }

    public boolean createDietPlan(DietPlan plan) throws SQLException {
        return dietPlanDAO.insert(plan);
    }

    public boolean updateDietPlan(DietPlan plan) throws SQLException {
        return dietPlanDAO.update(plan);
    }

    public boolean deleteDietPlan(long id) throws SQLException {
        return dietPlanDAO.delete(id);
    }

    public int getDietPlanCount() throws SQLException {
        return dietPlanDAO.count();
    }

    public boolean assignPlanToMember(String memberId, long dietPlanId) throws SQLException {
        return dietPlanDAO.assignPlanToMember(memberId, dietPlanId);
    }

    public DietPlan getMemberDietPlan(String memberId) throws SQLException {
        return dietPlanDAO.findPlanByMemberId(memberId);
    }

    public List<MemberDietPlan> getAllAssignments() throws SQLException {
        return dietPlanDAO.findAllAssignments();
    }

    public int getAssignmentCount() throws SQLException {
        return dietPlanDAO.countAssignments();
    }
}
