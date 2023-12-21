CREATE TABLE usuario(

    id    TEXT PRIMARY KEY ,
    nome  TEXT NOT NULL ,
    email TEXT NOT NULL UNIQUE ,
    senha TEXT NOT NULL

);