package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.models.Teacher;
import edu.hogwarts.studentadmin.repositories.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/teachers")
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
}
