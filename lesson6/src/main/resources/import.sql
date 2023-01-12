DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial, title VARCHAR(255), price INT, PRIMARY KEY (id));
INSERT INTO products (title, price) VALUES ('Хлеб', 40),('Масло', 150),('Сыр', 130), ('Мандарины', 100),('Яблоки', 90);

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers (name) VALUES ('Сергей'),('Семен'),('Валерий'),('Савелий');

DROP TABLE IF EXISTS customers_products CASCADE;
CREATE TABLE customers_products (customer_id bigint, product_id bigint, FOREIGN KEY (product_id) REFERENCES products (id), FOREIGN KEY (customer_id) REFERENCES customers (id));
INSERT INTO customers_products (customer_id, product_id) VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (2, 1), (2, 3), (3, 3), (3, 1), (3, 5), (4, 1), (4, 3), (4, 5);