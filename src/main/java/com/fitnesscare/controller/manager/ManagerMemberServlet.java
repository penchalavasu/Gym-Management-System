package com.fitnesscare.controller.manager;

import com.fitnesscare.model.Member;
import com.fitnesscare.service.MemberService;
import com.fitnesscare.service.MembershipHistoryService;
import com.fitnesscare.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.time.LocalDate;

public class ManagerMemberServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final MembershipHistoryService membershipHistoryService = new MembershipHistoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";
        try {
            switch (action) {
                case "view": {
                    String id = request.getParameter("id");
                    Member m = memberService.getMemberById(id);
                    if (m == null) { request.getSession().setAttribute("errorMessage","Member not found."); response.sendRedirect(request.getContextPath()+"/manager/members"); return; }
                    request.setAttribute("member", m); request.setAttribute("membershipHistory", membershipHistoryService.getHistory(m.getId())); request.setAttribute("currentPage","members");
                    request.getRequestDispatcher("/WEB-INF/views/manager/member-view.jsp").forward(request,response); break;
                }
                case "edit": {
                    String id = request.getParameter("id");
                    Member m = memberService.getMemberById(id);
                    if (m == null) { request.getSession().setAttribute("errorMessage","Member not found."); response.sendRedirect(request.getContextPath()+"/manager/members"); return; }
                    request.setAttribute("member", m); request.setAttribute("currentPage","members");
                    request.getRequestDispatcher("/WEB-INF/views/manager/member-edit.jsp").forward(request,response); break;
                }
                default: {
                    String search = request.getParameter("search"); String program = request.getParameter("program"); String gender = request.getParameter("gender");
                    List<Member> members = ((search != null && !search.trim().isEmpty()) || (program != null && !program.trim().isEmpty()) || (gender != null && !gender.trim().isEmpty()))
                        ? memberService.searchMembers(search, program, gender) : memberService.getAllMembers();
                    request.setAttribute("members", members); request.setAttribute("search", search); request.setAttribute("program", program); request.setAttribute("gender", gender);
                    request.setAttribute("currentPage","members");
                    request.getRequestDispatcher("/WEB-INF/views/manager/members.jsp").forward(request,response);
                }
            }
        } catch (Exception e) { throw new ServletException(e); }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("addMembership".equals(action)) {
                String memberId = request.getParameter("memberId");
                boolean ok = membershipHistoryService.addMembership(memberId, request.getParameter("planName"),
                        LocalDate.parse(request.getParameter("startDate")), LocalDate.parse(request.getParameter("endDate")));
                request.getSession().setAttribute(ok ? "successMessage" : "errorMessage", ok ? "Membership period added. Previous membership history has been retained." : "Invalid membership details.");
                response.sendRedirect(request.getContextPath()+"/manager/members?action=view&id="+memberId); return;
            } else if ("notRenewed".equals(action)) {
                String memberId = request.getParameter("memberId");
                membershipHistoryService.markNotRenewed(Long.parseLong(request.getParameter("membershipId")), memberId);
                request.getSession().setAttribute("successMessage","Membership marked as not renewed.");
                response.sendRedirect(request.getContextPath()+"/manager/members?action=view&id="+memberId); return;
            } else if ("edit".equals(action)) {
                String id = request.getParameter("id");
                Member member = new Member();
                member.setId(id);
                member.setName(request.getParameter("name").trim());
                member.setGender(request.getParameter("gender").trim());
                member.setProgram(request.getParameter("program").trim());
                member.setFitnessGoal(request.getParameter("fitnessGoal") != null ? request.getParameter("fitnessGoal").trim() : "");
                String ageStr = request.getParameter("age");
                member.setAge(!ValidationUtil.isEmpty(ageStr) ? Integer.parseInt(ageStr.trim()) : 0);
                memberService.updateMember(member);
                request.getSession().setAttribute("successMessage","Member updated successfully.");
            }
        } catch (Exception e) { request.getSession().setAttribute("errorMessage","Error updating member."); }
        response.sendRedirect(request.getContextPath()+"/manager/members");
    }
}
