package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.models.Course;
import edu.hogwarts.studentadmin.models.House;
import edu.hogwarts.studentadmin.models.Teacher;
import edu.hogwarts.studentadmin.repositories.HouseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> getHouse(@PathVariable int id) {
        Optional<House> house = houseRepository.findById(id);
        return ResponseEntity.of(house);
    }

    // POST

    @PostMapping
    public House createHouse(@RequestBody House house) {
        return houseRepository.save(house);
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable int id, @RequestBody House house) {
        Optional<House> houseGettingUpdated = houseRepository.findById(id);

        if (houseGettingUpdated.isPresent()) {
            House existingHouse = houseGettingUpdated.get();
            existingHouse.copyHouse(house);
            House updatedHouse = houseRepository.save(existingHouse);
            return ResponseEntity.ok().body(updatedHouse);
        } else {
            House newHouse = new House(house);
            House savedHouse = houseRepository.save(newHouse);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedHouse);
        }
    }
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<House> deleteHouse(@PathVariable int id) {
        Optional<House> houseToDelete = houseRepository.findById(id);
        houseRepository.deleteById(id);
        return ResponseEntity.of(houseToDelete);
    }
}
