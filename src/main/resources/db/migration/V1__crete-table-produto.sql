CREATE TABLE produto(
    id SERIAL PRIMARY KEY,
    nome TEXT NOT NULL,
    imagens TEXT,
    valor DOUBLE PRECISION,
    estoque INT NOT NULL
)