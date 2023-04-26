package com.code.factory.stundetrestapi.dto;

import com.code.factory.stundetrestapi.model.StudentSubjects;
import com.code.factory.stundetrestapi.model.Subject;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class StudentWithSubjectsDto {

    private Integer idStudent;
    private String document;
    private String fullName;
    private List<SubjectsWrapperDto> subjects;
}
