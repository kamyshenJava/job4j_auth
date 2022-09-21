create table person (
    id serial primary key not null,
    username text unique not null,
    password text
);

insert into person (username, password) values ('admin', '$2a$10$kGOd/DVU6KIlWJhcIFvRZO7yPOV.0Btj/Y2.R8l/epGtrQOHJEo4q');