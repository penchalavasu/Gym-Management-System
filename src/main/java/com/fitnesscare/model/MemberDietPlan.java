package com.fitnesscare.model;

import java.sql.Timestamp;

public class MemberDietPlan {
    private long id;
    private String memberId;
    private long dietPlanId;
    private Timestamp assignedAt;

    // Joined fields for display
    private String memberName;
    private String planName;

    public MemberDietPlan() {}

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public long getDietPlanId() { return dietPlanId; }
    public void setDietPlanId(long dietPlanId) { this.dietPlanId = dietPlanId; }
    public Timestamp getAssignedAt() { return assignedAt; }
    public void setAssignedAt(Timestamp assignedAt) { this.assignedAt = assignedAt; }
    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }
    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }
}
