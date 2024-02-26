package edu.hogwarts.studentadmin.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import edu.hogwarts.studentadmin.repositories.HouseRepository;

import java.time.LocalDate;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnoreProperties
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;

    @JoinColumn(name = "house")
    private String house;

    private boolean prefect;
    private int enrollmentYear;
    private int graduationYear;
    private boolean graduated;

    private int schoolYear;



    //Getters & Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public boolean isPrefect() {
        return prefect;
    }

    public void setPrefect(boolean prefect) {
        this.prefect = prefect;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }

    public int getSchoolYear() {
        return enrollmentYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    //Constructors

    public Student() {
    }

    public Student(String firstName, String middleName, String lastName, LocalDate dateOfBirth, String house, boolean prefect, int enrollmentYear, int graduationYear, boolean graduated) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.prefect = prefect;
        this.enrollmentYear = enrollmentYear;
        this.graduationYear = graduationYear;
        this.graduated = graduated;
    }

    public Student(String firstName, String lastName, LocalDate dateOfBirth, String house, int enrollmentYear) {
        this(firstName, "", lastName, dateOfBirth, house, false, enrollmentYear, 1998, true);
    }

    // Student constructor for fullName.
    public Student(String fullName, LocalDate dateOfBirth, String house, int enrollmentYear) {
        String[] names = fullName.split(" ");
        if (names.length == 2) {
            this.firstName = names[0];
            this.lastName = names[1];
        } else if (names.length == 3) {
            this.firstName = names[0];
            this.middleName = names[1];
            this.lastName = names[2];
        }
        this.dateOfBirth = dateOfBirth;
        this.house = house;
        this.enrollmentYear = enrollmentYear;
        this.graduationYear = 1998;
        this.graduated = true;
    }

    public Student(String fullName, String house) {
        String[] names = fullName.split(" ");
        if (names.length == 2) {
            this.firstName = names[0];
            this.lastName = names[1];
        } else if (names.length == 3) {
            this.firstName = names[0];
            this.middleName = names[1];
            this.lastName = names[2];
        }
        this.house = house;
    }



    public void copyFromStudent(Student student) {
        this.setFirstName(student.getFirstName());
        this.setMiddleName(student.getMiddleName());
        this.setLastName(student.getLastName());
        this.setDateOfBirth(student.getDateOfBirth());
        this.setHouse(student.getHouse());
        this.setPrefect(student.isPrefect());
        this.setEnrollmentYear(student.getEnrollmentYear());
        this.setGraduationYear(student.getGraduationYear());
        this.setGraduated(student.isGraduated());
    }

    public String getFullName() {
        return this.getFirstName() + " " + this.getMiddleName() + " " + this.getLastName();
    }
}
