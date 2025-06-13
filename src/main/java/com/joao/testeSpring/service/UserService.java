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
        users.put("thiago", new User("Thiago", "thiago@email.com"));
        users.put("fulano", new User("Fulano", "fulano@email.com"));
        users.put("marcos", new User("Marcos", "marcos@email.com"));
        users.put("eduarda", new User("Eduarda", "eduarda@email.com"));
        users.put("ana", new User("Ana", "ana@email.com"));
        users.put("carlos", new User("Carlos", "carlos@email.com"));
        users.put("beatriz", new User("Beatriz", "beatriz@email.com"));
        users.put("lucas", new User("Lucas", "lucas@email.com"));
        users.put("paula", new User("Paula", "paula@email.com"));
        users.put("fernando", new User("Fernando", "fernando@email.com"));
        users.put("amanda", new User("Amanda", "amanda@email.com"));
        users.put("rafael", new User("Rafael", "rafael@email.com"));
        users.put("juliana", new User("Juliana", "juliana@email.com"));
        users.put("rodrigo", new User("Rodrigo", "rodrigo@email.com"));
        users.put("patricia", new User("Patricia", "patricia@email.com"));
        users.put("bruno", new User("Bruno", "bruno@email.com"));
        users.put("camila", new User("Camila", "camila@email.com"));
        users.put("gustavo", new User("Gustavo", "gustavo@email.com"));
        users.put("aline", new User("Aline", "aline@email.com"));
        users.put("vinicius", new User("Vinicius", "vinicius@email.com"));
        users.put("leticia", new User("Leticia", "leticia@email.com"));
        users.put("daniel", new User("Daniel", "daniel@email.com"));
        users.put("larissa", new User("Larissa", "larissa@email.com"));
        users.put("andre", new User("Andre", "andre@email.com"));
        users.put("carolina", new User("Carolina", "carolina@email.com"));
        users.put("felipe", new User("Felipe", "felipe@email.com"));
        users.put("isabela", new User("Isabela", "isabela@email.com"));
        users.put("ricardo", new User("Ricardo", "ricardo@email.com"));
        users.put("sabrina", new User("Sabrina", "sabrina@email.com"));
        users.put("leandro", new User("Leandro", "leandro@email.com"));
        users.put("priscila", new User("Priscila", "priscila@email.com"));
        users.put("mateus", new User("Mateus", "mateus@email.com"));
        users.put("vanessa", new User("Vanessa", "vanessa@email.com"));
        users.put("jorge", new User("Jorge", "jorge@email.com"));
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
