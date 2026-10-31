package com.fitnesscare.service;

import com.fitnesscare.dao.ProgressDAO;
import com.fitnesscare.model.ProgressRecord;
import com.fitnesscare.util.ValidationUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.List;

public class ProgressService {

    private final ProgressDAO progressDAO = new ProgressDAO();

    public ProgressRecord getProgressById(long id) throws SQLException {
        return progressDAO.findById(id);
    }

    public List<ProgressRecord> getProgressByMemberId(String memberId) throws SQLException {
        return progressDAO.findByMemberId(memberId);
    }

    public List<ProgressRecord> getAllProgress() throws SQLException {
        return progressDAO.findAll();
    }

    public List<ProgressRecord> searchProgress(String memberId, String bmiStatus, String dateFrom, String dateTo) throws SQLException {
        return progressDAO.search(memberId, bmiStatus, dateFrom, dateTo);
    }

    public ProgressRecord getLatestProgress(String memberId) throws SQLException {
        return progressDAO.findLatestByMemberId(memberId);
    }

    /**
     * Add a progress record with server-side BMI calculation.
     */
    public boolean addProgress(ProgressRecord record) throws SQLException {
        // Calculate BMI on server
        double weightKg = record.getWeightKg().doubleValue();
        double heightCm = record.getHeightCm().doubleValue();
        double bmi = ValidationUtil.calculateBMI(weightKg, heightCm);
        String bmiStatus = ValidationUtil.getBMIStatus(bmi);

        record.setBmi(BigDecimal.valueOf(bmi).setScale(2, RoundingMode.HALF_UP));
        record.setBmiStatus(bmiStatus);

        return progressDAO.insert(record);
    }

    public boolean deleteProgress(long id) throws SQLException {
        return progressDAO.delete(id);
    }

    public int getProgressCount() throws SQLException {
        return progressDAO.count();
    }

    public int getProgressCountByMember(String memberId) throws SQLException {
        return progressDAO.countByMemberId(memberId);
    }
}
