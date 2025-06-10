package com.joao.testeSpring.controller;

import com.joao.testeSpring.domain.User;
import com.joao.testeSpring.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

//    teste simular o comportamento do service sem acessar dados reais.

    @Test
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
}