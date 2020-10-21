import React, { useState, useEffect } from 'react';
import {useLocation} from "@reach/router"
import JobService from './services/JobService';

import JobInstance from "./JobInstance";
import ExecutionDetail from "./ExecutionDetail";
import Emitter from "./Emitter";

import './JobInstances.css'

/**
 * React hook to handle displaying all instances of a specific job.
 *
 * @returns {JSX.Element}
 * @constructor
 */
const JobInstances = () => {

    const location = useLocation();

    const [jobInstances, setJobInstances] = useState([]);
    const [executionDetail, setExecutionDetail] = useState(null);

    /**
     * Callback for handling the event when a user selects a specific execution
     * of a job. This will load all the detail of the execution to display in the
     * ExecutionDetail component.
     *
     * @param executionId The ID if the job execution to display.
     */
    function handleSelectExecution(executionId) {
        JobService.executionDetail(executionId).then((executionDetail) => {
            setExecutionDetail(executionDetail);
        }, console.error);
    }

    /**
     * Loads all instances of a specific job. It will get the job's name from the path.
     */
    useEffect(() => {
        JobService.jobInstances(location.pathname).then((instances) => {
            setJobInstances(instances);
        }, console.error)
    }, [location.pathname, setJobInstances]);

    /**
     * Creates the event handler for the JOB-EXECUTION-SELECT event.
     */
    useEffect(() => {
        Emitter.on(Emitter.JOB_EXECUTION_SELECT, handleSelectExecution);

        // React will call this when unloading the component.
        return () => {Emitter.off(Emitter.JOB_EXECUTION_SELECT)};
    }, []);

    return (
        <div className="data-buckets">
            <div className="job-instance-list">
                {jobInstances.map((jobInstance) => (
                    <JobInstance key={jobInstance.instanceId}  jobInstance={jobInstance} />
                ))}
            </div>
            { executionDetail ? <ExecutionDetail executionDetail={executionDetail} /> : <div />}
        </div>
    );
}

export default JobInstances;