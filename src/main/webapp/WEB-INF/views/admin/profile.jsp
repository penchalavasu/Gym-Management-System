<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Admin Profile - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/admin-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content">
<div class="page-header"><h1>Profile</h1></div>
<div class="content-panel"><div class="panel-header">Your Profile</div><div class="panel-body">
<div class="profile-grid">
    <div class="profile-item"><div class="profile-label">Admin ID</div><div class="profile-value">${admin.id}</div></div>
    <div class="profile-item"><div class="profile-label">Name</div><div class="profile-value">${admin.name != null ? admin.name : 'N/A'}</div></div>
    <div class="profile-item"><div class="profile-label">Role</div><div class="profile-value">Administrator</div></div>
    <div class="profile-item"><div class="profile-label">Created</div><div class="profile-value">${admin.createdAt}</div></div>
</div></div></div></div></div></div>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
