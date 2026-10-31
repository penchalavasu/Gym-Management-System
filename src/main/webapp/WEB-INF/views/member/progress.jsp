<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>My Progress - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/member-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>My Progress</h1><a href="${pageContext.request.contextPath}/member/progress?action=add" class="btn-primary-fc"><i class="bi bi-plus-lg"></i> Add Record</a></div>
<div class="content-panel"><div class="panel-body">
<c:choose><c:when test="${not empty records}"><div class="table-container"><table class="fc-table"><thead><tr>
<th>Date</th><th>Height (cm)</th><th>Weight (kg)</th><th>BMI</th><th>Status</th><th>Notes</th></tr></thead><tbody>
<c:forEach var="r" items="${records}"><tr>
<td><fmt:formatDate value="${r.recordDate}" pattern="yyyy-MM-dd"/></td>
<td>${r.heightCm}</td><td>${r.weightKg}</td><td>${r.bmi}</td>
<td><span class="badge-status badge-${r.bmiStatus.toLowerCase()}">${r.bmiStatus}</span></td><td>${r.notes}</td>
</tr></c:forEach></tbody></table></div>
</c:when><c:otherwise><div class="empty-state"><i class="bi bi-graph-up"></i><p>No progress records yet. <a href="${pageContext.request.contextPath}/member/progress?action=add">Add your first record</a>.</p></div></c:otherwise></c:choose>
</div></div></div></div></div><script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
