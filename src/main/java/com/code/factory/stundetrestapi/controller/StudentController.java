package com.code.factory.stundetrestapi.controller;

import com.code.factory.stundetrestapi.model.Student;
import com.code.factory.stundetrestapi.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student){

        var student1 = studentService.createStudent(student);
        return ResponseEntity.ok(student1);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {

        var studentUpdated = studentService.updateStudent(student);

        return ResponseEntity.ok(studentUpdated);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Student>> findAll() {
        var studentList = studentService.findAll();

        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<Student>> findByName(@PathVariable String name) {
        var student = studentService.findByName(name);

        return ResponseEntity.ok(student);
    }
}
