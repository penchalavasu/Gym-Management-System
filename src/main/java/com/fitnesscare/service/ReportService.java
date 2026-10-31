package com.fitnesscare.service;

import com.fitnesscare.dao.*;
import com.fitnesscare.model.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Service for generating reports. Aggregates data from multiple DAOs.
 */
public class ReportService {

    private final MemberDAO memberDAO = new MemberDAO();
    private final DietPlanDAO dietPlanDAO = new DietPlanDAO();
    private final ProgressDAO progressDAO = new ProgressDAO();
    private final ComplaintDAO complaintDAO = new ComplaintDAO();

    // Member Reports
    public List<Member> getMemberReport(String keyword, String program, String gender) throws SQLException {
        return memberDAO.search(keyword, program, gender);
    }

    // Diet Plan Reports
    public List<DietPlan> getDietPlanReport(String keyword) throws SQLException {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return dietPlanDAO.search(keyword);
        }
        return dietPlanDAO.findAll();
    }

    public List<MemberDietPlan> getDietAssignmentReport() throws SQLException {
        return dietPlanDAO.findAllAssignments();
    }

    // Progress Reports
    public List<ProgressRecord> getProgressReport(String memberId, String bmiStatus, String dateFrom, String dateTo) throws SQLException {
        return progressDAO.search(memberId, bmiStatus, dateFrom, dateTo);
    }

    // Complaint Reports
    public List<Complaint> getComplaintReport(String keyword, String status) throws SQLException {
        return complaintDAO.search(keyword, status);
    }
}
