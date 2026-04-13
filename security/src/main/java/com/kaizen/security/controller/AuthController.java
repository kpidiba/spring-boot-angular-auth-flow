package com.kaizen.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping; 


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {   
 
    @GetMapping("test")
    public String getMethodName( ) {
        return "Hello World";
    } 
}
