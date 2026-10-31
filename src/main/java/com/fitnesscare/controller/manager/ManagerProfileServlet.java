package com.fitnesscare.controller.manager;

import com.fitnesscare.model.Manager;
import com.fitnesscare.service.ManagerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class ManagerProfileServlet extends HttpServlet {
    private final ManagerService managerService = new ManagerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            Manager manager = managerService.getManagerById(userId);
            request.setAttribute("manager", manager);
            request.setAttribute("currentPage", "profile");
            request.getRequestDispatcher("/WEB-INF/views/manager/profile.jsp").forward(request, response);
        } catch (Exception e) { throw new ServletException(e); }
    }
}
