package com.summitbra.cryptography.controller;


import com.summitbra.cryptography.config.LogConfig;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/v1")
public class VerifyController {

    private final LogConfig logger = new LogConfig();

    @GetMapping("/verify")
    public String verify(){
        logger.setInfoLogging("(GET) => /v1/verify");
        return "OK";
    }
}
