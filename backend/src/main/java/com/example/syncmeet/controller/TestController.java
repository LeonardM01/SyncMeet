package com.example.syncmeet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController for testing purposes
 * To be deleted before deployment
 */
@RestController
public class TestController {

    @PostMapping("/api/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Success");
    }
}
