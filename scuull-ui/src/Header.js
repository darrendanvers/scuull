import React, { useState, useEffect } from 'react';
import {Link} from '@reach/router';

import Emitter from "./services/Emitter";
import AuthService from "./services/AuthService";

import './Header.css'

/**
 * React hook to display the application header.
 *
 * @returns {JSX.Element}
 * @constructor
 */
const Header = () => {

    const [user, setUser] = useState(AuthService.getCurrentUser());

    /**
     * Configures maintaining the user name in the header.
     */
    useEffect(() => {
        Emitter.on(Emitter.LOGON, (user) => setUser(user));
        Emitter.on(Emitter.LOG_OFF, () => setUser(null));

        return () => {
            Emitter.off(Emitter.LOGON);
            Emitter.off(Emitter.LOG_OFF);
        }
    });

    /**
     * Function for when the user clicks on logout.
     */
    const logout = () => {
        AuthService.logout();
    }

    return (
        <ul className={"App-header"}>
            <li><Link to={"/"}>Scuull</Link> </li>
            {
                user && (
                    <li><a href="/" onClick={logout}>Logout</a></li>
                )
             }
            {
                user && (
                <li className={"user-name"}><span>You are logged in as {user.FNM}</span></li>
                )
            }
        </ul>
    );
};

export default Header;