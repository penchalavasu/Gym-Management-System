package com.fitnesscare.controller.manager;

import com.fitnesscare.service.ReportService;
import com.fitnesscare.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ManagerReportServlet extends HttpServlet {
    private final ReportService reportService = new ReportService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type == null) type = "members";
        try {
            String search = request.getParameter("search");
            switch (type) {
                case "diet-plans": {
                    request.setAttribute("plans", reportService.getDietPlanReport(search));
                    request.setAttribute("assignments", reportService.getDietAssignmentReport()); break;
                }
                case "progress": {
                    request.setAttribute("records", reportService.getProgressReport(search, request.getParameter("bmiStatus"), request.getParameter("dateFrom"), request.getParameter("dateTo")));
                    request.setAttribute("bmiStatus", request.getParameter("bmiStatus"));
                    request.setAttribute("dateFrom", request.getParameter("dateFrom")); request.setAttribute("dateTo", request.getParameter("dateTo")); break;
                }
                case "complaints": {
                    request.setAttribute("complaints", reportService.getComplaintReport(search, request.getParameter("status")));
                    request.setAttribute("statusFilter", request.getParameter("status")); break;
                }
                default: {
                    type = "members";
                    request.setAttribute("members", reportService.getMemberReport(search, request.getParameter("program"), request.getParameter("gender")));
                    request.setAttribute("program", request.getParameter("program")); request.setAttribute("gender", request.getParameter("gender")); break;
                }
            }
            request.setAttribute("search", search); request.setAttribute("reportType", type);
            request.setAttribute("currentPage", "reports");
            request.getRequestDispatcher("/WEB-INF/views/manager/reports.jsp").forward(request, response);
        } catch (Exception e) { throw new ServletException(e); }
    }
}
