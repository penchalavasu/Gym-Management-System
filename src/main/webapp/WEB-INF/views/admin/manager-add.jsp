<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Manager - FitnessCare</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
</head>
<body>
<div class="dashboard-wrapper">
    <jsp:include page="/WEB-INF/views/fragments/admin-sidebar.jsp"/>
    <div class="main-content">
        <jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
        <div class="page-content">
            <jsp:include page="/WEB-INF/views/fragments/alerts.jsp"/>
            <div class="page-header"><h1>Add Manager</h1><a href="${pageContext.request.contextPath}/admin/managers" class="btn-secondary-fc"><i class="bi bi-arrow-left"></i> Back</a></div>
            <div class="form-card">
                <form method="post" action="${pageContext.request.contextPath}/admin/managers" onsubmit="return validatePasswordMatch('password','confirmPassword');">
                    <input type="hidden" name="action" value="add">
                    <div class="form-group"><label for="managerId">Manager ID</label><input type="text" id="managerId" name="managerId" class="form-control" required maxlength="20" placeholder="e.g., MGR003"></div>
                    <div class="form-group"><label for="name">Full Name</label><input type="text" id="name" name="name" class="form-control" required maxlength="100"></div>
                    <div class="form-group"><label for="password">Password</label><input type="password" id="password" name="password" class="form-control" required minlength="6"></div>
                    <div class="form-group"><label for="confirmPassword">Confirm Password</label><input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required></div>
                    <div class="form-group"><label>Gender</label>
                        <div style="display:flex;gap:16px;margin-top:4px;">
                            <div class="form-check"><input type="radio" name="gender" id="gm" value="Male" checked><label for="gm">Male</label></div>
                            <div class="form-check"><input type="radio" name="gender" id="gf" value="Female"><label for="gf">Female</label></div>
                        </div>
                    </div>
                    <button type="submit" class="btn-success-fc"><i class="bi bi-plus-lg"></i> Add Manager</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body></html>
