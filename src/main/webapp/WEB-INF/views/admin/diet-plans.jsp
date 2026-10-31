<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Diet Plans - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/admin-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>Diet Plans</h1>
<div style="display:flex;gap:8px;">
    <a href="${pageContext.request.contextPath}/admin/diet-plans?action=assign" class="btn-success-fc"><i class="bi bi-person-plus"></i> Assign Plan</a>
    <a href="${pageContext.request.contextPath}/admin/diet-plans?action=add" class="btn-primary-fc"><i class="bi bi-plus-lg"></i> Add Diet Plan</a>
</div></div>
<form method="get" action="${pageContext.request.contextPath}/admin/diet-plans" class="filter-bar">
    <div class="filter-group"><label>Search</label><input type="text" name="search" placeholder="Plan name or goal..." value="${search}"></div>
    <button type="submit" class="btn-primary-fc"><i class="bi bi-search"></i> Search</button>
    <a href="${pageContext.request.contextPath}/admin/diet-plans" class="btn-secondary-fc">Clear</a>
</form>
<div class="content-panel"><div class="panel-header">All Diet Plans</div><div class="panel-body">
<c:choose><c:when test="${not empty plans}"><div class="table-container"><table class="fc-table"><thead><tr>
<th>ID</th><th>Plan Name</th><th>Goal</th><th>Created By</th><th>Created</th><th>Actions</th>
</tr></thead><tbody>
<c:forEach var="p" items="${plans}"><tr>
<td>${p.id}</td><td>${p.planName}</td><td>${p.goal}</td><td>${p.createdBy}</td>
<td><fmt:formatDate value="${p.createdAt}" pattern="yyyy-MM-dd"/></td>
<td><div class="action-btns">
    <a href="${pageContext.request.contextPath}/admin/diet-plans?action=view&id=${p.id}" class="btn-action btn-action-view"><i class="bi bi-eye"></i></a>
    <a href="${pageContext.request.contextPath}/admin/diet-plans?action=edit&id=${p.id}" class="btn-action btn-action-edit"><i class="bi bi-pencil"></i></a>
    <button type="button" class="btn-action btn-action-delete" onclick="confirmDelete('delDp-${p.id}','${p.planName}')"><i class="bi bi-trash"></i></button>
    <form id="delDp-${p.id}" method="post" action="${pageContext.request.contextPath}/admin/diet-plans" style="display:none;"><input type="hidden" name="action" value="delete"><input type="hidden" name="id" value="${p.id}"></form>
</div></td></tr></c:forEach>
</tbody></table></div></c:when><c:otherwise><div class="empty-state"><i class="bi bi-cup-hot"></i><p>No diet plans found.</p></div></c:otherwise></c:choose>
</div></div>

<c:if test="${not empty assignments}">
<div class="content-panel" style="margin-top:20px;"><div class="panel-header">Diet Plan Assignments</div><div class="panel-body">
<div class="table-container"><table class="fc-table"><thead><tr><th>Member ID</th><th>Member Name</th><th>Plan Name</th><th>Assigned</th></tr></thead><tbody>
<c:forEach var="a" items="${assignments}"><tr><td>${a.memberId}</td><td>${a.memberName}</td><td>${a.planName}</td><td><fmt:formatDate value="${a.assignedAt}" pattern="yyyy-MM-dd"/></td></tr></c:forEach>
</tbody></table></div></div></div>
</c:if>
</div></div></div>
<div class="confirm-overlay" id="confirmOverlay"><div class="confirm-dialog"><div class="confirm-title">Confirm Delete</div><div class="confirm-message" id="confirmMessage"></div><div class="confirm-actions"><button class="btn-danger-fc" id="confirmYes">Delete</button><button class="btn-secondary-fc" onclick="closeConfirm()">Cancel</button></div></div></div>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
