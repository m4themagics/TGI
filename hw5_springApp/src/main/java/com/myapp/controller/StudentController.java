package com.myapp.controller;

import com.myapp.entity.Student;
import com.myapp.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable int id) {
        return studentService.getStudent(id);
    }

    @PostMapping("/students")
    public Student saveStudent(@RequestBody Student student) {
        studentService.saveStudent(student);
        return student;
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "Deleted student id - " + id;
    }

    @PostMapping("/students/{studentId}/courses/{courseId}")
    public String addCourse(@PathVariable int studentId, @PathVariable int courseId) {
        studentService.addCourse(studentId, courseId);
        return "Added course id - " + courseId + " to student id - " + studentId;
    }

    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public String removeCourse(@PathVariable int studentId, @PathVariable int courseId) {
        studentService.removeCourse(studentId, courseId);
        return "Removed course id - " + courseId + " from student id - " + studentId;
    }
}