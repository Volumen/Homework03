package com.example.homework03.service;

import com.example.homework03.controller.CarController;
import com.example.homework03.repository.CarRepository;
import com.example.homework03.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
    this.carRepository=carRepository;
    }

    public List<Car> getCars()
    {
        return carRepository.getCars();
    }

    public Car getCarById(long id)
    {
        Optional<Car> firstCar = carRepository
                .getCars()
                .stream()
                .filter(car -> car.getIdOfCar()==id)
                .findFirst();
        if(firstCar.isPresent())
        {
            return firstCar.get();
        }
        return null;
    }
    public List<Car> getCarsByColor(String color)
    {
        List<Car> listOfCarsByColor = carRepository
                .getCars()
                .stream()
                .filter(car -> car
                        .getColor().toString().equalsIgnoreCase(color))
                .collect(Collectors.toList());
        if(!listOfCarsByColor.isEmpty())
        {
            System.out.println("nie jest pusty");
            return listOfCarsByColor;
        }
        System.out.println("jest pusty");
        return null;
    }

    public Boolean addCar(Car car)
    {
        return carRepository.getCars().add(car);
    }

    public Boolean deleteCar(Car car)
    {
        return carRepository.getCars().remove(car);
    }

    public Boolean modifyCarField(Car carToModify)
    {
        Optional<Car> optionalCar = carRepository.getCars()
                .stream()
                .filter(car -> car.getIdOfCar() == carToModify.getIdOfCar())
                .findFirst();
        if(optionalCar.isPresent())
        {
            System.out.println("Jest obecny");
            if(carToModify.getMark() != null){optionalCar.get().setMark(carToModify.getMark());}
            if(carToModify.getModel() != null){optionalCar.get().setModel(carToModify.getModel());}
            if(carToModify.getColor() != null){optionalCar.get().setColor(carToModify.getColor());}
            return true;
        }
        System.out.println("nie Jest obecny");
        return false;
    }
    public Boolean modifyCar(Car carToModify)
    {
        Optional<Car> optionalCar = carRepository.getCars()
                .stream()
                .filter(car -> car.getIdOfCar() == carToModify.getIdOfCar())
                .findFirst();
        if(optionalCar.isPresent())
        {
            System.out.println("Jest obecny");
            if(carToModify.getMark() != null && carToModify.getModel() != null && carToModify.getColor() != null)
            {
            optionalCar.get().setMark(carToModify.getMark());
            optionalCar.get().setModel(carToModify.getModel());
            optionalCar.get().setColor(carToModify.getColor());
                return true;
            }
            return false;
        }
        System.out.println("nie Jest obecny");
        return false;
    }
    public List<Car> getHATEOASCars()
    {
        Link link;
        for(int i = 0; i<carRepository.getCars().size(); i++)
        {
            link = ControllerLinkBuilder.linkTo(methodOn(CarController.class)
                    .getCarById(carRepository.getCars().get(i).getIdOfCar()))
                    .withSelfRel();
            carRepository.getCars().get(i).add(link);
        }
        return carRepository.getCars();
    }
}
