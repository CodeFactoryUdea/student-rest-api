package com.code.factory.stundetrestapi.facade;


import com.code.factory.stundetrestapi.config.jwt.GenerateJWT;
import com.code.factory.stundetrestapi.dto.*;
import com.code.factory.stundetrestapi.model.Rolecf;
import com.code.factory.stundetrestapi.model.User;
import com.code.factory.stundetrestapi.model.UserRole;
import com.code.factory.stundetrestapi.service.RoleService;
import com.code.factory.stundetrestapi.service.UserRoleService;
import com.code.factory.stundetrestapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static com.code.factory.stundetrestapi.constants.Constants.DEFAULT_ROLE;
import static com.code.factory.stundetrestapi.constants.Constants.TOKEN_TYPE;

@Transactional
@Service
@AllArgsConstructor
public class UserFacade {

    private GenerateJWT generateJWT;
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private RoleService roleService;
    private UserRoleService userRoleService;

    public List<Rolecf> getUserRoles(Integer userId) {
        var roles = userService.getUserRoles(userId);
       return roles;
    }

    public UserDto registerNewUserAccount(UserDto userDto)  {

        User user = new User();
        user.setUsername(userDto.getUserName());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        User userCreated = userService.saveUser(user);


        if(null== userDto.getRoleList() || userDto.getRoleList().isEmpty()) {
            UserRole userRole = new UserRole();
            Rolecf rolecf = roleService.findByRoleName(DEFAULT_ROLE);
            userRole.setIdUserFk(userCreated.getId());
            userRole.setIdRoleFk(rolecf.getId());
            var userRoleCreated = userRoleService.saveUserRole(userRole);
            user.setUserRoles(Arrays.asList(userRoleCreated));
        } else {
            userDto.getRoleList().forEach(userRole -> userRoleService.saveUserRole(userRole));
            user.setUserRoles(userDto.getRoleList());
        }

        UserDto userDtoCreated = new UserDto();
        userDtoCreated.setId(userCreated.getId());
        userDtoCreated.setUserName(userCreated.getUsername());
        userDtoCreated.setRoleList(userCreated.getUserRoles());


        return userDtoCreated;
    }

    public AuthenticatedUserDto authentication(UserDto userDto) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final var jwt = generateJWT.generateToken(authentication);

        var userRolePermissionsDtoList =userService.findUserRoleWithPermission(userDto.getUserName());
        var rolePermissions = goupingByRole(userRolePermissionsDtoList);

        AuthenticatedUserDto authenticatedUserDto = new AuthenticatedUserDto();
        authenticatedUserDto.setTokenType(TOKEN_TYPE);
        authenticatedUserDto.setToken(jwt);
        authenticatedUserDto.setRolePermissions(rolePermissions);

        return authenticatedUserDto;

    }


    private Set<RolePermissionWrapperDto> goupingByRole(List<UserRolePermissionsDto> userRolePermissionsDtoList) {
        Set<RolePermissionWrapperDto> result = userRolePermissionsDtoList.stream()
                .collect(Collectors.groupingBy(UserRolePermissionsDto::getRoleId))
                .entrySet().stream()
                .map(entry -> {
                    RolePermissionWrapperDto wrapperDto = new RolePermissionWrapperDto();
                    RoleDto roleDto = new RoleDto();
                    roleDto.setRoleId(entry.getKey());
                    roleDto.setRoleName(entry.getValue().get(0).getRoleName()); // asumí que todos los elementos tienen el mismo nombr en su rol
                    wrapperDto.setRoleDto(roleDto);
                    Set<PermissionDto> permissions = entry.getValue().stream()
                            .map(dto -> {
                                PermissionDto permissionDto = new PermissionDto();
                                permissionDto.setPermissionId(dto.getPermissionId());
                                permissionDto.setPermissionName(dto.getPermissionName());
                                return permissionDto;
                            })
                            .collect(Collectors.toSet());
                    wrapperDto.setPermissions(permissions);
                    return wrapperDto;
                })
                .collect(Collectors.toSet());

        return result;
    }





}
