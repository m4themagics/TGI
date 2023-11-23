package com.myapp.controller;

import com.myapp.entity.Course;
import com.myapp.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{id}")
    public Course getCourse(@PathVariable int id) {
        return courseService.getCourse(id);
    }

    @PostMapping("/courses")
    public Course saveCourse(@RequestBody Course course) {
        courseService.saveCourse(course);
        return course;
    }

    @DeleteMapping("/courses/{id}")
    public String deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return "Deleted course id - " + id;
    }

    @PostMapping("/courses/{courseId}/students/{studentId}")
    public String addStudent(@PathVariable int courseId, @PathVariable int studentId) {
        courseService.addStudent(studentId, courseId);
        return "Added student id - " + studentId + " to course id - " + courseId;
    }

    @DeleteMapping("/courses/{courseId}/students/{studentId}")
    public String removeStudent(@PathVariable int courseId, @PathVariable int studentId) {
        courseService.removeStudent(studentId, courseId);
        return "Removed student id - " + studentId + " from course id - " + courseId;
    }
}
