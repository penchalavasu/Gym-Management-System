<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0"><title>Edit Profile - FitnessCare</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css"></head>
<body><div class="dashboard-wrapper"><jsp:include page="/WEB-INF/views/fragments/member-sidebar.jsp"/><div class="main-content"><jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
<div class="page-content"><jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
<div class="page-header"><h1>Edit Profile</h1><a href="${pageContext.request.contextPath}/member/profile" class="btn-secondary-fc"><i class="bi bi-arrow-left"></i> Back</a></div>
<div class="form-card"><form method="post" action="${pageContext.request.contextPath}/member/profile">
<div class="form-group"><label>Member ID</label><input type="text" class="form-control" value="${member.id}" disabled></div>
<div class="form-group"><label for="name">Full Name</label><input type="text" id="name" name="name" class="form-control" value="${member.name}" required></div>
<div class="form-group"><label>Gender</label><div style="display:flex;gap:16px;margin-top:4px;">
<div class="form-check"><input type="radio" name="gender" id="m" value="Male" ${member.gender=='Male'?'checked':''}><label for="m">Male</label></div>
<div class="form-check"><input type="radio" name="gender" id="f" value="Female" ${member.gender=='Female'?'checked':''}><label for="f">Female</label></div>
<div class="form-check"><input type="radio" name="gender" id="o" value="Other" ${member.gender=='Other'?'checked':''}><label for="o">Other</label></div></div></div>
<div class="form-group"><label for="program">Program</label><select id="program" name="program" class="form-control" required>
<option value="General Fitness" ${member.program=='General Fitness'?'selected':''}>General Fitness</option><option value="Weight Training" ${member.program=='Weight Training'?'selected':''}>Weight Training</option>
<option value="Cardio" ${member.program=='Cardio'?'selected':''}>Cardio</option><option value="Yoga" ${member.program=='Yoga'?'selected':''}>Yoga</option><option value="Strength Training" ${member.program=='Strength Training'?'selected':''}>Strength Training</option></select></div>
<div class="form-group"><label for="fitnessGoal">Fitness Goal</label><select id="fitnessGoal" name="fitnessGoal" class="form-control"><option value="">-- Select --</option>
<option value="Weight Loss" ${member.fitnessGoal=='Weight Loss'?'selected':''}>Weight Loss</option><option value="Weight Gain" ${member.fitnessGoal=='Weight Gain'?'selected':''}>Weight Gain</option>
<option value="Muscle Building" ${member.fitnessGoal=='Muscle Building'?'selected':''}>Muscle Building</option><option value="General Fitness" ${member.fitnessGoal=='General Fitness'?'selected':''}>General Fitness</option><option value="Improve Endurance" ${member.fitnessGoal=='Improve Endurance'?'selected':''}>Improve Endurance</option></select></div>
<div class="form-group"><label for="age">Age</label><input type="number" id="age" name="age" class="form-control" value="${member.age > 0 ? member.age : ''}" min="1" max="120"></div>
<button type="submit" class="btn-primary-fc"><i class="bi bi-check-lg"></i> Update Profile</button>
</form></div></div></div></div><script src="${pageContext.request.contextPath}/assets/js/main.js"></script></body></html>
