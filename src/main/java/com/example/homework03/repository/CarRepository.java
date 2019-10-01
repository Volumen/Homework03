package com.example.homework03.repository;

import com.example.homework03.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {
    private List<Car> listOfCars;

    public CarRepository() {
        this.listOfCars = new ArrayList<>();
//        listOfCars.add(new Car(1L,Mark.OPEL,Model.ASTRA,Color.GREEN));
//        listOfCars.add(new Car(2L,Mark.FIAT,Model.PUNTO,Color.BLACK));
//        listOfCars.add(new Car(3L,Mark.MERCEDES,Model.GLC,Color.RED));
    }
    public List<Car> getCars()
    {
        return listOfCars;
    }
}
