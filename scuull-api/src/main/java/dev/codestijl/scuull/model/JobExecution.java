package dev.codestijl.scuull.model;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Represents a specific time a job ran. It is roughly equivalent to Spring's JobExecution.
 *
 * @author darren
 * @since 1.0.0
 */
@Getter
@Setter
@Accessors(chain = true)
public class JobExecution {

    private long id;
    private long startTime;
    private String status;

    private List<JobStep> steps = new LinkedList<>();

    /**
     * Constructs a new JobExecution. This overloading does not populate step level data.
     *
     * @param jobExecution The Spring JobExecution to copy data from.
     * @return A new JobExecution without step level data.
     */
    public static JobExecution from(final org.springframework.batch.core.JobExecution jobExecution) {

        return from(jobExecution, false);
    }

    /**
     * Constructs a new JobExecution. This overload allows for making either a shallow (no step data) or a deep (step data)
     * copy.
     *
     * @param jobExecution The JobExecution to copy data from.
     * @param includeSteps Whether or not to include step data.
     * @return A new JobExecution.
     */
    public static JobExecution from(final org.springframework.batch.core.JobExecution jobExecution, final boolean includeSteps) {

        final JobExecution toReturn = new JobExecution().setId(jobExecution.getId())
                .setStartTime(jobExecution.getStartTime().getTime())
                .setStatus(jobExecution.getStatus().toString());

        if (includeSteps) {
            jobExecution.getStepExecutions()
                    .stream()
                    .map(JobStep::from)
                    .forEach(toReturn.getSteps()::add);
        }

        return toReturn;
    }
}
