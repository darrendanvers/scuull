package dev.codestijl.scuullrouter.security;

/**
 * Exception to throw when a JWT token is invalid.
 *
 * @author darren
 * @since 1.0.0
 */
public class InvalidTokenException extends RuntimeException {

    private static final long serialVersionUID = -7485211621840351479L;

    /**
     * Constructs a new InvalidTokenException.
     *
     * @param message The error message.
     */
    public InvalidTokenException(final String message) {
        super(message);
    }
}
