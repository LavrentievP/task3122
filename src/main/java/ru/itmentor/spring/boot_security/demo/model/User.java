package ru.itmentor.spring.boot_security.demo.model;

import lombok.*;

import javax.persistence.*;


@Data
//@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "sex")
    private String sex;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public User() {
    }

    public User(int id,String username, String sex, String password) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.password= password;
    }

}
