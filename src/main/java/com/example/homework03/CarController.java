package com.example.homework03;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/cars", produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
public class CarController {

    List<Car> list;

    public CarController() {
        this.list = new ArrayList<>();
        list.add(new Car(1L,"Fiat","Punto","green"));
        list.add(new Car(2L,"Opel","Astra","black"));
        list.add(new Car(3L,"Mercedes","GLC","red"));
    }

    @GetMapping()
    public ResponseEntity<List<Car>> getCars()
    {
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/hateoas/cars")
    public ResponseEntity<List<Car>> getHATEOASCars()
    {
        Link link;
        for(int i = 0; i<list.size(); i++)
        {
            link = ControllerLinkBuilder.linkTo(methodOn(CarController.class)
                    .getCarsById(list.get(i).getIdOfCar()))
                    .withSelfRel();
            list.get(i).add(link);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarsById(@PathVariable long id)
    {
        Optional<Car> optionalCar = list
                .stream()
                .filter(car -> car.getIdOfCar()==id)
                .findFirst();
        if(optionalCar.isPresent())
        {
            return new ResponseEntity<>(optionalCar.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/carColor/{color}")
    public ResponseEntity<Car> getCarsByColor(@PathVariable String color)
    {
        List<Car> optionalCar = list
                .stream()
                .filter(car -> car.getColor().equals(color))
                .collect(Collectors.toList());
        if(!optionalCar.isEmpty())
        {
            return new ResponseEntity(optionalCar, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car)
    {
        boolean add = list.add(car);
        if(add)
        {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.SEE_OTHER);
    }
    @PutMapping
    public ResponseEntity<Car> modifyCar(@RequestBody Car newCar)
    {
        Optional<Car> optionalCar = list
                .stream()
                .filter(car -> car.getIdOfCar() == newCar.getIdOfCar())
                .findFirst();
        if(optionalCar.isPresent())
        {
            list.remove(optionalCar.get());
            list.add(newCar);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @PatchMapping
    public ResponseEntity<Car> modifyCarField(@RequestBody(required = false) Car modifyCarField)
    {
        Optional<Car> optionalCarCar = list
                .stream()
                .filter(car -> car.getIdOfCar() == modifyCarField.getIdOfCar())
                .findFirst();
        if(optionalCarCar.isPresent()) {
            if(modifyCarField.getMark() != null){optionalCarCar.get().setMark(modifyCarField.getMark());}
            if(modifyCarField.getModel() != null){optionalCarCar.get().setModel(modifyCarField.getModel());}
            if(modifyCarField.getColor() != null){optionalCarCar.get().setColor(modifyCarField.getColor());}
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping
    public ResponseEntity<Car> deleteCar(@RequestBody(required = false) long id)
    {
        Optional<Car> optionalCar = list
                .stream()
                .filter(car -> car.getIdOfCar() == id)
                .findFirst();
        if(optionalCar.isPresent())
        {
            list.remove(optionalCar.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
