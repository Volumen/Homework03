package com.example.homework03;

import com.example.homework03.model.Car;
import com.example.homework03.model.Color;
import com.example.homework03.model.Mark;
import com.example.homework03.model.Model;
import com.example.homework03.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataLoader implements CommandLineRunner {

    private CarRepository carRepository;
    public DataLoader(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        carRepository.getCars().add(new Car(1L, Mark.OPEL, Model.ASTRA, Color.GREEN));
        carRepository.getCars().add(new Car(2L,Mark.FIAT,Model.PUNTO,Color.BLACK));
        carRepository.getCars().add(new Car(3L,Mark.MERCEDES,Model.GLC,Color.RED));
    }
}
