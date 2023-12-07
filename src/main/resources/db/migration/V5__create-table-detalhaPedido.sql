CREATE TABLE detalhes_pedido (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                quantidade INT,
                                preco_unitario double precision,
                                produto INT,
                                pedido INT,

                                FOREIGN KEY (produto) REFERENCES produto(id),
                                FOREIGN KEY (pedido) REFERENCES pedido(id)
);
