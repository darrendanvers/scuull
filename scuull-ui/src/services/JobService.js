import axios  from 'axios';

const BASE_URI = "/jobs/";

/**
 * Returns a callback that will actually handle the success callback from the Promise
 * returned by Axios. This is here just because the object returned by Axios is
 * fairly complicated, and I didn't want to deal with it in all the client code.
 *
 * @param fn The function to call when the HTTP request was successful.
 * @returns {function({data?: *}): *}
 */
function callSuccessCallback(fn) {
    // Return just the data property of the object Axios hands over.
    return ({data}) => fn(data);
}

/**
 * Returns a callback that will actually handle the failure callback from the Promise
 * returned by Asios. This is here just because the object returned by Axios is
 * fairly complicated, and I didn't want to deal with it in all the client code.
 *
 * @param fn The function to call when the HTTP request failed.
 * @returns {function(*): *}
 */
function callFailureCallback(fn) {
    // Just use the toLocaleString to convert the error to a string.
    return (err) => fn(err.toLocaleString());
}

/**
 * Helper function that performs a GET against the API.
 *
 * @param path The path of the API endpoint to hit. Should be the part of the path
 * after BASE_URI.
 * @param successCallback The callback to use when the HTTP request was successful.
 * @param failureCallback The callback to use when the HTTP request was not successful.
 * @param params Any parameters to include in the call. This parameter is optional.
 * @returns {Promise<AxiosResponse<any>>}
 */
const makeGetRequest = (path, successCallback, failureCallback, params = {}) => {
    axios.get(`${BASE_URI}${path}`, {params})
        .then(callSuccessCallback(successCallback), callFailureCallback(failureCallback));
}

const JobService = {

    /**
     * Calls the jobs endpoint to retrieve a list of instances of a particular job.
     *
     * @param jobName The name of the job to return instance of.
     * @param successCallback The callback to use when the request for job instances was successful.
     * @param failureCallback The callback to use when the request for job instances was not successful.
     * @returns {Promise<AxiosResponse<any>>}
     */
    jobInstances(jobName, successCallback, failureCallback) {
        return makeGetRequest(`${jobName}`, successCallback, failureCallback);
    },

    /**
     * Calls the jobs endpoint to retrieve a list of all jobs.
     *
     * @param successCallback The callback to use when the request for job names was successful.
     * @param failureCallback The callback to use when the request for job names was not successful.
     * @returns {Promise<AxiosResponse<*>>}
     */
    jobNames(successCallback, failureCallback) {
        return makeGetRequest("", successCallback, failureCallback);
    },

    /**
     * Calls the jobs endpoint to retrieve data about a specific execution of a job.
     *
     * @param successCallback The callback to use when the request for execution detail was successful.
     * @param failureCallback The callback to use when the request for execution detail was not successful.
     * @param executionId The ID of the job execution.
     * @returns {Promise<AxiosResponse<*>>}
     */
    executionDetail(executionId, successCallback, failureCallback) {
        return makeGetRequest(`execution/${executionId}`, successCallback, failureCallback)
    }
}

export default JobService;