<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="top-header">
    <div style="display:flex;align-items:center;gap:10px;">
        <button class="sidebar-toggle" id="sidebarToggle"><i class="bi bi-list"></i></button>
        <span class="welcome-text">Welcome, <strong>${sessionScope.userName}</strong>!</span>
    </div>
    <div class="header-actions">
        <div class="user-info">
            <i class="bi bi-person-circle"></i>
            <span>${sessionScope.userName}</span>
            <span style="font-size:11px;color:#aaa;">(${sessionScope.role})</span>
        </div>
    </div>
</header>
