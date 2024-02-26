package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.models.Teacher;
import edu.hogwarts.studentadmin.repositories.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/teachers")
@RestController
public class TeacherController {
    // Fields
    private final TeacherRepository teacherRepository;

    // Constructor
    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // Mapping
    // Get
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable int id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return ResponseEntity.of(teacher);
    }

    // POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        Optional<Teacher> teacherToUpdate = teacherRepository.findById(id);
        if (teacherToUpdate.isPresent()) {
            Teacher existingTeacher = teacherToUpdate.get();
            existingTeacher.copyFromTeacher(teacher);
            Teacher updatedTeacher = teacherRepository.save(existingTeacher);
            return ResponseEntity.ok().body(updatedTeacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable int id) {
        Optional<Teacher> teacherToDelete = teacherRepository.findById(id);
        teacherRepository.deleteById(id);
        return ResponseEntity.of(teacherToDelete);
    }

    // PATCH
    @PatchMapping("/{id}/headOfHouse")
    public ResponseEntity<Teacher> changeHeadOfHouse(@PathVariable int id, @RequestBody Teacher teacher) {
        Optional<Teacher> teacherToChange = teacherRepository.findById(id);
        if (teacherToChange.isPresent()) {
            Teacher existingTeacher = teacherToChange.get();
            existingTeacher.setHeadOfHouse(teacher.isHeadOfHouse());
            Teacher updatedTeacher = teacherRepository.save(existingTeacher);
            return ResponseEntity.ok().body(updatedTeacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Patch mapping for changing the employment status.
    @PatchMapping("/{id}/employment")
    public ResponseEntity<Teacher> changeEmployment(@PathVariable int id, @RequestBody Teacher teacher) {
        Optional<Teacher> teacherToChange = teacherRepository.findById(id);
        if (teacherToChange.isPresent()) {
            Teacher existingTeacher = teacherToChange.get();
            existingTeacher.setEmployment(teacher.getEmployment());
            Teacher updatedTeacher = teacherRepository.save(existingTeacher);
            return ResponseEntity.ok().body(updatedTeacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Patch mapping for changing the employment end date.
    @PatchMapping("/{id}/employmentEnd")
    public ResponseEntity<Teacher> changeEmploymentEnd(@PathVariable int id, @RequestBody Teacher teacher) {
        Optional<Teacher> teacherToChange = teacherRepository.findById(id);
        if (teacherToChange.isPresent()) {
            Teacher existingTeacher = teacherToChange.get();
            existingTeacher.setEmploymentEnd(teacher.getEmploymentEnd());
            Teacher updatedTeacher = teacherRepository.save(existingTeacher);
            return ResponseEntity.ok().body(updatedTeacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
