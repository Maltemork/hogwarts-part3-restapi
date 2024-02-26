package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.models.Student;
import edu.hogwarts.studentadmin.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/students")
@RestController
public class StudentController {
    //Fields
    private final StudentRepository studentRepository;

    // Constructor


    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Mapping
    // GET
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        Optional<Student> student = studentRepository.findById(id);
        return ResponseEntity.of(student);
    }

    // POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
        Optional<Student> studentToUpdate = studentRepository.findById(id);
        if (studentToUpdate.isPresent()) {
            Student existingStudent = studentToUpdate.get();
            existingStudent.copyFromStudent(student);
            Student updatedStudent = studentRepository.save(existingStudent);
            return ResponseEntity.ok().body(updatedStudent);
        } else {
            Student newStudent = new Student();
            newStudent.copyFromStudent(student);
            Student savedStudent = studentRepository.save(newStudent);
            return ResponseEntity.ok().body(savedStudent);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
        Optional<Student> studentToDelete = studentRepository.findById(id);
        studentRepository.deleteById(id);
        return ResponseEntity.of(studentToDelete);
    }

    // PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Student> patchStudent(@PathVariable int id, @RequestBody Student student) {
        Optional<Student> studentToPatch = studentRepository.findById(id);
        if (studentToPatch.isPresent()) {
            Student existingStudent = studentToPatch.get();
            existingStudent.setPrefect(student.isPrefect());
            Student updatedStudent = studentRepository.save(existingStudent);
            return ResponseEntity.ok().body(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/schoolYear")
    public ResponseEntity<Student> patchStudentSchoolYear(@PathVariable int id, @RequestBody Student student) {
        Optional<Student> studentToPatch = studentRepository.findById(id);
        if (studentToPatch.isPresent()) {
            Student existingStudent = studentToPatch.get();
            existingStudent.setSchoolYear(student.getSchoolYear());
            Student updatedStudent = studentRepository.save(existingStudent);
            return ResponseEntity.ok().body(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
