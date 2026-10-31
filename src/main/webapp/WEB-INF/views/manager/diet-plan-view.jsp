<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>View Diet Plan - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/manager-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content">
<div class="page-header"><h1>Diet Plan Details</h1><a href="${pageContext.request.contextPath}/manager/diet-plans" class="btn-secondary-fc"><i class="bi bi-arrow-left"></i> Back</a></div>
<div class="content-panel"><div class="panel-header">${plan.planName}</div><div class="panel-body">
<div class="detail-row"><span class="detail-label">Plan ID</span><span class="detail-value">${plan.id}</span></div>
<div class="detail-row"><span class="detail-label">Plan Name</span><span class="detail-value">${plan.planName}</span></div>
<div class="detail-row"><span class="detail-label">Goal</span><span class="detail-value">${plan.goal}</span></div>
<div class="detail-row"><span class="detail-label">Breakfast</span><span class="detail-value">${plan.breakfast}</span></div>
<div class="detail-row"><span class="detail-label">Mid-Morning Meal</span><span class="detail-value">${plan.midMorningMeal}</span></div>
<div class="detail-row"><span class="detail-label">Lunch</span><span class="detail-value">${plan.lunch}</span></div>
<div class="detail-row"><span class="detail-label">Evening Snack</span><span class="detail-value">${plan.eveningSnack}</span></div>
<div class="detail-row"><span class="detail-label">Dinner</span><span class="detail-value">${plan.dinner}</span></div>
<div class="detail-row"><span class="detail-label">Additional Instructions</span><span class="detail-value">${plan.additionalInstructions}</span></div>
<div class="detail-row"><span class="detail-label">Created By</span><span class="detail-value">${plan.createdBy}</span></div>
<div class="detail-row"><span class="detail-label">Created</span><span class="detail-value">${plan.createdAt}</span></div>
</div></div></div></div></div><script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
