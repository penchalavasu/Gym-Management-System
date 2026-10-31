-- Additive migration: membership tracking/history only.
-- Run this on an existing FitnessCare database without dropping any existing tables/data.
USE fitnesscare;

CREATE TABLE IF NOT EXISTS membership_history (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id   VARCHAR(45) NOT NULL,
    plan_name   VARCHAR(100) NOT NULL,
    start_date  DATE NOT NULL,
    end_date    DATE NOT NULL,
    status      VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES user_gym(id) ON DELETE RESTRICT,
    CONSTRAINT chk_membership_dates CHECK (end_date >= start_date),
    CONSTRAINT chk_membership_status CHECK (status IN ('ACTIVE','EXPIRED','NOT_RENEWED')),
    INDEX idx_membership_member (member_id),
    INDEX idx_membership_end_date (end_date),
    INDEX idx_membership_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
