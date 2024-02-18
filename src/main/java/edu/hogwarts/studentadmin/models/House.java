package edu.hogwarts.studentadmin.models;

import java.util.Optional;

public class House {
    private int id;
    private String name;
    private String founder;
    private String[] colors;



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

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    // Constructors
    public House(int id, String name,  String founder, String[] colors) {
        this.id = id;
        this.name = name;
        this.founder = founder;
        this.colors = colors;
    }
    public House(String name, String founder, String[] colors) {
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

