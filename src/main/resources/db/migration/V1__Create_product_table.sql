create sequence hibernate_sequence start 100 increment 1;
CREATE TABLE IF NOT EXISTS product
(
    id    serial primary key not null,
    title text               not null,
    cost  integer            not null
);
