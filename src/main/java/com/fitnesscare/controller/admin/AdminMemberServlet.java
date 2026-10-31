package com.fitnesscare.controller.admin;

import com.fitnesscare.model.Member;
import com.fitnesscare.service.MemberService;
import com.fitnesscare.service.MembershipHistoryService;
import com.fitnesscare.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;

public class AdminMemberServlet extends HttpServlet {

    private final MemberService memberService = new MemberService();
    private final MembershipHistoryService membershipHistoryService = new MembershipHistoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "view": viewMember(request, response); break;
                case "edit": editMemberForm(request, response); break;
                default: listMembers(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        try {
            switch (action) {
                case "edit": updateMember(request, response); break;
                case "delete": deleteMember(request, response); break;
                case "addMembership": addMembership(request, response); break;
                case "notRenewed": markNotRenewed(request, response); break;
                default: response.sendRedirect(request.getContextPath() + "/admin/members");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listMembers(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String search = request.getParameter("search");
        String program = request.getParameter("program");
        String gender = request.getParameter("gender");

        List<Member> members;
        if ((search != null && !search.trim().isEmpty()) || (program != null && !program.trim().isEmpty()) || (gender != null && !gender.trim().isEmpty())) {
            members = memberService.searchMembers(search, program, gender);
        } else {
            members = memberService.getAllMembers();
        }

        request.setAttribute("members", members);
        request.setAttribute("search", search);
        request.setAttribute("program", program);
        request.setAttribute("gender", gender);
        request.setAttribute("currentPage", "members");
        request.getRequestDispatcher("/WEB-INF/views/admin/members.jsp").forward(request, response);
    }

    private void viewMember(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/admin/members");
            return;
        }
        Member member = memberService.getMemberById(id.trim());
        if (member == null) {
            request.getSession().setAttribute("errorMessage", "Member not found.");
            response.sendRedirect(request.getContextPath() + "/admin/members");
            return;
        }
        request.setAttribute("member", member);
        request.setAttribute("membershipHistory", membershipHistoryService.getHistory(member.getId()));
        request.setAttribute("currentPage", "members");
        request.getRequestDispatcher("/WEB-INF/views/admin/member-view.jsp").forward(request, response);
    }

    private void editMemberForm(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        Member member = memberService.getMemberById(id);
        if (member == null) {
            request.getSession().setAttribute("errorMessage", "Member not found.");
            response.sendRedirect(request.getContextPath() + "/admin/members");
            return;
        }
        request.setAttribute("member", member);
        request.setAttribute("currentPage", "members");
        request.getRequestDispatcher("/WEB-INF/views/admin/member-edit.jsp").forward(request, response);
    }

    private void updateMember(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String program = request.getParameter("program");
        String fitnessGoal = request.getParameter("fitnessGoal");
        String ageStr = request.getParameter("age");

        if (ValidationUtil.isEmpty(name) || ValidationUtil.isEmpty(gender) || ValidationUtil.isEmpty(program)) {
            request.getSession().setAttribute("errorMessage", "Please fill in all required fields.");
            response.sendRedirect(request.getContextPath() + "/admin/members?action=edit&id=" + id);
            return;
        }

        Member member = new Member();
        member.setId(id);
        member.setName(name.trim());
        member.setGender(gender.trim());
        member.setProgram(program.trim());
        member.setFitnessGoal(fitnessGoal != null ? fitnessGoal.trim() : "");
        member.setAge(!ValidationUtil.isEmpty(ageStr) ? Integer.parseInt(ageStr.trim()) : 0);

        memberService.updateMember(member);
        request.getSession().setAttribute("successMessage", "Member updated successfully.");
        response.sendRedirect(request.getContextPath() + "/admin/members");
    }

    private void addMembership(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String memberId = request.getParameter("memberId");
        String planName = request.getParameter("planName");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        if (membershipHistoryService.addMembership(memberId, planName, startDate, endDate))
            request.getSession().setAttribute("successMessage", "Membership period added. Previous membership history has been retained.");
        else request.getSession().setAttribute("errorMessage", "Invalid membership details.");
        response.sendRedirect(request.getContextPath() + "/admin/members?action=view&id=" + memberId);
    }

    private void markNotRenewed(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String memberId = request.getParameter("memberId");
        long membershipId = Long.parseLong(request.getParameter("membershipId"));
        membershipHistoryService.markNotRenewed(membershipId, memberId);
        request.getSession().setAttribute("successMessage", "Membership marked as not renewed.");
        response.sendRedirect(request.getContextPath() + "/admin/members?action=view&id=" + memberId);
    }

    private void deleteMember(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        if (id != null && !id.trim().isEmpty()) {
            memberService.deleteMember(id.trim());
            request.getSession().setAttribute("successMessage", "Member deleted successfully.");
        }
        response.sendRedirect(request.getContextPath() + "/admin/members");
    }
}
