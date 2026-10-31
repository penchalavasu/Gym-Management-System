package com.fitnesscare.controller.member;

import com.fitnesscare.model.Member;
import com.fitnesscare.service.MemberService;
import com.fitnesscare.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class MemberProfileServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            Member member = memberService.getMemberById(userId);
            request.setAttribute("member", member);
            if ("edit".equals(action)) {
                request.setAttribute("currentPage", "profile");
                request.getRequestDispatcher("/WEB-INF/views/member/profile-edit.jsp").forward(request, response);
            } else {
                request.setAttribute("currentPage", "profile");
                request.getRequestDispatcher("/WEB-INF/views/member/profile.jsp").forward(request, response);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            String name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String program = request.getParameter("program");
            String fitnessGoal = request.getParameter("fitnessGoal");
            String ageStr = request.getParameter("age");

            if (ValidationUtil.isEmpty(name)) {
                request.getSession().setAttribute("errorMessage", "Name is required.");
                response.sendRedirect(request.getContextPath() + "/member/profile?action=edit");
                return;
            }

            Member member = new Member();
            member.setId(userId);
            member.setName(name.trim());
            member.setGender(gender != null ? gender.trim() : "");
            member.setProgram(program != null ? program.trim() : "");
            member.setFitnessGoal(fitnessGoal != null ? fitnessGoal.trim() : "");
            member.setAge(!ValidationUtil.isEmpty(ageStr) ? Integer.parseInt(ageStr.trim()) : 0);

            memberService.updateMember(member);
            request.getSession().setAttribute("userName", name.trim());
            request.getSession().setAttribute("successMessage", "Profile updated successfully.");
            response.sendRedirect(request.getContextPath() + "/member/profile");
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error updating profile.");
            response.sendRedirect(request.getContextPath() + "/member/profile?action=edit");
        }
    }
}
