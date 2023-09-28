package com.code.factory.stundetrestapi.repository;

import com.code.factory.stundetrestapi.dto.UserRolePermissionsDto;
import com.code.factory.stundetrestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String userName);

    @Query("SELECT new com.code.factory.stundetrestapi.dto.UserRolePermissionsDto(r.id, r.roleName, " +
            "rp.idPermissionFk, p.permissionName) " +
            "FROM User u " +
            "INNER JOIN UserRole ur ON u.id = ur.idUserFk " +
            "INNER JOIN Rolecf r ON ur.idRoleFk = r.id " +
            "INNER JOIN RolePermission rp ON r.id = rp.idRoleFk " +
            "INNER JOIN Permission p ON rp.idPermissionFk = p.id " +
            "WHERE u.username = :username")
    List<UserRolePermissionsDto> findUserRoleWithPermission(@Param("username") String username);

}
