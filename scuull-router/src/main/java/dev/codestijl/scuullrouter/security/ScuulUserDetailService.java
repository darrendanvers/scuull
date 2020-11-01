package dev.codestijl.scuullrouter.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ScuulUserDetailService implements UserDetailsService {

    private final Map<String, ScuullUser> users = new ConcurrentHashMap<>();

    public ScuulUserDetailService(ScuullUser... usersToAdd) {

        for (ScuullUser user : usersToAdd) {
            this.users.put(user.getUsername(), user);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.users.get(username);
    }
}
