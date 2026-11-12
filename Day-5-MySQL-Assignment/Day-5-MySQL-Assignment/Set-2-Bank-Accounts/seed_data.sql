-- Set 2: 10 sample rows in each table
USE bank_accounts;

INSERT INTO Customer (customer_name,address,email) VALUES
('Arun Kumar','Karur','arun@example.com'),
('Bala Devi','Trichy','bala@example.com'),
('Charan Raj','Coimbatore','charan@example.com'),
('Divya S','Salem','divya@example.com'),
('Ezhil M','Madurai','ezhil@example.com'),
('Farah A','Chennai','farah@example.com'),
('Gokul R','Erode','gokul@example.com'),
('Harini P','Karur','harini@example.com'),
('Imran K','Trichy','imran@example.com'),
('Janani V','Salem','janani@example.com');

INSERT INTO Branch (branch_code,branch_name) VALUES
('BR001','Karur Main'),('BR002','Trichy Main'),('BR003','Coimbatore Main'),
('BR004','Salem Main'),('BR005','Madurai Main'),('BR006','Chennai Main'),
('BR007','Erode Main'),('BR008','Karur East'),('BR009','Trichy West'),
('BR010','Salem North');

INSERT INTO RelationshipManager (employee_code,manager_name) VALUES
('EMP001','Manager One'),('EMP002','Manager Two'),('EMP003','Manager Three'),
('EMP004','Manager Four'),('EMP005','Manager Five'),('EMP006','Manager Six'),
('EMP007','Manager Seven'),('EMP008','Manager Eight'),('EMP009','Manager Nine'),
('EMP010','Manager Ten');

INSERT INTO Account
(account_number,account_type,branch_id,manager_id,balance,opened_date) VALUES
('AC1001','SAVINGS',1,1,15000.00,'2026-08-05'),
('AC1002','CURRENT',2,2,25000.00,'2026-08-12'),
('AC1003','SALARY',3,3,18000.00,'2026-09-01'),
('AC1004','SAVINGS',4,4,9000.00,'2026-09-10'),
('AC1005','CURRENT',5,5,50000.00,'2026-09-15'),
('AC1006','SALARY',6,6,22000.00,'2026-10-02'),
('AC1007','SAVINGS',7,7,12000.00,'2026-10-08'),
('AC1008','CURRENT',8,8,35000.00,'2026-10-12'),
('AC1009','SAVINGS',9,9,8000.00,'2026-10-20'),
('AC1010','SALARY',10,10,30000.00,'2026-10-25');

INSERT INTO AccountHolder (account_id,customer_id) VALUES
(1,1),(2,2),(3,3),(4,4),(5,5),
(6,6),(7,7),(8,8),(9,9),(10,10);
