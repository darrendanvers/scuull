import React, {useEffect, useState} from 'react';
import {Link} from '@reach/router';

import JobService from "./services/JobService";
import JobInstance from "./JobInstance";

import './Jobs.css'

/**
 * React hook that handles displaying the list of all job names.
 *
 * @returns {JSX.Element}
 * @constructor
 */
const Jobs = () => {

    // Holds the list of job names.
    const [jobNames, setJobNames] = useState([]);

    /**
     * Fetches the list of all job names.
     */
    useEffect(() => {

        JobService.jobNames().then((jobNames) => {
            setJobNames(jobNames);
        }, console.error);
    },[])

    return (
        <div className="job-name-list">
            {jobNames.map((jobInstance) =>
                <Link key={jobInstance.instanceId} to={`/${jobInstance.jobName}`}>
                    <JobInstance jobInstance={jobInstance} />
                </Link>)}
        </div>
    );
}

export default Jobs;