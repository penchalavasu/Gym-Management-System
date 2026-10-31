<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%-- Flash success message from session --%>
<c:if test="${not empty sessionScope.successMessage}">
    <div class="fc-alert fc-alert-success">
        <span>${sessionScope.successMessage}</span>
        <button class="alert-close">&times;</button>
    </div>
    <c:remove var="successMessage" scope="session"/>
</c:if>

<%-- Flash error message from session --%>
<c:if test="${not empty sessionScope.errorMessage}">
    <div class="fc-alert fc-alert-error">
        <span>${sessionScope.errorMessage}</span>
        <button class="alert-close">&times;</button>
    </div>
    <c:remove var="errorMessage" scope="session"/>
</c:if>

<%-- Request-scoped error --%>
<c:if test="${not empty error}">
    <div class="fc-alert fc-alert-error">
        <span>${error}</span>
        <button class="alert-close">&times;</button>
    </div>
</c:if>

<%-- Request-scoped success --%>
<c:if test="${not empty success}">
    <div class="fc-alert fc-alert-success">
        <span>${success}</span>
        <button class="alert-close">&times;</button>
    </div>
</c:if>
