package com.fitnesscare.controller.member;

import com.fitnesscare.model.Complaint;
import com.fitnesscare.model.Member;
import com.fitnesscare.service.ComplaintService;
import com.fitnesscare.service.MemberService;
import com.fitnesscare.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class MemberComplaintServlet extends HttpServlet {
    private final ComplaintService complaintService = new ComplaintService();
    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            if ("add".equals(action)) {
                Member member = memberService.getMemberById(userId);
                request.setAttribute("member", member);
                request.setAttribute("currentPage", "complaint-add");
                request.getRequestDispatcher("/WEB-INF/views/member/complaint-add.jsp").forward(request, response);
            } else {
                List<Complaint> complaints = complaintService.getComplaintsByMemberId(userId);
                request.setAttribute("complaints", complaints);
                request.setAttribute("currentPage", "complaints");
                request.getRequestDispatcher("/WEB-INF/views/member/complaints.jsp").forward(request, response);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userId = (String) request.getSession().getAttribute("userId");
            String userName = (String) request.getSession().getAttribute("userName");
            String subject = request.getParameter("subject");
            String complaintText = request.getParameter("complaint");

            if (ValidationUtil.isEmpty(subject) || ValidationUtil.isEmpty(complaintText)) {
                request.getSession().setAttribute("errorMessage", "Subject and complaint are required.");
                response.sendRedirect(request.getContextPath() + "/member/complaints?action=add");
                return;
            }

            Complaint complaint = new Complaint();
            complaint.setMemberId(userId);
            complaint.setName(userName);
            complaint.setSubject(subject.trim());
            complaint.setComplaint(complaintText.trim());

            complaintService.submitComplaint(complaint);
            request.getSession().setAttribute("successMessage", "Complaint submitted successfully.");
            response.sendRedirect(request.getContextPath() + "/member/complaints");
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "Error submitting complaint.");
            response.sendRedirect(request.getContextPath() + "/member/complaints?action=add");
        }
    }
}
