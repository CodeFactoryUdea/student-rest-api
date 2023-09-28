package com.code.factory.stundetrestapi.controller;

import com.code.factory.stundetrestapi.dto.AuthenticatedUserDto;
import com.code.factory.stundetrestapi.dto.UserDto;
import com.code.factory.stundetrestapi.facade.UserFacade;
import com.code.factory.stundetrestapi.model.Rolecf;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<List<Rolecf>> findRoleByUserId(@PathVariable Integer id) {
        var rolecfList = userFacade.getUserRoles(id);

        return ResponseEntity.ok(rolecfList);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticatedUserDto> login(@RequestBody UserDto userDto) {
        var user = userFacade.authentication(userDto);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/registration")
    public ResponseEntity<UserDto> registration(@RequestBody UserDto userDto) {
        var user = userFacade.registerNewUserAccount(userDto);

        return ResponseEntity.ok(user);
    }
}
