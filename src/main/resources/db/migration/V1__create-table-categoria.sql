CREATE TABLE categoria(
    id SERIAL primary key,
    nomeCategoria TEXT not null unique
);