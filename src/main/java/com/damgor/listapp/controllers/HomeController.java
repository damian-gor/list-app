package com.damgor.listapp.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/home")
@RestController
public class HomeController {

    @Value("${application.name}")
    private String applicationName;

    @GetMapping
    public String getWelcomeMessage() {
        return "Witaj w aplikacji " + applicationName + "!";
    }
}
