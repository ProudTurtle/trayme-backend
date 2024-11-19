-- Flyway migration to update missing descriptions in the module table
UPDATE module
SET description = CASE
    WHEN module = 'notes' THEN 'Record your thoughts, ideas, and important stuff'
    WHEN module = 'shopping-list' THEN 'Organize shopping lists to remember everything'
    WHEN module = 'fees' THEN 'Manage your bills and keep track of payments'
    WHEN module = 'birthdays' THEN 'Record your loved ones'' birthdays and receive reminders'
    WHEN module = 'recommendations' THEN 'Store recommendations from friends'
    WHEN module = 'class-schedule' THEN 'Have your own or your loved ones'' lesson plan at hand'
    ELSE description
END
WHERE description IS NULL OR description = '';

-- Flyway migration to change share_key column to nullable
ALTER TABLE space
MODIFY COLUMN share_key VARCHAR(6) NULL;

-- Flyway migration to add shareKeyExpiredAt column
ALTER TABLE space
ADD COLUMN share_Key_Expired_At DATETIME NULL;
