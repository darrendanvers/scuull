import axios from "axios";
import jwt from 'jwt-decode';

import Emitter from "./Emitter";

const BASE_URI = "/auth/user";

/**
 * Service that provides methods related to loggin in and out.
 *
 * @type {{logout(): void, getCurrentUser(): (any|undefined), getCurrentToken(): string | null, login(*=, *=): Promise<*>}}
 */
const AuthService = {

    /**
     * Logs the user in.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @returns {Promise<*>}
     */
    login(username, password) {
        return axios
            .post(BASE_URI, {
                userName: username,
                password: password
            })
            .then(response => {
                if (response.data) {
                    sessionStorage.setItem("token", response.data);
                    const user = jwt(response.data);
                    sessionStorage.setItem('user', JSON.stringify(user));
                    Emitter.emit(Emitter.LOGON, user);
                }

                return response.data;
            });
    },

    /**
     * Logs the user out. This will emit a logout event.
     */
    logout() {
        Emitter.emit(Emitter.LOG_OFF);
        sessionStorage.removeItem('user');
        sessionStorage.removeItem('token');
    },

    /**
     * Returns information about the currently logged in user.
     * @returns {any}
     */
    getCurrentUser() {
        return JSON.parse(sessionStorage.getItem('user'));;
    },

    /**
     * Returns the JWT token for the currently logged in user.
     * @returns {string}
     */
    getCurrentToken() {
        return sessionStorage.getItem('token');
    }
}

export default AuthService;