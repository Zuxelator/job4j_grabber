CREATE TABLE grabber (
id serial primary key,
name text,
text text,
link text,
created timestamp,
UNIQUE(link)
);