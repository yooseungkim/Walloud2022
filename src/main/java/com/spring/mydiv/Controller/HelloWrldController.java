// src/main/java/com.demogroup.demoweb/Controller/HelloWorldController.java

package com.spring.mydiv.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWrldController {

    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }
}