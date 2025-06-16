package com.joao.testeSpring.controller;

import com.joao.testeSpring.domain.User;
import com.joao.testeSpring.service.HelloWorldService;
import com.joao.testeSpring.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HelloWorldControllerTest {

    @Test
    @DisplayName("Deve retornar Hello World")
    void testHelloWorld() {
        HelloWorldService serviceMock = mock(HelloWorldService.class);
        HelloWorldController controller = new HelloWorldController();

        // Injetando o mock do service no controller
        java.lang.reflect.Field field;
        try {
            field = HelloWorldController.class.getDeclaredField("helloWorldService");
            field.setAccessible(true);
            field.set(controller, serviceMock);
        } catch (Exception e) {
            fail("Erro ao injetar mock: " + e.getMessage());
        }

        // Definindo o comportamento do mock
        when(serviceMock.helloWorld("João")).thenReturn("Hello Service JOÃO");

        String result = controller.helloWorld("João");
        assertEquals("Hello Service JOÃO", result);
    }

    @Test
    @DisplayName("Deve retornar bloqueado")
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
    @DisplayName("Deve retornar mensagem de erro")
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
    @DisplayName("Deve retornar que não passou filtro")
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
    @DisplayName("Deve retornar Hello Post com filtro")
    void testHelloWorlFilterPost() {
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

        User user = new User("Fulano", "fulano@email.com");
        String result = controller.helloWorldPost("123", "filtro", user);
        assertEquals("Hello Post filtro para Fulano", result);
    }

    @Test
    @DisplayName("Deve retornar no invertido e qtd de caracteres")
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

    @Test
    @DisplayName("Deve retornar o Wellcome, user !")
    void testWellcomeUser() {
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

        User user = new User("Isabela", "isa@email.com");
        String result = controller.welcomeUser(user).getBody();
        assertEquals("Bem vindo, Isabela !", result);
    }

    @Test
    @DisplayName("Deve retornar Bad Request para usuário inválido")
    void testWellcomeUserInvalid() {
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

        User user = new User(null, null);
        String result = controller.welcomeUser(user).getBody();
        assertEquals("Usuário invalido !", result);
    }

    @Test
    @DisplayName("Deve retornar Bad Request para usuário nulo")
    void testWellcomeUserNull() {
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

        String result = controller.welcomeUser(null).getBody();
        assertEquals("Usuário invalido !", result);
    }

}