import React from "react";
import axios from 'axios'
import {APP_BASE_URL, SERVER_BASE_URL} from '../constants/Constants';


const serverBaseUrl = process.env.REACT_APP_SERVER_BASE_URL;
const appBaseUrl = process.env.REACT_APP_APP_BASE_URL;


const apiClient = axios.create({
    baseURL: SERVER_BASE_URL,
    headers: {
        "Content-Type": "application/json"
    }
});


// Adding methods for get post put and delete.
const _get = (url, config = {}) => {
    return apiClient.get(url, config);
};

const _delete = (url, config = {}) => {
    return apiClient.delete(url, config);
};

const _put = (url, config = {}, data = {}) => {
    return apiClient.put(url, data, config);
};

const _post = (url, config = {}, data = {}) => {
    return apiClient.post(url, data, config);
}

//Exporting API methods.
export {_get, _post, _put, _delete};