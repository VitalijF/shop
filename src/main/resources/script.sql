DROP DATABASE web_project;

CREATE DATABASE IF NOT EXISTS web_project;
USE web_project;

CREATE TABLE IF NOT EXISTS user (
	id INT PRIMARY KEY,
	email VARCHAR(45) NOT NULL,
    password VARCHAR(15) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50),
	role ENUM('USER', 'ADMIN') DEFAULT 'USER'
);

CREATE TABLE IF NOT EXISTS bucket (
	id INT PRIMARY KEY,
	created_date TIMESTAMP,
	FOREIGN KEY(id) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS product (
	id INT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	description VARCHAR(255) DEFAULT NULL,
	price DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS bucket_product(
	bucket_id INT NOT NULL,
	product_id INT NOT NULL,
    CONSTRAINT id PRIMARY KEY (bucket_id, product_id),
    FOREIGN KEY(bucket_id) REFERENCES bucket(id),
    FOREIGN KEY(product_id) REFERENCES product(id)
);
