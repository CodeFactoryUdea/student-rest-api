package com.code.factory.stundetrestapi.repository;

import com.code.factory.stundetrestapi.model.StudentSubjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentSubjectsRepository extends JpaRepository<StudentSubjects, Integer> {


    @Query("SELECT s from StudentSubjects s WHERE s.idStudentFk = :idStudentFk")
    List<StudentSubjects> findSubjects(@Param("idStudentFk") Integer idStudentFk);
}
