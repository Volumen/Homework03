package com.example.homework03;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarApi {

    List<Car> list;

    public CarApi() {
        this.list = new ArrayList<>();
        list.add(new Car(1L,"Fiat","Punto","green"));
        list.add(new Car(2L,"Opel","Astra","black"));
        list.add(new Car(3L,"Mercedes","GLC","red"));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars()
    {
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarsById(@PathVariable long id)
    {
        Optional<Car> optionalCar = list
                .stream()
                .filter(car -> car.getId()==id)
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
                .filter(car -> car.getId() == newCar.getId())
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
                .filter(car -> car.getId() == modifyCarField.getId())
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
    public ResponseEntity<Car> deleteCarField(@RequestBody(required = false) Car deleteCarField)
    {
        Optional<Car> optionalCar = list
                .stream()
                .filter(car -> car.getId() == deleteCarField.getId())
                .findFirst();
        if(optionalCar.isPresent()) {
            if(deleteCarField.getMark() != null){ optionalCar.get().setMark(""); }
            if(deleteCarField.getModel() != null){ optionalCar.get().setModel("");}
            if(deleteCarField.getColor() != null){optionalCar.get().setColor("");}
            return new ResponseEntity<>(HttpStatus.GONE);
        }
        return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }
}
