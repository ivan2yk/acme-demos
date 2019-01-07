package com.acme.portal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Ivan on 6/01/2019.
 */
@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(path = {"/home", "/home.html"})
    public String home() {
        logger.info("Show home.jsp");
        return "home";
    }

    @GetMapping(path = {"/login", "/login.html"})
    public String login() {
        return "login";
    }

}
