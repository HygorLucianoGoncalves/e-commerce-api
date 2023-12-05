CREATE TABLE pedido(
    id serial primary key,
    data_pedido DATE,
    status text,
    total double precision,
    usuario text,

    foreign key (usuario) references usuario(id)
);