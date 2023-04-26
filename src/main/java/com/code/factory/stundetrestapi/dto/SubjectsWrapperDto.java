package com.code.factory.stundetrestapi.dto;

import com.code.factory.stundetrestapi.model.Subject;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SubjectsWrapperDto {

    private Integer idStudentSubject;
    private Integer idSubject;
    private LocalDateTime registrationDate;
    private String subjectName;
    private String subjectCredits;
}
