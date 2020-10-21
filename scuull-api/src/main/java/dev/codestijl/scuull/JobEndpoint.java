package dev.codestijl.scuull;

import dev.codestijl.scuull.model.JobExecution;
import dev.codestijl.scuull.model.JobRun;

import java.util.Objects;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST endpoint to return job history information.
 *
 * @author darren
 * @since 1.0.0
 */
@RestController
@RequestMapping("/jobs")
public class JobEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(JobEndpoint.class);

    private static final int DEFAULT_START = 0;
    private static final int DEFAULT_COUNT =  20;

    private final JobExplorer jobExplorer;

    /**
     * Constructs a new JobEndpoint.
     *
     * @param jobExplorer The JobExplorer to use to retrieve job history information.
     */
    @Autowired
    public JobEndpoint(final JobExplorer jobExplorer) {
        this.jobExplorer = jobExplorer;
    }

    /**
     * Returns a stream of JobRuns that represents the most recent instance of all jobs that
     * are available in the job history.
     *
     * @return A stream of JobRuns that has the most recent instance of all jobs.
     */
    @GetMapping
    public Stream<JobRun> getJobNames() {

        logger.info("Returning all job names.");

        return this.jobExplorer.getJobNames()
                .stream()
                .map(this::nameToJob)
                .filter(Objects::nonNull);
    }

    private JobRun nameToJob(final String jobName) {

        // Get the most recent instance of the job.
        final JobInstance mostRecent = this.jobExplorer.getLastJobInstance(jobName);

        // If it's null, not sure what that even means, so just log the error.
        if (Objects.isNull(mostRecent)) {
            logger.error(String.format("Job '%s' was returned by the explorer, but no last instance was returned.", jobName));
            return null;
        }
        return this.instanceToRun(mostRecent);
    }

    /**
     * Returns a stream of JobRuns for a specific job. This endpoint is build to support pagination, so
     * it takes a firstRecord to return and a maximum number of records to return. If firstRecord is null, then
     * 0 is assumed. If count is null then 20 is assumed.
     *
     * @param jobName The name of the job to find runs for.
     * @param firstRecord The first record to look for.
     * @param recordCount The maximum number of records to return.
     * @return A stream with a page of JobRuns for the job named jobName.
     */
    @GetMapping(path = "{jobName}")
    public Stream<JobRun> getByJobName(@PathVariable("jobName") final String jobName,
                                    @RequestParam(value = "first", required = false) final Integer firstRecord,
                                    @RequestParam(value = "count", required = false) final Integer recordCount) {

        final int first = Objects.isNull(firstRecord) ? DEFAULT_START : firstRecord;
        final int count = Objects.isNull(recordCount) ? DEFAULT_COUNT : recordCount;

        logger.info(String.format("Returning records %d of through %d for job '%s'.", first, count, jobName));

        return this.jobExplorer.getJobInstances(jobName, first, count)
                .stream()
                .map(this::instanceToRun);
    }

    private JobRun instanceToRun(final JobInstance jobInstance) {

        return JobRun.from(jobInstance, this.jobExplorer.getJobExecutions(jobInstance));
    }

    /**
     * Returns a stream of all executions associated with a specific job in stance. This will return
     * a shallow version of each execution as the step details will not be populated.
     *
     * @param instanceId The ID of the instance to return execution detail for.
     * @return A stream of all executions associated with job instance instanceId.
     * @throws NotFoundException If the instance ID cannot be found.
     */
    @GetMapping(path = "/instance/{instanceId}")
    public Stream<JobExecution> getExecutions(@PathVariable("instanceId") final long instanceId) {

        logger.info(String.format("Returning job executions for job instance %d.", instanceId));

        final JobInstance jobInstance = this.jobExplorer.getJobInstance(instanceId);
        if (Objects.isNull(jobInstance)) {
            throw new IllegalArgumentException();
        }

        return this.jobExplorer.getJobExecutions(jobInstance)
                .stream()
                .map(JobExecution::from);
    }

    /**
     * Returns all detail related to a specific execution of a job. This will be a deeper version than
     * getExecutions as it will contain all step detail.
     *
     * @param executionId The execution ID to return information for.
     * @return Detailed execution data for executionId.
     * @throws NotFoundException If the execution ID is not found.
     */
    @GetMapping(path = "/execution/{executionId}")
    public JobExecution getJobExecution(@PathVariable("executionId") final long executionId) {

        logger.info(String.format("Returning job execution detail for job execution %d.", executionId));

        final org.springframework.batch.core.JobExecution jobExecution = this.jobExplorer.getJobExecution(executionId);
        if (Objects.isNull(jobExecution)) {
            throw new NotFoundException("Execution %d not found.", executionId);
        }

        return JobExecution.from(jobExecution, true);
    }
}
