package com.code.factory.stundetrestapi.controller;

import com.code.factory.stundetrestapi.dto.StudentSubjectsDto;
import com.code.factory.stundetrestapi.dto.StudentWithSubjectsDto;
import com.code.factory.stundetrestapi.model.Student;
import com.code.factory.stundetrestapi.model.StudentSubjects;
import com.code.factory.stundetrestapi.service.StudentService;
import com.code.factory.stundetrestapi.service.StudentSubjectService;
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

    private StudentSubjectService studentSubjectService;


    public StudentController(StudentService studentService, StudentSubjectService studentSubjectService) {
        this.studentService = studentService;
        this.studentSubjectService = studentSubjectService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id){
        log.info("Rest request buscar student por id: "+ id);
        var student = studentService.findById(id);
        return ResponseEntity.ok(student);
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

    @PostMapping("/save-student-with-subjects")
    public ResponseEntity<String> saveStudentWithSubjects(@RequestBody StudentSubjectsDto studentSubjectsDto){

        studentSubjectService.saveStudentWithSubjects(studentSubjectsDto);
        return ResponseEntity.ok("student1");
    }


}
