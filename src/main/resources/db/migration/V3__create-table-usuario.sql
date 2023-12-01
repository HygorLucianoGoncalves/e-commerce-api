CREATE TABLE usuario(
    id text primary key,
    nome TEXT not null,
    email TEXT not null unique,
    senha TEXT not null
);