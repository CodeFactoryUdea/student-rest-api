package com.code.factory.stundetrestapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class RolePermissionWrapperDto {

    private RoleDto roleDto;
    private Set<PermissionDto> permissions = new HashSet<>();;
}
