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
                    localStorage.setItem("token", response.data);
                    const user = jwt(response.data);
                    localStorage.setItem('user', JSON.stringify(user));
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
        localStorage.removeItem('user');
        localStorage.removeItem('token');
    },

    /**
     * Returns information about the currently logged in user.
     * @returns {any}
     */
    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));;
    },

    /**
     * Returns the JWT token for the currently logged in user.
     * @returns {string}
     */
    getCurrentToken() {
        return localStorage.getItem('token');
    }
}

export default AuthService;