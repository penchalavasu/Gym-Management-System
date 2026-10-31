<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Managers - FitnessCare</title>
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
                <h1>Managers</h1>
                <a href="${pageContext.request.contextPath}/admin/managers?action=add" class="btn-primary-fc"><i class="bi bi-plus-lg"></i> Add Manager</a>
            </div>
            <form method="get" action="${pageContext.request.contextPath}/admin/managers" class="filter-bar">
                <div class="filter-group"><label>Search</label><input type="text" name="search" placeholder="Name or ID..." value="${search}"></div>
                <button type="submit" class="btn-primary-fc"><i class="bi bi-search"></i> Search</button>
                <a href="${pageContext.request.contextPath}/admin/managers" class="btn-secondary-fc">Clear</a>
            </form>
            <div class="content-panel"><div class="panel-body">
                <c:choose>
                    <c:when test="${not empty managers}">
                        <div class="table-container"><table class="fc-table"><thead><tr>
                            <th>Manager ID</th><th>Name</th><th>Gender</th><th>Created</th><th>Actions</th>
                        </tr></thead><tbody>
                            <c:forEach var="m" items="${managers}"><tr>
                                <td>${m.managerId}</td><td>${m.name}</td><td>${m.gender}</td>
                                <td><fmt:formatDate value="${m.createdAt}" pattern="yyyy-MM-dd"/></td>
                                <td><div class="action-btns">
                                    <a href="${pageContext.request.contextPath}/admin/managers?action=view&id=${m.managerId}" class="btn-action btn-action-view"><i class="bi bi-eye"></i></a>
                                    <a href="${pageContext.request.contextPath}/admin/managers?action=edit&id=${m.managerId}" class="btn-action btn-action-edit"><i class="bi bi-pencil"></i></a>
                                    <button type="button" class="btn-action btn-action-delete" onclick="confirmDelete('delMgr-${m.managerId}','${m.name}')"><i class="bi bi-trash"></i></button>
                                    <form id="delMgr-${m.managerId}" method="post" action="${pageContext.request.contextPath}/admin/managers" style="display:none;">
                                        <input type="hidden" name="action" value="delete"><input type="hidden" name="id" value="${m.managerId}">
                                    </form>
                                </div></td>
                            </tr></c:forEach>
                        </tbody></table></div>
                    </c:when>
                    <c:otherwise><div class="empty-state"><i class="bi bi-person-badge"></i><p>No managers found.</p></div></c:otherwise>
                </c:choose>
            </div></div>
        </div>
    </div>
</div>
<div class="confirm-overlay" id="confirmOverlay"><div class="confirm-dialog"><div class="confirm-title">Confirm Delete</div><div class="confirm-message" id="confirmMessage"></div><div class="confirm-actions"><button class="btn-danger-fc" id="confirmYes">Delete</button><button class="btn-secondary-fc" onclick="closeConfirm()">Cancel</button></div></div></div>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body></html>
