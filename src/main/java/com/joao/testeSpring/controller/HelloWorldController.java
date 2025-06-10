package com.joao.testeSpring.controller;

import com.joao.testeSpring.domain.User;
import com.joao.testeSpring.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    // Get no end-point /hello-world
//    @GetMapping
//    public String helloWorld() {
//        return helloWorldService.helloWorld("Joao");
//    }
    @GetMapping //Fazendo método para usar no caso de teste. onde o teste unitário chama o método helloWorld e
    // retorna a mensagem "Hello Service Joao" onde o teste unitário verifica se a mensagem é igual a "Hello Service Joao"
    public String helloWorld(@RequestParam(value = "name", defaultValue = "Joao") String name){
        return helloWorldService.helloWorld(name);
    }
    /*
     @PostMapping("/test")
    public String test() {
        return "s";
    }
    */

    @PostMapping("/{id}")
    public String helloWorldPost(@PathVariable("id") String id, @RequestParam(value = "filter", defaultValue = "nenhum") String filter, @RequestBody User body) {
//        return "Hello Posting " + filter; vamos mudar para fazer os casos de teste unitário
        if ("block".equalsIgnoreCase(filter)) {
            return "Ação bloqueada para o usuário com ID: " + id;
        }
        return "Hello Post " + filter + " para " + body.getName();
    }

}
