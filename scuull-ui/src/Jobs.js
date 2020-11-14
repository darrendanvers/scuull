import React, {useEffect, useState} from 'react';
import { useLocation } from '@reach/router';

import JobService from "./services/JobService";
import JobInstance from "./JobInstance";
import Error from './Error';

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
    const [errorMessage, setErrorMessage] = useState(null);

    const location = useLocation();

    /**
     * Fetches the list of all job names.
     */
    useEffect(() => {

        JobService.pushLocation(location).jobNames((jobNames) => {
            setJobNames(jobNames);
        }, (err) => {
            setErrorMessage(err);
        });
    },[errorMessage, location]);

    return (
        <div className="job-name-list">
            {errorMessage ? (
                <Error displayMessage="There was an error trying to retrieve a list of job names"
                    errorMessage={errorMessage} />
            ) : (
                jobNames.map((jobInstance) =>
                    <JobInstance key={jobInstance.instanceId} jobInstance={jobInstance} />
                )
            )}
        </div>
    );
}

export default Jobs;