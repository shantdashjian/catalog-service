package com.cloudnativebookshop.catalogservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getGreeting() {
        return "Welcome to the Cloud Native Bookshop catalog!";
    }
}
