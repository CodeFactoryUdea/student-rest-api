package com.code.factory.stundetrestapi.repository;

import com.code.factory.stundetrestapi.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}