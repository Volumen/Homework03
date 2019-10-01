package com.example.homework03.model;

import org.springframework.hateoas.ResourceSupport;

public class Car extends ResourceSupport {
    private long id;
    private Mark mark;
    private Model model;
    private Color color;

    public Car(long id, Mark mark, Model model, Color color) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
    }

    public Car() {
    }

    public long getIdOfCar() {
        return id;
    }

    public void setIdOfCar(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark=" + mark +
                ", model=" + model +
                ", color=" + color +
                '}';
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
