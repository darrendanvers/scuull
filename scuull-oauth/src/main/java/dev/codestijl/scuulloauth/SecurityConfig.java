package dev.codestijl.scuulloauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()
                    .loginPage("http://localhost:8090/auth/login.html")
                    .loginProcessingUrl("/perform_login")
                .and()
                .authorizeRequests()
                    .antMatchers("/login/**", "/perform_login/**")
                        .permitAll()
                .and()
                    .csrf().disable();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        return new InMemoryUserDetailsManager(
                User.builder().username("enduser")
                        .password("password")
                        .roles("USER")
                        .passwordEncoder(this.passwordEncoder::encode)
                        .build());
    }
}
