package com.fitnesscare.controller.admin;

import com.fitnesscare.model.Complaint;
import com.fitnesscare.service.ComplaintService;
import com.fitnesscare.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AdminComplaintServlet extends HttpServlet {
    private final ComplaintService complaintService = new ComplaintService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";
        try {
            if ("view".equals(action)) {
                String id = request.getParameter("id");
                Complaint c = complaintService.getComplaintById(id);
                if (c == null) { request.getSession().setAttribute("errorMessage", "Complaint not found."); response.sendRedirect(request.getContextPath() + "/admin/complaints"); return; }
                request.setAttribute("complaint", c);
                request.setAttribute("currentPage", "complaints");
                request.getRequestDispatcher("/WEB-INF/views/admin/complaint-view.jsp").forward(request, response);
            } else {
                String search = request.getParameter("search");
                String status = request.getParameter("status");
                List<Complaint> complaints = (search != null && !search.trim().isEmpty()) || (status != null && !status.trim().isEmpty())
                    ? complaintService.searchComplaints(search, status) : complaintService.getAllComplaints();
                request.setAttribute("complaints", complaints);
                request.setAttribute("search", search);
                request.setAttribute("statusFilter", status);
                request.setAttribute("currentPage", "complaints");
                request.getRequestDispatcher("/WEB-INF/views/admin/complaints.jsp").forward(request, response);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("update-status".equals(action)) {
                String id = request.getParameter("id");
                String status = request.getParameter("status");
                if (!ValidationUtil.isEmpty(id) && ValidationUtil.isValidComplaintStatus(status)) {
                    complaintService.updateComplaintStatus(id, status.toUpperCase());
                    request.getSession().setAttribute("successMessage", "Complaint status updated to " + status.toUpperCase() + ".");
                } else {
                    request.getSession().setAttribute("errorMessage", "Invalid status update.");
                }
            }
        } catch (Exception e) { request.getSession().setAttribute("errorMessage", "Error updating complaint."); }
        response.sendRedirect(request.getContextPath() + "/admin/complaints");
    }
}
