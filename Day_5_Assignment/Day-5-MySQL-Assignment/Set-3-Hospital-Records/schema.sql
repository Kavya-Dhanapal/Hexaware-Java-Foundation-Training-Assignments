-- Set 3: Hospital Patient Records
CREATE DATABASE IF NOT EXISTS hospital_records;
USE hospital_records;

CREATE TABLE Department (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE Patient (
    patient_id INT PRIMARY KEY,
    patient_name VARCHAR(100) NOT NULL,
    address VARCHAR(200) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE Doctor (
    doctor_id INT PRIMARY KEY,
    doctor_name VARCHAR(100) NOT NULL,
    department_id INT NOT NULL,
    FOREIGN KEY (department_id) REFERENCES Department(department_id)
) ENGINE=InnoDB;

CREATE TABLE Visit (
    visit_id INT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    visit_date DATE NOT NULL,
    diagnosis VARCHAR(200) NOT NULL,
    prescription VARCHAR(200),
    visit_status VARCHAR(20) NOT NULL DEFAULT 'OPEN',
    CONSTRAINT chk_visit_date CHECK (visit_date >= '2000-01-01'),
    CONSTRAINT chk_visit_status
        CHECK (visit_status IN ('OPEN','CLOSED','CANCELLED')),
    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id)
) ENGINE=InnoDB;

CREATE TABLE Billing (
    billing_id INT PRIMARY KEY,
    visit_id INT NOT NULL UNIQUE,
    bill_amount DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    CONSTRAINT chk_bill_amount CHECK (bill_amount >= 0),
    FOREIGN KEY (visit_id) REFERENCES Visit(visit_id)
) ENGINE=InnoDB;
