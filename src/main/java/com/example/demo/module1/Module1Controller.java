package com.example.demo.module1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/module1")
class Module1Controller {
    @GetMapping("/test")
    public String getModule2() {
        return "ok";
    }
}