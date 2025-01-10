create table patient(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    phone varchar(20) not null,
    id_number varchar(10) not null unique,
    city varchar(100) not null,
    district varchar(100) not null,
    street varchar(100) not null,
    number varchar(20),
    complement varchar(100),
    active tinyint not null,

    primary key(id)

);