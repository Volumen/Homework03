package com.example.homework03.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Collectors;

public enum Color {
    GREEN("green"),
    BLACK("black"),
    RED("red");

    private String color;


    Color(String color)
    {
        this.color = color;
    }
    @JsonValue
    public String getColor()
    {
        return color;
    }
}
