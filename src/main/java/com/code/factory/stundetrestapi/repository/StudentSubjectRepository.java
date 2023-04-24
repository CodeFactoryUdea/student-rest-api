package com.code.factory.stundetrestapi.repository;

import com.code.factory.stundetrestapi.model.StudentSubjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentSubjectRepository extends JpaRepository<StudentSubjects, Integer> {

    Long countByIdStudentFk(Integer id);
}
