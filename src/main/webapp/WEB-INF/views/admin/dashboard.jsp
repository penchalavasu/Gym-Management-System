<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - FitnessCare</title>
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

                <div class="page-header">
                    <h1>Dashboard</h1>
                </div>

                <!-- Statistics Cards -->
                <div class="stats-grid">
                    <div class="stat-card blue">
                        <div class="stat-icon"><i class="bi bi-people-fill"></i></div>
                        <div class="stat-info">
                            <div class="stat-value">${memberCount}</div>
                            <div class="stat-label">Total Members</div>
                        </div>
                    </div>
                    <div class="stat-card green">
                        <div class="stat-icon"><i class="bi bi-cup-hot-fill"></i></div>
                        <div class="stat-info">
                            <div class="stat-value">${dietPlanCount}</div>
                            <div class="stat-label">Diet Plans</div>
                        </div>
                    </div>
                    <div class="stat-card yellow">
                        <div class="stat-icon"><i class="bi bi-graph-up"></i></div>
                        <div class="stat-info">
                            <div class="stat-value">${progressCount}</div>
                            <div class="stat-label">Progress Records</div>
                        </div>
                    </div>
                    <div class="stat-card cyan">
                        <div class="stat-icon"><i class="bi bi-card-checklist"></i></div>
                        <div class="stat-info">
                            <div class="stat-value">${assignmentCount}</div>
                            <div class="stat-label">Membership Records</div>
                        </div>
                    </div>
                </div>

                <!-- Profile Panel -->
                <div class="content-panel">
                    <div class="panel-header">Your Profile</div>
                    <div class="panel-body">
                        <c:if test="${not empty admin}">
                            <div class="profile-grid">
                                <div class="profile-item">
                                    <div class="profile-label">Admin ID</div>
                                    <div class="profile-value">${admin.id}</div>
                                </div>
                                <div class="profile-item">
                                    <div class="profile-label">Name</div>
                                    <div class="profile-value">${admin.name != null ? admin.name : 'N/A'}</div>
                                </div>
                                <div class="profile-item">
                                    <div class="profile-label">Role</div>
                                    <div class="profile-value">Administrator</div>
                                </div>
                                <div class="profile-item">
                                    <div class="profile-label">Created</div>
                                    <div class="profile-value">${admin.createdAt}</div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>
