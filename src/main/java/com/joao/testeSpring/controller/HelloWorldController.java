package com.joao.testeSpring.controller;

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
    @GetMapping
    public String helloWorld() {
        return helloWorldService.helloWorld("Joao");
    }
    @PostMapping("/test")
    public String test() {
        return "s";
    }
}
