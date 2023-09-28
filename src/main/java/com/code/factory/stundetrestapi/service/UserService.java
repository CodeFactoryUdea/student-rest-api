package com.code.factory.stundetrestapi.service;

import com.code.factory.stundetrestapi.dto.UserRolePermissionsDto;
import com.code.factory.stundetrestapi.model.Rolecf;
import com.code.factory.stundetrestapi.model.User;
import com.code.factory.stundetrestapi.model.UserRole;
import com.code.factory.stundetrestapi.repository.UserRepository;
import com.code.factory.stundetrestapi.repository.UserRoleRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private static final String ROLE_PREFIX="ROLE_";
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;


    public UserService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRolePermissionsDto> findUserRoleWithPermission(String username) {
        if (Objects.isNull(username)) {
            throw new ObjectNotFoundException(username, "User not found");
        }
        List<UserRolePermissionsDto> userRolePermissionsDtoList =
                userRepository.findUserRoleWithPermission(username);
        if (userRolePermissionsDtoList.isEmpty()){
            throw new RuntimeException("exception.data_not_found.provider");
        }

        return userRolePermissionsDtoList;
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


    public User saveUser(User user){
        if(Objects.nonNull(user.getId())){
            Optional<User> userOptional = userRepository.findById(user.getId());
            if(userOptional.isPresent()){
                throw new RuntimeException("exception.data_duplicated.user");
            }

        }

        if(Objects.nonNull(user.getUsername())){
            Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
            if(userOptional.isPresent()){
                throw new RuntimeException("exception.data_duplicated.user");
            }

        }

        try {
            return userRepository.save(user);
        }catch (DataIntegrityViolationException e) {
            Logger.getGlobal().log(Level.SEVERE, "No se puedo guardar el usuario", e);
            throw new RuntimeException("exception.data_constraint_violation.user");
        }
    }






}
