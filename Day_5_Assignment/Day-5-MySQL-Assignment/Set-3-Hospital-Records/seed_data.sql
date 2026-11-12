-- Set 3: 10 sample rows in each table
USE hospital_records;

INSERT INTO Department VALUES
(1,'Cardiology'),(2,'Neurology'),(3,'Orthopaedics'),(4,'Paediatrics'),
(5,'Dermatology'),(6,'ENT'),(7,'General Medicine'),(8,'Ophthalmology'),
(9,'Dental'),(10,'Urology');

INSERT INTO Patient VALUES
(1,'Arun','Karur'),(2,'Banu','Trichy'),(3,'Charan','Salem'),
(4,'Deepa','Erode'),(5,'Elango','Madurai'),(6,'Farah','Chennai'),
(7,'Guna','Karur'),(8,'Harini','Coimbatore'),(9,'Imran','Trichy'),
(10,'Janani','Salem');

INSERT INTO Doctor VALUES
(1,'Dr. Kumar',1),(2,'Dr. Priya',2),(3,'Dr. Ravi',3),
(4,'Dr. Meena',4),(5,'Dr. Suresh',5),(6,'Dr. Nisha',6),
(7,'Dr. Anand',7),(8,'Dr. Lakshmi',8),(9,'Dr. Karthik',9),
(10,'Dr. Divya',10);

INSERT INTO Visit
(visit_id,patient_id,doctor_id,visit_date,diagnosis,prescription,visit_status) VALUES
(1,1,1,'2026-10-02','Chest pain','Medicine A','OPEN'),
(2,2,2,'2026-10-04','Headache','Medicine B','CLOSED'),
(3,3,3,'2026-10-06','Leg pain','Medicine C','OPEN'),
(4,4,4,'2026-10-08','Fever','Medicine D','CLOSED'),
(5,5,5,'2026-10-10','Skin allergy','Cream A','OPEN'),
(6,6,6,'2026-10-12','Ear pain','Drops A','CLOSED'),
(7,7,7,'2026-10-14','Cold','Medicine E','OPEN'),
(8,8,8,'2026-10-16','Eye irritation','Drops B','CLOSED'),
(9,9,9,'2026-10-18','Tooth pain','Medicine F','OPEN'),
(10,10,10,'2026-10-20','Back pain','Medicine G','CLOSED');

INSERT INTO Billing VALUES
(1,1,500.00),(2,2,650.00),(3,3,700.00),(4,4,300.00),
(5,5,450.00),(6,6,250.00),(7,7,350.00),(8,8,400.00),
(9,9,550.00),(10,10,800.00);
