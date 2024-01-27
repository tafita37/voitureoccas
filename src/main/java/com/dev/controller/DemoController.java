package com.dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
        
    
    @GetMapping(path = "/hello" , produces = "application/json")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Hello from secured endpoints");
    }
}
