import React from "react";
import { Link } from "@reach/router";

import './Error.css'

/**
 * React hook to display error messages.
 *
 * @param displayMessage The generic message to display with this error.
 * @param errorMessage The specific error that happened.
 * @returns {JSX.Element}
 * @constructor
 */
const Error = ({displayMessage, errorMessage}) => {

    return (
        <div className="error-display">
            <h2>Error</h2>
            <p className="display-message">{displayMessage}</p>
            <p className="error-message">{errorMessage}</p>
            <p>Click <Link to="/">here</Link> to return to home.</p>
        </div>
    )
}

export default Error;


