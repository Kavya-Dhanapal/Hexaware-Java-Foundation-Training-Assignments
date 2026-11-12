-- Run these statements ONE AT A TIME. Each should fail.
USE retail_inventory;

-- Duplicate primary key: supplier_id 1 already exists.
INSERT INTO Supplier VALUES (1,'Duplicate Supplier','9111111111');

-- Missing parent: category_id 999 does not exist.
INSERT INTO Product VALUES ('SKU999','Unknown Product',999,1);

-- Negative quantity is blocked by CHECK.
INSERT INTO Inventory VALUES (99,'SKU001',1,-5,2);

-- supplier_name is mandatory (NOT NULL).
INSERT INTO Supplier (supplier_id,supplier_name,supplier_phone)
VALUES (99,NULL,'9222222222');
