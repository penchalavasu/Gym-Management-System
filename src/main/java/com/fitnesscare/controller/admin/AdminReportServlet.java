package com.fitnesscare.controller.admin;

import com.fitnesscare.service.ReportService;
import com.fitnesscare.model.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AdminReportServlet extends HttpServlet {
    private final ReportService reportService = new ReportService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type == null) type = "members";
        try {
            switch (type) {
                case "diet-plans": {
                    String search = request.getParameter("search");
                    List<DietPlan> plans = reportService.getDietPlanReport(search);
                    List<MemberDietPlan> assignments = reportService.getDietAssignmentReport();
                    request.setAttribute("plans", plans);
                    request.setAttribute("assignments", assignments);
                    break;
                }
                case "progress": {
                    String search = request.getParameter("search");
                    String bmiStatus = request.getParameter("bmiStatus");
                    String dateFrom = request.getParameter("dateFrom");
                    String dateTo = request.getParameter("dateTo");
                    List<ProgressRecord> records = reportService.getProgressReport(search, bmiStatus, dateFrom, dateTo);
                    request.setAttribute("records", records);
                    request.setAttribute("bmiStatus", bmiStatus);
                    request.setAttribute("dateFrom", dateFrom);
                    request.setAttribute("dateTo", dateTo);
                    break;
                }
                case "complaints": {
                    String search = request.getParameter("search");
                    String status = request.getParameter("status");
                    List<Complaint> complaints = reportService.getComplaintReport(search, status);
                    request.setAttribute("complaints", complaints);
                    request.setAttribute("statusFilter", status);
                    break;
                }
                default: { // members
                    type = "members";
                    String search = request.getParameter("search");
                    String program = request.getParameter("program");
                    String gender = request.getParameter("gender");
                    List<Member> members = reportService.getMemberReport(search, program, gender);
                    request.setAttribute("members", members);
                    request.setAttribute("program", program);
                    request.setAttribute("gender", gender);
                    break;
                }
            }
            request.setAttribute("search", request.getParameter("search"));
            request.setAttribute("reportType", type);
            request.setAttribute("currentPage", "reports");
            request.getRequestDispatcher("/WEB-INF/views/admin/reports.jsp").forward(request, response);
        } catch (Exception e) { throw new ServletException(e); }
    }
}
