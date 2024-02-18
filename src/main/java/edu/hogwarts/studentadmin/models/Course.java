package edu.hogwarts.studentadmin.models;


import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String subject;
    private int schoolYear;
    private boolean current;
    @ManyToOne
    private Teacher teacher;
    @ManyToMany
    private List<Student> students;



    // Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // Constructors
    public Course() {

    }

    // Constructors
    public Course(String subject, int schoolYear, boolean current, Teacher teacher, List<Student> students) {
        this.subject = subject;
        this.schoolYear = schoolYear;
        this.current = current;
        this.teacher = teacher;
        this.students = students;
    }

    public Course(Course otherCourse) {
        this.id = otherCourse.getId();
        this.subject = otherCourse.getSubject();
        this.schoolYear = otherCourse.getSchoolYear();
        this.current = otherCourse.isCurrent();
        this.students = otherCourse.getStudents();
        this.teacher = otherCourse.getTeacher();
    }

    public void copyCourse(Course otherCourse) {
        this.setSubject(otherCourse.getSubject());
        this.setSchoolYear(otherCourse.getSchoolYear());
        this.setCurrent(otherCourse.isCurrent());
        this.setStudents(otherCourse.getStudents());
        this.setTeacher(otherCourse.getTeacher());
    }

    // Extra methods
    public void setStudentToCourse(Student student) {

    }
}
