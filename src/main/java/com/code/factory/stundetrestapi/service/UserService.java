package com.code.factory.stundetrestapi.service;

import com.code.factory.stundetrestapi.config.jwt.GenerateJWT;
import com.code.factory.stundetrestapi.dto.UserDto;
import com.code.factory.stundetrestapi.dto.UserSingIn;
import com.code.factory.stundetrestapi.model.Rolecf;
import com.code.factory.stundetrestapi.model.User;
import com.code.factory.stundetrestapi.model.UserRole;
import com.code.factory.stundetrestapi.repository.RoleRepository;
import com.code.factory.stundetrestapi.repository.UserRepository;
import com.code.factory.stundetrestapi.repository.UserRoleRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private static final String ROLE_PREFIX="ROLE_";


    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserRoleRepository userRoleRepository;


    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
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


    public User registerNewUserAccount(UserDto userDto)  {


        if(Objects.nonNull(userDto.getUserName())){
            Optional<User> userOptional = userRepository.findByUsername(userDto.getUserName());
            if(userOptional.isPresent()){
                throw new RuntimeException("exception.data_duplicated.user");
            }

        }

        User user = new User();
        user.setUsername(userDto.getUserName());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        User userCreated = userRepository.save(user);


        if(null== userDto.getRoleList() || userDto.getRoleList().isEmpty()) {
            UserRole userRole = new UserRole();
            Rolecf rolecf = roleRepository.findByRoleName("STUDENT");
            userRole.setIdUserFk(userCreated.getId());
            userRole.setIdRoleFk(rolecf.getId());
            var userRoleCreated = userRoleRepository.save(userRole);
            user.setUserRoles(Arrays.asList(userRoleCreated));
        } else {
            userRoleRepository.saveAll(userDto.getRoleList());
            user.setUserRoles(userDto.getRoleList());
        }

        return userCreated;
    }




}
