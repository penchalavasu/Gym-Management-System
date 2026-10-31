package com.fitnesscare.controller.auth;

import com.fitnesscare.model.Admin;
import com.fitnesscare.model.Manager;
import com.fitnesscare.model.Member;
import com.fitnesscare.service.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // If already logged in, redirect to appropriate dashboard
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("role") != null) {
            String role = (String) session.getAttribute("role");
            switch (role) {
                case "ADMIN": response.sendRedirect(request.getContextPath() + "/admin/dashboard"); return;
                case "MANAGER": response.sendRedirect(request.getContextPath() + "/manager/dashboard"); return;
                case "MEMBER": response.sendRedirect(request.getContextPath() + "/member/dashboard"); return;
            }
        }
        request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (userId == null || userId.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            role == null || role.trim().isEmpty()) {
            request.setAttribute("error", "Please fill in all fields.");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            return;
        }

        userId = userId.trim();
        password = password.trim();
        role = role.trim().toUpperCase();

        try {
            switch (role) {
                case "ADMIN": {
                    Admin admin = authService.authenticateAdmin(userId, password);
                    if (admin != null) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("userId", admin.getId());
                        session.setAttribute("userName", admin.getName() != null ? admin.getName() : admin.getId());
                        session.setAttribute("role", "ADMIN");
                        session.setAttribute("successMessage", "Welcome back, " + (admin.getName() != null ? admin.getName() : "admin") + "!");
                        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                        return;
                    }
                    break;
                }
                case "MANAGER": {
                    Manager manager = authService.authenticateManager(userId, password);
                    if (manager != null) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("userId", manager.getManagerId());
                        session.setAttribute("userName", manager.getName());
                        session.setAttribute("role", "MANAGER");
                        session.setAttribute("successMessage", "Welcome back, " + manager.getName() + "!");
                        response.sendRedirect(request.getContextPath() + "/manager/dashboard");
                        return;
                    }
                    break;
                }
                case "MEMBER": {
                    Member member = authService.authenticateMember(userId, password);
                    if (member != null) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("userId", member.getId());
                        session.setAttribute("userName", member.getName());
                        session.setAttribute("role", "MEMBER");
                        session.setAttribute("successMessage", "Welcome back, " + member.getName() + "!");
                        response.sendRedirect(request.getContextPath() + "/member/dashboard");
                        return;
                    }
                    break;
                }
                default:
                    request.setAttribute("error", "Invalid role selected.");
                    request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                    return;
            }

            // Authentication failed
            request.setAttribute("error", "Invalid credentials. Please try again.");
            request.setAttribute("selectedRole", role);
            request.setAttribute("userId", userId);
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "An error occurred. Please try again later.");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        }
    }
}
