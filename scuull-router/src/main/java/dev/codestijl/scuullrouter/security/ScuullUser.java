package dev.codestijl.scuullrouter.security;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

public class ScuullUser implements UserDetails {

    private static final long serialVersionUID = 1600828137242017714L;

    private final List<ScuullAuthority> authorities = new LinkedList<>();
    private final String username;
    private final String password;
    private final String fullName;

    private static final class ScuullAuthority implements GrantedAuthority {

        private static final long serialVersionUID = -7963755817248661402L;

        private final String role;

        public ScuullAuthority(String role) {

            Assert.notNull(role, "Role cannot be null.");

            this.role = role;
        }

        @Override
        public String getAuthority() {
            return this.role;
        }
    }

    public static final class Builder {

        private String username;
        private String password;
        private String fullName;
        private List<String> authorities;
        private Function<String, String> passwordEncoder = password -> password;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setAuthorities(String... authorities) {

            this.authorities = Arrays.asList(authorities);
            return this;
        }

        public Builder setPasswordEncoder(Function<String, String> passwordEncoder) {
            this.passwordEncoder = passwordEncoder;
            return this;
        }

        public ScuullUser build() {
            return new ScuullUser(this.username,
                    this.passwordEncoder.apply(this.password),
                    this.fullName,
                    this.authorities.stream().map(ScuullAuthority::new));
        }
    }

    public static Builder builder()  {
        return new Builder();
    }

    private ScuullUser(String username, String password, String fullName, Stream<ScuullAuthority> authorities) {

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

    public String getFullName() {
        return fullName;
    }
}
