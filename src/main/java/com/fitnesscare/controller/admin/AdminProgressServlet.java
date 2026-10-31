package com.fitnesscare.controller.admin;

import com.fitnesscare.model.ProgressRecord;
import com.fitnesscare.service.ProgressService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class AdminProgressServlet extends HttpServlet {
    private final ProgressService progressService = new ProgressService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String search = request.getParameter("search");
            String bmiStatus = request.getParameter("bmiStatus");
            String dateFrom = request.getParameter("dateFrom");
            String dateTo = request.getParameter("dateTo");
            List<ProgressRecord> records;
            if ((search != null && !search.trim().isEmpty()) || (bmiStatus != null && !bmiStatus.trim().isEmpty())
                || (dateFrom != null && !dateFrom.trim().isEmpty()) || (dateTo != null && !dateTo.trim().isEmpty())) {
                records = progressService.searchProgress(search, bmiStatus, dateFrom, dateTo);
            } else {
                records = progressService.getAllProgress();
            }
            request.setAttribute("records", records);
            request.setAttribute("search", search);
            request.setAttribute("bmiStatus", bmiStatus);
            request.setAttribute("dateFrom", dateFrom);
            request.setAttribute("dateTo", dateTo);
            request.setAttribute("currentPage", "progress");
            request.getRequestDispatcher("/WEB-INF/views/admin/progress.jsp").forward(request, response);
        } catch (Exception e) { throw new ServletException(e); }
    }
}
