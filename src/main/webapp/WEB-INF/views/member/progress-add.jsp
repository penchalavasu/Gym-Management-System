<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Add Progress - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/member-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>Add Progress Record</h1><a href="${pageContext.request.contextPath}/member/progress" class="btn-secondary-fc"><i class="bi bi-arrow-left"></i> Back</a></div>
<div class="form-card"><form method="post" action="${pageContext.request.contextPath}/member/progress">
<div class="form-group"><label for="weight">Weight (kg) *</label><input type="number" id="weight" name="weight" class="form-control" step="0.1" min="1" max="500" required placeholder="e.g., 70.5"></div>
<div class="form-group"><label for="height">Height (cm) *</label><input type="number" id="height" name="height" class="form-control" step="0.1" min="1" max="300" required placeholder="e.g., 175.0"></div>
<div class="form-group"><label for="recordDate">Date *</label><input type="date" id="recordDate" name="recordDate" class="form-control" required></div>
<div class="form-group"><label for="notes">Notes</label><textarea id="notes" name="notes" class="form-control" rows="3" placeholder="Any additional notes..."></textarea></div>
<span class="form-text" style="margin-bottom:14px;display:block;">BMI will be calculated automatically based on your weight and height.</span>
<button type="submit" class="btn-success-fc"><i class="bi bi-plus-lg"></i> Add Record</button>
</form></div></div></div></div><script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
