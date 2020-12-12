package dev.codestijl.scuullrouter.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class can be used to configure a JwtTokenFilter as part of the security filter chain.
 *
 * @author darren
 * @since 1.0.0
 */
public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Creates a new JwtTokenFilterConfigurer.
     *
     * @param jwtTokenProvider The JwtTokenProvider for the JwtTokenFilter to use to validate tokens.
     */
    public JwtTokenFilterConfigurer(final JwtTokenProvider jwtTokenProvider) {

        super();
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void configure(final HttpSecurity http) {

        // Add a JwtTokenFilter before the UsernamePasswordAuthenticationFilter in the
        // security chain.
        final JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

}