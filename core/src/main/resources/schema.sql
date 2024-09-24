create table if not exists users
(
    ID      integer not null primary key,
    balance integer,
    name    varchar(255)
);