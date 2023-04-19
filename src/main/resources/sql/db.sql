create schema mysqldb;

create table member
(
    id       int auto_increment,
    userid   varchar(12) not null,
    password varchar(16) not null,
    email    varchar(30) null,
    name     varchar(15) null,
    constraint member_pk
        primary key (id),
    constraint member_pk2
        unique (userid)
)
    collate = utf8mb4_general_ci;