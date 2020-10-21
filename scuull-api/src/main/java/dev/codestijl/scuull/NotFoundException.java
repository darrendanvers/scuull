package dev.codestijl.scuull;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to throw when a resource is not found.
 *
 * @author darren
 * @since 1.0.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8879015058695295492L;

    /**
     * Creates a new NotFoundException.
     *
     * @param format A format to use to construct the error message for this exception.
     * @param params Parameters to pass when building the error message.
     */
    public NotFoundException(final String format, final Object... params) {
        super(String.format(format, params));
    }
}
