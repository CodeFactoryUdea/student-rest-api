package com.code.factory.stundetrestapi.service;

import com.code.factory.stundetrestapi.model.UserRole;
import com.code.factory.stundetrestapi.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class UserRoleService {

    private UserRoleRepository userRoleRepository;

    public UserRole saveUserRole(UserRole userRole){
        return userRoleRepository.save(userRole);
    }

}
