-- Set 1: Sample data (10 rows in each table)
USE retail_inventory;

INSERT INTO Supplier VALUES
(1,'ABC Suppliers','9000000001'),(2,'Bright Traders','9000000002'),
(3,'City Wholesale','9000000003'),(4,'Delta Distributors','9000000004'),
(5,'Evergreen Supply','9000000005'),(6,'First Choice','9000000006'),
(7,'Global Goods','9000000007'),(8,'Horizon Traders','9000000008'),
(9,'India Supplies','9000000009'),(10,'Jaya Distributors','9000000010');

INSERT INTO Category VALUES
(1,'Electronics'),(2,'Clothing'),(3,'Books'),(4,'Furniture'),(5,'Groceries'),
(6,'Sports'),(7,'Kitchen'),(8,'Beauty'),(9,'Toys'),(10,'Stationery');

INSERT INTO Warehouse VALUES
(1,'Central Warehouse'),(2,'North Warehouse'),(3,'South Warehouse'),
(4,'East Warehouse'),(5,'West Warehouse'),(6,'City Warehouse'),
(7,'Market Warehouse'),(8,'Airport Warehouse'),(9,'Rural Warehouse'),
(10,'Backup Warehouse');

INSERT INTO Product VALUES
('SKU001','Keyboard',1,1),('SKU002','T Shirt',2,2),
('SKU003','Java Book',3,3),('SKU004','Office Chair',4,4),
('SKU005','Rice Bag',5,5),('SKU006','Football',6,6),
('SKU007','Mixer',7,7),('SKU008','Face Wash',8,8),
('SKU009','Toy Car',9,9),('SKU010','Notebook',10,10);

INSERT INTO Inventory VALUES
(1,'SKU001',1,50,10),(2,'SKU002',2,40,8),(3,'SKU003',3,60,10),
(4,'SKU004',4,15,5),(5,'SKU005',5,100,20),(6,'SKU006',6,25,5),
(7,'SKU007',7,18,5),(8,'SKU008',8,30,8),(9,'SKU009',9,45,10),
(10,'SKU010',10,70,15);
