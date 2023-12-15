CREATE TABLE produto(
    id INT PRIMARY KEY,
    nome TEXT NOT NULL ,
    categoria_id INT,
    imagens TEXT,
    valor DOUBLE PRECISION,
    estoque INT NOT NULL,
    unique (nome),

    foreign key (categoria_id) REFERENCES categoria(id)


);