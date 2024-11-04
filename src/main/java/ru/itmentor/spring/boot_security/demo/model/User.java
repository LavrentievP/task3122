package ru.itmentor.spring.boot_security.demo.model;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //    @NotEmpty(message = "Name should not be empty")
//    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    //    @NotEmpty(message = "Sex should not be empty")
//   @Pattern(regexp = "male|female", message = "Sex should be male or female")
    @Column(name = "sex")
    private String sex;

    @Column(name = "pass")
    private String pass;

    public User() {
    }

    public User(int id, String name, String sex, String pass) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.pass = pass;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
