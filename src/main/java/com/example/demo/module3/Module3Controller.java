package com.example.demo.module3;

import com.example.demo.module2.Module2RestAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/module3")
class Module3Controller implements MyInterface {
    @GetMapping("/test")
    public String test() {
        return "ok";
    }
}