package com.code.factory.stundetrestapi.repository;

import com.code.factory.stundetrestapi.model.Rolecf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Rolecf, Integer> {

    Optional<Rolecf> findByRoleName(String roleName);

}
