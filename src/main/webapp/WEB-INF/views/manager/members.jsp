<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Members - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/manager-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>Members</h1></div>
<form method="get" action="${pageContext.request.contextPath}/manager/members" class="filter-bar">
    <div class="filter-group"><label>Search</label><input type="text" name="search" placeholder="Name or ID..." value="${search}"></div>
    <div class="filter-group"><label>Program</label><select name="program"><option value="">All</option>
        <option value="General Fitness" ${program=='General Fitness'?'selected':''}>General Fitness</option><option value="Weight Training" ${program=='Weight Training'?'selected':''}>Weight Training</option>
        <option value="Cardio" ${program=='Cardio'?'selected':''}>Cardio</option><option value="Yoga" ${program=='Yoga'?'selected':''}>Yoga</option><option value="Strength Training" ${program=='Strength Training'?'selected':''}>Strength Training</option></select></div>
    <div class="filter-group"><label>Gender</label><select name="gender"><option value="">All</option><option value="Male" ${gender=='Male'?'selected':''}>Male</option><option value="Female" ${gender=='Female'?'selected':''}>Female</option></select></div>
    <button type="submit" class="btn-primary-fc"><i class="bi bi-search"></i> Search</button>
    <a href="${pageContext.request.contextPath}/manager/members" class="btn-secondary-fc">Clear</a>
</form>
<div class="content-panel"><div class="panel-body">
<c:choose><c:when test="${not empty members}"><div class="table-container"><table class="fc-table"><thead><tr><th>Member ID</th><th>Name</th><th>Gender</th><th>Program</th><th>Goal</th><th>Created</th><th>Actions</th></tr></thead><tbody>
<c:forEach var="m" items="${members}"><tr><td>${m.id}</td><td>${m.name}</td><td>${m.gender}</td><td>${m.program}</td><td>${m.fitnessGoal}</td><td><fmt:formatDate value="${m.createdAt}" pattern="yyyy-MM-dd"/></td>
<td><div class="action-btns"><a href="${pageContext.request.contextPath}/manager/members?action=view&id=${m.id}" class="btn-action btn-action-view"><i class="bi bi-eye"></i></a>
<a href="${pageContext.request.contextPath}/manager/members?action=edit&id=${m.id}" class="btn-action btn-action-edit"><i class="bi bi-pencil"></i></a></div></td></tr></c:forEach>
</tbody></table></div></c:when><c:otherwise><div class="empty-state"><i class="bi bi-people"></i><p>No members found.</p></div></c:otherwise></c:choose>
</div></div></div></div></div><script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
