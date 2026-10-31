-- ============================================================
-- FitnessCare Database Schema
-- ============================================================

CREATE DATABASE IF NOT EXISTS fitnesscare;
USE fitnesscare;

-- Drop tables in reverse dependency order if they exist
DROP TABLE IF EXISTS membership_history;
DROP TABLE IF EXISTS member_diet_plan;
DROP TABLE IF EXISTS progress_record;
DROP TABLE IF EXISTS complaint_gym;
DROP TABLE IF EXISTS diet_plan;
DROP TABLE IF EXISTS user_gym;
DROP TABLE IF EXISTS managerlogintable_gym;
DROP TABLE IF EXISTS admin_gym;

-- 1. Admin table
CREATE TABLE admin_gym (
    id          VARCHAR(20) PRIMARY KEY,
    password    VARCHAR(255) NOT NULL,
    name        VARCHAR(100),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2. Manager table
CREATE TABLE managerlogintable_gym (
    managerid   VARCHAR(20) PRIMARY KEY,
    password    VARCHAR(255) NOT NULL,
    name        VARCHAR(100) NOT NULL,
    gender      VARCHAR(20),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3. Member table
CREATE TABLE user_gym (
    id           VARCHAR(45) PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    gender       VARCHAR(20) NOT NULL,
    program      VARCHAR(50) NOT NULL,
    fitness_goal VARCHAR(100),
    age          INT,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4. Diet Plan table
CREATE TABLE diet_plan (
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    plan_name               VARCHAR(100) NOT NULL,
    goal                    VARCHAR(100),
    breakfast               TEXT,
    mid_morning_meal        TEXT,
    lunch                   TEXT,
    evening_snack           TEXT,
    dinner                  TEXT,
    additional_instructions TEXT,
    created_by              VARCHAR(45),
    created_at              TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5. Member-Diet Plan assignment (one active plan per member)
CREATE TABLE member_diet_plan (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id    VARCHAR(45) NOT NULL,
    diet_plan_id BIGINT NOT NULL,
    assigned_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES user_gym(id) ON DELETE RESTRICT,
    FOREIGN KEY (diet_plan_id) REFERENCES diet_plan(id) ON DELETE CASCADE,
    UNIQUE KEY uk_member_diet (member_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 6. Progress Record table
CREATE TABLE progress_record (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id   VARCHAR(45) NOT NULL,
    weight_kg   DECIMAL(6,2) NOT NULL,
    height_cm   DECIMAL(6,2) NOT NULL,
    bmi         DECIMAL(5,2) NOT NULL,
    bmi_status  VARCHAR(30) NOT NULL,
    record_date DATE NOT NULL,
    notes       TEXT,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES user_gym(id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 7. Complaint table
CREATE TABLE complaint_gym (
    id          VARCHAR(45) PRIMARY KEY,
    member_id   VARCHAR(45) NOT NULL,
    name        VARCHAR(100) NOT NULL,
    subject     VARCHAR(150) NOT NULL,
    complaint   VARCHAR(1000) NOT NULL,
    status      VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP NULL,
    FOREIGN KEY (member_id) REFERENCES user_gym(id) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 8. Membership history table (retains every membership/renewal period)
CREATE TABLE membership_history (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id   VARCHAR(45) NOT NULL,
    plan_name   VARCHAR(100) NOT NULL,
    start_date  DATE NOT NULL,
    end_date    DATE NOT NULL,
    status      VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES user_gym(id) ON DELETE RESTRICT,
    CONSTRAINT chk_membership_dates CHECK (end_date >= start_date),
    CONSTRAINT chk_membership_status CHECK (status IN ('ACTIVE','EXPIRED','NOT_RENEWED'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ======================== INDEXES ========================

CREATE INDEX idx_progress_member ON progress_record(member_id);
CREATE INDEX idx_progress_date ON progress_record(record_date);
CREATE INDEX idx_complaint_member ON complaint_gym(member_id);
CREATE INDEX idx_complaint_status ON complaint_gym(status);
CREATE INDEX idx_member_diet ON member_diet_plan(member_id);
CREATE INDEX idx_user_program ON user_gym(program);
CREATE INDEX idx_user_gender ON user_gym(gender);

CREATE INDEX idx_membership_member ON membership_history(member_id);
CREATE INDEX idx_membership_end_date ON membership_history(end_date);
CREATE INDEX idx_membership_status ON membership_history(status);
