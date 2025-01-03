package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebGreetingController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Название HTML-файла без расширения
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
