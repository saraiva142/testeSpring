package com.joao.testeSpring.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloWorldServiceTest {

    @Test
    void testHelloWorld() {
        HelloWorldService service = new HelloWorldService();
        String result = service.helloWorld("João");
        assertEquals("Hello Service JOÃO", result);
    }

//    Verificar o retorno para nome vazio, nulo, normal e "erro" no service.

    @Test
    void testHelloWorldEmpty() {
        HelloWorldService service = new HelloWorldService();
        String result = service.helloWorld("");
        assertEquals("Nome não Informado", result);
    }

    @Test
    void testHelloWorldNull() {
        HelloWorldService service = new HelloWorldService();
        String result = service.helloWorld(null);
        assertEquals("Nome não Informado", result);
    }

    @Test
    void testHelloWorldError() {
        HelloWorldService service = new HelloWorldService();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.helloWorld("erro");
        });
        assertEquals("Nome inválido", exception.getMessage());
    }


//    Testar o controller para diferentes parâmetros e corpo de requisição -body.

    @Test
    void testHelloWorldDifferentParameters() {
        HelloWorldService service = new HelloWorldService();
        String result = service.helloWorld("Maria");
        assertEquals("Hello Service MARIA", result);
    }

}