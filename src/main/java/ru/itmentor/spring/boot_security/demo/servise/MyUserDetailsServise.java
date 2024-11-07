package ru.itmentor.spring.boot_security.demo.servise;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;
import ru.itmentor.spring.boot_security.demo.security.PersonDetails;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsServise implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsServise(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new PersonDetails(user.get());

    }

    public List<User> upindex() {
        return userRepository.findAll();
    }

    public User show(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void update(int id, User updatedUser) {
        userRepository.save(updatedUser);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

}
