import axios  from 'axios';
import { navigate } from "@reach/router"

import AuthService from "./AuthService";

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
    return ({data}) => {
        fn(data);
    }

}

/**
 * Returns a callback that will actually handle the failure callback from the Promise
 * returned by Asios. This is here just because the object returned by Axios is
 * fairly complicated, and I didn't want to deal with it in all the client code.
 *
 * @param fn The function to call when the HTTP request failed.
 * @returns {function(*): *}
 */
function callFailureCallback(location, fn) {
    // Just use the toLocaleString to convert the error to a string.
    return (err) => {
        debugger;
        if (err.response && err.response.status && [401, 403].indexOf(err.response.status) !== -1) {
            AuthService.logout();
            navigate("/login", { state: {returnToPath: location}})
            return;
        }
        fn(err.toLocaleString());
    }
}

function authHeader() {
    debugger;
    const user = JSON.parse(localStorage.getItem('user'));

    if (user) {
        return { Authorization: 'Bearer ' + user };
    } else {
        return {};
    }
}

/**
 * Helper function that performs a GET against the API.
 *
 * @param path The path of the API endpoint to hit. Should be the part of the path
 * after BASE_URI.
 * @param successCallback The callback to use when the HTTP request was successful.
 * @param failureCallback The callback to use when the HTTP request was not successful.
 * @returns {Promise<AxiosResponse<any>>}
 */
const makeGetRequest = (location, path, successCallback, failureCallback) => {
    axios.get(`${BASE_URI}${path}`, { headers: authHeader() })
        .then(callSuccessCallback(successCallback), callFailureCallback(location, failureCallback));
}

const JobService = {

    location: "/",

    pushLocation(location) {
        this.location = location;
        return this;
    },

    /**
     * Calls the jobs endpoint to retrieve a list of instances of a particular job.
     *
     * @param jobName The name of the job to return instance of.
     * @param successCallback The callback to use when the request for job instances was successful.
     * @param failureCallback The callback to use when the request for job instances was not successful.
     * @returns {Promise<AxiosResponse<any>>}
     */
    jobInstances(jobName, successCallback, failureCallback) {
        return makeGetRequest(this.location, `${jobName}`, successCallback, failureCallback);
    },

    /**
     * Calls the jobs endpoint to retrieve a list of all jobs.
     *
     * @param successCallback The callback to use when the request for job names was successful.
     * @param failureCallback The callback to use when the request for job names was not successful.
     * @returns {Promise<AxiosResponse<*>>}
     */
    jobNames(successCallback, failureCallback) {
        return makeGetRequest(this.location,"", successCallback, failureCallback);
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
        return makeGetRequest(this.location,`execution/${executionId}`, successCallback, failureCallback)
    }
}

export default JobService;