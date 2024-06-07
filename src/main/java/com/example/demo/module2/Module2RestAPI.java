package com.example.demo.module2;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;

public interface Module2RestAPI {

    @Operation(summary = "Test module")
    @GetMapping("/test")
    String getModule1();
}