package com.fitnesscare.controller.member;

import com.fitnesscare.model.*;
import com.fitnesscare.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class MemberDashboardServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final DietPlanService dietPlanService = new DietPlanService();
    private final ProgressService progressService = new ProgressService();
    private final ComplaintService complaintService = new ComplaintService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            Member member = memberService.getMemberById(userId);
            DietPlan dietPlan = dietPlanService.getMemberDietPlan(userId);
            ProgressRecord latestProgress = progressService.getLatestProgress(userId);
            int progressCount = progressService.getProgressCountByMember(userId);
            int complaintCount = complaintService.getComplaintCountByMember(userId);

            request.setAttribute("member", member);
            request.setAttribute("dietPlan", dietPlan);
            request.setAttribute("latestProgress", latestProgress);
            request.setAttribute("progressCount", progressCount);
            request.setAttribute("complaintCount", complaintCount);
            request.setAttribute("currentPage", "dashboard");
            request.getRequestDispatcher("/WEB-INF/views/member/dashboard.jsp").forward(request, response);
        } catch (Exception e) { throw new ServletException(e); }
    }
}
