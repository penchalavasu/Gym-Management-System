package com.fitnesscare.controller.admin;

import com.fitnesscare.dao.AdminDAO;
import com.fitnesscare.model.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AdminProfileServlet extends HttpServlet {
    private final AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            Admin admin = adminDAO.findById(userId);
            request.setAttribute("admin", admin);
            request.setAttribute("currentPage", "profile");
            request.getRequestDispatcher("/WEB-INF/views/admin/profile.jsp").forward(request, response);
        } catch (Exception e) { throw new ServletException(e); }
    }
}
