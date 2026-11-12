-- Set 2: Bank Branch Accounts
CREATE DATABASE IF NOT EXISTS bank_accounts;
USE bank_accounts;

CREATE TABLE Customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    address VARCHAR(200) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE Branch (
    branch_id INT AUTO_INCREMENT PRIMARY KEY,
    branch_code VARCHAR(10) NOT NULL UNIQUE,
    branch_name VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE RelationshipManager (
    manager_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_code VARCHAR(10) NOT NULL UNIQUE,
    manager_name VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE Account (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(20) NOT NULL UNIQUE,
    account_type VARCHAR(20) NOT NULL,
    branch_id INT NOT NULL,
    manager_id INT NOT NULL,
    balance DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    opened_date DATE NOT NULL,
    CONSTRAINT chk_balance CHECK (balance >= 0),
    CONSTRAINT chk_account_type
        CHECK (account_type IN ('SAVINGS','CURRENT','SALARY')),
    FOREIGN KEY (branch_id) REFERENCES Branch(branch_id),
    FOREIGN KEY (manager_id) REFERENCES RelationshipManager(manager_id)
) ENGINE=InnoDB;

-- The two columns together are a composite primary key.
-- This table also allows joint account holders.
CREATE TABLE AccountHolder (
    account_id INT NOT NULL,
    customer_id INT NOT NULL,
    PRIMARY KEY (account_id, customer_id),
    FOREIGN KEY (account_id) REFERENCES Account(account_id),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
) ENGINE=InnoDB;
