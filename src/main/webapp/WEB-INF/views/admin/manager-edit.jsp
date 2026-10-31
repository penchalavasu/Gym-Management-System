<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Edit Manager - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/admin-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>Edit Manager</h1><a href="${pageContext.request.contextPath}/admin/managers" class="btn-secondary-fc"><i class="bi bi-arrow-left"></i> Back</a></div>
<div class="form-card"><form method="post" action="${pageContext.request.contextPath}/admin/managers">
    <input type="hidden" name="action" value="edit"><input type="hidden" name="id" value="${manager.managerId}">
    <div class="form-group"><label>Manager ID</label><input type="text" class="form-control" value="${manager.managerId}" disabled></div>
    <div class="form-group"><label for="name">Full Name</label><input type="text" id="name" name="name" class="form-control" value="${manager.name}" required></div>
    <div class="form-group"><label>Gender</label><div style="display:flex;gap:16px;margin-top:4px;">
        <div class="form-check"><input type="radio" name="gender" id="gm" value="Male" ${manager.gender=='Male'?'checked':''}><label for="gm">Male</label></div>
        <div class="form-check"><input type="radio" name="gender" id="gf" value="Female" ${manager.gender=='Female'?'checked':''}><label for="gf">Female</label></div>
    </div></div>
    <button type="submit" class="btn-primary-fc"><i class="bi bi-check-lg"></i> Update Manager</button>
</form></div></div></div></div><script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
