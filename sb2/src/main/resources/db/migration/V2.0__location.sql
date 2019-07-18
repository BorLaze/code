create table location
(
    id        identity     not null primary key,
    zip       int,
    city      varchar(250) not null,
    person_fk long,
    foreign key (person_fk) references person (id)
);