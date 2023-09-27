package com.code.factory.stundetrestapi.service;

import com.code.factory.stundetrestapi.model.Rolecf;
import com.code.factory.stundetrestapi.model.User;
import com.code.factory.stundetrestapi.model.UserRole;
import com.code.factory.stundetrestapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Rolecf> getUserRoles(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<UserRole> userRoles = user.getUserRoles();
            return userRoles.stream()
                    .map(UserRole::getRoleFk)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
