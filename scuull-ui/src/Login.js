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

const Login = ({returnToPath}) => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");

    const onChangeUsername = (e) => {
        const username = e.target.value;
        setUsername(username);
    };

    const onChangePassword = (e) => {
        const password = e.target.value;
        setPassword(password);
    };

    const handleLogin = (e) => {
        e.preventDefault();

        setMessage("");
        setLoading(true);


            AuthService.login(username, password).then(
                () => {
                    navigate(returnToPath);
                },
                (error) => {
                    debugger;
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
                <form onSubmit={handleLogin} className="login-form">

                    {/*<div className="form-group">*/}
                    <label htmlFor={"username"}>Username</label>
                        <input type={"text"}
                               name={"username"}
                               value={username}
                               onChange={onChangeUsername}
                               validations={[required]}
                        />

                    {/*</div>*/}

                    {/*<div className="form-group">*/}
                        <label htmlFor={"password"}>Password </label>
                            <input type={"password"}
                                   name={"password"}
                                   value={password}
                                   onChange={onChangePassword}
                                   validations={[required]}
                            />

                    {/*</div>*/}

                    {/*<div className="form-group">*/}
                        <button className="form-button" disabled={loading}>
                            {loading && (
                                <span className="spinner-border spinner-border-sm"></span>
                            )}
                            <span>Login</span>
                        </button>
                    {/*</div>*/}
                </form>
                    {message && (
                        <div className="login-error" role="alert">
                            {message}
                        </div>
                    )}

            </div>
    );
};

export default Login;