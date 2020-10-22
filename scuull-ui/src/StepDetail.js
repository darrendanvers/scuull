import React from 'react';

import './StepDetail.css'
/**
 * React hook to show the detail of a specific step in a specific execution of a job.
 *
 * @param stepDetail An object containing the step detail.
 * @returns {JSX.Element}
 * @constructor
 */
const StepDetail = ({stepDetail}) => {

    const DATE_TIME_FORMAT_OPTIONS = {dateStyle: "medium", timeStyle: "medium"};
    const DATE_TIME_FORMATTER = new Intl.DateTimeFormat("en-US", DATE_TIME_FORMAT_OPTIONS);

    return (
        <div className="step-detail box" key={stepDetail.stepName}>
            <div className="step-header"><h4>{stepDetail.stepName}</h4></div>
            <div className={stepDetail.status === "FAILED" ? "step-status failed" : "step-status"}>{stepDetail.status}</div>
            <div className="step-stats">
                <div className="step-stat">
                    <span className="step-stat-header">Start Time</span><span className="step-stat-detail">{DATE_TIME_FORMATTER.format(new Date(stepDetail.startTime))}</span>
                </div>
                <div className="step-stat">
                    <span className="step-stat-header">End Time</span><span className="step-stat-detail">{DATE_TIME_FORMATTER.format(new Date(stepDetail.endTime))}</span>
                </div>

                <div className="step-stat">
                    <span className="step-stat-header">Read Count</span><span className="step-stat-detail">{stepDetail.readCount.toLocaleString()}</span>
                </div>

                <div className="step-stat">
                    <span className="step-stat-header">Write Count</span><span className="step-stat-detail">{stepDetail.writeCount.toLocaleString()}</span>
                </div>

                <div className="step-stat">
                    <span className="step-stat-header">Commit Count</span><span className="step-stat-detail">{stepDetail.commitCount.toLocaleString()}</span>
                </div>

                <div className="step-stat">
                    <span className="step-stat-header">Rollback Count</span><span className="step-stat-detail">{stepDetail.rollbackCount.toLocaleString()}</span>
                </div>

                <div className="step-stat">
                    <span className="step-stat-header">Read Skip Count</span><span className="step-stat-detail">{stepDetail.readSkipCount.toLocaleString()}</span>
                </div>

                <div className="step-stat">
                    <span className="step-stat-header">Write Skip Count</span><span className="step-stat-detail">{stepDetail.writeSkipCount.toLocaleString()}</span>
                </div>

                <div className="step-stat">
                    <span className="step-stat-header">Filter Count</span><span className="step-stat-detail">{stepDetail.filterCount.toLocaleString()}</span>
                </div>
            </div>
        </div>
    );
}

export default StepDetail;