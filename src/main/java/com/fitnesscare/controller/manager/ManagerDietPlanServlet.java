package com.fitnesscare.controller.manager;

import com.fitnesscare.model.DietPlan;
import com.fitnesscare.model.Member;
import com.fitnesscare.model.MemberDietPlan;
import com.fitnesscare.service.DietPlanService;
import com.fitnesscare.service.MemberService;
import com.fitnesscare.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ManagerDietPlanServlet extends HttpServlet {
    private final DietPlanService dietPlanService = new DietPlanService();
    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";
        try {
            switch (action) {
                case "add": request.setAttribute("currentPage","diet-plans"); request.getRequestDispatcher("/WEB-INF/views/manager/diet-plan-add.jsp").forward(request,response); break;
                case "view": {
                    DietPlan p = dietPlanService.getDietPlanById(Long.parseLong(request.getParameter("id")));
                    if (p == null) { request.getSession().setAttribute("errorMessage","Not found."); response.sendRedirect(request.getContextPath()+"/manager/diet-plans"); return; }
                    request.setAttribute("plan", p); request.setAttribute("currentPage","diet-plans");
                    request.getRequestDispatcher("/WEB-INF/views/manager/diet-plan-view.jsp").forward(request,response); break;
                }
                case "edit": {
                    DietPlan p = dietPlanService.getDietPlanById(Long.parseLong(request.getParameter("id")));
                    if (p == null) { request.getSession().setAttribute("errorMessage","Not found."); response.sendRedirect(request.getContextPath()+"/manager/diet-plans"); return; }
                    request.setAttribute("plan", p); request.setAttribute("currentPage","diet-plans");
                    request.getRequestDispatcher("/WEB-INF/views/manager/diet-plan-edit.jsp").forward(request,response); break;
                }
                case "assign": {
                    request.setAttribute("plans", dietPlanService.getAllDietPlans());
                    request.setAttribute("members", memberService.getAllMembers());
                    request.setAttribute("currentPage","diet-plans");
                    request.getRequestDispatcher("/WEB-INF/views/manager/diet-plan-assign.jsp").forward(request,response); break;
                }
                default: {
                    String search = request.getParameter("search");
                    List<DietPlan> plans = (search != null && !search.trim().isEmpty()) ? dietPlanService.searchDietPlans(search) : dietPlanService.getAllDietPlans();
                    request.setAttribute("plans", plans); request.setAttribute("assignments", dietPlanService.getAllAssignments());
                    request.setAttribute("search", search); request.setAttribute("currentPage","diet-plans");
                    request.getRequestDispatcher("/WEB-INF/views/manager/diet-plans.jsp").forward(request,response);
                }
            }
        } catch (Exception e) { throw new ServletException(e); }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        try {
            switch (action) {
                case "add": {
                    DietPlan p = extractPlan(request); p.setCreatedBy((String)request.getSession().getAttribute("userId"));
                    if (ValidationUtil.isEmpty(p.getPlanName())) { request.getSession().setAttribute("errorMessage","Plan name required."); response.sendRedirect(request.getContextPath()+"/manager/diet-plans?action=add"); return; }
                    dietPlanService.createDietPlan(p); request.getSession().setAttribute("successMessage","Diet plan created."); break;
                }
                case "edit": {
                    DietPlan p = extractPlan(request); p.setId(Long.parseLong(request.getParameter("id")));
                    dietPlanService.updateDietPlan(p); request.getSession().setAttribute("successMessage","Diet plan updated."); break;
                }
                case "delete": {
                    dietPlanService.deleteDietPlan(Long.parseLong(request.getParameter("id"))); request.getSession().setAttribute("successMessage","Diet plan deleted."); break;
                }
                case "assign": {
                    String memberId = request.getParameter("memberId"); long planId = Long.parseLong(request.getParameter("planId"));
                    if (!memberService.memberExists(memberId.trim())) { request.getSession().setAttribute("errorMessage","Member not found."); response.sendRedirect(request.getContextPath()+"/manager/diet-plans?action=assign"); return; }
                    dietPlanService.assignPlanToMember(memberId.trim(), planId); request.getSession().setAttribute("successMessage","Plan assigned."); break;
                }
            }
        } catch (Exception e) { request.getSession().setAttribute("errorMessage","Error processing request."); }
        response.sendRedirect(request.getContextPath()+"/manager/diet-plans");
    }

    private DietPlan extractPlan(HttpServletRequest r) {
        DietPlan p = new DietPlan();
        p.setPlanName(r.getParameter("planName") != null ? r.getParameter("planName").trim() : "");
        p.setGoal(r.getParameter("goal") != null ? r.getParameter("goal").trim() : "");
        p.setBreakfast(r.getParameter("breakfast") != null ? r.getParameter("breakfast").trim() : "");
        p.setMidMorningMeal(r.getParameter("midMorningMeal") != null ? r.getParameter("midMorningMeal").trim() : "");
        p.setLunch(r.getParameter("lunch") != null ? r.getParameter("lunch").trim() : "");
        p.setEveningSnack(r.getParameter("eveningSnack") != null ? r.getParameter("eveningSnack").trim() : "");
        p.setDinner(r.getParameter("dinner") != null ? r.getParameter("dinner").trim() : "");
        p.setAdditionalInstructions(r.getParameter("additionalInstructions") != null ? r.getParameter("additionalInstructions").trim() : "");
        return p;
    }
}
