package edu.hogwarts.studentadmin.application;

import edu.hogwarts.studentadmin.models.*;
import edu.hogwarts.studentadmin.repositories.CourseRepository;
import edu.hogwarts.studentadmin.repositories.HouseRepository;
import edu.hogwarts.studentadmin.repositories.StudentRepository;
import edu.hogwarts.studentadmin.repositories.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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

        // Generate houses
        House gryffindor = new House("Gryffindor", "Godric Gryffindor", List.of("Scarlet", "Gold"));
        House hufflepuff = new House("Hufflepuff", "Helga Hufflepuff", List.of("Yellow", "Black"));
        House ravenclaw = new House("Ravenclaw", "Rowen Ravenclaw", List.of("Blue", "Bronze"));
        House slytherin = new House("Slytherin", "Salazar", List.of("Green", "Silver"));

        houseRepository.save(gryffindor);
        houseRepository.save(hufflepuff);
        houseRepository.save(ravenclaw);
        houseRepository.save(slytherin);

        // Generate students
        Student harryPotter = new Student("Harry", "James", "Potter", LocalDate.of(1980, 7, 31), "Gryffindor", false, 1991, 1998, true);
        Student hermioneGranger = new Student("Hermione", "Jean", "Granger", LocalDate.of(1979, 9, 19), "Gryffindor", false, 1991, 1998, true);
        Student ronaldWeasley = new Student("Ronald", "Bilius", "Weasley", LocalDate.of(1980, 3, 1), "Gryffindor", false, 1991, 1998, true);
        Student nevilleLongbottom = new Student("Neville", "", "Longbottom", LocalDate.of(1980, 7, 30), "Gryffindor", true, 1991, 1998, true);
        Student dracoMalfoy = new Student("Draco", "Lucious", "Malfoy", LocalDate.of(1980, 6, 5), "Slytherin", false, 1991, 1998, true);
        Student gregoryGoyle = new Student("Gregory", "", "Goyle", LocalDate.of(1979, 9, 19), "Slytherin", false, 1991, 1998, true);
        Student vincentCrabbe = new Student("Vincent", "", "Crabbe", LocalDate.of(1980, 11, 20), "Slytherin", false, 1991, 1998, true);
        Student hannahAbbott = new Student("Hannah", "", "Abbott", LocalDate.of(1979, 12, 5), "Hufflepuff", false, 1991, 1998, true);
        Student justinFinch = new Student("Justin", "", "Finch-Fletchley", LocalDate.of(1979, 1, 19), "Hufflepuff", false, 1991, 1998, true);
        Student michaelCorner = new Student("Michael", "", "Corner", LocalDate.of(1980, 5, 2), "Ravenclaw", false, 1991, 1998, true);
        Student seamusFinnigan = new Student("Seamus", "", "Finnigan", LocalDate.of(1979, 8, 30), "Gryffindor", false, 1991, 1998, true);
        Student anthonyGoldstein = new Student("Anthony", "", "Goldstein", LocalDate.of(1979, 1, 30), "Ravenclaw", false, 1991, 1998, true);

        studentRepository.save(harryPotter);
        studentRepository.save(hermioneGranger);
        studentRepository.save(ronaldWeasley);
        studentRepository.save(nevilleLongbottom);
        studentRepository.save(dracoMalfoy);
        studentRepository.save(gregoryGoyle);
        studentRepository.save(vincentCrabbe);
        studentRepository.save(hannahAbbott);
        studentRepository.save(justinFinch);
        studentRepository.save(michaelCorner);
        studentRepository.save(seamusFinnigan);
        studentRepository.save(anthonyGoldstein);

        // Generate teachers
        Teacher severusSnape = new Teacher("Severus", "", "Snape", LocalDate.of(1960, 1, 9), slytherin, true, EmpType.DECEASED, LocalDate.of(1983, 8, 30), LocalDate.of(1998, 5, 2));
        teacherRepository.save(severusSnape);

        // Generate courses
        Course potions = new Course("Potions", 1991, false, severusSnape);
        courseRepository.save(potions);



        Student foundStudent = studentRepository.findByFirstName("Harry");
        System.out.println(foundStudent.getHouse());



    }

}
