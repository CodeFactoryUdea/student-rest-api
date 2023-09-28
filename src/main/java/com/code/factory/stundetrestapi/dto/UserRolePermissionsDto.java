package com.code.factory.stundetrestapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRolePermissionsDto {

    private Integer roleId;

    private String roleName;

    private Integer permissionId;

    private String permissionName;

    public UserRolePermissionsDto(Integer roleId, String roleName, Integer permissionId, String permissionName) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.permissionId = permissionId;
        this.permissionName = permissionName;
    }



}
