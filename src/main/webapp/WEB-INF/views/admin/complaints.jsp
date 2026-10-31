<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Complaints - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/admin-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>Complaints</h1></div>
<form method="get" action="${pageContext.request.contextPath}/admin/complaints" class="filter-bar">
    <div class="filter-group"><label>Search</label><input type="text" name="search" placeholder="ID, member, subject..." value="${search}"></div>
    <div class="filter-group"><label>Status</label><select name="status"><option value="">All</option>
        <option value="PENDING" ${statusFilter=='PENDING'?'selected':''}>Pending</option>
        <option value="RESOLVED" ${statusFilter=='RESOLVED'?'selected':''}>Resolved</option>
        <option value="REJECTED" ${statusFilter=='REJECTED'?'selected':''}>Rejected</option></select></div>
    <button type="submit" class="btn-primary-fc"><i class="bi bi-search"></i> Search</button>
    <a href="${pageContext.request.contextPath}/admin/complaints" class="btn-secondary-fc">Clear</a>
</form>
<div class="content-panel"><div class="panel-body">
<c:choose><c:when test="${not empty complaints}"><div class="table-container"><table class="fc-table"><thead><tr>
<th>ID</th><th>Member</th><th>Subject</th><th>Status</th><th>Submitted</th><th>Actions</th>
</tr></thead><tbody>
<c:forEach var="c" items="${complaints}"><tr>
<td>${c.id}</td><td>${c.name} (${c.memberId})</td><td>${c.subject}</td>
<td><span class="badge-status badge-${c.status.toLowerCase()}">${c.status}</span></td>
<td><fmt:formatDate value="${c.createdAt}" pattern="yyyy-MM-dd"/></td>
<td><div class="action-btns">
    <a href="${pageContext.request.contextPath}/admin/complaints?action=view&id=${c.id}" class="btn-action btn-action-view"><i class="bi bi-eye"></i></a>
    <c:if test="${c.status == 'PENDING'}">
        <button type="button" class="btn-action btn-action-resolve" onclick="confirmStatusChange('resolveForm-${c.id}','RESOLVED')"><i class="bi bi-check-lg"></i></button>
        <form id="resolveForm-${c.id}" method="post" action="${pageContext.request.contextPath}/admin/complaints" style="display:none;"><input type="hidden" name="action" value="update-status"><input type="hidden" name="id" value="${c.id}"><input type="hidden" name="status" value="RESOLVED"></form>
        <button type="button" class="btn-action btn-action-reject" onclick="confirmStatusChange('rejectForm-${c.id}','REJECTED')"><i class="bi bi-x-lg"></i></button>
        <form id="rejectForm-${c.id}" method="post" action="${pageContext.request.contextPath}/admin/complaints" style="display:none;"><input type="hidden" name="action" value="update-status"><input type="hidden" name="id" value="${c.id}"><input type="hidden" name="status" value="REJECTED"></form>
    </c:if>
</div></td></tr></c:forEach></tbody></table></div>
</c:when><c:otherwise><div class="empty-state"><i class="bi bi-chat-left-text"></i><p>No complaints found.</p></div></c:otherwise></c:choose>
</div></div></div></div></div>
<div class="confirm-overlay" id="confirmOverlay"><div class="confirm-dialog"><div class="confirm-title">Confirm Action</div><div class="confirm-message" id="confirmMessage"></div><div class="confirm-actions"><button class="btn-primary-fc" id="confirmYes">Confirm</button><button class="btn-secondary-fc" onclick="closeConfirm()">Cancel</button></div></div></div>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
