package com.fitnesscare.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class ProgressRecord {
    private long id;
    private String memberId;
    private BigDecimal weightKg;
    private BigDecimal heightCm;
    private BigDecimal bmi;
    private String bmiStatus;
    private Date recordDate;
    private String notes;
    private Timestamp createdAt;

    // Joined field for display
    private String memberName;

    public ProgressRecord() {}

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public BigDecimal getWeightKg() { return weightKg; }
    public void setWeightKg(BigDecimal weightKg) { this.weightKg = weightKg; }
    public BigDecimal getHeightCm() { return heightCm; }
    public void setHeightCm(BigDecimal heightCm) { this.heightCm = heightCm; }
    public BigDecimal getBmi() { return bmi; }
    public void setBmi(BigDecimal bmi) { this.bmi = bmi; }
    public String getBmiStatus() { return bmiStatus; }
    public void setBmiStatus(String bmiStatus) { this.bmiStatus = bmiStatus; }
    public Date getRecordDate() { return recordDate; }
    public void setRecordDate(Date recordDate) { this.recordDate = recordDate; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }
}
