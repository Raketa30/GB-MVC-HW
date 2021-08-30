CREATE TABLE IF NOT EXISTS category
(
    id    serial primary key not null,
    title text               not null
);

insert into category(title)
values ('food'),
       ('electronics'),
       ('test');

ALTER TABLE IF EXISTS product
    add column category_id int;

update product
set category_id = 3
where title like '%test%';

ALTER TABLE IF EXISTS product
    add constraint category_product_fk
        foreign key (category_id) references category;



