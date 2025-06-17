package com.joao.testeSpring.controller;

import com.joao.testeSpring.domain.User;
import com.joao.testeSpring.service.HelloWorldService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @GetMapping //Fazendo metodo para usar no caso de teste. onde o teste unitário chama o metodo helloWorld e
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
        if ("error".equalsIgnoreCase(filter)) {
            return "Erro ao processar a requisição para o usuário com o ID: " + id;
        }
        if ("nenhum".equalsIgnoreCase(filter)) {
            return "Você não passou nada no filtro, então ele está com 'nenhum'";
        }
        return "Hello Post " + filter + " para " + body.getName();
    }


    @PostMapping("/reverse")
    public String reverseUserName(@RequestBody @NotNull User user) {
        String reversed = new StringBuilder(user.getName()).reverse().toString();
        int lenght = user.getName().length();
        return "Nome invertido: " + reversed + " (" + lenght + " caracteres)";
    }

    @PostMapping("/wellcome")
    public ResponseEntity<String> welcomeUser(@RequestBody User user) {
        if (user == null || user.getName() == null) {
            return ResponseEntity.badRequest().body("Usuário invalido !");
        }
        return ResponseEntity.ok("Bem vindo, " + user.getName() + " !");
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (user == null){
            return ResponseEntity.badRequest().body("Usuário não informado !");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            return ResponseEntity.badRequest().body("Nome obrigatório !");
        }
        if (user.getName().matches(".*\\d.*")) {
            return ResponseEntity.badRequest().body("Nome não pode conter números !");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("E-mail obrigatório !");
        }
        if (!user.getEmail().contains("@") || user.getEmail().split("@").length != 2) {
            return ResponseEntity.badRequest().body("E-mail inválido !");
        }
        return ResponseEntity.ok("Usuário " + user.getName() + " cadastrado com sucesso !");
    }

    @PostMapping("/personalize-wellcome")
    public ResponseEntity<String> personalizeWellcome(@RequestBody User user) {
        if (user == null || user.getName() == null || user.getName().isBlank()) {
            return ResponseEntity.badRequest().body("Ops! Não consigo te dar as boas-vindas sem um nome.");
        }

        String name = user.getName().trim();
        int nameLength = name.length();
        String wellcomeMessage;

        if (name.length() <= 4) {
            wellcomeMessage = "E aí, " + name + "! Que bom te ver por aqui! Prepara-se para a aventura!";
        } else if (nameLength <= 8) {
            wellcomeMessage = "Olá, " + name + " O universo te espera seu otário!";
        } else {
            wellcomeMessage = "Salve, " + name + "! Saiba que nunca é tarde para desistir!";
        }

        return ResponseEntity.ok(wellcomeMessage);
    }


}
