drop table if exists products cascade;
create table products (id bigserial, title varchar(255), price int, primary key (id));

create table users (
  id                    bigserial,
  username              varchar(30) not null unique,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into products (title, price)
values
('Хлеб', 40),
('Молоко', 80),
('Масло', 120),
('Сливки', 85),
('Сметана', 80),
('Кефир', 75),
('Йогурт', 50),
('Сыр', 130),
('Творог', 75),
('Колбаса', 200),
('Сосиски', 150),
('Рыба', 300),
('Конфеты', 100),
('Печенье', 50),
('Варенье', 180),
('Джем', 190),
('Яблоки', 80),
('Груши', 85),
('Апельсины', 120),
('Виноград', 100);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_MANAGER'), ('ROLE_ADMIN'), ('ROLE_SUPERADMIN');

insert into users (username, password, email)
values
('user', '$2a$12$TK5JzECdm04yRJ1Gtg5RW.ptVT1FMCqLywGBsbw97AEJHxRGwPJXK', 'user@gmail.com'),
('manager', '$2a$12$TK5JzECdm04yRJ1Gtg5RW.ptVT1FMCqLywGBsbw97AEJHxRGwPJXK', 'manager@gmail.com'),
('admin', '$2a$12$TK5JzECdm04yRJ1Gtg5RW.ptVT1FMCqLywGBsbw97AEJHxRGwPJXK', 'admin@gmail.com'),
('superadmin', '$2a$12$TK5JzECdm04yRJ1Gtg5RW.ptVT1FMCqLywGBsbw97AEJHxRGwPJXK', 'superadmin@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3),
(4, 1),
(4, 2),
(4, 3),
(4, 4);


