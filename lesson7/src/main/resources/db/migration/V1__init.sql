create table categories (id bigserial primary key, title varchar(255));
insert into categories (title)
values
('Food');



create table products (id bigserial primary key, title varchar(255), price int, category_id bigint references categories (id));
insert into products (title, price, category_id)
values
('Хлеб', 40, 1),
('Молоко', 80, 1),
('Масло', 120, 1),
('Сливки', 85, 1),
('Сметана', 80, 1),
('Кефир', 75, 1),
('Йогурт', 50, 1),
('Сыр', 130, 1),
('Творог', 75, 1),
('Колбаса', 200, 1),
('Сосиски', 150, 1),
('Рыба', 300, 1),
('Конфеты', 100, 1),
('Печенье', 50, 1),
('Варенье', 180, 1),
('Джем', 190, 1),
('Яблоки', 80, 1),
('Груши', 85, 1),
('Апельсины', 120, 1),
('Виноград', 100, 1);