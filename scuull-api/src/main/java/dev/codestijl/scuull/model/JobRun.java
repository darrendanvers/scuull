package dev.codestijl.scuull.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.batch.core.JobInstance;

/**
 * Represents the run of a specific job. This may represent multiple executions of the job if, for example, it failed
 * one or more times. It is basically equivalent to the Spring Batch JobInstance.
 *
 * @author darren
 * @since 1.0.0
 */
@Getter
@Setter
@Accessors(chain = true)
public class JobRun {

    private String jobName;
    private long instanceId;

    private List<JobExecution> executions = new LinkedList<>();

    /**
     * Creates a new JobRun. The JobRun will contain information about each execution of the job, but the data for
     * each execution will be shallow as it will not contain step information.
     *
     * @param jobInstance The JobInstance this object will contain information about.
     * @param executions A collection of Spring JobExecutions containing information about each time this job
     *                   JobInstance was run.
     * @return A JobRun with all instance level data populated and some  execution level data populated.
     */
    public static JobRun from(final JobInstance jobInstance, final Collection<org.springframework.batch.core.JobExecution> executions) {

        final JobRun jobRun = new JobRun().setInstanceId(jobInstance.getInstanceId())
                .setJobName(jobInstance.getJobName());

        executions.stream()
                .map(JobExecution::from)
                .forEach(jobRun.getExecutions()::add);

        return jobRun;
    }
}
