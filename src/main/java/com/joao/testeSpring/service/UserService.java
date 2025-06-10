package com.joao.testeSpring.service;

import com.joao.testeSpring.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

@Service
public class UserService {

//    Verificar se o nome contém números e rejeitar.
//    Retornar saudações diferentes conforme o horário do dia.
//    Simular busca de usuário em um "banco de dados" (pode ser um Map em memória).

    private final Map<String, User> users = new HashMap<>();

    public UserService(){
        users.put("joao", new User("Joao", "joao@email.com"));
        users.put("maria", new User("Maria", "maria@email.com"));
    }

    public User findUserByName(String name) {
        if (name == null || name.matches(".*\\d.*")){
            throw new IllegalArgumentException("Nome Inválido");
        }
        return users.getOrDefault(name.toLowerCase(), null);
    }

    public List<User> listAllUsers() {
        return new ArrayList<>(users.values());
    }
}
