package com.fitnesscare.controller.auth;

import com.fitnesscare.model.Member;
import com.fitnesscare.service.MemberService;
import com.fitnesscare.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String memberId = request.getParameter("memberId");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String gender = request.getParameter("gender");
        String program = request.getParameter("program");
        String fitnessGoal = request.getParameter("fitnessGoal");
        String ageStr = request.getParameter("age");

        // Validation
        StringBuilder errors = new StringBuilder();

        if (ValidationUtil.isEmpty(memberId)) errors.append("Member ID is required. ");
        if (ValidationUtil.isEmpty(name)) errors.append("Full Name is required. ");
        if (ValidationUtil.isEmpty(password)) errors.append("Password is required. ");
        if (ValidationUtil.isEmpty(confirmPassword)) errors.append("Confirm Password is required. ");
        if (ValidationUtil.isEmpty(gender) || !ValidationUtil.isValidGender(gender)) errors.append("Please select a valid gender. ");
        if (ValidationUtil.isEmpty(program) || !ValidationUtil.isValidProgram(program)) errors.append("Please select a valid program. ");

        if (!ValidationUtil.isEmpty(password) && !ValidationUtil.isEmpty(confirmPassword)) {
            if (!password.equals(confirmPassword)) {
                errors.append("Passwords do not match. ");
            }
            if (password.length() < 6) {
                errors.append("Password must be at least 6 characters. ");
            }
        }

        if (!ValidationUtil.isEmpty(ageStr) && !ValidationUtil.isValidAge(ageStr)) {
            errors.append("Please enter a valid age (1-120). ");
        }

        if (ValidationUtil.exceedsMaxLength(memberId, 45)) errors.append("Member ID is too long. ");
        if (ValidationUtil.exceedsMaxLength(name, 100)) errors.append("Name is too long. ");

        if (errors.length() > 0) {
            request.setAttribute("error", errors.toString().trim());
            preserveFormData(request, memberId, name, gender, program, fitnessGoal, ageStr);
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
            return;
        }

        try {
            Member member = new Member();
            member.setId(memberId.trim());
            member.setName(name.trim());
            member.setPassword(password);
            member.setGender(gender.trim());
            member.setProgram(program.trim());
            member.setFitnessGoal(fitnessGoal != null ? fitnessGoal.trim() : "");
            member.setAge(!ValidationUtil.isEmpty(ageStr) ? Integer.parseInt(ageStr.trim()) : 0);

            boolean success = memberService.registerMember(member);
            if (success) {
                HttpSession session = request.getSession(true);
                session.setAttribute("successMessage", "Account created successfully! Please sign in.");
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                request.setAttribute("error", "Member ID already exists. Please choose a different ID.");
                preserveFormData(request, memberId, name, gender, program, fitnessGoal, ageStr);
                request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Registration failed. Please try again.");
            preserveFormData(request, memberId, name, gender, program, fitnessGoal, ageStr);
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
        }
    }

    private void preserveFormData(HttpServletRequest request, String memberId, String name,
                                   String gender, String program, String fitnessGoal, String age) {
        request.setAttribute("memberId", memberId);
        request.setAttribute("name", name);
        request.setAttribute("gender", gender);
        request.setAttribute("program", program);
        request.setAttribute("fitnessGoal", fitnessGoal);
        request.setAttribute("age", age);
    }
}
