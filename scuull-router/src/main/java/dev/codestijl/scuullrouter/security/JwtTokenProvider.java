package dev.codestijl.scuullrouter.security;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Creates and resolves JWT tokens.
 *
 * @author darren
 * @since 1.0.0
 */
@Service
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    private static final String JWT_CLAIM_AUTH = "auth";
    private static final String JWT_CLAIM_FULL_NAME = "FNM";

    private static final String EMPTY_CREDENTIALS = "";

    private final String secret;
    private final long validityInMilliseconds;
    private final UserDetailsService userDetailsService;

    /**
     * Constructs a new JwtTokenProvider.
     *
     * @param userDetailsService A UserDetailsService that provides user information.
     * @param secret A secret to use to sign tokens with.
     * @param validityInMilliseconds How long a token should be valid for (in milliseconds).
     */
    @Autowired
    public JwtTokenProvider(final UserDetailsService userDetailsService,
                            @Value("${auth.jwt.secret}")final String secret,
                            @Value("${auth.jwt.validity}")final long validityInMilliseconds) {

        logger.info(String.format("Using %s as secret.", secret));

        this.secret = Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_16));
        this.validityInMilliseconds = validityInMilliseconds;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Creates a token for an already authenticated user.
     *
     * @param userName The ID of the user to make a token for.
     * @return A JWT token for that user.
     */
    public String createToken(final String userName) {

        logger.debug(String.format("Creating token for %s.", userName));

        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
        if (Objects.isNull(userDetails)) {
            logger.error(String.format("User %s not found.", userName));
            throw new IllegalStateException("User token being created, but the user does not exist.");
        }

        final List<String> authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        final Claims claims = Jwts.claims().setSubject(userName);
        claims.put(JWT_CLAIM_AUTH, authorities);

        final Date validity = new Date(Instant.now().toEpochMilli() + this.validityInMilliseconds);

        if (userDetails instanceof ScuullUser) {

            claims.put(JWT_CLAIM_FULL_NAME, ((ScuullUser) userDetails).getFullName());
        } else {

            logger.warn(String.format("Principal for %s is of type %s.", userName, userDetails.getClass().getName()));
        }

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }

    /**
     * Given a token, will try and convert it to an Authentication object.
     *
     * @param token The token to try and convert to Authentication.
     * @return The Authentication object that represents the user the token was created for.
     * @throws InvalidTokenException If the token is expired or invalid.
     */
    public Authentication resolveToken(final String token) {

        logger.debug("Resolving token \"{}\".", token);

        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(this.extractUsername(token));
        if (Objects.isNull(userDetails)) {
            throw new InvalidTokenException("Token is for a user that no longer exists.");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, EMPTY_CREDENTIALS, userDetails.getAuthorities());
    }

    private String extractUsername(final String token) {

        return this.extractClaims(token)
                .getBody()
                .getSubject();
    }

    private Jws<Claims> extractClaims(final String token) {

        try {

            return Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token);
        } catch (JwtException | IllegalArgumentException e) {

            logger.error(e.getLocalizedMessage());
            throw (InvalidTokenException) new InvalidTokenException("Expired or invalid JWT token").initCause(e.getCause());
        }
    }
}
