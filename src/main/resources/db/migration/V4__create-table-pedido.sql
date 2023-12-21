CREATE TABLE pedido(
    id INT PRIMARY KEY ,
    data_pedido TIMESTAMP,
    status TEXT,
    total double precision,
    usuario TEXT,

    foreign key (usuario) references usuario(id)
);