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

    //END ENTITY

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_student_fk", insertable = false, updatable = false, nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subject_fk", insertable = false, updatable = false, nullable = false)
    private Subject subject;

}
