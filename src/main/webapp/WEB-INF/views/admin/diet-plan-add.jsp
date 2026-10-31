<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Add Diet Plan - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/admin-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>Add Diet Plan</h1><a href="${pageContext.request.contextPath}/admin/diet-plans" class="btn-secondary-fc"><i class="bi bi-arrow-left"></i> Back</a></div>
<div class="form-card"><form method="post" action="${pageContext.request.contextPath}/admin/diet-plans">
<input type="hidden" name="action" value="add">
<div class="form-group"><label for="planName">Plan Name *</label><input type="text" id="planName" name="planName" class="form-control" required maxlength="100"></div>
<div class="form-group"><label for="goal">Goal</label>
<select id="goal" name="goal" class="form-control"><option value="">-- Select --</option><option value="Weight Loss">Weight Loss</option><option value="Weight Gain">Weight Gain</option><option value="Muscle Building">Muscle Building</option><option value="General Fitness">General Fitness</option><option value="Improve Endurance">Improve Endurance</option></select></div>
<div class="form-group"><label for="breakfast">Breakfast</label><textarea id="breakfast" name="breakfast" class="form-control" rows="2"></textarea></div>
<div class="form-group"><label for="midMorningMeal">Mid-Morning Meal</label><textarea id="midMorningMeal" name="midMorningMeal" class="form-control" rows="2"></textarea></div>
<div class="form-group"><label for="lunch">Lunch</label><textarea id="lunch" name="lunch" class="form-control" rows="2"></textarea></div>
<div class="form-group"><label for="eveningSnack">Evening Snack</label><textarea id="eveningSnack" name="eveningSnack" class="form-control" rows="2"></textarea></div>
<div class="form-group"><label for="dinner">Dinner</label><textarea id="dinner" name="dinner" class="form-control" rows="2"></textarea></div>
<div class="form-group"><label for="additionalInstructions">Additional Instructions</label><textarea id="additionalInstructions" name="additionalInstructions" class="form-control" rows="3"></textarea></div>
<button type="submit" class="btn-success-fc"><i class="bi bi-plus-lg"></i> Create Diet Plan</button>
</form></div></div></div></div>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
