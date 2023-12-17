CREATE TABLE pedido(
    id SERIAL primary key,
    data_pedido DATE,
    status TEXT,
    total double precision,
    usuario TEXT,

    foreign key (usuario) references usuario(id)
);