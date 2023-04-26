package com.code.factory.stundetrestapi.service;

import com.code.factory.stundetrestapi.dto.StudentDto;
import com.code.factory.stundetrestapi.dto.StudentSubjectsDto;
import com.code.factory.stundetrestapi.model.Student;
import com.code.factory.stundetrestapi.model.StudentSubjects;
import com.code.factory.stundetrestapi.model.Subject;
import com.code.factory.stundetrestapi.repository.StudentSubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentSubjectService {

    private StudentSubjectRepository studentSubjectRepository;
    private StudentService studentService;
    private SubjectService subjectService;

    public StudentSubjectService(StudentSubjectRepository studentSubjectRepository,
                                 StudentService studentService, SubjectService subjectService) {
        this.studentSubjectRepository = studentSubjectRepository;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    public void saveStudentWithSubjects(StudentSubjectsDto studentSubjectsDto) {
        // TODO: validar existensia de estudiantes y materias, para el ejemplo se asume que existe y no se crea
        //TODO: convertir el StudentDto a Student entity (mapper strategy)
        //TODO: validar que las materias existan, se asume que existen

    }
}
