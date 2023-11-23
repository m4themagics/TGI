package com.myapp.controller;

import com.myapp.entity.Lecturer;
import com.myapp.service.LecturerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LecturerController {

    private final LecturerService lecturerService;

    public LecturerController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping("/lecturers")
    public List<Lecturer> getAllLecturers() {
        return lecturerService.getAllLecturers();
    }

    @GetMapping("/lecturers/{id}")
    public Lecturer getLecturer(@PathVariable int id) {
        return lecturerService.getLecturer(id);
    }

    @PostMapping("/lecturers")
    public Lecturer saveLecturer(@RequestBody Lecturer lecturer) {
        lecturerService.saveLecturer(lecturer);
        return lecturer;
    }

    @DeleteMapping("/lecturers/{id}")
    public String deleteLecturer(@PathVariable int id) {
        lecturerService.deleteLecturer(id);
        return "Deleted lecturer id - " + id;
    }

    @PostMapping("/lecturers/{lecturerId}/courses/{courseId}")
    public String addCourse(@PathVariable int lecturerId, @PathVariable int courseId) {
        lecturerService.addCourse(lecturerId, courseId);
        return "Added course id - " + courseId + " to lecturer id - " + lecturerId;
    }

    @DeleteMapping("/lecturers/{lecturerId}/courses/{courseId}")
    public String removeCourse(@PathVariable int lecturerId, @PathVariable int courseId) {
        lecturerService.removeCourse(lecturerId, courseId);
        return "Removed course id - " + courseId + " from lecturer id - " + lecturerId;
    }
}