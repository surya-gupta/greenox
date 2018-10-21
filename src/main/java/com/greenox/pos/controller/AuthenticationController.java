package com.greenox.pos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class AuthenticationController {
    @GetMapping("/api/user")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }
}
