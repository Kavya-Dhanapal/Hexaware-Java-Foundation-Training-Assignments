-- Set 2: DML, operators, ALTER and table commands
USE bank_accounts;

INSERT INTO Customer (customer_name,address,email)
VALUES ('Kiran Test','Namakkal','kiran@example.com');

UPDATE Customer SET address = 'Karur' WHERE customer_id = 11;
DELETE FROM Customer WHERE customer_id = 11;

-- AND: both conditions must be true.
SELECT * FROM Account
WHERE balance > 10000 AND account_type = 'SAVINGS';

-- OR: at least one condition must be true.
SELECT * FROM Account
WHERE account_type = 'CURRENT' OR account_type = 'SALARY';

-- IN: match one of the listed values.
SELECT * FROM Account
WHERE account_type IN ('SAVINGS','SALARY');

-- BETWEEN: includes both boundary values.
SELECT * FROM Account
WHERE balance BETWEEN 10000 AND 30000;

-- LIKE: names starting with A.
SELECT * FROM Customer
WHERE customer_name LIKE 'A%';

-- Accounts opened in the last quarter (July-September 2026)
-- with balance greater than 10,000.
SELECT account_number,account_type,balance,opened_date
FROM Account
WHERE opened_date >= '2026-07-01'
AND opened_date < '2026-10-01'
AND balance > 10000;

-- Add a column. Existing records receive the default value.
ALTER TABLE Account
ADD COLUMN kyc_status VARCHAR(20) NOT NULL DEFAULT 'PENDING';

-- Rename the table.
RENAME TABLE Account TO BankAccount;

-- Remove the named CHECK constraint.
ALTER TABLE BankAccount DROP CHECK chk_account_type;
DESCRIBE BankAccount;

-- DROP, TRUNCATE and DELETE on a practice table.
CREATE TABLE AutoIncrementTest (
    id INT AUTO_INCREMENT PRIMARY KEY,
    note VARCHAR(30)
) ENGINE=InnoDB;

INSERT INTO AutoIncrementTest (note) VALUES ('First'),('Second');
SELECT * FROM AutoIncrementTest;

-- DELETE can be rolled back inside a transaction.
START TRANSACTION;
DELETE FROM AutoIncrementTest;
SELECT * FROM AutoIncrementTest;
ROLLBACK;
SELECT * FROM AutoIncrementTest;

-- DELETE normally does not reset the AUTO_INCREMENT counter.
INSERT INTO AutoIncrementTest (note) VALUES ('After delete');
SELECT * FROM AutoIncrementTest;

-- TRUNCATE removes every row, resets the counter and causes implicit commit.
TRUNCATE TABLE AutoIncrementTest;
INSERT INTO AutoIncrementTest (note) VALUES ('After truncate');
SELECT * FROM AutoIncrementTest;

-- DROP removes the table structure and its data.
DROP TABLE AutoIncrementTest;

-- Summary:
-- DELETE: can use WHERE; transactional rollback is possible; counter not reset.
-- TRUNCATE: no WHERE; removes all rows; implicit commit; counter reset.
-- DROP: removes the complete table and its data; cannot be rolled back.
