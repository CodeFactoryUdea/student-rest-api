package com.code.factory.stundetrestapi.repository;

import com.code.factory.stundetrestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
