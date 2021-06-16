package com.commerce.song.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/api/v1/test")
    public String testApi() {
        return "test 성공";
    }
    @GetMapping("/api/v1/admin/test")
    public String testApi2() {
        return "test 2 성공";
    }
}
