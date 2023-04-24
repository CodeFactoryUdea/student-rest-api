package com.code.factory.stundetrestapi.service;

import com.code.factory.stundetrestapi.model.Student;
import com.code.factory.stundetrestapi.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);


    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        if (Objects.nonNull(student.getId())) {
            Optional<Student> studentOptional = studentRepository.findById(student.getId());
            if (studentOptional.isPresent()) {
               log.error("Datos duplicados");
               throw new RuntimeException("Datos duplicados");
            }
        }

        try {
            return studentRepository.save(student);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("ex.student.data_constraint_violation");
        }
    }

    public Student updateStudent(Student student) {
        if (Objects.isNull(student.getId())){
            throw new RuntimeException("ex.student.object_not_found");
        }

        var studentTransaction = studentRepository.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("ex.student.data_not_found") );

        studentTransaction.setDocument(student.getDocument());
        studentTransaction.setFullName(student.getFullName());

        return studentTransaction;
    }


    public List<Student> findAll() {

        var studentList = studentRepository.findAll();
        return studentList;

    }

    public List<Student> findByName(String name) {
        var student = studentRepository.findByFullNameStartingWith(name);

        return student;
    }
}
