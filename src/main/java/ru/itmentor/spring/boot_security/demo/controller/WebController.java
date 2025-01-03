package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.PersonServiceImpl;

import java.util.Optional;


@org.springframework.stereotype.Controller
@RequestMapping("/web")
public class WebController {

    private final PersonServiceImpl personService;


    @Autowired
    public WebController(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @GetMapping("/admin")
    public String indexOfAllModel(Model model) {
        model.addAttribute("allPeople", personService.showAll());
        return "/peoples";
    }

    @GetMapping("/{id}")
    public String showId(@PathVariable("id") int id, Model model) {
        model.addAttribute("showPerson", personService.show(id));
        return "/show";

    }















    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("createPerson", new User());
        return "/create";
    }

    @PostMapping()
    public String create(@ModelAttribute("createPerson") User user) {
        personService.save(user);
        return "redirect:/web/admin";
    }


    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("updatePerson", personService.show(id));
        return "/update";
    }

    @PutMapping("/{id}")
    public String update(@ModelAttribute("updatePerson") User user,
                         @PathVariable("id") int id) {
        System.out.println("update // метод обновления. id = " + id + " , user = " + user);
        personService.update(id, user);
        return "redirect:/web/admin";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        System.out.println("delete // метод удаления. id = " + id);
        personService.delete(id);
        return "redirect:/web/admin";
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
}