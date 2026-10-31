<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>My Dashboard - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/member-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>My Dashboard</h1></div>
<div class="stats-grid">
    <div class="stat-card blue"><div class="stat-icon"><i class="bi bi-person-circle"></i></div><div class="stat-info"><div class="stat-value">${member.program}</div><div class="stat-label">My Program</div></div></div>
    <div class="stat-card green"><div class="stat-icon"><i class="bi bi-cup-hot-fill"></i></div><div class="stat-info"><div class="stat-value">${dietPlan != null ? 'Active' : 'None'}</div><div class="stat-label">Diet Plan</div></div></div>
    <div class="stat-card yellow"><div class="stat-icon"><i class="bi bi-graph-up"></i></div><div class="stat-info"><div class="stat-value">${progressCount}</div><div class="stat-label">Progress Records</div></div></div>
    <div class="stat-card cyan"><div class="stat-icon"><i class="bi bi-chat-left-text-fill"></i></div><div class="stat-info"><div class="stat-value">${complaintCount}</div><div class="stat-label">My Complaints</div></div></div>
</div>

<div style="display:grid;grid-template-columns:1fr 1fr;gap:20px;">
<!-- Profile Summary -->
<div class="content-panel"><div class="panel-header">Profile Summary</div><div class="panel-body">
<c:if test="${not empty member}">
<div class="detail-row"><span class="detail-label">Member ID</span><span class="detail-value">${member.id}</span></div>
<div class="detail-row"><span class="detail-label">Name</span><span class="detail-value">${member.name}</span></div>
<div class="detail-row"><span class="detail-label">Gender</span><span class="detail-value">${member.gender}</span></div>
<div class="detail-row"><span class="detail-label">Fitness Goal</span><span class="detail-value">${member.fitnessGoal}</span></div>
</c:if></div></div>

<!-- Latest Progress -->
<div class="content-panel"><div class="panel-header">Latest Progress</div><div class="panel-body">
<c:choose><c:when test="${not empty latestProgress}">
<div class="detail-row"><span class="detail-label">Date</span><span class="detail-value">${latestProgress.recordDate}</span></div>
<div class="detail-row"><span class="detail-label">Weight</span><span class="detail-value">${latestProgress.weightKg} kg</span></div>
<div class="detail-row"><span class="detail-label">Height</span><span class="detail-value">${latestProgress.heightCm} cm</span></div>
<div class="detail-row"><span class="detail-label">BMI</span><span class="detail-value">${latestProgress.bmi} <span class="badge-status badge-${latestProgress.bmiStatus.toLowerCase()}">${latestProgress.bmiStatus}</span></span></div>
</c:when><c:otherwise><div class="empty-state"><i class="bi bi-graph-up"></i><p>No progress recorded yet. <a href="${pageContext.request.contextPath}/member/progress?action=add">Add your first record</a>.</p></div></c:otherwise></c:choose>
</div></div>
</div>
</div></div></div><script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
