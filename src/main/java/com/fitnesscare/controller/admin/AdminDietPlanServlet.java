package com.fitnesscare.controller.admin;

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

public class AdminDietPlanServlet extends HttpServlet {

    private final DietPlanService dietPlanService = new DietPlanService();
    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";
        try {
            switch (action) {
                case "add": showAddForm(request, response); break;
                case "view": viewPlan(request, response); break;
                case "edit": showEditForm(request, response); break;
                case "assign": showAssignForm(request, response); break;
                default: listPlans(request, response);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        try {
            switch (action) {
                case "add": addPlan(request, response); break;
                case "edit": updatePlan(request, response); break;
                case "delete": deletePlan(request, response); break;
                case "assign": assignPlan(request, response); break;
                default: response.sendRedirect(request.getContextPath() + "/admin/diet-plans");
            }
        } catch (Exception e) { throw new ServletException(e); }
    }

    private void listPlans(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String search = request.getParameter("search");
        List<DietPlan> plans = (search != null && !search.trim().isEmpty()) ? dietPlanService.searchDietPlans(search) : dietPlanService.getAllDietPlans();
        List<MemberDietPlan> assignments = dietPlanService.getAllAssignments();
        request.setAttribute("plans", plans);
        request.setAttribute("assignments", assignments);
        request.setAttribute("search", search);
        request.setAttribute("currentPage", "diet-plans");
        request.getRequestDispatcher("/WEB-INF/views/admin/diet-plans.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("currentPage", "diet-plans");
        request.getRequestDispatcher("/WEB-INF/views/admin/diet-plan-add.jsp").forward(request, response);
    }

    private void viewPlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idStr = request.getParameter("id");
        if (idStr == null) { response.sendRedirect(request.getContextPath() + "/admin/diet-plans"); return; }
        DietPlan plan = dietPlanService.getDietPlanById(Long.parseLong(idStr));
        if (plan == null) { request.getSession().setAttribute("errorMessage", "Diet plan not found."); response.sendRedirect(request.getContextPath() + "/admin/diet-plans"); return; }
        request.setAttribute("plan", plan);
        request.setAttribute("currentPage", "diet-plans");
        request.getRequestDispatcher("/WEB-INF/views/admin/diet-plan-view.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idStr = request.getParameter("id");
        DietPlan plan = dietPlanService.getDietPlanById(Long.parseLong(idStr));
        if (plan == null) { request.getSession().setAttribute("errorMessage", "Diet plan not found."); response.sendRedirect(request.getContextPath() + "/admin/diet-plans"); return; }
        request.setAttribute("plan", plan);
        request.setAttribute("currentPage", "diet-plans");
        request.getRequestDispatcher("/WEB-INF/views/admin/diet-plan-edit.jsp").forward(request, response);
    }

    private void showAssignForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<DietPlan> plans = dietPlanService.getAllDietPlans();
        List<Member> members = memberService.getAllMembers();
        request.setAttribute("plans", plans);
        request.setAttribute("members", members);
        request.setAttribute("currentPage", "diet-plans");
        request.getRequestDispatcher("/WEB-INF/views/admin/diet-plan-assign.jsp").forward(request, response);
    }

    private void addPlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DietPlan plan = extractPlan(request);
        plan.setCreatedBy((String) request.getSession().getAttribute("userId"));
        if (ValidationUtil.isEmpty(plan.getPlanName())) {
            request.getSession().setAttribute("errorMessage", "Plan name is required.");
            response.sendRedirect(request.getContextPath() + "/admin/diet-plans?action=add"); return;
        }
        dietPlanService.createDietPlan(plan);
        request.getSession().setAttribute("successMessage", "Diet plan created successfully.");
        response.sendRedirect(request.getContextPath() + "/admin/diet-plans");
    }

    private void updatePlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DietPlan plan = extractPlan(request);
        plan.setId(Long.parseLong(request.getParameter("id")));
        dietPlanService.updateDietPlan(plan);
        request.getSession().setAttribute("successMessage", "Diet plan updated successfully.");
        response.sendRedirect(request.getContextPath() + "/admin/diet-plans");
    }

    private void deletePlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idStr = request.getParameter("id");
        if (idStr != null) { dietPlanService.deleteDietPlan(Long.parseLong(idStr)); request.getSession().setAttribute("successMessage", "Diet plan deleted."); }
        response.sendRedirect(request.getContextPath() + "/admin/diet-plans");
    }

    private void assignPlan(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String memberId = request.getParameter("memberId");
        String planIdStr = request.getParameter("planId");
        if (ValidationUtil.isEmpty(memberId) || ValidationUtil.isEmpty(planIdStr)) {
            request.getSession().setAttribute("errorMessage", "Please select both member and plan.");
            response.sendRedirect(request.getContextPath() + "/admin/diet-plans?action=assign"); return;
        }
        if (!memberService.memberExists(memberId.trim())) {
            request.getSession().setAttribute("errorMessage", "Member not found.");
            response.sendRedirect(request.getContextPath() + "/admin/diet-plans?action=assign"); return;
        }
        dietPlanService.assignPlanToMember(memberId.trim(), Long.parseLong(planIdStr));
        request.getSession().setAttribute("successMessage", "Diet plan assigned successfully.");
        response.sendRedirect(request.getContextPath() + "/admin/diet-plans");
    }

    private DietPlan extractPlan(HttpServletRequest request) {
        DietPlan p = new DietPlan();
        p.setPlanName(request.getParameter("planName") != null ? request.getParameter("planName").trim() : "");
        p.setGoal(request.getParameter("goal") != null ? request.getParameter("goal").trim() : "");
        p.setBreakfast(request.getParameter("breakfast") != null ? request.getParameter("breakfast").trim() : "");
        p.setMidMorningMeal(request.getParameter("midMorningMeal") != null ? request.getParameter("midMorningMeal").trim() : "");
        p.setLunch(request.getParameter("lunch") != null ? request.getParameter("lunch").trim() : "");
        p.setEveningSnack(request.getParameter("eveningSnack") != null ? request.getParameter("eveningSnack").trim() : "");
        p.setDinner(request.getParameter("dinner") != null ? request.getParameter("dinner").trim() : "");
        p.setAdditionalInstructions(request.getParameter("additionalInstructions") != null ? request.getParameter("additionalInstructions").trim() : "");
        return p;
    }
}
