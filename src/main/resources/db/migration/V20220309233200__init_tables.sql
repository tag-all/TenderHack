create sequence customer_seq start 1;
create table customer
(
    id              int primary key,
    email           varchar(128) not null,
    email_confirmed bool default false,
    password        varchar(255) not null,
    name            varchar(255) not null,
    last_name       varchar(255) not null,
    patronymic      varchar(255),
    phone           varchar(10),
    code_region     varchar(3),
    role            varchar(255) not null
);

create sequence token_seq start 1;
create table token
(
    id          int primary key,
    customer_id int references customer (id),
    token       text not null
);