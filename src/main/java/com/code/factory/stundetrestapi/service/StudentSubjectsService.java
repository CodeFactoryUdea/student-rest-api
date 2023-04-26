package com.code.factory.stundetrestapi.service;

import com.code.factory.stundetrestapi.model.StudentSubjects;
import com.code.factory.stundetrestapi.model.Subject;
import com.code.factory.stundetrestapi.repository.StudentSubjectsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentSubjectsService {

    private StudentSubjectsRepository studentSubjectsRepository;

    public StudentSubjectsService(StudentSubjectsRepository studentSubjectsRepository) {
        this.studentSubjectsRepository = studentSubjectsRepository;
    }

    public List<StudentSubjects> findSubjectsByIdStudent(Integer idStudent) {

        var studentSubjects =  studentSubjectsRepository.findSubjects(idStudent);

        return studentSubjects;

    }
}
