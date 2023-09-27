package com.code.factory.stundetrestapi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "role_permission")
@Data
public class RolePermission {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_role_fk")
    private Integer idRoleFk;

    @Column(name = "id_permission_fk")
    private Integer idPermissionFk;

    @ManyToOne
    @JoinColumn(name = "id_role_fk", insertable = false, updatable = false, nullable = false)
    private Rolecf roleFk;

    @ManyToOne
    @JoinColumn(name = "id_permission_fk", insertable = false, updatable = false, nullable = false)
    private Permission permissionFk;

}
