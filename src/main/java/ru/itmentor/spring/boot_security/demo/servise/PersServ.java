package ru.itmentor.spring.boot_security.demo.servise;



import ru.itmentor.spring.boot_security.demo.model.Person;

import java.util.List;

public interface PersServ {

    public List<Person> upindex();

    public Person show(int id);

    public void save(Person person);

    public void update(int id, Person updatedPerson);

    public void delete(int id);

}
