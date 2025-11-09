package com.tiago.midia_review.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public String helloWorld() {
        String helloWorld = "Hello, World! I'm alredy on 🚀"; 
        return helloWorld;
    }
    
    
}