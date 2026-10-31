-- Run ONLY if you already installed the previous membership_history migration.
-- Protect historical membership records from deletion through user_gym.
USE fitnesscare;
SET @fk := (SELECT CONSTRAINT_NAME FROM information_schema.KEY_COLUMN_USAGE
 WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='membership_history'
 AND COLUMN_NAME='member_id' AND REFERENCED_TABLE_NAME='user_gym' LIMIT 1);
SET @stmt := IF(@fk IS NULL, 'SELECT 1',
 CONCAT('ALTER TABLE membership_history DROP FOREIGN KEY `', REPLACE(@fk, '`','``'), '`'));
PREPARE stmt FROM @stmt; EXECUTE stmt; DEALLOCATE PREPARE stmt;
ALTER TABLE membership_history ADD CONSTRAINT fk_membership_member_protected
 FOREIGN KEY (member_id) REFERENCES user_gym(id) ON DELETE RESTRICT;
