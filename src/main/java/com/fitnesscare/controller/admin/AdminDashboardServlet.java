package com.fitnesscare.controller.admin;

import com.fitnesscare.dao.AdminDAO;
import com.fitnesscare.model.Admin;
import com.fitnesscare.service.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AdminDashboardServlet extends HttpServlet {

    private final MemberService memberService = new MemberService();
    private final DietPlanService dietPlanService = new DietPlanService();
    private final ProgressService progressService = new ProgressService();
    private final ComplaintService complaintService = new ComplaintService();
    private final AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Stats
            request.setAttribute("memberCount", memberService.getMemberCount());
            request.setAttribute("dietPlanCount", dietPlanService.getDietPlanCount());
            request.setAttribute("progressCount", progressService.getProgressCount());
            request.setAttribute("assignmentCount", dietPlanService.getAssignmentCount());

            // Admin profile
            String userId = (String) request.getSession().getAttribute("userId");
            Admin admin = adminDAO.findById(userId);
            request.setAttribute("admin", admin);

            request.setAttribute("currentPage", "dashboard");
            request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error loading admin dashboard", e);
        }
    }
}
