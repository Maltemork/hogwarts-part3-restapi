package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.models.House;
import edu.hogwarts.studentadmin.repositories.HouseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/houses")
@RestController
public class HouseController {
    // Fields
    private final HouseRepository houseRepository;

    // Constructor
    public HouseController(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    // Mapping
    // GET
    @GetMapping
    public ArrayList<House> getAllHouses() {
        return new ArrayList<>(houseRepository.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<House> getHouse(@PathVariable String name) {
        // I want to find the house by name. I need to change the parameter type to String
        Optional<House> house;
        house = houseRepository.findByName(name);
        return ResponseEntity.of(house);
    }
}
