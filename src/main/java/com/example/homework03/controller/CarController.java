package com.example.homework03.controller;

import com.example.homework03.model.Car;
import com.example.homework03.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/cars", produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
public class CarController {
    CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public ResponseEntity<List<Car>> getCars()
    {
        return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
    }
    @GetMapping("/hateoas/cars")
    public ResponseEntity<List<Car>> getHATEOASCars()
    {
       return new ResponseEntity<>(carService.getHATEOASCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id)
    {
        Car car = carService.getCarById(id);
        if(car != null)
        {
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/carColor/{color}")
    public ResponseEntity<List<Car>> getCarsByColor(@PathVariable String color)
    {
        List<Car> listOfCarsByColor = carService.getCarsByColor(color);
        if(listOfCarsByColor!=null) {
            return new ResponseEntity(listOfCarsByColor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car newCar)
    {
        Boolean result = carService.addCar(newCar);
        if(result) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.SEE_OTHER);
    }
    @PutMapping
    public ResponseEntity<Car> modifyCar(@RequestBody(required = false) Car modifiedCar)
    {
        boolean result = carService.modifyCar(modifiedCar);
        if(result)
        {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }
    @PatchMapping
    public ResponseEntity<Car> modifyCarField(@RequestBody(required = false) Car modifiedCar)
    {
        boolean result = carService.modifyCarField(modifiedCar);
        if(result)
        {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping
    public ResponseEntity deleteCar(@RequestBody(required = false) Car carToRemove)
    {
        carService.deleteCar(carToRemove);
        if(!carService.getCars().contains(carToRemove))
        {
            return new ResponseEntity( HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
