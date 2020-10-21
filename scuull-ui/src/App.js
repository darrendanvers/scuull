import React from 'react';
import { Router, Link } from '@reach/router';

import JobInstances from "./JobInstances";
import Jobs from "./Jobs";

import './App.css';

/**
 * React hook containing the driver of the rest of the applicaiton.
 *
 * @returns {JSX.Element}
 * @constructor
 */
function App() {
    return (
        <div className="App">
            <header className="App-header">
                <Link to="/"><h1>Scuull</h1></Link>
            </header>
            <div className="App-body">
                <Router>
                    <Jobs path="/" />
                    <JobInstances path={"/:jobName"}/>
                </Router>
            </div>
        </div>
    );
}

export default App;
