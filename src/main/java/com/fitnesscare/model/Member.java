package com.fitnesscare.model;

import java.sql.Timestamp;

public class Member {
    private String id;
    private String name;
    private String password;
    private String gender;
    private String program;
    private String fitnessGoal;
    private int age;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Member() {}

    public Member(String id, String name, String password, String gender,
                  String program, String fitnessGoal, int age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.program = program;
        this.fitnessGoal = fitnessGoal;
        this.age = age;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getProgram() { return program; }
    public void setProgram(String program) { this.program = program; }
    public String getFitnessGoal() { return fitnessGoal; }
    public void setFitnessGoal(String fitnessGoal) { this.fitnessGoal = fitnessGoal; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}
