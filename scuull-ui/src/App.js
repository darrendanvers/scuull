import React from 'react';
import { Router, Link } from '@reach/router';

import Login from "./Login";
import Jobs from "./Jobs";
import JobInstances from "./JobInstances";
import ExecutionDetail from "./ExecutionDetail";

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
                    <Login path="/login" returnToPath={"/"} />
                    <Jobs path="/" />
                    <JobInstances path={"/:jobName"} />
                    <ExecutionDetail path={"/executions/:executionId"} />
                </Router>
            </div>
        </div>
    );
}

export default App;
