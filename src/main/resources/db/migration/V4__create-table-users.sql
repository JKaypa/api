create table users(
    id bigint not null auto_increment,
    username varchar(250) not null,
    password varchar(500) not null unique,

    primary key(id)
);