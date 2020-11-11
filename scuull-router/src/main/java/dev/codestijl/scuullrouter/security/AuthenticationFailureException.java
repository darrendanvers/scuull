package dev.codestijl.scuullrouter.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to throw when authorization fails.
 *
 * @author darren
 * @since 1.0.0
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Invalid user ID or password")
public class AuthenticationFailureException extends RuntimeException {

    private static final long serialVersionUID = -471279994952332279L;

    /**
     * Creates a new AuthenticationFailureException.
     *
     * @param message The error message.
     */
    public AuthenticationFailureException(final String message) {
        super(message);
    }
}
