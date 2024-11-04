package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.servise.PersServ;

import javax.validation.Valid;

@Controller
public class AuthController {

    private  PersServ service;

    @Autowired
    public AuthController(PersServ service) {
        this.service = service;
    }

    @GetMapping("/admin")
    public String indexOfAllModel(Model model) {

        model.addAttribute("allPeople", service.upindex());
        return "/peoples";
    }

    @GetMapping("/{id}")
    public String showId(@PathVariable("id") int id, Model model) {
        model.addAttribute("showPerson", service.show(id));
        return "/show";

    }

    @GetMapping("/new")
    public String newPerson(Model model) {

        model.addAttribute("personCreated", new User());

        return"/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("personCreated") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/new";
        }
        service.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("personEdit", service.show(id));
        return "/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("personEdit") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/edit";
        }

        service.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        service.delete(id);
        return "redirect:/admin";
    }



    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Название HTML-файла без расширения
    }

    @GetMapping("/index")
    public String showAdminPage() {
        return "index";
    }

    @GetMapping("/user")
    public String showUserPage() {
        return "user";
    }

@GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

}