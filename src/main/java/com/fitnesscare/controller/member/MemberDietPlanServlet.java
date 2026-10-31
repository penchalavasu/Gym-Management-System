package com.fitnesscare.controller.member;

import com.fitnesscare.model.DietPlan;
import com.fitnesscare.service.DietPlanService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class MemberDietPlanServlet extends HttpServlet {
    private final DietPlanService dietPlanService = new DietPlanService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            DietPlan plan = dietPlanService.getMemberDietPlan(userId);
            request.setAttribute("plan", plan);
            request.setAttribute("currentPage", "diet-plan");
            request.getRequestDispatcher("/WEB-INF/views/member/diet-plan.jsp").forward(request, response);
        } catch (Exception e) { throw new ServletException(e); }
    }
}
