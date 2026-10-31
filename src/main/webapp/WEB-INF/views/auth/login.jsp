<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Sign in to FitnessCare - Gym and Fitness Management System">
    <title>Sign In - FitnessCare</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
</head>
<body>
    <div class="auth-wrapper">
        <div class="auth-card">
            <div class="auth-brand">
                <i class="bi bi-lightning-charge-fill auth-icon"></i>
                <div class="auth-title">FitnessCare</div>
                <div class="auth-subtitle">Sign in to your account</div>
            </div>

            <c:if test="${not empty error}">
                <div class="fc-alert fc-alert-error" style="margin-bottom:14px;">
                    <span>${error}</span>
                    <button class="alert-close">&times;</button>
                </div>
            </c:if>

            <c:if test="${not empty sessionScope.successMessage}">
                <div class="fc-alert fc-alert-success" style="margin-bottom:14px;">
                    <span>${sessionScope.successMessage}</span>
                    <button class="alert-close">&times;</button>
                </div>
                <c:remove var="successMessage" scope="session"/>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/login" id="loginForm">
                <div class="form-group">
                    <label for="role">Login As</label>
                    <select id="role" name="role" class="form-control" required>
                        <option value="">-- Select Role --</option>
                        <option value="ADMIN" ${selectedRole == 'ADMIN' ? 'selected' : ''}>Admin</option>
                        <option value="MANAGER" ${selectedRole == 'MANAGER' ? 'selected' : ''}>Manager</option>
                        <option value="MEMBER" ${selectedRole == 'MEMBER' ? 'selected' : ''}>Member</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="userId">User ID</label>
                    <input type="text" id="userId" name="userId" class="form-control"
                           placeholder="Enter your ID" required value="${userId}">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" class="form-control"
                           placeholder="Enter your password" required>
                </div>
                <button type="submit" class="btn-login">
                    <i class="bi bi-box-arrow-in-right"></i> Login
                </button>
            </form>

            <div class="auth-footer">
                Don't have an account? <a href="${pageContext.request.contextPath}/register">Register here</a>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>
