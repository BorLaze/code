insert into person(id, name)
values (1, 'Adam'),
       (2, 'Eva'),
       (3, 'Lilith')
;

alter sequence person_id_seq restart with 4;