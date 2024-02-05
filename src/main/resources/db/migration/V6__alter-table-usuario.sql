-- Adicionar uma nova coluna
ALTER TABLE usuario
    ADD COLUMN sobrenome VARCHAR(255) NOT NULL  ;

-- Renomear a coluna
ALTER TABLE usuario
    RENAME COLUMN nome TO primeiro_nome;
