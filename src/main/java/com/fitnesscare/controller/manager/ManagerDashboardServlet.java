package com.fitnesscare.controller.manager;

import com.fitnesscare.model.Manager;
import com.fitnesscare.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class ManagerDashboardServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final DietPlanService dietPlanService = new DietPlanService();
    private final ProgressService progressService = new ProgressService();
    private final ComplaintService complaintService = new ComplaintService();
    private final ManagerService managerService = new ManagerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("memberCount", memberService.getMemberCount());
            request.setAttribute("dietPlanCount", dietPlanService.getDietPlanCount());
            request.setAttribute("progressCount", progressService.getProgressCount());
            request.setAttribute("pendingComplaints", complaintService.getComplaintCountByStatus("PENDING"));
            request.setAttribute("recentMembers", memberService.getRecentMembers(5));
            request.setAttribute("recentComplaints", complaintService.getRecentComplaints(5));
            String userId = (String) request.getSession().getAttribute("userId");
            Manager manager = managerService.getManagerById(userId);
            request.setAttribute("manager", manager);
            request.setAttribute("currentPage", "dashboard");
            request.getRequestDispatcher("/WEB-INF/views/manager/dashboard.jsp").forward(request, response);
        } catch (Exception e) { throw new ServletException(e); }
    }
}
