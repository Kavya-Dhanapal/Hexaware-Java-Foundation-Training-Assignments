-- Set 3: DML, retrieval and database commands
USE hospital_records;

INSERT INTO Patient VALUES (11,'Kavin','Namakkal');
UPDATE Patient SET address = 'Karur' WHERE patient_id = 11;
DELETE FROM Patient WHERE patient_id = 11;

-- Patients who visited in October 2026.
SELECT p.patient_id,p.patient_name,v.visit_date,v.diagnosis
FROM Patient p
JOIN Visit v ON p.patient_id = v.patient_id
WHERE v.visit_date >= '2026-10-01'
AND v.visit_date < '2026-11-01';

-- Doctors and their departments.
SELECT d.doctor_name,dep.department_name
FROM Doctor d
JOIN Department dep ON d.department_id = dep.department_id
ORDER BY dep.department_name;

-- Visit and billing details.
SELECT v.visit_id,p.patient_name,v.visit_date,v.visit_status,b.bill_amount
FROM Visit v
JOIN Patient p ON v.patient_id = p.patient_id
LEFT JOIN Billing b ON v.visit_id = b.visit_id;

-- Rename a table and then rename it back.
RENAME TABLE Patient TO HospitalPatient;
DESCRIBE HospitalPatient;
RENAME TABLE HospitalPatient TO Patient;

-- Create a separate practice database.
CREATE DATABASE IF NOT EXISTS hospital_practice;
SHOW DATABASES;

-- WARNING: dropping a database removes its tables and stored data.
DROP DATABASE hospital_practice;
CREATE DATABASE hospital_practice;
SHOW DATABASES;

-- MySQL manages database files in its own data directory.
-- The exact physical file handling depends on server settings and operating system.
