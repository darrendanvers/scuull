import React from 'react';
import { Router } from '@reach/router';

import Header from "./Header";
import Login from "./Login";
import Jobs from "./Jobs";
import JobInstances from "./JobInstances";
import ExecutionDetail from "./ExecutionDetail";

import './App.css';

/**
 * React hook containing the driver of the rest of the application.
 *
 * @returns {JSX.Element}
 * @constructor
 */
function App() {
    return (
        <div className="App">
            <header>
                <Header />
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
