import axios  from 'axios';

const BASE_URI = "/jobs/";

/**
 * Common success handling routine.
 *
 * @param data The object from the HTTP call.
 * @returns {*}
 */
// The information we care about is wrapped in an Axios object. We're not interested in
// any other part of the wrapper, so just pull out the data from it.
const success = ({data}) => data;

/**
 * Common error handling routine.
 *
 * @param err
 * @returns {*}
 */
// Just forward the error on.
const fail = err => err;

/**
 * Helper function that performs a GET against the API.
 *
 * @param path The path of the API endpoint to hit. Should be the part of the path
 * after BASE_URI.
 * @param params Any parameters to include in the call.
 * @returns {Promise<AxiosResponse<any>>}
 */
const makeGetRequest = (path, params = {}) =>
    axios.get(`${BASE_URI}${path}`, {params}).then(success, fail);

const JobService = {

    /**
     * Calls the jobs endpoint to retrieve a list of instances of a particular job.
     *
     * @param jobName The name of the job to return instance of.
     * @returns {Promise<AxiosResponse<any>>}
     */
    jobInstances(jobName) {
        return makeGetRequest(`${jobName}`);
    },

    /**
     * Calls the jobs endpoint to retrieve a list of all jobs.
     *
     * @returns {Promise<AxiosResponse<*>>}
     */
    jobNames() {
        return makeGetRequest("");
    },

    /**
     * Calls the jobs endpoint to retrieve data about a specific execution of a job.
     *
     * @param executionId The ID of the job execution.
     * @returns {Promise<AxiosResponse<*>>}
     */
    executionDetail(executionId) {
        return makeGetRequest(`execution/${executionId}`)
    }
}

export default JobService;