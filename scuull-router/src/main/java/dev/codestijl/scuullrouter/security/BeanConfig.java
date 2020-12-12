package dev.codestijl.scuullrouter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configures beans needed by the scurity setup.
 *
 * @author darren
 * @since 1.0.0
 */
@Configuration
public class BeanConfig {

    /**
     * A bean to encode passwords.
     *
     * @return A bean to encode passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * A UserDetailsService that will manage returning user information. For this simple example, it sets up
     * two users only, one with full access and one with limited access.
     *
     * @return A UserDetailsService bean.
     */
    @Bean
    public UserDetailsService userDetailsService() {

        return new ScuulUserDetailService(fullUser(),  limitedUser());
    }

    // A user with some limitations in their access.
    private ScuullUser limitedUser()  {

        return ScuullUser.builder().setUsername("limiteduser")
                .setFullName("Limited User")
                .setPassword("pwd")
                .setPasswordEncoder(passwordEncoder()::encode)
                .setAuthorities("JOBS")
                .build();
    }

    // A user with full access to everything.
    private ScuullUser fullUser()  {

        return ScuullUser.builder().setUsername("fulluser")
                .setFullName("Full User")
                .setPassword("pwd")
                .setPasswordEncoder(passwordEncoder()::encode)
                .setAuthorities("JOBS", "INSTANCES", "EXECUTIONS")
                .build();
    }
}
