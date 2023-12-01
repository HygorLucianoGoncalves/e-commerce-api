CREATE TABLE detalhesPedido (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                quantidade INT,
                                precoUnitario INT,
                                produto BIGINT,
                                pedido BIGINT,
                                FOREIGN KEY (produto) REFERENCES produto(id),
                                FOREIGN KEY (pedido) REFERENCES pedido(id)
);
