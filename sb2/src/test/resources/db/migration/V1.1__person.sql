insert into person(id, name)
values (1, 'Adam'),
       (2, 'Eva'),
       (3, 'Lilith')
;

alter table person
    alter column id restart with 4;