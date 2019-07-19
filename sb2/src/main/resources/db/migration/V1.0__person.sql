create table person (
    id   serial       not null
        constraint person_pkey primary key,
    name varchar(250) not null
);