package com.joao.testeSpring.controller;

import com.joao.testeSpring.domain.User;
import com.joao.testeSpring.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

//    teste simular o comportamento do service sem acessar dados reais.

    @Test
    @DisplayName("Deve retornar 200 se encontrar o usuário")
    void testUserFound(){

        //Vamos criar um Mock do UserService, ou seja mockar o comportamento do serviço sem acessar dados reais.
        UserService userService = mock(UserService.class);

        //Criar o controller injetando o mock do service, ou seja, o controller vai usar o service mockado.
        UserController controller = new UserController(userService);

        //Definir o comportamento do mock, ou seja, quando o metodo findUserByName for chamado com o parâmetro "maria",
        // ele vai retornar um usuário com o nome "Maria".
        when(userService.findUserByName("maria")).thenReturn(new User("Maria", "maria@email.com"));

        //Chamar o metodo do controller que vai usar o service mockado, ou seja, executar o metodo do controller que vai chamar o service mockado.
        ResponseEntity<User> response = controller.getUser("maria");

        //Verificar se o retorno é 200 OK e se o usuário retornado tem o nome "Maria", o que significa que o mock funcionou corretamente.
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Maria", response.getBody().getName());
    }

    @Test
    @DisplayName("Deve retornar 404 se não encontrar o usuário")
    void testUserNotFound() {

        //Criar um mock do UserService.
        UserService userService = mock(UserService.class);

        //Criar o controller injetando o mock do service.
        UserController controller = new UserController(userService);

        //Definir o comportamento do mock, ou seja, quando o metodo findUserByName for chamado com o parâmetro "joao",
        // ele vai retornar null, ou seja, usuário não encontrado.
        when(userService.findUserByName("joao")).thenReturn(null);

        //Chamar o metodo do controller que vai usar o service mockado.
        ResponseEntity<User> response = controller.getUser("joao");

        //Verificar se o retorno é 404 NOT FOUND, ou seja, usuário não encontrado.
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Deve retornar qts pessoas possuem")
    void testListUsersTripleA() {
        //Regra do tripleA:
        //Arrange: Configurar o mock e no caso o controller
        //Act: Executar o metodo do controller (o qual queremos testar)
        //Assert: Verificar o resultado do metodo rodado

        //Arrange
        UserService userService = mock(UserService.class);
        UserController controller = new UserController(userService);
        List<User> mockUser = List.of(
                new User("Joao", "joao@email.com"),
                new User("Maria", "maria@email"),
                new User("Fulano", "fulano@email.com")
        );
        when(userService.listAllUsers()).thenReturn(mockUser);

        //Act
        List<User> result = controller.lisUsers();

        //Assert
        assertEquals(3, result.size());
        assertEquals("Joao", result.get(0).getName());
        assertEquals("Maria", result.get(1).getName());
    }

    @Test
    @DisplayName("Deve retornar nome invalido para usuários com int")
    void testGuestUser_InvalidName_ThrowsException() {
        //Arrange
        UserService userService = mock(UserService.class);
        UserController controller = new UserController(userService);
        when(userService.findUserByName("joao123")).thenThrow(new IllegalArgumentException("Nome Inválido"));

        //Act & Arrange
        assertThrows(IllegalArgumentException.class, () -> controller.getUser("joao123"));

    }

    @Test
    @DisplayName("Deve retornar que a lista de usários ta vazia")
    void testListUsers_EmptyList() {
        //Arrange
        UserService userService = mock(UserService.class);
        UserController controller = new UserController(userService);

        when(userService.listAllUsers()).thenReturn(List.of());

        //Act
        List<User> result = controller.lisUsers();

        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Tentando buscar um usuário com nome Maiúsculo")
    void testGuestUser_CaseInsensitive() {
        //Arrange
        UserService userService = mock(UserService.class);
        UserController controller = new UserController(userService);
        when(userService.findUserByName(anyString())).thenReturn(new User("Fifi", "maria@email.com"));

        //Act
        ResponseEntity<User> response = controller.getUser("FIFI");

        //Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Fifi", response.getBody().getName());
    }

    @Test
    @DisplayName("Deve retornar usuário ao buscar por e-mail")
    void testGuestUserByEmail_Found() {
        //Arrange
        UserService userService = mock(UserService.class);
        UserController controller = new UserController(userService);
        User maria = new User("Maria", "maria@email.com");
        when(userService.listAllUsers()).thenReturn(List.of(maria));

        //Act
        ResponseEntity<User> response = controller.getUserByEmail("maria@email.com");

        //Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Maria", response.getBody().getName());
    }

    @Test
    @DisplayName("Deve retornar 404 ao buscar e-mail inexistente")
    void testGuestUserByEmail_NotFound() {
        //Arrange
        UserService userService = mock(UserService.class);
        UserController controller = new UserController(userService);
        when(userService.listAllUsers()).thenReturn(List.of());

        //Act
        ResponseEntity<User> response = controller.getUserByEmail("naoexiste@email.com");

        //Assert
        assertEquals(404, response.getStatusCodeValue());

    }

}