package com.code.factory.stundetrestapi.service;

import com.code.factory.stundetrestapi.model.Rolecf;
import com.code.factory.stundetrestapi.model.User;
import com.code.factory.stundetrestapi.model.UserRole;
import com.code.factory.stundetrestapi.repository.UserRepository;
import com.code.factory.stundetrestapi.repository.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserRoleRepository userRoleRepository;


    @InjectMocks
    private UserService userService;


    @Test
    public void getUserRoles_success() {

        // Arrange
        List<UserRole> userRoles = new ArrayList<>();
        UserRole userRole1 = new UserRole();
        userRole1.setIdRoleFk(1);
        userRole1.setIdUserFk(1);
        userRole1.setId(1);
        UserRole userRole2 = new UserRole();
        userRole2.setIdRoleFk(2);
        userRole2.setIdUserFk(1);
        userRole2.setId(2);

        userRoles.add(userRole1);
        userRoles.add(userRole2);

        User user = new User();
        user.setUserRoles(userRoles);

        List<Rolecf> listaRoles = new ArrayList<>();
        Rolecf role1 = new Rolecf();
        role1.setId(1);
        role1.setRoleName("ROLE_TEST");
        listaRoles.add(role1);

        UserRepository userRepository1 = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository1.findById(Mockito.anyInt())).thenReturn(Optional.of(user));

        UserService userService1 = new UserService(userRepository1, userRoleRepository);

        // Act
        var result = userService1.getUserRoles(Mockito.anyInt());

        // Assert

        assertNotNull(result);
        assertEquals(2, result.size());


    }

}
