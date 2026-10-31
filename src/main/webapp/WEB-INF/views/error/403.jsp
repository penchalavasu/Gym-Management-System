<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>403 - Access Denied</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
</head>
<body>
    <div class="error-wrapper">
        <div class="error-card">
            <div class="error-code">403</div>
            <div class="error-title">Access Denied</div>
            <div class="error-message">You do not have permission to access this page.</div>
            <div class="error-action">
                <a href="${pageContext.request.contextPath}/login" class="btn-primary-fc">
                    <i class="bi bi-box-arrow-in-right"></i> Go to Login
                </a>
            </div>
        </div>
    </div>
</body>
</html>
