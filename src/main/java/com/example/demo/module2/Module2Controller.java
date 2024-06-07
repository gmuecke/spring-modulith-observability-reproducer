package com.example.demo.module2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/module2")
class Module2Controller implements Module2RestAPI {
    @Override
    public String getModule1() {
        return "ok";
    }
}