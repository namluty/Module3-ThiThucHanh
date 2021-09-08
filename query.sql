create database product_manager;
use product_manager;

create table product(
    id int auto_increment primary key ,
    name varchar(255) not null ,
    price int not null ,
    quantity int not null ,
    color varchar(255) not null ,
    description varchar(255) not null
);

create table category(
    id_cate int auto_increment primary key ,
    name varchar(255) not null,
    description varchar(255) not null,
    id_product int not null,
    foreign key (id_product) references product(id)
);

create table product_category(
    id_pc int not null auto_increment primary key,
    id_product int not null,
    id_category int not null,
    foreign key (id_category) references category(id_cate),
    foreign key (id_product) references product (id)
);
select * from category join product_category pc on category.id_cate = pc.id_category and pc.id_product;
select * from product join category c on product.id = c.id_product where id_product;
select * from category join product_category pc on category.id_cate = pc.id_category where id_cate;
select * from category join product p on category.id_product = p.id where id_cate;