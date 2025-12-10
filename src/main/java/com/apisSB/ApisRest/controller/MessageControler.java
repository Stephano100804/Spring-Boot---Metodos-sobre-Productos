package com.apisSB.ApisRest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.apisSB.ApisRest.entity.Messaje;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/messages")
public class MessageControler {
    
    private List<Messaje> messages = new ArrayList<>();

    public MessageControler() {
        messages.add(new Messaje(1, "Hello, World!"));
        messages.add(new Messaje(2, "Welcome to the API."));
    }
    
    @GetMapping
    public List<Messaje> getAllMessages() {
        return messages;
    }
    
    @GetMapping("/{id}")
    public Messaje getMessageById(@PathVariable int id) {
        return messages.stream()
                .filter(msg -> msg.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    @PostMapping("/insertar")
    public Messaje crearMsg(@RequestBody Messaje msg) {
        messages.add(msg);
        return msg;
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarmsg(@PathVariable int id){
        messages.removeIf(msg -> msg.getId() == id);
    }
}