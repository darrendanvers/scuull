import React, { useState, useEffect } from 'react';
import {useParams} from "@reach/router"

import JobService from './services/JobService';
import JobInstance from "./JobInstance";

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

    /**
     * Loads all instances of a specific job. It will get the job's name from the path.
     */
    useEffect(() => {
        JobService.jobInstances(routeParams.jobName).then((instances) => {
            setJobInstances(instances);
        }, console.error)
    }, [routeParams.jobName, setJobInstances]);

    return (
        <div className="job-instance-list">
            {jobInstances.map((jobInstance) => (
                <JobInstance key={jobInstance.instanceId}  jobInstance={jobInstance} />
            ))}
        </div>
    );
}

export default JobInstances;