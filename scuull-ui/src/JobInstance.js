import React from 'react';

import Emitter from "./Emitter";

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

    /**
     * Will issue an event when the user clicks on a particular execution of this job instance. The event
     * will contain the execution ID of the execution the user selected.
     *
     * @param event The event triggered by the user's click.
     */
    function executionSelectEvent(event) {
        Emitter.emit(Emitter.JOB_EXECUTION_SELECT, +event.target.dataset.index);
    }

    return (
        <div className="job-instance" >
            <div>
                <h3>{`${jobInstance.jobName} [${jobInstance.instanceId}]`}</h3>
            </div>
            {/*Need to narrow the clickable and put it on the data-index*/}
            <div className="job-executions">
                <div className="job-execution-header">
                    <span className="job_execution-header-cell">ID</span>
                    <span className="job_execution-header-cell">Job Status</span>
                    <span className="job_execution-header-cell">Start Time</span>
                </div>
                {jobInstance.executions.map((jobExecution) => (
                    <div className="job-execution" key={jobExecution.id}>
                        <span className="execution-id execution-cell" onClick={executionSelectEvent} data-index={jobExecution.id}>{jobExecution.id}</span>
                        <span className="execution-status execution-cell">{jobExecution.status}</span>
                        <span className="execution-time execution-cell">{DATE_TIME_FORMATTER.format(new Date(jobExecution.startTime))}</span>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default JobInstance;