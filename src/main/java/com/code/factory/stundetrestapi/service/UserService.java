package com.code.factory.stundetrestapi.service;

import com.code.factory.stundetrestapi.model.Rolecf;
import com.code.factory.stundetrestapi.model.User;
import com.code.factory.stundetrestapi.model.UserRole;
import com.code.factory.stundetrestapi.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    public static final String ROLE_PREFIX="ROLE_";

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

    public User findByUserName(String user) {
        System.out.println("user: "+user);
        if (Objects.isNull(user)) {
            throw new ObjectNotFoundException(user, "User not found");
        }
        return userRepository.findByUsername(user)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findByUserName(username);
        List<UserRole> rolesList = user.getUserRoles();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities(rolesList));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<UserRole> roles) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX+role.getRoleFk().getRoleName()));
        }

        return authorities;
    }
}
