--liquibase formatted sql
-- changeset galina:1
CREATE TABLE IF NOT EXISTS notification_task(
    Id INT,
    Date DATE,
    Time TIME,
    Text VARCHAR,
    Textmessage VARCHAR
);
DROP TABLE notification_task;
CREATE TABLE IF NOT EXISTS notification(
    Id INT,
    DateTime TIMESTAMP,
    Text VARCHAR,
    Textmessage VARCHAR
);


