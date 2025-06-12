package com.joao.testeSpring.controller;

import com.joao.testeSpring.domain.User;
import com.joao.testeSpring.service.HelloWorldService;
import com.joao.testeSpring.service.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HelloWorldControllerTest {

    @Test
    void testeHelloWorldFilterBlock(){
        HelloWorldService serviceMock = mock(HelloWorldService.class);
        HelloWorldController controller = new HelloWorldController();

        java.lang.reflect.Field field;
        try {
            field = HelloWorldController.class.getDeclaredField("helloWorldService");
            field.setAccessible(true);
            field.set(controller, serviceMock);
        } catch (Exception e) {
            fail("Erro ao injetar mock: " + e.getMessage());
        }

        User user = new User("João", "joao@email.com");
        String result = controller.helloWorldPost("1231", "block", user);
        assertEquals("Ação bloqueada para o usuário com ID: 1231", result);
    }

    @Test
    void testHelloWorldFilterError(){
        HelloWorldService serviceMock = mock(HelloWorldService.class);
        HelloWorldController controller = new HelloWorldController();

        java.lang.reflect.Field field;
        try {
            field = HelloWorldController.class.getDeclaredField("helloWorldService");
            field.setAccessible(true);
            field.set(controller, serviceMock);
        } catch (Exception e) {
            fail("Erro ao injetar mock: " + e.getMessage());
        }

        User user = new User("Pedro", "pedro@email.com");
        String result = controller.helloWorldPost("099", "error", user);
        assertEquals("Erro ao processar a requisição para o usuário com o ID: 099", result);
    }

    @Test
    void testHelloWorldFilterEmpty() {
        HelloWorldService serviceMock = mock(HelloWorldService.class);
        HelloWorldController controller = new HelloWorldController();

        java.lang.reflect.Field field;
        try {
            field = HelloWorldController.class.getDeclaredField("helloWorldService");
            field.setAccessible(true);
            field.set(controller, serviceMock);
        } catch (Exception e) {
            fail("Error ao injetar mock: " + e.getMessage());
        }

        User user = new User("Ciclano", "ciclano@email.com");
        String result = controller.helloWorldPost("0321", "nenhum", user);
        assertEquals("Você não passou nada no filtro, então ele está com 'nenhum'", result);
    }

    @Test
    void testHelloWorldReverse() {
        HelloWorldService serviceMock = mock(HelloWorldService.class);
        HelloWorldController controller = new HelloWorldController();

        java.lang.reflect.Field field;
        try {
            field = HelloWorldController.class.getDeclaredField("helloWorldService");
            field.setAccessible(true);
            field.set(controller, serviceMock);
        } catch (Exception e) {
            fail("Erro ao injetar mock: " + e.getMessage());
        }

        User user = new User("arara", "arara@email.com");
        String result = controller.reverseUserName(user);
        assertEquals("Nome invertido: arara (5 caracteres)", result);
    }

}