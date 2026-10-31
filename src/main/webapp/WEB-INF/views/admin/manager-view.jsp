<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>View Manager - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/admin-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><div class="page-header"><h1>Manager Details</h1><a href="${pageContext.request.contextPath}/admin/managers" class="btn-secondary-fc"><i class="bi bi-arrow-left"></i> Back</a></div>
<div class="content-panel"><div class="panel-body">
    <div class="detail-row"><span class="detail-label">Manager ID</span><span class="detail-value">${manager.managerId}</span></div>
    <div class="detail-row"><span class="detail-label">Name</span><span class="detail-value">${manager.name}</span></div>
    <div class="detail-row"><span class="detail-label">Gender</span><span class="detail-value">${manager.gender}</span></div>
    <div class="detail-row"><span class="detail-label">Created</span><span class="detail-value">${manager.createdAt}</span></div>
</div></div></div></div></div><script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
