package com.example.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccessDenialController {

    @PostMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

}
