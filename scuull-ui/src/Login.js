import React, { useState } from "react";
import { navigate } from "@reach/router"

import AuthService from "./services/AuthService";

import './Login.css';

const required = (value) => {
    if (!value) {
        return (
            <div className="alert alert-danger" role="alert">
                This field is required!
            </div>
        );
    }
};

/**
 * React hook to handle displaying the Login page.
 *
 * @param returnToPath A path to return to after successful login.
 * @returns {JSX.Element}
 * @constructor
 */
const Login = ({returnToPath}) => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");

    /**
     * Called when the user updates the user name field.
     * @param e The event that triggered the change.
     */
    const onChangeUsername = (e) => {
        const username = e.target.value;
        setUsername(username);
    };

    /**
     * Called when the user updates the user name field.
     * @param e The event that triggered the change.
     */
    const onChangePassword = (e) => {
        const password = e.target.value;
        setPassword(password);
    };

    /**
     * Called to log the user in.
     * @param e The event that triggered the login.
     */
    const handleLogin = (e) => {
        e.preventDefault();

        setMessage("");
        setLoading(true);


            AuthService.login(username, password).then(
                () => {
                    navigate(returnToPath);
                },
                (error) => {
                    const resMessage =
                        (error.response &&
                            error.response.data &&
                            error.response.data.message) ||
                        error.message ||
                        error.toString();

                    setLoading(false);
                    setMessage(resMessage);
                }
            );

    };

    return (
            <div className={"login-grid"}>
                    {loading ? (<span className="spinner"></span>) : (
                        <form onSubmit={handleLogin} className="login-form">
                            <label htmlFor={"username"}>Username</label>
                                <input type={"text"}
                                       name={"username"}
                                       id={"username"}
                                       value={username}
                                       onChange={onChangeUsername}
                                       validations={[required]}
                                />

                            <label htmlFor={"password"}>Password </label>
                                <input type={"password"}
                                       name={"password"}
                                       id={"password"}
                                       value={password}
                                       onChange={onChangePassword}
                                       validations={[required]}
                                />
                            <button className="form-button" disabled={loading}>
                                <span>Login</span>
                            </button>
                        </form>
                    )}

                    {message && (
                        <div className="login-error" role="alert">
                            {message}
                        </div>
                    )}
            </div>
    );
};

export default Login;