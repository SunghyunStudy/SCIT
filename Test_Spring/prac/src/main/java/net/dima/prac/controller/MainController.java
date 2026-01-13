package net.dima.prac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index.html"; // resources/static/index.html 또는 templates/index.html을 찾음
    }
}
