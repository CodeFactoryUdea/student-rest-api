package com.code.factory.stundetrestapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userFk")
    private List<UserRole> userRoles;

}
