package com.code.factory.stundetrestapi.service;

import com.code.factory.stundetrestapi.model.Rolecf;
import com.code.factory.stundetrestapi.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;

    public Rolecf findByRoleName(String roleName) {
        if (Objects.isNull(roleName)) {
            throw new ObjectNotFoundException(roleName, "Role not found");
        }
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }


}
