package com.damgor.listapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/home")
@RestController
public class HomeController {

    @GetMapping
    public String getWelcomeMessage() {
        return "Welcome in list-app!";
    }
}
