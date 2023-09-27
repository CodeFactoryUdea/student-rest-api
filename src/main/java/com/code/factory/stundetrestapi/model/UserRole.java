package com.code.factory.stundetrestapi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_rolecf")
@Data
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_user_fk")
    private Integer idUserFk;

    @Column(name = "id_role_fk")
    private Integer idRoleFk;

    @ManyToOne
    @JoinColumn(name = "id_user_fk", insertable = false, updatable = false, nullable = false)
    private User userFk;

    @ManyToOne
    @JoinColumn(name = "id_role_fk", insertable = false, updatable = false, nullable = false)
    private Rolecf roleFk;
}
