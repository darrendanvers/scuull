package dev.codestijl.scuullrouter.security;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * Holds information about a user in the Scuull application.
 *
 * @author darren
 * @since 1.1.0
 */
public class ScuullUser implements UserDetails {

    private static final long serialVersionUID = 1600828137242017714L;

    private final List<ScuullAuthority> authorities = new LinkedList<>();
    private final String username;
    private final String password;
    private final String fullName;

    /**
     * Holds a particular permission a user can have.
     *
     * @author darren
     * @since 1.0.0
     */
    private static final class ScuullAuthority implements GrantedAuthority {

        private static final long serialVersionUID = -7963755817248661402L;

        private final String role;

        /**
         * Constructs a new ScuullAuthority.
         *
         * @param role The user's role.
         */
        public ScuullAuthority(final String role) {

            Assert.notNull(role, "Role cannot be null.");

            this.role = role;
        }

        @Override
        public String getAuthority() {
            return this.role;
        }

        @Override
        public String toString() {
            return this.role;
        }
    }

    /**
     * Builder class for ScuullUsers.
     *
     * @author darren
     * @since 1.0.0
     */
    public static final class Builder {

        private static final Logger logger = LoggerFactory.getLogger(Builder.class);

        private static final Function<String, String> DEFAULT_ENCODER = password -> password;

        private String username;
        private String password;
        private String fullName;
        private List<String> authorities;
        private Function<String, String> passwordEncoder = DEFAULT_ENCODER;

        /**
         * Sets the username property.
         *
         * @param username The username property.
         * @return This object for further configuration.
         */
        public Builder setUsername(final String username) {
            this.username = username;
            return this;
        }

        /**
         * Sets the password property.
         *
         * @param password The password property.
         * @return This object for further configuration.
         */
        public Builder setPassword(final String password) {
            this.password = password;
            return this;
        }

        /**
         * Sets the fullName property.
         *
         * @param fullName The fullName property.
         * @return This object for further configuration.
         */
        public Builder setFullName(final String fullName) {
            this.fullName = fullName;
            return this;
        }

        /**
         * Sets the user's authorities. Multiple calls to this function is not additive, it will
         * overwrite previous added authorities.
         *
         * @param authorities A list of the user's authorities.
         * @return This object for further configuration.
         */
        public Builder setAuthorities(final String... authorities) {

            this.authorities = Arrays.asList(authorities);
            return this;
        }

        /**
         * Sets the passwordEncoder property. This field is optional. If not set, the password
         * will not be encoded.
         *
         * @param passwordEncoder The passwordEncoder top .
         * @return This object for further configuration.
         */
        public Builder setPasswordEncoder(final Function<String, String> passwordEncoder) {
            this.passwordEncoder = passwordEncoder;
            return this;
        }

        /**
         * Constructs a new ScuullUser.
         *
         * @return A new ScuullUser.
         */
        public ScuullUser build() {

            if (Objects.equals(DEFAULT_ENCODER, this.passwordEncoder)) {
                logger.warn("Passwords are not being encoded.");
            }

            return new ScuullUser(this.username,
                    this.passwordEncoder.apply(this.password),
                    this.fullName,
                    this.authorities.stream().map(ScuullAuthority::new));
        }
    }

    /**
     * Returns a Builder which can be used to construct a new ScuulUser.
     *
     * @return A Builder which can be used to construct a new ScuulUser
     */
    public static Builder builder()  {
        return new Builder();
    }

    /**
     * Constructs a new ScuullUser.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @param fullName The user's full name.
     * @param authorities A stream of the user's authorities.
     */
    private ScuullUser(final String username, final String password, final String fullName, final Stream<ScuullAuthority> authorities) {

        Assert.notNull(username, "Username cannot be null");
        Assert.notNull(fullName, "Full name cannot be null");
        Assert.notNull(password, "Password cannot be null");

        this.username = username;
        this.password = password;
        this.fullName = fullName;
        if (Objects.nonNull(authorities)) {
            authorities.forEach(this.authorities::add);
        }
    }

    /**
     * Returns the user's full name.
     *
     * @return The user's full name.
     */
    public String getFullName() {
        return fullName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {

        final StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        this.authorities.forEach(a -> stringJoiner.add(a.getAuthority()));
        return String.format("%s:%s", this.username, stringJoiner.toString());
    }
}
