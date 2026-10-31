<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="nav-link <%= "dashboard".equals(currentPage) ? "active" : "" %>">
            <i class="bi bi-grid-1x2-fill"></i> Dashboard
        </a>
        <a href="${pageContext.request.contextPath}/admin/members" class="nav-link <%= "members".equals(currentPage) ? "active" : "" %>">
            <i class="bi bi-people-fill"></i> Members
        </a>
        <a href="${pageContext.request.contextPath}/admin/managers" class="nav-link <%= "managers".equals(currentPage) ? "active" : "" %>">
            <i class="bi bi-person-badge-fill"></i> Managers
        </a>
        <a href="${pageContext.request.contextPath}/admin/diet-plans" class="nav-link <%= "diet-plans".equals(currentPage) ? "active" : "" %>">
            <i class="bi bi-cup-hot-fill"></i> Diet Plans
        </a>
        <a href="${pageContext.request.contextPath}/admin/progress" class="nav-link <%= "progress".equals(currentPage) ? "active" : "" %>">
            <i class="bi bi-graph-up"></i> Progress
        </a>
        <a href="${pageContext.request.contextPath}/admin/complaints" class="nav-link <%= "complaints".equals(currentPage) ? "active" : "" %>">
            <i class="bi bi-chat-left-text-fill"></i> Complaints
        </a>
        <a href="${pageContext.request.contextPath}/admin/reports" class="nav-link <%= "reports".equals(currentPage) ? "active" : "" %>">
            <i class="bi bi-file-earmark-bar-graph-fill"></i> Reports
        </a>
        <a href="${pageContext.request.contextPath}/admin/profile" class="nav-link <%= "profile".equals(currentPage) ? "active" : "" %>">
            <i class="bi bi-person-circle"></i> Profile
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
