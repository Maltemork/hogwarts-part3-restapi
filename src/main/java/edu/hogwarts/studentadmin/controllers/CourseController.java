package edu.hogwarts.studentadmin.controllers;

import edu.hogwarts.studentadmin.models.Course;
import edu.hogwarts.studentadmin.models.Student;
import edu.hogwarts.studentadmin.models.Teacher;
import edu.hogwarts.studentadmin.repositories.CourseRepository;
import edu.hogwarts.studentadmin.repositories.StudentRepository;
import edu.hogwarts.studentadmin.repositories.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/courses")
@RestController
public class CourseController {
    //Fields
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;


    // Constructor
    public CourseController(CourseRepository courseRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    // Mapping
    // GET
    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id) {
        Optional<Course> course = courseRepository.findById(id);
        return ResponseEntity.of(course);
    }

    @GetMapping("/{id}/teacher")
    public ResponseEntity<Teacher> getCourseTeacher(@PathVariable int id) {
        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            return ResponseEntity.ok(course.get().getTeacher());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getCourseStudents(@PathVariable int id) {
        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            return ResponseEntity.ok(course.get().getStudents());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    // PUT
    @PutMapping("/{id}/teacher")
    public ResponseEntity<Course> updateCourseTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        Optional<Course> course = courseRepository.findById(id);
        Optional<Teacher> newTeacher = teacherRepository.findById(teacher.getId());

        if (course.isPresent() && newTeacher.isPresent()) {
            Course existingCourse = course.get();
            Teacher existingTeacher = newTeacher.get();

            existingCourse.setTeacher(existingTeacher);

            courseRepository.save(existingCourse);
            return ResponseEntity.ok().body(existingCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/students")
    public ResponseEntity<Course> addStudentToCourse(@PathVariable int id, @RequestBody Student student) {
        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            Course existingCourse = course.get();
            existingCourse.setStudentToCourse(student);
            courseRepository.save(existingCourse);
            return ResponseEntity.ok().body(existingCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable int id) {
        Optional<Course> courseToDelete = courseRepository.findById(id);
        courseRepository.deleteById(id);
        return ResponseEntity.of(courseToDelete);
    }

    @DeleteMapping("{id}/teacher")
    public ResponseEntity<Course> deleteTeacherFromCourse(@PathVariable int id, @RequestBody Teacher teacher) {
        Optional<Teacher> teacherToDelete = teacherRepository.findById(teacher.getId());
        Optional<Course> courseToDeleteTeacherIn = courseRepository.findById(id);

        if (teacherToDelete.isPresent() && courseToDeleteTeacherIn.isPresent()) {
            Course existingCourse = courseToDeleteTeacherIn.get();
            existingCourse.setTeacher(null);

            courseRepository.save(existingCourse);
            return ResponseEntity.ok().body(existingCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}/students")
    public ResponseEntity<Course> deleteStudentFromCourse(@PathVariable int id, @RequestBody Student student) {
        Optional<Student> studentToDelete = studentRepository.findById(student.getId());
        Optional<Course> courseToDeleteStudentIn = courseRepository.findById(id);

        if (studentToDelete.isPresent() && courseToDeleteStudentIn.isPresent()) {
            Course existingCourse = courseToDeleteStudentIn.get();
            existingCourse.getStudents().remove(studentToDelete.get());
            courseRepository.save(existingCourse);
            return ResponseEntity.ok().body(existingCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
