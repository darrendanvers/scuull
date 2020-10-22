package dev.codestijl.scuull.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.batch.core.StepExecution;

/**
 * Holds data about a specific execution of a specific step in a batch job. Basically equivalent to Spring's
 * StepExecution.
 *
 * @author darren
 * @since 1.0.0
 */
@Getter
@Setter
@Accessors(chain = true)
public class JobStep {

    private String stepName;
    private String status;

    private int readCount;
    private int writeCount;
    private int commitCount;
    private int rollbackCount;
    private int readSkipCount;
    private int writeSkipCount;
    private int filterCount;

    private long startTime;
    private long endTime;

    /**
     * Makes a new JobStep.
     *
     * @param stepExecution The StepExecution to copy data from.
     * @return A new JobStep.
     */
    public static JobStep from(final StepExecution stepExecution) {

        return new JobStep().setStepName(stepExecution.getStepName())
                .setStatus(stepExecution.getExitStatus().getExitCode())
                .setReadCount(stepExecution.getReadCount())
                .setWriteCount(stepExecution.getWriteCount())
                .setCommitCount(stepExecution.getCommitCount())
                .setRollbackCount(stepExecution.getRollbackCount())
                .setReadSkipCount(stepExecution.getReadSkipCount())
                .setWriteSkipCount(stepExecution.getWriteSkipCount())
                .setFilterCount(stepExecution.getFilterCount())
                .setStartTime(stepExecution.getStartTime().getTime())
                .setEndTime(stepExecution.getEndTime().getTime());
    }
}
