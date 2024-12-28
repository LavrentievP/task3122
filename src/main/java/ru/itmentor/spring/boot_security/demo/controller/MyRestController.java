package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class MyRestController {

    private final PersonService personService;

    @Autowired
    public MyRestController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/admin")
    public ResponseEntity<List<User>> upindex() {
        List<User> allUsers = personService.upindex();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserInfo(Authentication authentication) {
        String userName = authentication.getName();
        Optional<User> user = personService.getUserByUsername(userName);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }
        personService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        personService.delete(id);
        return ResponseEntity.ok("User deleted");

    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id,
                                           @RequestBody User user) {
        if (user.getId() != id)  {
            return ResponseEntity.badRequest().body(null);
        }
        personService.update(id, user);
        return ResponseEntity.ok(user);
    }

}

