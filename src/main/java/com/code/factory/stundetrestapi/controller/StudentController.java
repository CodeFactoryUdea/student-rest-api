package com.code.factory.stundetrestapi.controller;

import com.code.factory.stundetrestapi.model.Student;
import com.code.factory.stundetrestapi.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final Logger log = LoggerFactory.getLogger(StudentController.class);


    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id){
        log.info("Rest request buscar student por id: "+ id);
        var student = studentService.findById(id);
        return ResponseEntity.ok(student);
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

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.accepted().body("student.deleted.ok");

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
