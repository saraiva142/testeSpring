package com.joao.testeSpring.service;

import org.springframework.stereotype.Service;

//@Service
//public class HelloWorldService {
//    public String helloWorld(String name){
//        return "Hello Service " + name;
//    }
//}


/* Implementar helloWorld com as seguintes regras, para implementar o teste unitário:

* Retornar mensagem personalizada se o nome for vazio ou nulo.

* Lançar exceção se o nome for "erro".

* Adicionar lógica para transformar o nome em maiúsculas.

* */

@Service
public class HelloWorldService {
    public String helloWorld(String name) {
        if (name == null || name.isEmpty()){
            return "Nome não Informado";
        }
        if ("erro".equalsIgnoreCase(name)) {
            throw new IllegalArgumentException(("Nome inválido"));
        }
        return "Hello Service " + name.toUpperCase();
    }
}