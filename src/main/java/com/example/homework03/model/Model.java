package com.example.homework03.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Model {
    PUNTO("Punto"),
    ASTRA("Astra"),
    GLC("GLC");

    private String model;

    Model(String model) {
        this.model = model;
    }
    @JsonValue
    public String getModel() {
        return model;

    }
}
