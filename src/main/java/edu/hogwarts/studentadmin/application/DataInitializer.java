package edu.hogwarts.studentadmin.application;

import edu.hogwarts.studentadmin.models.House;
import edu.hogwarts.studentadmin.models.Student;
import edu.hogwarts.studentadmin.repositories.CourseRepository;
import edu.hogwarts.studentadmin.repositories.HouseRepository;
import edu.hogwarts.studentadmin.repositories.StudentRepository;
import edu.hogwarts.studentadmin.repositories.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private HouseRepository houseRepository;

    public DataInitializer() {};

    @Autowired
    public DataInitializer(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, HouseRepository houseRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.houseRepository = houseRepository;
    }

    public void run(String...args) {
        House gryffindor = new House("Gryffindor", "Godric Gryffindor", List.of("Scarlet", "Gold"));
        House hufflepuff = new House("Hufflepuff", "Helga Hufflepuff", List.of("Yellow", "Black"));
        House ravenclaw = new House("Ravenclaw", "Rowen Ravenclaw", List.of("Blue", "Bronze"));
        House slytherin = new House("Slytherin", "Salazar", List.of("Green", "Silver"));

        houseRepository.save(gryffindor);
        houseRepository.save(hufflepuff);
        houseRepository.save(ravenclaw);
        houseRepository.save(slytherin);

        Student harryPotter = new Student("Harry", "James", "Potter", LocalDate.of(1980, Calendar.JULY, 31), "Gryffindor", 1991, 1998, 1, "");
    }

}
