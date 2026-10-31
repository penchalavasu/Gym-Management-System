package com.fitnesscare.service;

import com.fitnesscare.dao.MembershipHistoryDAO;
import com.fitnesscare.model.MembershipHistory;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MembershipHistoryService {
    private final MembershipHistoryDAO dao = new MembershipHistoryDAO();
    public List<MembershipHistory> getHistory(String memberId) throws SQLException { return dao.findByMemberId(memberId); }
    public boolean addMembership(String memberId, String planName, LocalDate start, LocalDate end) throws SQLException {
        if (memberId == null || memberId.isBlank() || planName == null || planName.isBlank() || start == null || end == null || end.isBefore(start)) return false;
        return dao.add(memberId.trim(), planName.trim(), start, end);
    }
    public boolean markNotRenewed(long id, String memberId) throws SQLException { return dao.markNotRenewed(id, memberId); }
}
