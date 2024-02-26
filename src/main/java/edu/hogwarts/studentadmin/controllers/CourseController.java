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

import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.util.ArrayList;
import java.util.Collections;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    // POST MAPPING FOR ADDING A LIST OF STUDENTS TO A COURSE
    // ------------------------------------------------------
    // The following classes are necessary for receiving a JSON list and converting it to a list of StudentIdentifier objects.
    public static class StudentList {
        public StudentList() {
        }

        private List<StudentIdentifier> students;

        // getters and setters
        public List<StudentIdentifier> getStudents() {
            return students;
        }
    }
    public static class StudentIdentifier {
        public StudentIdentifier() {
        }

        private Integer id;
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
    @PostMapping("/{id}/students")
    public ResponseEntity<Course> addStudentsToCourse(@PathVariable int id, @RequestBody StudentList studentList) {

        Optional<Course> course = courseRepository.findById(id);
        List<Student> studentsToAdd = new ArrayList<>();

        if (course.isPresent()) {
            Course existingCourse = course.get();
            for (StudentIdentifier studentIdentifier : studentList.getStudents()) {
                // Holds found students in a list.
                List<Student> students = new ArrayList<>();

                // Splits input into names.
                String[] names = studentIdentifier.getName().split(" ");
                String firstName = names.length > 0 ? names[0] : null;
                String middleName = names.length > 2 ? names[1] : null;
                String lastName = names.length > 1 ? names[names.length - 1] : null;

                // Checks how many names are given and finds the student accordingly.
                if (names.length == 1) {
                    students.add(studentRepository.findByFirstName(firstName));
                } else if (names.length == 2) {
                    students.add(studentRepository.findByFirstNameAndLastName(firstName, lastName));
                } else {
                    students.add(studentRepository.findByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName));
                }

                // Checks if the student was found and adds them to the list of students to add to the course. If there are more than 1 student in the list, it will spit out an error.
                if (students.size() > 1) {
                    throw new IllegalArgumentException("More than one student found with the given name");
                } else if (students.size() == 1) {
                    Student student = students.get(0);
                    if (student.getSchoolYear() != existingCourse.getSchoolYear()) {
                        throw new IllegalArgumentException("Student's school year doesn't match the course's school year");
                    }
                    studentsToAdd.add(student);
                }
            }
            // Adds the students to the course and saves the course.
            existingCourse.getStudents().addAll(studentsToAdd);
            courseRepository.save(existingCourse);
            return ResponseEntity.ok().body(existingCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // ------------------------------------------------------

    // Patch mapping for changing or removing the teacher of a course.
    @PatchMapping("/{id}/teacher")
    public ResponseEntity<Course> changeCourseTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        Optional<Teacher> teacherToChange = teacherRepository.findById(teacher.getId());
        Optional<Course> courseToChangeTeacherIn = courseRepository.findById(id);

        if (teacherToChange.isPresent() && courseToChangeTeacherIn.isPresent()) {
            Course existingCourse = courseToChangeTeacherIn.get();
            existingCourse.setTeacher(teacherToChange.get());
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

    // Delete mapping for removing a student from a course.
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