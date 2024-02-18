package edu.hogwarts.studentadmin.models;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;

@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String founder;
    @ElementCollection
    private List<String> colors = Arrays.asList("", "");

    //Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    public House(int id, String name,  String founder, List<String> colors) {
        this.id = id;
        this.name = name;
        this.founder = founder;
        this.colors = colors;
    }
    public House(String name, String founder, List<String> colors) {
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
        this(house.getId(), house.getName(), house.getFounder(), house.getColors());
    }
}

