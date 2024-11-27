CREATE DATABASE IF NOT EXISTS travelplanner_db;
USE travelplanner_db;
CREATE TABLE IF NOT EXISTS travelplanner_db.users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL
);
INSERT INTO travelplanner_db.users (username, email, password)
SELECT 'adinath', 'adinath@example.com', '$2a$12$B/oozang2D3y2u1qsaaK5OjmG42HRnVCCkVTO4yOO9IXOFICKdUkK'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'adinath@example.com'
);
