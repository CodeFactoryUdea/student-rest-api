package com.code.factory.stundetrestapi.controller;

import com.code.factory.stundetrestapi.dto.StudentWithSubjectsDto;
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


    @GetMapping("/get-student-with-subjects/{id}")
    public ResponseEntity<StudentWithSubjectsDto> getStudentWithSubjects(@PathVariable Integer id) {
        var studentSubjects = studentService.getStudentSubjects(id);

        return ResponseEntity.ok(studentSubjects);
    }

    @PostMapping()
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        var student1 = studentService.createStudent(student);

        return ResponseEntity.ok(student1);

    }


    @PutMapping("/update-student")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        var student1 = studentService.updateStudent(student);

        return ResponseEntity.ok(student1);

    }

    @DeleteMapping()
    public ResponseEntity<String> findByName(@RequestParam() Integer id) {
        studentService.deleteStudent(id);

        return ResponseEntity.ok("student.deleted");
    }
}
