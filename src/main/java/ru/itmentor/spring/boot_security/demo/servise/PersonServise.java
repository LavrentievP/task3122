package ru.itmentor.spring.boot_security.demo.servise;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServise
      //  implements PersServ
{
private final MyUserDetailsServise myUserDetailsServise;

@Autowired
public PersonServise(MyUserDetailsServise myUserDetailsServise) {
    this.myUserDetailsServise = myUserDetailsServise;
}


public List<User> upindex() {
    return myUserDetailsServise.upindex();
}

public User show(int id) {
    return myUserDetailsServise.show(id);
}

public void save(User user) {
    myUserDetailsServise.save(user);
}

public void update(int id, User updatedUser) {
    myUserDetailsServise.update(id, updatedUser);
}

public void delete(int id) {
    myUserDetailsServise.delete(id);
}

    public Optional<User> getUserById(int id) {
        return myUserDetailsServise.getUserById(id);
    }

    public Optional<User> getUserByUsername(String userName) {
        return myUserDetailsServise.getUserByUsername(userName);
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
