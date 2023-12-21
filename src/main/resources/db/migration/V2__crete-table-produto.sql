CREATE TABLE produto(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL ,
    categoria_id INT,
    imagens TEXT,
    valor DOUBLE PRECISION,
    estoque INT NOT NULL,
    unique (nome),

    foreign key (categoria_id) REFERENCES categoria(id)


);