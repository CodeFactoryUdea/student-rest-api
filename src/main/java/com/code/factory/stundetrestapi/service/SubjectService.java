package com.code.factory.stundetrestapi.service;

import com.code.factory.stundetrestapi.model.Subject;
import com.code.factory.stundetrestapi.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> findAll() {
        var subjectList = subjectRepository.findAll();
        return  subjectList;
    }
}
