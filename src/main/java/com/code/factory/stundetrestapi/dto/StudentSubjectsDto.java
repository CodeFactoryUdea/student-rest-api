package com.code.factory.stundetrestapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentSubjectsDto {
    private StudentDto student;
    private List<SubjectDto> subjectList;
}
