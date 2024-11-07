package ru.itmentor.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.servise.MyUserDetailsServise;

import java.util.Collections;

@Component
public class AuthProviderImpl
//        implements AuthenticationProvider
{

//    private final MyUserDetailsServise myUserDetailsServise;
//
//    @Autowired
//    public AuthProviderImpl(MyUserDetailsServise myUserDetailsServise) {
//        this.myUserDetailsServise = myUserDetailsServise;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//
//       UserDetails personDetails = myUserDetailsServise.loadUserByUsername(username);
//       String password = authentication.getCredentials().toString();
//      if(! password.equals(personDetails.getPassword()))
//throw new BadCredentialsException("Bad credentials");
//
//      return new UsernamePasswordAuthenticationToken(personDetails, password, Collections.emptyList());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return true;
//    }
}
