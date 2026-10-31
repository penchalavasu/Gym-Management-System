<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Manager Dashboard - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/manager-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>Dashboard</h1></div>
<div class="stats-grid">
    <div class="stat-card blue"><div class="stat-icon"><i class="bi bi-people-fill"></i></div><div class="stat-info"><div class="stat-value">${memberCount}</div><div class="stat-label">Total Members</div></div></div>
    <div class="stat-card green"><div class="stat-icon"><i class="bi bi-cup-hot-fill"></i></div><div class="stat-info"><div class="stat-value">${dietPlanCount}</div><div class="stat-label">Active Diet Plans</div></div></div>
    <div class="stat-card yellow"><div class="stat-icon"><i class="bi bi-graph-up"></i></div><div class="stat-info"><div class="stat-value">${progressCount}</div><div class="stat-label">Progress Records</div></div></div>
    <div class="stat-card cyan"><div class="stat-icon"><i class="bi bi-chat-left-text-fill"></i></div><div class="stat-info"><div class="stat-value">${pendingComplaints}</div><div class="stat-label">Pending Complaints</div></div></div>
</div>

<div style="display:grid;grid-template-columns:1fr 1fr;gap:20px;">
<div class="content-panel"><div class="panel-header">Recent Members</div><div class="panel-body">
<c:choose><c:when test="${not empty recentMembers}"><table class="fc-table"><thead><tr><th>ID</th><th>Name</th><th>Program</th></tr></thead><tbody>
<c:forEach var="m" items="${recentMembers}"><tr><td>${m.id}</td><td>${m.name}</td><td>${m.program}</td></tr></c:forEach></tbody></table>
</c:when><c:otherwise><div class="empty-state"><p>No members yet.</p></div></c:otherwise></c:choose></div></div>

<div class="content-panel"><div class="panel-header">Recent Complaints</div><div class="panel-body">
<c:choose><c:when test="${not empty recentComplaints}"><table class="fc-table"><thead><tr><th>ID</th><th>Subject</th><th>Status</th></tr></thead><tbody>
<c:forEach var="c" items="${recentComplaints}"><tr><td>${c.id}</td><td>${c.subject}</td><td><span class="badge-status badge-${c.status.toLowerCase()}">${c.status}</span></td></tr></c:forEach></tbody></table>
</c:when><c:otherwise><div class="empty-state"><p>No complaints.</p></div></c:otherwise></c:choose></div></div>
</div>
</div></div></div>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
