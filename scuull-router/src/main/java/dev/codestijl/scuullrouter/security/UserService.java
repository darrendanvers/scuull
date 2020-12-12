package dev.codestijl.scuullrouter.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * UserService that holds the logic to authenticate a user and teh create a token for them.
 *
 * @author darren
 * @since 1.0.0
 */
@Service
public class UserService {

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    /**
     * Creates a new UserService.
     *
     * @param jwtTokenProvider The JwtTokenProvider to generate tokens for authenticated users.
     * @param authenticationManager The AuthenticationManager to authenticate users with.
     */
    public UserService(final JwtTokenProvider jwtTokenProvider, final AuthenticationManager authenticationManager) {

        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Perform the user signing.
     *
     * @param username The ID of the user.
     * @param password The password of the user.
     * @return A JWT token for the user.
     * @throws AuthenticationException If user authentication fails.
     */
    public String signIn(final String username, final String password) {

        try {

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return this.jwtTokenProvider.createToken(username);
        } catch (AuthenticationException e) {
            throw (AuthenticationFailureException) new AuthenticationFailureException(e.getMessage()).initCause(e.getCause());
        }
    }
}