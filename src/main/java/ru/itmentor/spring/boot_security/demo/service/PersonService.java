package ru.itmentor.spring.boot_security.demo.service;

import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface PersonService {

    List<User> showAll();

    User show(int id);

    void save(User user);

    void update(int id, User updatedUser);

    void delete(int id);

    void updatePassword(String username, String password);
}
