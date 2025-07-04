package dev.mark.chabot_using_gemini.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthTest {

    @GetMapping("/health")
    public String health() {
        return "hello world";
    }
}
