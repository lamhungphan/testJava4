DROP database IF EXISTS test_java4;
create database test_java4;
use test_java4;

DROP TABLE IF EXISTS category;
CREATE TABLE category (
    id VARCHAR(45) PRIMARY KEY,
    name VARCHAR(45) NOT NULL
);

DROP TABLE IF EXISTS product;
CREATE TABLE product (
    id INT PRIMARY KEY,
    name NVARCHAR(45) NOT NULL,
    price FLOAT NOT NULL,
    category_id VARCHAR(45),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

INSERT INTO category (id, name) VALUES
('cat01', 'Dien tu'),
('cat02', 'Thuc pham'),
('cat03', 'Do uong'),
('cat04', 'Van phong pham');

INSERT INTO product (id, name, price, category_id) VALUES
(1, 'Laptop', 350, 'cat01'),
(2, 'Iphone', 1350, 'cat01'),
(3, 'Tivi', 120, 'cat01'),
(5, 'Bia Tiger', 20000, 'cat03'),
(6, 'Pepsi', 5000, 'cat03');
