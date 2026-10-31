<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>My Profile - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/member-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>My Profile</h1><a href="${pageContext.request.contextPath}/member/profile?action=edit" class="btn-primary-fc"><i class="bi bi-pencil"></i> Edit Profile</a></div>
<div class="content-panel"><div class="panel-header">Profile Information</div><div class="panel-body">
<div class="profile-grid">
    <div class="profile-item"><div class="profile-label">Member ID</div><div class="profile-value">${member.id}</div></div>
    <div class="profile-item"><div class="profile-label">Full Name</div><div class="profile-value">${member.name}</div></div>
    <div class="profile-item"><div class="profile-label">Gender</div><div class="profile-value">${member.gender}</div></div>
    <div class="profile-item"><div class="profile-label">Program</div><div class="profile-value">${member.program}</div></div>
    <div class="profile-item"><div class="profile-label">Fitness Goal</div><div class="profile-value">${member.fitnessGoal}</div></div>
    <div class="profile-item"><div class="profile-label">Age</div><div class="profile-value">${member.age > 0 ? member.age : 'N/A'}</div></div>
    <div class="profile-item"><div class="profile-label">Member Since</div><div class="profile-value">${member.createdAt}</div></div>
</div></div></div></div></div></div><script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
