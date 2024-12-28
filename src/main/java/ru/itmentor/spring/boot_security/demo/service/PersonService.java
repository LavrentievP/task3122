package ru.itmentor.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService
      //  implements PersServ
{
private final MyUserDetailsService myUserDetailsService;

@Autowired
public PersonService(MyUserDetailsService myUserDetailsService) {
    this.myUserDetailsService = myUserDetailsService;
}


public List<User> upindex() {
    return myUserDetailsService.upindex();
}

public User show(int id) {
    return myUserDetailsService.show(id);
}

public void save(User user) {
    myUserDetailsService.save(user);
}

public void update(int id, User updatedUser) {
    myUserDetailsService.update(id, updatedUser);
}

public void delete(int id) {
    myUserDetailsService.delete(id);
}

    public Optional<User> getUserById(int id) {
        return myUserDetailsService.getUserById(id);
    }

    public Optional<User> getUserByUsername(String userName) {
        return myUserDetailsService.getUserByUsername(userName);
    }

//    @PersistenceContext
//    private EntityManager entityManager; ;
//
//
//
//    @Override
//    @Transactional
//    public List<User> upindex() {
//        return entityManager.createQuery("FROM User", User.class).getResultList();
//    }
//
//    @Override
//    @Transactional
//    public User show(int id) {
//        return entityManager.find(User.class, id);
//    }
//
//    @Override
//    @Transactional
//    public void save(User user) {
//        entityManager.persist(user);
//    }
//
//    @Override
//    @Transactional
//    public void update(int id, User updatedUser) {
//        entityManager.merge(updatedUser);
//    }
//
//    @Override
//    @Transactional
//    public void delete(int id) {
//        User user = entityManager.find(User.class, id);
//        if (user != null) {
//            entityManager.remove(user);
//
//        }
//    }
}
