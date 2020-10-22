import React, {useState, useEffect} from 'react';
import {Link, useParams} from "@reach/router"

import StepDetail from "./StepDetail";

import './ExecutionDetail.css'
import JobService from "./services/JobService";

/**
 * React hook to display the detailed information about a specific execution of a job.
 *
 * @returns {JSX.Element}
 * @constructor
 */
const ExecutionDetail = () => {

    const routeParams = useParams();
    const [executionDetail, setExecutionDetail] = useState(null);

    /**
     * Fetches the information about a specific execution instance.
     */
    useEffect(() => {
        JobService.executionDetail(routeParams.executionId).then((executionDetail) => {
            setExecutionDetail(executionDetail);
        }, console.error)
    }, [routeParams.executionId, setExecutionDetail]);

    return (
        <div className="execution-detail">
            {executionDetail ? (
                <div>
                    <div className="execution-header">
                        <h2>
                            <Link to={`/${executionDetail.jobName}`}>{executionDetail.jobName}</Link>
                        </h2>
                        <p>
                            <label className="job-execution-info">Job Instance ID:<span>{executionDetail.instanceId}</span></label>
                        </p>
                        <p>
                            <label className="job-execution-info">Job Execution ID:<span>{executionDetail.executionId}</span></label>
                        </p>
                    </div>
                    <h3>Job Parameters</h3>
                    <div className="job-parameters box">
                        { Object.keys(executionDetail.parameters).map(k => (
                            <div className="job-parameter" key={k}>
                                <span className="job-parameter-header ">{k}</span>
                                <span className="job-parameter-value"> {executionDetail.parameters[k]}</span>
                            </div>
                        ))}
                    </div>
                    <h3>Step Details</h3>
                    <div className="step-details">
                        {executionDetail.steps.map((step) => <StepDetail key={step.stepName} stepDetail={step} /> )}
                    </div>
                </div>
            ) : (<div />)}
        </div>
    );
}

export default ExecutionDetail;