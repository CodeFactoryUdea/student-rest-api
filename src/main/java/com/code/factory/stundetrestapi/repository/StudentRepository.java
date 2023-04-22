package com.code.factory.stundetrestapi.repository;

import com.code.factory.stundetrestapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {


    List<Student> findByFullNameStartingWith(String name);



}
