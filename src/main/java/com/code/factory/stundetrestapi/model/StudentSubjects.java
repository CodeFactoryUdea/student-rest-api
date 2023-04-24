package com.code.factory.stundetrestapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_subject")
@Data
public class StudentSubjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_student_fk")
    private Integer idStudentFk;

    @Column(name = "id_subject_fk")
    private Integer idSubjectFk;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    /*
    * significa que Un Student puede tener muchas materias (Many subjects To One student)
    *
    */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_student_fk", insertable = false, updatable = false, nullable = true)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_subject_fk", insertable = false, updatable = false, nullable = true)
    private Subject subject;


}
