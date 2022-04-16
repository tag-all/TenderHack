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

create sequence status_session_seq start 1;
create table status_session
(
    id          int primary key,
    customer_id int references customer (id),
    session_id  int         not null,
    status      varchar(16) not null,
    operating_mode boolean default false,
);

create sequence bot_settings_seq start 1;
create table bot_settings
(
    id          int primary key,
    customer_id int references customer (id),
    status_session_id int references status_session (id),
    priority    int  not null,
    time_delay  time not null,
    time_start  time,
    step        real not null,
    min_payment real not null
);

create sequence message_seq start 1;
create table message
(
    id           int primary key,
    type         varchar(16) not null,
    name         varchar(64) not null,
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