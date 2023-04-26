package com.code.factory.stundetrestapi.dto;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class StudentSubjectsDto {

    private Integer id;
    private Integer idStudentFk;
    private Integer idSubjectFk;
    private LocalDateTime registrationDate;
}
