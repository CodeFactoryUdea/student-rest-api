package com.code.factory.stundetrestapi.controller;

import com.code.factory.stundetrestapi.model.Subject;
import com.code.factory.stundetrestapi.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subject")
@PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
public class SubjectController {

    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Subject>> findAll() {
        var subjectList = subjectService.findAll();

        return ResponseEntity.ok(subjectList);
    }
}
