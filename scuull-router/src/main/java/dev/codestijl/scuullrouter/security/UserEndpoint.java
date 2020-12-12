package dev.codestijl.scuullrouter.security;

import org.springframework.web.bind.annotation.*;

/**
 * REST endpoint to authenticate users and return a JWT token.
 *
 * @author darren
 * @since 1.0.0
 */
@RestController()
@RequestMapping("/auth/user")
public class UserEndpoint {

    private final UserService userService;

    /**
     * Class to marshall a user login request.
     *
     * @author darren
     * @since 1.0.0
     */
    private static final class UserLoginRequest {

        private String userName;
        private String password;

        /**
         * Returns the userName.
         *
         * @return The userName.
         */
        public String getUserName() {
            return userName;
        }

        /**
         * Sets the userName.
         *
         * @param userName The userName.
         * @return This object for further configuration.
         */
        public UserLoginRequest setUserName(final String userName) {
            this.userName = userName;
            return this;
        }

        /**
         * Returns the password.
         *
         * @return The password.
         */
        public String getPassword() {
            return password;
        }

        /**
         * Sets the password.
         *
         * @param password The password.
         * @return This object for further configuration.
         */
        public UserLoginRequest setPassword(final String password) {
            this.password = password;
            return this;
        }

        @Override
        public String toString() {
            return this.userName;
        }
    }

    /**
     * Constructs a new UserEndpoint.
     *
     * @param userService The UserService to authenticate users and generate JWT tokens with.
     */
    public UserEndpoint(final UserService userService) {

        this.userService = userService;
    }

    /**
     * Endpoint to hit to authenticate a user.
     *
     * @param loginRequest The UserLoginRequest with the user's ID and password.
     * @return A JWT token for the user.
     * @throws org.springframework.security.core.AuthenticationException If authentication fails.
     */
    @PostMapping
    public String login(@RequestBody final UserLoginRequest loginRequest) {

        return this.userService.signIn(loginRequest.getUserName(), loginRequest.getPassword());
    }
}
