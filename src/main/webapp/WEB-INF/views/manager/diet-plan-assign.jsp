<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Assign Diet Plan - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/manager-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>Assign Diet Plan</h1><a href="${pageContext.request.contextPath}/manager/diet-plans" class="btn-secondary-fc"><i class="bi bi-arrow-left"></i> Back</a></div>
<div class="form-card"><form method="post" action="${pageContext.request.contextPath}/manager/diet-plans">
<input type="hidden" name="action" value="assign">
<div class="form-group"><label for="memberId">Select Member *</label><select id="memberId" name="memberId" class="form-control" required><option value="">-- Select Member --</option>
<c:forEach var="m" items="${members}"><option value="${m.id}">${m.id} - ${m.name}</option></c:forEach></select></div>
<div class="form-group"><label for="planId">Select Diet Plan *</label><select id="planId" name="planId" class="form-control" required><option value="">-- Select Plan --</option>
<c:forEach var="p" items="${plans}"><option value="${p.id}">${p.planName} (${p.goal})</option></c:forEach></select></div>
<span class="form-text" style="margin-bottom:14px;display:block;">Note: Assigning a new plan will replace any existing plan for this member.</span>
<button type="submit" class="btn-success-fc"><i class="bi bi-person-plus"></i> Assign Plan</button>
</form></div></div></div></div><script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
