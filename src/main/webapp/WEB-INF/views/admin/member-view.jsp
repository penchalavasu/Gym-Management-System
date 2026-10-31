<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Member - FitnessCare</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css">
</head>
<body>
<div class="dashboard-wrapper">
    <jsp:include page="/WEB-INF/views/fragments/admin-sidebar.jsp"/>
    <div class="main-content">
        <jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
        <div class="page-content">
            <div class="page-header">
                <h1>Member Details</h1>
                <a href="${pageContext.request.contextPath}/admin/members" class="btn-secondary-fc"><i class="bi bi-arrow-left"></i> Back</a>
            </div>
            <div class="content-panel">
                <div class="panel-body">
                    <div class="detail-row"><span class="detail-label">Member ID</span><span class="detail-value">${member.id}</span></div>
                    <div class="detail-row"><span class="detail-label">Full Name</span><span class="detail-value">${member.name}</span></div>
                    <div class="detail-row"><span class="detail-label">Gender</span><span class="detail-value">${member.gender}</span></div>
                    <div class="detail-row"><span class="detail-label">Program</span><span class="detail-value">${member.program}</span></div>
                    <div class="detail-row"><span class="detail-label">Fitness Goal</span><span class="detail-value">${member.fitnessGoal}</span></div>
                    <div class="detail-row"><span class="detail-label">Age</span><span class="detail-value">${member.age > 0 ? member.age : 'N/A'}</span></div>
                    <div class="detail-row"><span class="detail-label">Created</span><span class="detail-value">${member.createdAt}</span></div>
                    <div class="detail-row"><span class="detail-label">Updated</span><span class="detail-value">${member.updatedAt}</span></div>
                </div>
            </div>

<div class="content-panel" style="margin-top:20px">
  <div class="panel-header"><h3>Membership Tracking / History</h3></div>
  <div class="panel-body">
    <form method="post" action="${pageContext.request.contextPath}/admin/members" style="margin-bottom:20px">
      <input type="hidden" name="action" value="addMembership">
      <input type="hidden" name="memberId" value="${member.id}">
      <div class="form-row">
        <div class="form-group"><label>Membership Plan</label><input class="form-control" name="planName" placeholder="Monthly / Quarterly / Annual" required></div>
        <div class="form-group"><label>Start Date</label><input class="form-control" type="date" name="startDate" required></div>
        <div class="form-group"><label>Expiry Date</label><input class="form-control" type="date" name="endDate" required></div>
      </div>
      <button class="btn-primary-fc" type="submit">Add / Renew Membership</button>
    </form>
    <div class="table-responsive"><table class="data-table"><thead><tr><th>Plan</th><th>Start</th><th>Expiry</th><th>Status</th><th>Action</th></tr></thead><tbody>
      <c:forEach var="mh" items="${membershipHistory}"><tr><td>${mh.planName}</td><td>${mh.startDate}</td><td>${mh.endDate}</td><td>${mh.status}</td><td>
        <c:if test="${mh.status != 'NOT_RENEWED'}"><form method="post" action="${pageContext.request.contextPath}/admin/members" style="display:inline"><input type="hidden" name="action" value="notRenewed"><input type="hidden" name="memberId" value="${member.id}"><input type="hidden" name="membershipId" value="${mh.id}"><button type="submit" class="btn-secondary-fc">Mark Not Renewed</button></form></c:if>
      </td></tr></c:forEach>
      <c:if test="${empty membershipHistory}"><tr><td colspan="5">No membership history recorded yet.</td></tr></c:if>
    </tbody></table></div>
  </div>
</div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</body>
</html>
