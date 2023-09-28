package com.code.factory.stundetrestapi.dto;

import com.code.factory.stundetrestapi.model.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Integer id;
    private String userName;
    private String password;
    private List<UserRole> roleList;

}
