<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>My Complaints - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/member-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>My Complaints</h1><a href="${pageContext.request.contextPath}/member/complaints?action=add" class="btn-primary-fc"><i class="bi bi-plus-lg"></i> Submit Complaint</a></div>
<div class="content-panel"><div class="panel-body">
<c:choose><c:when test="${not empty complaints}"><div class="table-container"><table class="fc-table"><thead><tr>
<th>ID</th><th>Subject</th><th>Status</th><th>Submitted</th><th>Updated</th></tr></thead><tbody>
<c:forEach var="c" items="${complaints}"><tr>
<td>${c.id}</td><td>${c.subject}</td>
<td><span class="badge-status badge-${c.status.toLowerCase()}">${c.status}</span></td>
<td><fmt:formatDate value="${c.createdAt}" pattern="yyyy-MM-dd"/></td>
<td>${c.updatedAt != null ? c.updatedAt : 'Pending'}</td>
</tr></c:forEach></tbody></table></div>
</c:when><c:otherwise><div class="empty-state"><i class="bi bi-chat-left-text"></i><p>No complaints submitted yet.</p></div></c:otherwise></c:choose>
</div></div></div></div></div><script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
