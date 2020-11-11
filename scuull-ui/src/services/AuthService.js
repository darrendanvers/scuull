import axios from "axios";

const BASE_URI = "/auth/user";

const AuthService = {

    login(username, password) {
        return axios
            .post(BASE_URI, {
                userName: username,
                password: password
            })
            .then(response => {
                if (response.data) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }

                return response.data;
            });
    },

    logout() {
        localStorage.removeItem("user");
    },

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));;
    }
}

export default AuthService;