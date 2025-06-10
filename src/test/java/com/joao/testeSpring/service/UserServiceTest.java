package com.joao.testeSpring.service;

import com.joao.testeSpring.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

//    Buscar usuário existente e inexistente.
//    Buscar usuário com nome inválido (com número).
//    Listar todos os usuários.

    @Test
    void testFindExistingUser() {
        UserService userService = new UserService();
        User user = userService.findUserByName("maria");
        assertNotNull(user);
        assertEquals("Maria", user.getName());
    }

    @Test
    void testFindNonExistingUser() {
        UserService userService = new UserService();
        User user = userService.findUserByName("pedro");
        assertNull(user);
        assertEquals(null, userService.findUserByName("pedro"));
    }

    @Test
    void testFindUserWithInvalidName() {
        UserService userService = new UserService();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.findUserByName("joao123");
        });
        assertEquals("Nome Inválido", exception.getMessage());
    }

    @Test
    void testListAllUsers() {
        UserService userService = new UserService();
        assertFalse(userService.listAllUsers().isEmpty());
        assertEquals(2, userService.listAllUsers().size());
    }

}