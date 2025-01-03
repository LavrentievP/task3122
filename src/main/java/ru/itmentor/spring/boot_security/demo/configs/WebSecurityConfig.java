package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.itmentor.spring.boot_security.demo.service.PersonServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Включает использование аннотаций @PreAuthorize и @PostAuthorize
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler; // Обработчик успешной аутентификации
    private final UserDetailsService userDetailsService; // Сервис для работы с пользовательскими данными
    private final PersonServiceImpl personService; // Реализация пользовательского сервиса

    @Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserDetailsService myUserDetailsService, PersonServiceImpl personService) {
        this.successUserHandler = successUserHandler;
        this.userDetailsService = myUserDetailsService;
        this.personService = personService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Отключает CSRF для упрощения (не рекомендуется для production)
                .authorizeRequests()
                .antMatchers("/login").permitAll() // Разрешаем всем доступ к странице входа
                .antMatchers("/logout").permitAll() // Даем доступ к разлогиниванию всем пользователям
                .antMatchers("/admin/**").hasRole("ADMIN") // Доступ к /admin только для роли ADMIN
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Доступ к /user для USER и ADMIN
                .anyRequest().authenticated() // Для всех остальных страниц требуется аутентификация
                .and()
                .formLogin()
                .loginPage("/login") // Указываем кастомную страницу входа
                .successHandler(successUserHandler) // Указываем обработчик успешной аутентификации
                .permitAll() // Даем доступ ко всем связанным с логином запросам
                .and()
                .logout()
                .logoutUrl("/logout") // Указываем URL для разлогинивания
                .logoutSuccessUrl("/login") // Перенаправление на страницу входа после выхода
                .invalidateHttpSession(true) // Аннулируем текущую сессию
                .deleteCookies("JSESSIONID") // Удаляем куки после выхода
                .permitAll(); // Доступ к разлогиниванию для всех
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Указываем кастомную реализацию UserDetailsService для аутентификации
        auth.userDetailsService(personService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Используем простой кодировщик для паролей (для тестов, не рекомендуется для production)
        return NoOpPasswordEncoder.getInstance();
    }
}
