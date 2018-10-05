package com.greenox.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    String sayHello() {
        LOG.info("Hello World");
        return "Hello World";
    }
}