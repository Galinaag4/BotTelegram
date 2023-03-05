--liquibase formatted sql
-- changeset galina:1

CREATE TABLE IF NOT EXISTS notificationTask(
   Id INT,
   DateTime TIMESTAMP,
   Text VARCHAR,
   Textmessage VARCHAR
);
ALTER TABLE  notification_task
    ADD COLUMN chatId VARCHAR ;
ALTER TABLE notification_task
    DROP COLUMN chatId;


