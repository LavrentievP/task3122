# Task-7

---

### Тестовые данные:

Эндпоинт (_Endpoint_): [http://**localhost:8080/login**](http://localhost:8080/login)

Логин: `Admin` // Пароль: `Admin` // Роль: `ROLE_ADMIN`

Логин: `User` // Пароль: `User` // Роль: `ROLE_USER` 

---


### Структура проекта:
```
Directory structure:
└── LavrentievP-task3122/
    ├── README.md
    ├── pom.xml
    └── src/
       ├── main/
       │   ├── java/
       │   │   └── ru/
       │   │       └── itmentor/
       │   │           └── spring/
       │   │               └── boot_security/
       │   │                   └── demo/
       │   │                       ├── SpringBootSecurityDemoApplication.java
       │   │                       ├── configs/
       │   │                       │   ├── MvcConfig.java
       │   │                       │   ├── SuccessUserHandler.java
       │   │                       │   └── WebSecurityConfig.java
       │   │                       ├── controller/
       │   │                       │   ├── RestController.java
       │   │                       │   ├── WebController.java
       │   │                       │   └── WebGreetingController.java
       │   │                       ├── exeptions/
       │   │                       │   └── UserNotFoundException.java
       │   │                       ├── model/
       │   │                       │   ├── PasswordDto.java
       │   │                       │   └── User.java
       │   │                       ├── repository/
       │   │                       │   └── UserRepository.java
       │   │                       ├── security/
       │   │                       │   └── PersonDetails.java
       │   │                       ├── service/
       │   │                       │   ├── PersonService.java
       │   │                       │   └── PersonServiceImpl.java
       │   │                       └── utils/
       │   │                           └── DatabaseInitializer.java
       │   └── resources/
       │       ├── application.properties
       │       └── templates/
       │           ├── admin.html
       │           ├── change-password.html
       │           ├── create.html
       │           ├── index.html
       │           ├── login.html
       │           ├── peoples.html
       │           ├── show.html
       │           ├── update.html
       │           └── user.html
       └── test/
           └── java/
               └── ru/
                   └── itmentor/
                       └── spring/
                           └── boot_security/
                               └── demo/
                                   └── SpringBootSecurityDemoApplicationTests.java


```

