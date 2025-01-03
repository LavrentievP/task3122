package ru.itmentor.spring.boot_security.demo.exeptions;

public class UserNotFoundException extends IllegalArgumentException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
