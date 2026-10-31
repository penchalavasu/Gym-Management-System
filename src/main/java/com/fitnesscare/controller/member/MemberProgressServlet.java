package com.fitnesscare.controller.member;

import com.fitnesscare.model.ProgressRecord;
import com.fitnesscare.service.ProgressService;
import com.fitnesscare.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class MemberProgressServlet extends HttpServlet {
    private final ProgressService progressService = new ProgressService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            if ("add".equals(action)) {
                request.setAttribute("currentPage", "progress");
                request.getRequestDispatcher("/WEB-INF/views/member/progress-add.jsp").forward(request, response);
            } else {
                List<ProgressRecord> records = progressService.getProgressByMemberId(userId);
                request.setAttribute("records", records);
                request.setAttribute("currentPage", "progress");
                request.getRequestDispatcher("/WEB-INF/views/member/progress.jsp").forward(request, response);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            String weightStr = request.getParameter("weight");
            String heightStr = request.getParameter("height");
            String dateStr = request.getParameter("recordDate");
            String notes = request.getParameter("notes");

            if (ValidationUtil.isEmpty(weightStr) || ValidationUtil.isEmpty(heightStr) || ValidationUtil.isEmpty(dateStr)) {
                request.getSession().setAttribute("errorMessage", "Weight, height, and date are required.");
                response.sendRedirect(request.getContextPath() + "/member/progress?action=add");
                return;
            }

            ProgressRecord record = new ProgressRecord();
            record.setMemberId(userId);
            record.setWeightKg(new BigDecimal(weightStr.trim()));
            record.setHeightCm(new BigDecimal(heightStr.trim()));
            record.setRecordDate(Date.valueOf(dateStr.trim()));
            record.setNotes(notes != null ? notes.trim() : "");

            progressService.addProgress(record);
            request.getSession().setAttribute("successMessage", "Progress record added successfully.");
            response.sendRedirect(request.getContextPath() + "/member/progress");
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error adding progress record. Please check your inputs.");
            response.sendRedirect(request.getContextPath() + "/member/progress?action=add");
        }
    }
}
