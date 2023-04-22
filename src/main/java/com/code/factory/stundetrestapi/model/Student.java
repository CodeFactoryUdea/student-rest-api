package com.code.factory.stundetrestapi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "student")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "document")
    private String document;

    @Column(name = "full_name")
    private String fullName;



}
