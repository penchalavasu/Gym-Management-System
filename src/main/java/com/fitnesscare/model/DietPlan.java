package com.fitnesscare.model;

import java.sql.Timestamp;

public class DietPlan {
    private long id;
    private String planName;
    private String goal;
    private String breakfast;
    private String midMorningMeal;
    private String lunch;
    private String eveningSnack;
    private String dinner;
    private String additionalInstructions;
    private String createdBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public DietPlan() {}

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }
    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }
    public String getBreakfast() { return breakfast; }
    public void setBreakfast(String breakfast) { this.breakfast = breakfast; }
    public String getMidMorningMeal() { return midMorningMeal; }
    public void setMidMorningMeal(String midMorningMeal) { this.midMorningMeal = midMorningMeal; }
    public String getLunch() { return lunch; }
    public void setLunch(String lunch) { this.lunch = lunch; }
    public String getEveningSnack() { return eveningSnack; }
    public void setEveningSnack(String eveningSnack) { this.eveningSnack = eveningSnack; }
    public String getDinner() { return dinner; }
    public void setDinner(String dinner) { this.dinner = dinner; }
    public String getAdditionalInstructions() { return additionalInstructions; }
    public void setAdditionalInstructions(String additionalInstructions) { this.additionalInstructions = additionalInstructions; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}
