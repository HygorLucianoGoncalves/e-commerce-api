package com.hygorluciano.lojaderoupa.domain.model.enums;

public enum Cargo {

    ADMIN("admin"),
    USER("user");

    private String cargo;

    Cargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo(){
        return cargo;
    }
}
