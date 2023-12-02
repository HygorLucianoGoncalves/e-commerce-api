CREATE TABLE categoria(
    id SERIAL primary key,
    nome_categoria TEXT not null unique
);