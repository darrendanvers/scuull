package dev.codestijl.scuullrouter.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Configures application security.
 *
 * @author darren
 * @since 1.0.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Creates a new SecurityConfig.
     *
     * @param jwtTokenProvider The JwtTokenProvider to use to create and validate JWT tokens.
     */
    @Autowired
    public SecurityConfig(final JwtTokenProvider jwtTokenProvider) {

        super();
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        // Since there's no stored user session, there's no CSRF handling.
        http.csrf().disable();

        // No user session.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Expose the auth endpoints so the tokens can be geenrated, but require
        // authentication on all others.
        http.authorizeRequests().antMatchers("/auth/user/**").permitAll()
                .antMatchers("/jobs/**").authenticated()
                .anyRequest().permitAll();

        // Add the JwtTokenFilter to the secrutiy config.
        http.apply(new JwtTokenFilterConfigurer(this.jwtTokenProvider));
    }

    // This is needed to expose the AuthenticationManager to any other beans.
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }
}
