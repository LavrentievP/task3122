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
                new User(0, "Alice", "Female", "password1"),
                new User(0, "Bob", "Male", "password2"),
                new User(0, "Charlie", "Male", "password3"),
                new User(0, "Diana", "Female", "password4"),
                new User(0, "Edward", "Male", "password5"),
                new User(0, "Fiona", "Female", "password6"),
                new User(0, "George", "Male", "password7"),
                new User(0, "Hannah", "Female", "password8"),
                new User(0, "Ian", "Male", "password9"),
                new User(0, "Julia", "Female", "password10"),
                new User(0, "Kevin", "Male", "password11"),
                new User(0, "Laura", "Female", "password12"),
                new User(0, "Mike", "Male", "password13"),
                new User(0, "Nina", "Female", "password14"),
                new User(0, "Oscar", "Male", "password15"),
                new User(0, "Paula", "Female", "password16"),
                new User(0, "Quentin", "Male", "password17"),
                new User(0, "Rachel", "Female", "password18"),
                new User(0, "Steve", "Male", "password19"),
                new User(0, "Tina", "Female", "password20")
        );

        userRepository.saveAll(users);
    }
}
