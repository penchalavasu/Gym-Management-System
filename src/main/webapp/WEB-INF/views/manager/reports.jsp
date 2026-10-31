<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Reports - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/manager-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>Reports</h1><button onclick="window.print()" class="btn-secondary-fc"><i class="bi bi-printer"></i> Print</button></div>
<div class="report-tabs">
    <a href="${pageContext.request.contextPath}/manager/reports?type=members" class="report-tab ${reportType=='members'?'active':''}">Member Report</a>
    <a href="${pageContext.request.contextPath}/manager/reports?type=diet-plans" class="report-tab ${reportType=='diet-plans'?'active':''}">Diet Plan Report</a>
    <a href="${pageContext.request.contextPath}/manager/reports?type=progress" class="report-tab ${reportType=='progress'?'active':''}">Progress Report</a>
    <a href="${pageContext.request.contextPath}/manager/reports?type=complaints" class="report-tab ${reportType=='complaints'?'active':''}">Complaint Report</a>
</div>

<c:if test="${reportType == 'members'}">
<form method="get" action="${pageContext.request.contextPath}/manager/reports" class="filter-bar"><input type="hidden" name="type" value="members">
    <div class="filter-group"><label>Search</label><input type="text" name="search" value="${search}" placeholder="Name or ID..."></div>
    <div class="filter-group"><label>Program</label><select name="program"><option value="">All</option><option value="General Fitness" ${program=='General Fitness'?'selected':''}>General Fitness</option><option value="Weight Training" ${program=='Weight Training'?'selected':''}>Weight Training</option><option value="Cardio" ${program=='Cardio'?'selected':''}>Cardio</option><option value="Yoga" ${program=='Yoga'?'selected':''}>Yoga</option><option value="Strength Training" ${program=='Strength Training'?'selected':''}>Strength Training</option></select></div>
    <button type="submit" class="btn-primary-fc"><i class="bi bi-funnel"></i> Filter</button></form>
<div class="content-panel"><div class="panel-header">Member Report</div><div class="panel-body">
<c:choose><c:when test="${not empty members}"><div class="table-container"><table class="fc-table"><thead><tr><th>ID</th><th>Name</th><th>Gender</th><th>Program</th><th>Goal</th><th>Created</th></tr></thead><tbody>
<c:forEach var="m" items="${members}"><tr><td>${m.id}</td><td>${m.name}</td><td>${m.gender}</td><td>${m.program}</td><td>${m.fitnessGoal}</td><td><fmt:formatDate value="${m.createdAt}" pattern="yyyy-MM-dd"/></td></tr></c:forEach></tbody></table></div>
</c:when><c:otherwise><div class="empty-state"><p>No members found.</p></div></c:otherwise></c:choose></div></div></c:if>

<c:if test="${reportType == 'diet-plans'}">
<div class="content-panel"><div class="panel-header">Diet Plan Report</div><div class="panel-body">
<c:choose><c:when test="${not empty plans}"><div class="table-container"><table class="fc-table"><thead><tr><th>ID</th><th>Plan Name</th><th>Goal</th><th>Created By</th><th>Created</th></tr></thead><tbody>
<c:forEach var="p" items="${plans}"><tr><td>${p.id}</td><td>${p.planName}</td><td>${p.goal}</td><td>${p.createdBy}</td><td><fmt:formatDate value="${p.createdAt}" pattern="yyyy-MM-dd"/></td></tr></c:forEach></tbody></table></div>
</c:when><c:otherwise><div class="empty-state"><p>No diet plans.</p></div></c:otherwise></c:choose></div></div></c:if>

<c:if test="${reportType == 'progress'}">
<form method="get" action="${pageContext.request.contextPath}/manager/reports" class="filter-bar"><input type="hidden" name="type" value="progress">
    <div class="filter-group"><label>Search</label><input type="text" name="search" value="${search}" placeholder="Member..."></div>
    <div class="filter-group"><label>BMI Status</label><select name="bmiStatus"><option value="">All</option><option value="Underweight" ${bmiStatus=='Underweight'?'selected':''}>Underweight</option><option value="Normal" ${bmiStatus=='Normal'?'selected':''}>Normal</option><option value="Overweight" ${bmiStatus=='Overweight'?'selected':''}>Overweight</option><option value="Obese" ${bmiStatus=='Obese'?'selected':''}>Obese</option></select></div>
    <div class="filter-group"><label>From</label><input type="date" name="dateFrom" value="${dateFrom}"></div><div class="filter-group"><label>To</label><input type="date" name="dateTo" value="${dateTo}"></div>
    <button type="submit" class="btn-primary-fc"><i class="bi bi-funnel"></i> Filter</button></form>
<div class="content-panel"><div class="panel-header">Progress Report</div><div class="panel-body">
<c:choose><c:when test="${not empty records}"><div class="table-container"><table class="fc-table"><thead><tr><th>Member</th><th>Date</th><th>Height</th><th>Weight</th><th>BMI</th><th>Status</th></tr></thead><tbody>
<c:forEach var="r" items="${records}"><tr><td>${r.memberName} (${r.memberId})</td><td><fmt:formatDate value="${r.recordDate}" pattern="yyyy-MM-dd"/></td><td>${r.heightCm}</td><td>${r.weightKg}</td><td>${r.bmi}</td><td><span class="badge-status badge-${r.bmiStatus.toLowerCase()}">${r.bmiStatus}</span></td></tr></c:forEach></tbody></table></div>
</c:when><c:otherwise><div class="empty-state"><p>No progress records.</p></div></c:otherwise></c:choose></div></div></c:if>

<c:if test="${reportType == 'complaints'}">
<form method="get" action="${pageContext.request.contextPath}/manager/reports" class="filter-bar"><input type="hidden" name="type" value="complaints">
    <div class="filter-group"><label>Search</label><input type="text" name="search" value="${search}" placeholder="ID, member, subject..."></div>
    <div class="filter-group"><label>Status</label><select name="status"><option value="">All</option><option value="PENDING" ${statusFilter=='PENDING'?'selected':''}>Pending</option><option value="RESOLVED" ${statusFilter=='RESOLVED'?'selected':''}>Resolved</option><option value="REJECTED" ${statusFilter=='REJECTED'?'selected':''}>Rejected</option></select></div>
    <button type="submit" class="btn-primary-fc"><i class="bi bi-funnel"></i> Filter</button></form>
<div class="content-panel"><div class="panel-header">Complaint Report</div><div class="panel-body">
<c:choose><c:when test="${not empty complaints}"><div class="table-container"><table class="fc-table"><thead><tr><th>ID</th><th>Member</th><th>Subject</th><th>Status</th><th>Submitted</th></tr></thead><tbody>
<c:forEach var="c" items="${complaints}"><tr><td>${c.id}</td><td>${c.name} (${c.memberId})</td><td>${c.subject}</td><td><span class="badge-status badge-${c.status.toLowerCase()}">${c.status}</span></td><td><fmt:formatDate value="${c.createdAt}" pattern="yyyy-MM-dd"/></td></tr></c:forEach></tbody></table></div>
</c:when><c:otherwise><div class="empty-state"><p>No complaints.</p></div></c:otherwise></c:choose></div></div></c:if>
</div></div></div><script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
