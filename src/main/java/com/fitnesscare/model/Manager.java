package com.fitnesscare.model;

import java.sql.Timestamp;

public class Manager {
    private String managerId;
    private String password;
    private String name;
    private String gender;
    private Timestamp createdAt;

    public Manager() {}

    public Manager(String managerId, String password, String name, String gender) {
        this.managerId = managerId;
        this.password = password;
        this.name = name;
        this.gender = gender;
    }

    public String getManagerId() { return managerId; }
    public void setManagerId(String managerId) { this.managerId = managerId; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
