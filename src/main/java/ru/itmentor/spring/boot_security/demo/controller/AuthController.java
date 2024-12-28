package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.PersonService;

import javax.validation.Valid;
import java.util.Optional;


@Controller
public class AuthController {

    private final PersonService personService;


    @Autowired
    public AuthController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/admin")
    public String indexOfAllModel(Model model) {
        model.addAttribute("allPeople", personService.upindex());
        return "/peoples";
    }

    @GetMapping("/{id}")
    public String showId(@PathVariable("id") int id, Model model) {
        model.addAttribute("showPerson", personService.show(id));
        return "/show";

    }

    @GetMapping("/new")
    public String newPerson(Model model) {

        model.addAttribute("personCreated", new User());

        return "/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("personCreated")  User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/new";
        }
        personService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("personEdit", personService.show(id));
        return "/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("personEdit")  User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/edit";
        }

        personService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
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
    public String getUserInfo(Authentication authentication, Model model) {
        String userName = authentication.getName();
        Optional<User> user = personService.getUserByUsername(userName);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user";
        } else {
            return "index";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

}