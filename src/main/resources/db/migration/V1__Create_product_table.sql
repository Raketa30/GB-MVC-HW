create sequence hibernate_sequence start 1 increment 1;
CREATE TABLE IF NOT EXISTS product
(
    id    serial primary key not null,
    title text                not null,
    cost  integer             not null
);

insert into product(title, cost)
values ('test1', 23),
       ('test2', 32);