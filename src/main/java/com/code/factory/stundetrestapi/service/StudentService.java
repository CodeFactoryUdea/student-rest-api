package com.code.factory.stundetrestapi.service;

import com.code.factory.stundetrestapi.model.Student;
import com.code.factory.stundetrestapi.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
