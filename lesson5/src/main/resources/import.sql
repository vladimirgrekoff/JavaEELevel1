DROP TABLE products IF EXISTS;
CREATE TABLE IF NOT EXISTS products (id bigserial, title VARCHAR(255), cost int, PRIMARY KEY(id));
INSERT INTO products (title, cost) VALUES ('Хлеб', 45), ('Масло', 140), ('Сыр', 100), ('Мандарины', 130), ('Яблоки', 150);



