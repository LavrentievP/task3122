package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.security.PersonDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {
    // Spring Security использует объект Authentication, пользователя авторизованной сессии.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_USER")) {
           // Object principal = authentication.getPrincipal();
//            if (principal instanceof UserDetails) {
//                User user = ((PersonDetails) principal).getUser(); // Assuming your UserDetailService returns a PersonDetails object
//                int userId = user.getId(); // Assuming User has an ID field
                httpServletResponse.sendRedirect("/user" );

        } else if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/web/admin");
            } else {
            httpServletResponse.sendRedirect("/web/");
        }
    }
}