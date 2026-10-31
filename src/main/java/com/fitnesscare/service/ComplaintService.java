package com.fitnesscare.service;

import com.fitnesscare.dao.ComplaintDAO;
import com.fitnesscare.model.Complaint;

import java.sql.SQLException;
import java.util.List;

public class ComplaintService {

    private final ComplaintDAO complaintDAO = new ComplaintDAO();

    public Complaint getComplaintById(String id) throws SQLException {
        return complaintDAO.findById(id);
    }

    public List<Complaint> getAllComplaints() throws SQLException {
        return complaintDAO.findAll();
    }

    public List<Complaint> getComplaintsByMemberId(String memberId) throws SQLException {
        return complaintDAO.findByMemberId(memberId);
    }

    public List<Complaint> searchComplaints(String keyword, String status) throws SQLException {
        return complaintDAO.search(keyword, status);
    }

    public List<Complaint> getRecentComplaints(int limit) throws SQLException {
        return complaintDAO.findRecent(limit);
    }

    /**
     * Submit a new complaint.
     */
    public boolean submitComplaint(Complaint complaint) throws SQLException {
        complaint.setId(complaintDAO.generateNextId());
        complaint.setStatus("PENDING");
        return complaintDAO.insert(complaint);
    }

    public boolean updateComplaintStatus(String id, String status) throws SQLException {
        return complaintDAO.updateStatus(id, status);
    }

    public int getComplaintCount() throws SQLException {
        return complaintDAO.count();
    }

    public int getComplaintCountByStatus(String status) throws SQLException {
        return complaintDAO.countByStatus(status);
    }

    public int getComplaintCountByMember(String memberId) throws SQLException {
        return complaintDAO.countByMemberId(memberId);
    }
}
