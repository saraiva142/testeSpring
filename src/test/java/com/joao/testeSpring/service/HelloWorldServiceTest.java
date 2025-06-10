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
        assertEquals("Nome não informado", result);
    }

//    Testar o controller para diferentes parâmetros e corpo de requisição -body.

    @Test
    void testHelloWorldDifferentParameters() {
        HelloWorldService service = new HelloWorldService();
        String result = service.helloWorld("Maria");
        assertEquals("Hello Service MARIA", result);
    }

}