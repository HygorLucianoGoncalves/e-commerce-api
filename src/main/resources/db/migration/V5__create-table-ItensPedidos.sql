CREATE TABLE itens_pedidos (
                                id SERIAL PRIMARY KEY,
                                quantidade INT,
                                preco_unitario double precision,
                                sub_total double precision,
                                produto INT,
                                pedido INT,

                                FOREIGN KEY (produto) REFERENCES produto(id),
                                FOREIGN KEY (pedido) REFERENCES pedido(id)
);
