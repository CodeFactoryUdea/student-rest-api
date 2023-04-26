package com.code.factory.stundetrestapi.service;

import com.code.factory.stundetrestapi.dto.StudentWithSubjectsDto;
import com.code.factory.stundetrestapi.dto.SubjectsWrapperDto;
import com.code.factory.stundetrestapi.model.Student;
import com.code.factory.stundetrestapi.model.StudentSubjects;
import com.code.factory.stundetrestapi.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentService.class);
    private StudentRepository studentRepository;
    private StudentSubjectsService studentSubjectsService;

    public StudentService(StudentRepository studentRepository, StudentSubjectsService studentSubjectsService) {
        this.studentRepository = studentRepository;
        this.studentSubjectsService = studentSubjectsService;
    }

    public Student findById(Integer id) {
        if (Objects.isNull(id)) {
            throw new RuntimeException("ex.student.object_not_found");
        }
        return studentRepository.findById(id).orElseThrow(()-> new RuntimeException("ex.student.data_not_found"));
    }


    public StudentWithSubjectsDto getStudentSubjects(Integer idStudent) {
        List<StudentSubjects> studentWithSubjects = studentSubjectsService.findSubjectsByIdStudent(idStudent);

        if(studentWithSubjects.isEmpty()) {
            //TODO: puede hacer sus exception handler personalizadas
            throw new RuntimeException("Este estudiante no tiene materias");
        }

        StudentWithSubjectsDto studentWithSubjectsDto = new StudentWithSubjectsDto();

        studentWithSubjectsDto.setIdStudent(idStudent);
        studentWithSubjectsDto.setFullName(studentWithSubjects.get(0).getStudent().getFullName());
        studentWithSubjectsDto.setDocument(studentWithSubjects.get(0).getStudent().getDocument());

        List<SubjectsWrapperDto> subjectsWrapperDtoList =  studentWithSubjects.stream().map(studentSubjects -> SubjectsWrapperDto.builder()
                .idStudentSubject(studentSubjects.getId())
                .registrationDate(studentSubjects.getRegistrationDate())
                .idSubject(studentSubjects.getSubject().getId())
                .subjectName(studentSubjects.getSubject().getName())
                .subjectCredits(studentSubjects.getSubject().getCredits()).build()
        ).collect(Collectors.toList());

        studentWithSubjectsDto.setSubjects(subjectsWrapperDtoList);

        return studentWithSubjectsDto;

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

    public void deleteStudent(Integer studentId) {
        if(Objects.nonNull(studentId)) {
            Optional<Student> studentOptional = studentRepository.findById(studentId);
            if (!studentOptional.isPresent()) {
                throw new RuntimeException("ex.student.data_not_found");
            }
        }

        studentRepository.deleteById(studentId);
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
