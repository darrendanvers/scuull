package dev.codestijl.scuullrouter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class BeanConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        return new ScuulUserDetailService(fullUser(),  limitedUser());
    }

    private ScuullUser limitedUser()  {

        return ScuullUser.builder().setUsername("limiteduser")
                .setFullName("Limited User")
                .setPassword("pwd")
                .setPasswordEncoder(passwordEncoder()::encode)
                .setAuthorities("JOBS")
                .build();
    }

    private ScuullUser fullUser()  {

        return ScuullUser.builder().setUsername("fulluser")
                .setFullName("Full User")
                .setPassword("pwd")
                .setPasswordEncoder(passwordEncoder()::encode)
                .setAuthorities("JOBS", "INSTANCES", "EXECUTIONS")
                .build();
    }
}
