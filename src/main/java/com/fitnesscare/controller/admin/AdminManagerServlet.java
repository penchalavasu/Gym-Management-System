package com.fitnesscare.controller.admin;

import com.fitnesscare.model.Manager;
import com.fitnesscare.service.ManagerService;
import com.fitnesscare.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AdminManagerServlet extends HttpServlet {

    private final ManagerService managerService = new ManagerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";
        try {
            switch (action) {
                case "add": request.setAttribute("currentPage","managers"); request.getRequestDispatcher("/WEB-INF/views/admin/manager-add.jsp").forward(request,response); break;
                case "view": viewManager(request,response); break;
                case "edit": editManagerForm(request,response); break;
                default: listManagers(request,response);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        try {
            switch (action) {
                case "add": addManager(request,response); break;
                case "edit": updateManager(request,response); break;
                case "delete": deleteManager(request,response); break;
                default: response.sendRedirect(request.getContextPath()+"/admin/managers");
            }
        } catch (Exception e) { throw new ServletException(e); }
    }

    private void listManagers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String search = request.getParameter("search");
        List<Manager> managers = (search != null && !search.trim().isEmpty()) ? managerService.searchManagers(search) : managerService.getAllManagers();
        request.setAttribute("managers", managers);
        request.setAttribute("search", search);
        request.setAttribute("currentPage", "managers");
        request.getRequestDispatcher("/WEB-INF/views/admin/managers.jsp").forward(request, response);
    }

    private void viewManager(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Manager manager = managerService.getManagerById(id);
        if (manager == null) { request.getSession().setAttribute("errorMessage","Manager not found."); response.sendRedirect(request.getContextPath()+"/admin/managers"); return; }
        request.setAttribute("manager", manager);
        request.setAttribute("currentPage", "managers");
        request.getRequestDispatcher("/WEB-INF/views/admin/manager-view.jsp").forward(request, response);
    }

    private void editManagerForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Manager manager = managerService.getManagerById(id);
        if (manager == null) { request.getSession().setAttribute("errorMessage","Manager not found."); response.sendRedirect(request.getContextPath()+"/admin/managers"); return; }
        request.setAttribute("manager", manager);
        request.setAttribute("currentPage", "managers");
        request.getRequestDispatcher("/WEB-INF/views/admin/manager-edit.jsp").forward(request, response);
    }

    private void addManager(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String managerId = request.getParameter("managerId");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String gender = request.getParameter("gender");

        if (ValidationUtil.isEmpty(managerId) || ValidationUtil.isEmpty(name) || ValidationUtil.isEmpty(password)) {
            request.getSession().setAttribute("errorMessage","All fields are required.");
            response.sendRedirect(request.getContextPath()+"/admin/managers?action=add"); return;
        }
        if (!password.equals(confirmPassword)) {
            request.getSession().setAttribute("errorMessage","Passwords do not match.");
            response.sendRedirect(request.getContextPath()+"/admin/managers?action=add"); return;
        }

        Manager manager = new Manager(managerId.trim(), password, name.trim(), gender != null ? gender.trim() : "");
        boolean success = managerService.createManager(manager);
        if (success) {
            request.getSession().setAttribute("successMessage","Manager added successfully.");
        } else {
            request.getSession().setAttribute("errorMessage","Manager ID already exists.");
        }
        response.sendRedirect(request.getContextPath()+"/admin/managers");
    }

    private void updateManager(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");

        Manager manager = new Manager();
        manager.setManagerId(id);
        manager.setName(name != null ? name.trim() : "");
        manager.setGender(gender != null ? gender.trim() : "");
        managerService.updateManager(manager);
        request.getSession().setAttribute("successMessage","Manager updated successfully.");
        response.sendRedirect(request.getContextPath()+"/admin/managers");
    }

    private void deleteManager(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        if (id != null) { managerService.deleteManager(id.trim()); request.getSession().setAttribute("successMessage","Manager deleted successfully."); }
        response.sendRedirect(request.getContextPath()+"/admin/managers");
    }
}
