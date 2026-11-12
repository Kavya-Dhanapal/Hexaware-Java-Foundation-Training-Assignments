-- Set 1: UPDATE, DELETE and system database exploration
USE retail_inventory;

-- Phone number before update
SELECT * FROM Supplier WHERE supplier_id = 2;

UPDATE Supplier
SET supplier_phone = '9888888882'
WHERE supplier_id = 2;

-- Phone number after update
SELECT * FROM Supplier WHERE supplier_id = 2;

-- Check discontinued SKU before deletion
SELECT * FROM Product WHERE sku = 'SKU010';

-- Delete its inventory row first because the FK uses ON DELETE RESTRICT.
DELETE FROM Inventory WHERE sku = 'SKU010';
DELETE FROM Product WHERE sku = 'SKU010';

-- Confirm deletion
SELECT * FROM Product WHERE sku = 'SKU010';

-- Explore MySQL system databases
SHOW DATABASES;
SHOW TABLES FROM information_schema;
SHOW TABLES FROM mysql;
SHOW TABLES FROM performance_schema;
SHOW TABLES FROM sys;

-- Table name, storage engine and approximate row count
SELECT TABLE_NAME, ENGINE, TABLE_ROWS
FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'retail_inventory'
AND TABLE_TYPE = 'BASE TABLE';
