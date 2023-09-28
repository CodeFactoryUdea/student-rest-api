package com.code.factory.stundetrestapi.dto;

import com.code.factory.stundetrestapi.model.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Integer id;
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private List<UserRole> roleList;

}
