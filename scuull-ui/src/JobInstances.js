import React, { useState, useEffect } from 'react';
import {useParams} from "@reach/router"

import JobService from './services/JobService';
import JobInstance from "./JobInstance";
import Error from "./Error";

import './JobInstances.css'

/**
 * React hook to handle displaying all instances of a specific job.
 *
 * @returns {JSX.Element}
 * @constructor
 */
const JobInstances = () => {

    const routeParams = useParams();

    const [jobInstances, setJobInstances] = useState([]);
    const [errorMessage, setErrorMessage] = useState(null);

    /**
     * Loads all instances of a specific job. It will get the job's name from the path.
     */
    useEffect(() => {
        JobService.jobInstances(routeParams.jobName,
            (instances) => {
                setJobInstances(instances);
            }, (errorMessage) => {
                setErrorMessage(errorMessage);
            })
    }, [routeParams.jobName, setJobInstances, setErrorMessage]);

    return (
        <div className="job-instance-list">
            { errorMessage ? (
                <Error displayMessage="There was an error trying to retrieve a list of job instances"
                       errorMessage={errorMessage} />
            ) : (
                jobInstances.map((jobInstance) => (
                        <JobInstance key={jobInstance.instanceId}  jobInstance={jobInstance} />
                    ))
            )}
        </div>
    );
}

export default JobInstances;