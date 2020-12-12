import React, {useState, useEffect} from 'react';
import {Link, useLocation, useParams} from "@reach/router"

import StepDetail from "./StepDetail";
import Error from "./Error";

import './ExecutionDetail.css'
import JobService from "./services/JobService";

/**
 * React hook to display the detailed information about a specific execution of a job.
 *
 * @returns {JSX.Element}
 * @constructor
 */
const ExecutionDetail = () => {

    const location = useLocation();
    const routeParams = useParams();
    const [executionDetail, setExecutionDetail] = useState(null);
    const [errorMessage, setErrorMessage] = useState(null);

    /**
     * Fetches the information about a specific execution instance.
     */
    useEffect(() => {
        JobService.pushLocation(location.pathname).executionDetail(routeParams.executionId,
            (executionDetail) => {
                setExecutionDetail(executionDetail);
            }, (errorMessage) => {
                setErrorMessage(errorMessage);
            }
        )
    }, [routeParams.executionId, setExecutionDetail, setErrorMessage, location]);

    return (
        <div className="execution-detail">
            {errorMessage ?
                (<Error displayMessage="There was an error trying to retrieve job execution detail"
                        errorMessage={errorMessage} />) : (<div />)}
            {executionDetail ?
                (<div>
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
                </div>) : (<div />)
            }
        </div>
    );
}

export default ExecutionDetail;