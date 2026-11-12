-- Set 1: Retail Inventory Management
CREATE DATABASE IF NOT EXISTS retail_inventory;
USE retail_inventory;

CREATE TABLE Supplier (
    supplier_id INT PRIMARY KEY,
    supplier_name VARCHAR(100) NOT NULL,
    supplier_phone VARCHAR(15) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE Category (
    category_id INT PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE Warehouse (
    warehouse_id INT PRIMARY KEY,
    warehouse_name VARCHAR(100) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE Product (
    sku VARCHAR(20) PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    category_id INT NOT NULL,
    supplier_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES Category(category_id)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (supplier_id) REFERENCES Supplier(supplier_id)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE Inventory (
    inventory_id INT PRIMARY KEY,
    sku VARCHAR(20) NOT NULL,
    warehouse_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    reorder_level INT NOT NULL DEFAULT 5,
    CONSTRAINT chk_quantity CHECK (quantity >= 0),
    CONSTRAINT chk_reorder CHECK (reorder_level >= 0),
    CONSTRAINT uq_sku_warehouse UNIQUE (sku, warehouse_id),
    FOREIGN KEY (sku) REFERENCES Product(sku)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (warehouse_id) REFERENCES Warehouse(warehouse_id)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;
