package dev.codestijl.scuullrouter.security;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth/user")
public class UserEndpoint {

    private static final class ExposedUser {

        private final String username;
        private final String fullName;
        private final List<String> authorities = new LinkedList<>();

        private ExposedUser(String username, String fullName, Stream<String> authorities) {

            Assert.notNull(username, "Username cannot be null");
            Assert.notNull(fullName, "Full name cannot be null.");

            this.username = username;
            this.fullName = fullName;
            if (Objects.nonNull(this.authorities)) {
                authorities.forEach(this.authorities::add);
            }
        }

        private static ExposedUser from(ScuullUser scuullUser) {
            Assert.notNull(scuullUser, "User cannot be null.");

            return new ExposedUser(scuullUser.getUsername(), scuullUser.getFullName(),
                    scuullUser.getAuthorities().stream().map(GrantedAuthority::getAuthority));
        }

        public String getUsername() {
            return username;
        }

        public String getFullName() {
            return fullName;
        }

        public List<String> getAuthorities() {
            return authorities;
        }
    }
    @GetMapping
    public ExposedUser get(Authentication authentication) {

        if (Objects.isNull(authentication)) {
            throw new IllegalStateException("User is not logged in.");
        }

        if (! (authentication.getPrincipal() instanceof ScuullUser)) {
            throw new IllegalStateException("Principal does not contain a ScuulUser.");
        }

        return ExposedUser.from((ScuullUser) authentication.getPrincipal());
    }
}
