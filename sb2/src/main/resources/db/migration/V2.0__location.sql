create table location (
    id        serial       not null
        constraint location_pkey primary key,
    zip       bigint,
    city      varchar(250) not null,
    person_fk serial
        constraint person_fk references person
);