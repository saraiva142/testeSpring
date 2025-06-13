package com.joao.testeSpring.service;

import com.joao.testeSpring.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

//    Buscar usuário existente e inexistente.
//    Buscar usuário com nome inválido (com número).
//    Listar todos os usuários.

    @Test
    @DisplayName("Deve retornar o usuário")
    void testFindExistingUser() {
        UserService userService = new UserService();
        User user = userService.findUserByName("maria");
        assertNotNull(user);
        assertEquals("Maria", user.getName());
    }

    @Test
    @DisplayName("Deve retornar null, pois não existe usuário")
    void testFindNonExistingUser() {
        UserService userService = new UserService();
        User user = userService.findUserByName("pedro");
        assertNull(user);
        assertEquals(null, userService.findUserByName("pedro"));
    }

    @Test
    @DisplayName("Deve retornar que o nome não é válido")
    void testFindUserWithInvalidName() {
        UserService userService = new UserService();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.findUserByName("joao123");
        });
        assertEquals("Nome Inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Deve retornanar quantos usuários existem")
    void testListAllUsers() {
        UserService userService = new UserService();
        assertFalse(userService.listAllUsers().isEmpty());
        assertEquals(36, userService.listAllUsers().size());
    }

}