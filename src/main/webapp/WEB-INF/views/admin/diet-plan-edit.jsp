<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Edit Diet Plan - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/admin-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>Edit Diet Plan</h1><a href="${pageContext.request.contextPath}/admin/diet-plans" class="btn-secondary-fc"><i class="bi bi-arrow-left"></i> Back</a></div>
<div class="form-card"><form method="post" action="${pageContext.request.contextPath}/admin/diet-plans">
<input type="hidden" name="action" value="edit"><input type="hidden" name="id" value="${plan.id}">
<div class="form-group"><label for="planName">Plan Name *</label><input type="text" id="planName" name="planName" class="form-control" value="${plan.planName}" required></div>
<div class="form-group"><label for="goal">Goal</label>
<select id="goal" name="goal" class="form-control"><option value="">-- Select --</option>
<option value="Weight Loss" ${plan.goal=='Weight Loss'?'selected':''}>Weight Loss</option>
<option value="Weight Gain" ${plan.goal=='Weight Gain'?'selected':''}>Weight Gain</option>
<option value="Muscle Building" ${plan.goal=='Muscle Building'?'selected':''}>Muscle Building</option>
<option value="General Fitness" ${plan.goal=='General Fitness'?'selected':''}>General Fitness</option>
<option value="Improve Endurance" ${plan.goal=='Improve Endurance'?'selected':''}>Improve Endurance</option></select></div>
<div class="form-group"><label for="breakfast">Breakfast</label><textarea id="breakfast" name="breakfast" class="form-control" rows="2">${plan.breakfast}</textarea></div>
<div class="form-group"><label for="midMorningMeal">Mid-Morning Meal</label><textarea id="midMorningMeal" name="midMorningMeal" class="form-control" rows="2">${plan.midMorningMeal}</textarea></div>
<div class="form-group"><label for="lunch">Lunch</label><textarea id="lunch" name="lunch" class="form-control" rows="2">${plan.lunch}</textarea></div>
<div class="form-group"><label for="eveningSnack">Evening Snack</label><textarea id="eveningSnack" name="eveningSnack" class="form-control" rows="2">${plan.eveningSnack}</textarea></div>
<div class="form-group"><label for="dinner">Dinner</label><textarea id="dinner" name="dinner" class="form-control" rows="2">${plan.dinner}</textarea></div>
<div class="form-group"><label for="additionalInstructions">Additional Instructions</label><textarea id="additionalInstructions" name="additionalInstructions" class="form-control" rows="3">${plan.additionalInstructions}</textarea></div>
<button type="submit" class="btn-primary-fc"><i class="bi bi-check-lg"></i> Update Diet Plan</button>
</form></div></div></div></div>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
