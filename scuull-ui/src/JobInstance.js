import React from 'react';
import {Link} from '@reach/router';

import './JobInstance.css'

/**
 * React Hook that handles showing a single instance of a job. It will also list out each execution tied to that instance.
 *
 * @param jobInstance The JobInstance to display.
 * @returns {JSX.Element}
 * @constructor
 */
const JobInstance = ({jobInstance}) => {

    const DATE_TIME_FORMAT_OPTIONS = {dateStyle: "medium", timeStyle: "medium"};
    const DATE_TIME_FORMATTER = new Intl.DateTimeFormat("en-US", DATE_TIME_FORMAT_OPTIONS);

    return (
        <div className="job-instance box" >
            <div>
                <h2><Link to={`/${jobInstance.jobName}`}>{jobInstance.jobName}</Link> {` [${jobInstance.instanceId}]`}</h2>
            </div>
            <div className="job-executions">
                <div className="job-execution-header">
                    <span className="job_execution-header-cell">ID</span>
                    <span className="job_execution-header-cell">Job Status</span>
                    <span className="job_execution-header-cell">Start Time</span>
                </div>
                {jobInstance.executions.map((jobExecution) => (
                    <div className="job-execution" key={jobExecution.executionId}>
                        <Link to={`/executions/${jobExecution.executionId}`}>
                            <span className="execution-id execution-cell">{jobExecution.executionId}</span>
                        </Link>
                        <span className={jobExecution.status === "FAILED" ? "execution-status execution-cell failed" : "execution-status execution-cell"}>
                            {jobExecution.status}
                        </span>
                        <span className="execution-time execution-cell">{DATE_TIME_FORMATTER.format(new Date(jobExecution.startTime))}</span>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default JobInstance;