package com.code.factory.stundetrestapi.controller;

import com.code.factory.stundetrestapi.model.Rolecf;
import com.code.factory.stundetrestapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-roles/{id}")
    public ResponseEntity<List<Rolecf>> findRoleByUserId(@PathVariable Integer id) {
        var rolecfList = userService.getUserRoles(id);

        return ResponseEntity.ok(rolecfList);
    }
}
