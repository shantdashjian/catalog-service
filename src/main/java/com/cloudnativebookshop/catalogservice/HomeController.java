package com.cloudnativebookshop.catalogservice;

import com.cloudnativebookshop.catalogservice.config.CloudNativeProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    private final CloudNativeProperties cloudNativeProperties;

    public HomeController(CloudNativeProperties cloudNativeProperties) {
        this.cloudNativeProperties = cloudNativeProperties;
    }

    @GetMapping
    public String getGreeting() {
        return cloudNativeProperties.getGreeting();
    }
}
