import React from 'react';

/**
 * React hook to show the detail of a specific step in a specific execution of a job.
 *
 * @param stepDetail An object containing the step detail.
 * @returns {JSX.Element}
 * @constructor
 */
const StepDetail = ({stepDetail}) => {

    return (
        <div className="step-detail" key={stepDetail.stepName}>
            <h4>{stepDetail.stepName}</h4>
            <p><label>Read Count: <span>{stepDetail.readCount}</span></label></p>
            <p><label>Write Count: <span>{stepDetail.writeCount}</span></label></p>
        </div>
    );
}

export default StepDetail;