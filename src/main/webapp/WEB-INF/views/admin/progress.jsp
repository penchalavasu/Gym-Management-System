<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Progress Records - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/admin-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>Progress Records</h1></div>
<form method="get" action="${pageContext.request.contextPath}/admin/progress" class="filter-bar">
    <div class="filter-group"><label>Search Member</label><input type="text" name="search" placeholder="Member ID or name..." value="${search}"></div>
    <div class="filter-group"><label>BMI Status</label><select name="bmiStatus"><option value="">All</option>
        <option value="Underweight" ${bmiStatus=='Underweight'?'selected':''}>Underweight</option>
        <option value="Normal" ${bmiStatus=='Normal'?'selected':''}>Normal</option>
        <option value="Overweight" ${bmiStatus=='Overweight'?'selected':''}>Overweight</option>
        <option value="Obese" ${bmiStatus=='Obese'?'selected':''}>Obese</option></select></div>
    <div class="filter-group"><label>From</label><input type="date" name="dateFrom" value="${dateFrom}"></div>
    <div class="filter-group"><label>To</label><input type="date" name="dateTo" value="${dateTo}"></div>
    <button type="submit" class="btn-primary-fc"><i class="bi bi-search"></i> Search</button>
    <a href="${pageContext.request.contextPath}/admin/progress" class="btn-secondary-fc">Clear</a>
</form>
<div class="content-panel"><div class="panel-header">Health Records</div><div class="panel-body">
<c:choose><c:when test="${not empty records}"><div class="table-container"><table class="fc-table"><thead><tr>
<th>Member</th><th>Date</th><th>Height (cm)</th><th>Weight (kg)</th><th>BMI</th><th>Status</th><th>Notes</th>
</tr></thead><tbody>
<c:forEach var="r" items="${records}"><tr>
<td>${r.memberName} (${r.memberId})</td>
<td><fmt:formatDate value="${r.recordDate}" pattern="yyyy-MM-dd"/></td>
<td>${r.heightCm}</td><td>${r.weightKg}</td><td>${r.bmi}</td>
<td><span class="badge-status badge-${r.bmiStatus.toLowerCase()}">${r.bmiStatus}</span></td>
<td>${r.notes}</td>
</tr></c:forEach></tbody></table></div>
</c:when><c:otherwise><div class="empty-state"><i class="bi bi-graph-up"></i><p>No progress records found.</p></div></c:otherwise></c:choose>
</div></div></div></div></div>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
