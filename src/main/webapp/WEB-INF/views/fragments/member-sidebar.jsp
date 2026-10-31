<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String currentPage = (String) request.getAttribute("currentPage");
    if (currentPage == null) currentPage = "";
%>
<aside class="sidebar" id="sidebar">
    <div class="sidebar-brand">
        <i class="bi bi-lightning-charge-fill brand-icon"></i>
        <span class="brand-text">FitnessCare</span>
    </div>
    <nav class="sidebar-nav">
        <a href="${pageContext.request.contextPath}/member/dashboard" class="nav-link <%= "dashboard".equals(currentPage) ? "active" : "" %>">
            <i class="bi bi-grid-1x2-fill"></i> Dashboard
        </a>
        <a href="${pageContext.request.contextPath}/member/profile" class="nav-link <%= "profile".equals(currentPage) ? "active" : "" %>">
            <i class="bi bi-person-circle"></i> My Profile
        </a>
        <a href="${pageContext.request.contextPath}/member/diet-plan" class="nav-link <%= "diet-plan".equals(currentPage) ? "active" : "" %>">
            <i class="bi bi-cup-hot-fill"></i> My Diet Plan
        </a>
        <a href="${pageContext.request.contextPath}/member/progress" class="nav-link <%= "progress".equals(currentPage) ? "active" : "" %>">
            <i class="bi bi-graph-up"></i> My Progress
        </a>
        <a href="${pageContext.request.contextPath}/member/complaints?action=add" class="nav-link <%= "complaint-add".equals(currentPage) ? "active" : "" %>">
            <i class="bi bi-pencil-square"></i> Submit Complaint
        </a>
        <a href="${pageContext.request.contextPath}/member/complaints" class="nav-link <%= "complaints".equals(currentPage) ? "active" : "" %>">
            <i class="bi bi-chat-left-text-fill"></i> My Complaints
        </a>
        <div class="sidebar-divider"></div>
    </nav>
    <div class="sidebar-footer">
        <a href="${pageContext.request.contextPath}/logout" class="nav-link">
            <i class="bi bi-box-arrow-left"></i> Logout
        </a>
    </div>
</aside>
<div class="sidebar-overlay" id="sidebarOverlay"></div>
