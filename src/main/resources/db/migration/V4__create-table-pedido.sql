CREATE TABLE pedido(
    id serial primary key,
    dataPedido timestamp,
    status text,
    total double precision,
    usuario text,

    foreign key (usuario) references usuario(id)
);