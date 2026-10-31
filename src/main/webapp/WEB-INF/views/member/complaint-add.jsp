<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Submit Complaint - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/member-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>Submit Complaint</h1><a href="${pageContext.request.contextPath}/member/complaints" class="btn-secondary-fc"><i class="bi bi-arrow-left"></i> Back</a></div>
<div class="form-card"><form method="post" action="${pageContext.request.contextPath}/member/complaints">
<div class="form-group"><label>Member ID</label><input type="text" class="form-control" value="${sessionScope.userId}" disabled></div>
<div class="form-group"><label>Name</label><input type="text" class="form-control" value="${sessionScope.userName}" disabled></div>
<div class="form-group"><label for="subject">Subject *</label><input type="text" id="subject" name="subject" class="form-control" required maxlength="200" placeholder="Brief subject of your complaint"></div>
<div class="form-group"><label for="complaint">Complaint Details *</label><textarea id="complaint" name="complaint" class="form-control" rows="5" required placeholder="Describe your complaint in detail..."></textarea></div>
<button type="submit" class="btn-success-fc"><i class="bi bi-send"></i> Submit Complaint</button>
</form></div></div></div></div><script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
