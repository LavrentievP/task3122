package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.PasswordDto;
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
    public String showAllPage(Authentication authentication, Model model) {
        String currentUserName = authentication.getName();
        Optional<User> currentUser = personService.getUserByUsername(currentUserName);

        model.addAttribute("currentUser", currentUser.get());
        model.addAttribute("allPeople", personService.showAll());
        return "/peoples";
    }


    @GetMapping("/{id}")
    public String showByIdPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("showPerson", personService.show(id));
        return "/show";
    }


    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("createPerson", new User());
        return "/create";
    }

    @PostMapping()
    public String create(@ModelAttribute("createPerson") User user) {
        personService.save(user);
        return "redirect:/web/admin";
    }


    @GetMapping("/update/{id}")
    public String showUpdatePage(@PathVariable("id") int id, Model model) {
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


    @GetMapping("/change-password")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("passwordDto", new PasswordDto()); // Передаем пустой объект DTO для формы
        return "change-password"; // Имя шаблона для отображения формы
    }

    @PostMapping("/change-password")
    public String сhangePassword(@ModelAttribute("passwordDto") PasswordDto passwordDto,
                                 @AuthenticationPrincipal UserDetails currentUser) {
        String username = currentUser.getUsername(); // Получаем имя текущего пользователя
        personService.updatePassword(username, passwordDto.getNewPassword()); // Обновляем пароль
        return "redirect:/web/user"; // Перенаправляем на страницу профиля пользователя
    }



    @GetMapping("/index")
    public String showAdminPage() {
        return "index";
    }


    @GetMapping("/user")
    public String userInfoPage(Authentication authentication, Model model) {
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