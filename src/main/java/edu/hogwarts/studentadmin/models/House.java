package edu.hogwarts.studentadmin.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class House {

    @Id
    private String name;
    private String founder;
    @ElementCollection
    private List<String> colors = new ArrayList<>();

    //Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    // Constructors
    public House() {
    }
    public House(String name,  String founder, List<String> colors) {
        this.name = name;
        this.founder = founder;
        this.colors = colors;
    }

    public void copyHouse(House otherHouse) {
        this.setName(otherHouse.getName());
        this.setFounder(otherHouse.getFounder());
        this.setColors(otherHouse.getColors());
    }
    public House(House house) {
        this(house.getName(), house.getFounder(), house.getColors());
    }
}

