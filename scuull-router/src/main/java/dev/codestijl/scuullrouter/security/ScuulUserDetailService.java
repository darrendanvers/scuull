package dev.codestijl.scuullrouter.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * UserDetailsService to serve up information about configured users.
 *
 * @author darren
 * @since 1.0.0
 */
public class ScuulUserDetailService implements UserDetailsService {

    private final Map<String, ScuullUser> users = new ConcurrentHashMap<>();

    /**
     * Constructs a new ScuulUserDetailService.
     *
     * @param usersToAdd The list of users to have available.
     */
    public ScuulUserDetailService(final ScuullUser... usersToAdd) {

        for (final ScuullUser user : usersToAdd) {
            this.users.put(user.getUsername(), user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {

        if (!this.users.containsKey(username)) {
            throw new UsernameNotFoundException(String.format("User %s not found.", username));
        }

        return this.users.get(username);
    }
}
