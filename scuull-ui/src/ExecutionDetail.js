import React from 'react';

import StepDetail from "./StepDetail";

/**
 * React hook to display the detailed information about a specific execution of a job.
 *
 * @param executionDetail The detail of the job execution.
 * @returns {JSX.Element}
 * @constructor
 */
const ExecutionDetail = ({executionDetail}) => {

    return (
        <div key={executionDetail.id}>
            {executionDetail.steps.map((step) => <StepDetail key={step.stepName} stepDetail={step} /> )}
        </div>
    );
}

export default ExecutionDetail;