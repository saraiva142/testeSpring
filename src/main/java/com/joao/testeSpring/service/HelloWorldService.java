package com.joao.testeSpring.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {
    public String helloWorld(String name){
        return "Hello Service " + name;
    }
}
