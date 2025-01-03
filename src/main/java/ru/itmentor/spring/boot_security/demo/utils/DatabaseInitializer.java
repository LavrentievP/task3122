package ru.itmentor.spring.boot_security.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializer {

    private final UserRepository userRepository;

    @Autowired
    public DatabaseInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        List<User> users = Arrays.asList(
                new User(0, "User", "Female", "User", "ROLE_USER"),
                new User(0, "Admin", "Male", "Admin", "ROLE_ADMIN"),
                new User(0, "Charlie", "Male", "password3", "ROLE_USER"),
                new User(0, "Diana", "Female", "password4", "ROLE_USER"),
                new User(0, "Edward", "Male", "password5", "ROLE_USER"),
                new User(0, "Fiona", "Female", "password6" , "ROLE_USER"),
                new User(0, "George", "Male", "password7" , "ROLE_USER"),
                new User(0, "Hannah", "Female", "password8" , "ROLE_USER")
        );

        userRepository.saveAll(users);
    }
}
