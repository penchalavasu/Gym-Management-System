<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>My Diet Plan - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/member-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>My Diet Plan</h1></div>
<c:choose><c:when test="${not empty plan}">
<div class="content-panel"><div class="panel-header">${plan.planName} <span style="font-weight:400;font-size:13px;color:#6c757d;margin-left:8px;">Goal: ${plan.goal}</span></div>
<div class="panel-body">
<div class="detail-row"><span class="detail-label"><i class="bi bi-sunrise"></i> Breakfast</span><span class="detail-value">${plan.breakfast}</span></div>
<div class="detail-row"><span class="detail-label"><i class="bi bi-cup"></i> Mid-Morning</span><span class="detail-value">${plan.midMorningMeal}</span></div>
<div class="detail-row"><span class="detail-label"><i class="bi bi-sun"></i> Lunch</span><span class="detail-value">${plan.lunch}</span></div>
<div class="detail-row"><span class="detail-label"><i class="bi bi-cloud-sun"></i> Evening Snack</span><span class="detail-value">${plan.eveningSnack}</span></div>
<div class="detail-row"><span class="detail-label"><i class="bi bi-moon"></i> Dinner</span><span class="detail-value">${plan.dinner}</span></div>
<c:if test="${not empty plan.additionalInstructions}">
<div class="detail-row"><span class="detail-label"><i class="bi bi-info-circle"></i> Instructions</span><span class="detail-value">${plan.additionalInstructions}</span></div>
</c:if>
</div></div>
</c:when><c:otherwise>
<div class="content-panel"><div class="panel-body"><div class="empty-state"><i class="bi bi-cup-hot" style="font-size:48px;"></i><p style="margin-top:10px;">No diet plan has been assigned to you yet.</p><p style="color:#999;">Please contact your manager or admin to get a diet plan assigned.</p></div></div></div>
</c:otherwise></c:choose>
</div></div></div><script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
