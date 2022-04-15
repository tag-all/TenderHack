create sequence customer_seq start 1;
create table customer
(
    id                int primary key,
    email             varchar(128) not null,
    password          varchar(255) not null,
    company_name      varchar(255),
    access_key        varchar(255),
    notification_time time
);

create sequence token_seq start 1;
create table token
(
    id          int primary key,
    customer_id int references customer (id),
    token       text not null
);

create sequence bot_settings_seq start 1;
create table bot_settings
(
    id          int primary key,
    customer_id int references customer (id),
    session_id  int  not null,
    priority    int  not null,
    time_delay  time not null,
    step        real not null,
    min_payment real not null,
    status_work boolean default false
);

create sequence message_seq start 1;
create table message
(
    id           int primary key,
    type         varchar(16) not null,
    message_text text
);

create sequence notification_seq start 1;
create table notification
(
    id          int primary key,
    customer_id int references customer (id),
    message_id  int references message (id),
    read_status boolean default false
);