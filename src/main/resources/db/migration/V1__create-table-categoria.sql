CREATE TABLE categoria(
    id SERIAL PRIMARY KEY,
    nome_categoria TEXT not null unique
);