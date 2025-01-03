package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.exeptions.UserNotFoundException;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;
import ru.itmentor.spring.boot_security.demo.security.PersonDetails;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class PersonServiceImpl implements PersonService, UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public PersonServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> showAll() {
        return userRepository.findAll().stream()
                .sorted(Comparator.comparingInt(User::getId))
                .toList();
    }

    @Override
    public User show(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(int id, User updatedUser) {
        User existUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        existUser.setUsername(updatedUser.getUsername());
        existUser.setSex(updatedUser.getSex());
        existUser.setPassword(updatedUser.getPassword());
        existUser.setRole(updatedUser.getRole());
        userRepository.save(existUser);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updatePassword(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден: " + username));
        user.setPassword(password); // Кодируем новый пароль
        userRepository.save(user); // Сохраняем изменения
    }

    public Optional<User> getUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new PersonDetails(user.get());
    }
}



