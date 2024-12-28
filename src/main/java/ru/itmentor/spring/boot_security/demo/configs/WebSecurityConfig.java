package ru.itmentor.spring.boot_security.demo.configs;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import ru.itmentor.spring.boot_security.demo.security.AuthProviderImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itmentor.spring.boot_security.demo.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private final AuthProviderImpl authProvider;
//
//    @Autowired
//    public WebSecurityConfig(AuthProviderImpl authProvider) {
//        this.authProvider = authProvider;
//    }
//
//@Override
//    protected void configure(AuthenticationManagerBuilder auth){
//
//        auth.authenticationProvider(authProvider);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    private final SuccessUserHandler successUserHandler;
    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler, MyUserDetailsService myUserDetailsService) {
        this.successUserHandler = successUserHandler;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic(Customizer.withDefaults())
                .authorizeRequests()
                .antMatchers("/login"/*, "/register"*/).permitAll() // Разрешить доступ к страницам входа и регистрации для всех
                .antMatchers("/admin/").hasRole("ADMIN")
                .antMatchers("/user/").hasAnyRole("USER", "ADMIN")  // доступ для USER и ADMIN
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successUserHandler)
                .loginPage("/login") // Указываем путь к вашей кастомной странице входа
                .permitAll() // Даем доступ к странице входа всем пользователям
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }



    // аутентификация inMemory
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build();
//
//        UserDetails admin =
//                User.withDefaultPasswordEncoder()
//                        .username("admin")
//                        .password("admin")
//                        .roles("ADMIN")
//                        .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
}