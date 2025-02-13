package com.w2m.naves.user.application;

import com.w2m.naves.user.domain.UserAccount;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    @PostConstruct
    private void doit(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "w2m-password";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }

    private final PasswordEncoder passwordEncoder;

    private final static UserAccount DEFAULT_USER = new UserAccount(
            "user",
            "$2a$10$W7tZI8aTcAKI1ALVy8nOKOA6kvRpNH8RjYo7pnB7cXys8ys.ygUsC" // -> 'password'
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (DEFAULT_USER.getUsername().equals(username)) {
            return new User(
                    DEFAULT_USER.getUsername(),
                    DEFAULT_USER.getPassword(),
                    Collections.emptyList()
            );
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
    }
}
