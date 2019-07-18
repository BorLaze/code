insert into location(id, zip, city, person_fk)
values (1, 1, 'Atlanta', 1),
       (2, 2, 'Evansville', 2),
       (3, 3, 'London', 3),
       (4, 4, 'Liverpool', 3)
;

alter table location
    alter column id restart with 5;