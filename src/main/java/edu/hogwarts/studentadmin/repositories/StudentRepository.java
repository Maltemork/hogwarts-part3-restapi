package edu.hogwarts.studentadmin.repositories;

import edu.hogwarts.studentadmin.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByFirstName(String firstName);
    Student findByFirstNameAndLastName(String firstName, String lastName);
    Student findByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName);



}
