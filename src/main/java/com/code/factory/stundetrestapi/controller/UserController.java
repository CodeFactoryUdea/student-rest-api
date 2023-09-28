package com.code.factory.stundetrestapi.controller;

import com.code.factory.stundetrestapi.dto.UserDto;
import com.code.factory.stundetrestapi.dto.UserSingIn;
import com.code.factory.stundetrestapi.facade.UserFacade;
import com.code.factory.stundetrestapi.model.Rolecf;
import com.code.factory.stundetrestapi.model.User;
import com.code.factory.stundetrestapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserFacade userFacade;

    public UserController(UserService userService, UserFacade userFacade) {
        this.userService = userService;
        this.userFacade = userFacade;
    }

    @GetMapping("/user-roles/{id}")
    public ResponseEntity<List<Rolecf>> findRoleByUserId(@PathVariable Integer id) {
        var rolecfList = userService.getUserRoles(id);

        return ResponseEntity.ok(rolecfList);
    }

    @PostMapping("/login")
    public ResponseEntity<UserSingIn> login(@RequestBody UserDto userDto) {
        var user = userFacade.authentication(userDto);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody UserDto userDto) {
        var user = userService.registerNewUserAccount(userDto);

        return ResponseEntity.ok(user);
    }
}
