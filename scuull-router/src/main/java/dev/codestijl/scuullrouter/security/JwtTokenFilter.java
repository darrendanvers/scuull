package dev.codestijl.scuullrouter.security;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter that wil take the token from the request and determine its validity.
 *
 * @author darren
 * @since 1.0.0
 */
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String AUTH_HEADER_TYPE = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Constructs a new JwtTokenFilter.
     *
     * @param jwtTokenProvider The JwtTokenProvider to use to validate JWT tokens with.
     */
    public JwtTokenFilter(final JwtTokenProvider jwtTokenProvider) {
        super();
        logger.info("Constructing JwtTokenFilter.");
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(@NonNull final HttpServletRequest request,
                                    @NonNull final HttpServletResponse response,
                                    @NonNull final FilterChain filterChain) throws ServletException, IOException {

        try {

            // If a token is present in the request, extract it, try to convert it to an Authentication,
            // and store it in the security context.
            extractToken(request)
                    .map(this.jwtTokenProvider::resolveToken)
                    .ifPresent(SecurityContextHolder.getContext()::setAuthentication);

        } catch (InvalidTokenException ex) {
            
            handleInvalidToken(response, ex.getMessage());
            return;
        }

        // Continue on with the chain.
        filterChain.doFilter(request, response);
    }

    private static Optional<String> extractToken(final HttpServletRequest request) {

        final String bearerToken = request.getHeader(AUTH_HEADER_NAME);
        logger.debug(String.format("Auth token: %s", bearerToken));

        if (Objects.nonNull(bearerToken) && bearerToken.startsWith(AUTH_HEADER_TYPE)) {
            return Optional.of(bearerToken.substring(AUTH_HEADER_TYPE.length()));
        }

        return Optional.empty();
    }

    private static void handleInvalidToken(final HttpServletResponse response, final String message) throws IOException {

        logger.debug("Invalid token found.");

        // Unauthenticate the user.
        SecurityContextHolder.clearContext();

        // Send FORBIDDEN back.
        response.sendError(HttpServletResponse.SC_FORBIDDEN, message);
    }
}
