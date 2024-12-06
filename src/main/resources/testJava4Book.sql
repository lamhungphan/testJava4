-- DROP database IF EXISTS test_java4_book;
-- create database test_java4_book;
use test_java4_book;

DROP TABLE IF EXISTS authors;
CREATE TABLE authors (
    id VARCHAR(45) PRIMARY KEY,
    name NVARCHAR(45) NOT NULL
);

DROP TABLE IF EXISTS books;
CREATE TABLE books (
    id INT PRIMARY KEY,
    title NVARCHAR(45) NOT NULL,
    price FLOAT NOT NULL,
    author_id VARCHAR(45),
    FOREIGN KEY (author_id) REFERENCES authors(id)
);

INSERT INTO authors (id, name) VALUES
('au01', N'Huy cận'),
('au02', N'Nguyễn Bính'),
('au03', N'Nguyễn Nhật Ánh');

INSERT INTO books (id, title, price, author_id) VALUES
(5, N'Đất nở hoa', 23000, 'au01'),
(6, N'Trời mỗi ngày lại sáng', 45000, 'au01'),
(7, N'Vũ trụ ca', 16700, 'au01'),
(8, N'Kính Vạn Hoa', 22000, 'au03');
