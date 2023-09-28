package com.code.factory.stundetrestapi.dto;


import lombok.Data;

import java.util.Set;

@Data
public class AuthenticatedUserDto {
    private String token;
    private String tokenType;
    private Set<RolePermissionWrapperDto> rolePermissions;


}
