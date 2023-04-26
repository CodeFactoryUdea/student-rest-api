package com.code.factory.stundetrestapi.service;

import com.code.factory.stundetrestapi.dto.StudentWithSubjectsDto;
import com.code.factory.stundetrestapi.dto.SubjectsWrapperDto;
import com.code.factory.stundetrestapi.model.Student;
import com.code.factory.stundetrestapi.model.StudentSubjects;
import com.code.factory.stundetrestapi.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

    private StudentRepository studentRepository;
    private StudentSubjectsService studentSubjectsService;

    public StudentService(StudentRepository studentRepository, StudentSubjectsService studentSubjectsService) {
        this.studentRepository = studentRepository;
        this.studentSubjectsService = studentSubjectsService;
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

    public List<Student> findAll() {

        var studentList = studentRepository.findAll();
        return studentList;

    }

    public List<Student> findByName(String name) {
        var student = studentRepository.findByFullNameStartingWith(name);

        return student;
    }

    public Student createStudent(Student student){

        return studentRepository.save(student);

    }


    public Student updateStudent(Student student) {

        Student studentTransaction = studentRepository.findById(student.getId())
                .orElseThrow(()-> new RuntimeException("El estudiante no existe"));

        studentTransaction.setDocument(student.getDocument());
        studentTransaction.setFullName(student.getFullName());

        return studentTransaction;

    }

    public void deleteStudent(Integer id) {
        if(Objects.nonNull(id)) {
            Optional<Student> studentOptional =  studentRepository.findById(id);
            if (!studentOptional.isPresent()) {
                throw new RuntimeException("El estudiante no existe");
            }
        }

        studentRepository.deleteById(id);
    }
}
