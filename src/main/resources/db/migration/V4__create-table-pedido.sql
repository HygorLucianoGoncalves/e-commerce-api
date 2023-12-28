CREATE TABLE pedido(
    id SERIAL PRIMARY KEY ,
    data_pedido TIMESTAMP,
    status TEXT,
    total double precision,
    usuario TEXT,

    foreign key (usuario) references usuario(id)
);