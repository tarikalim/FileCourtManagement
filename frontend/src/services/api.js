import axios from 'axios';
import {getNavigate} from './navigateWrapper';

const api = axios.create({
    baseURL: 'http://localhost:8080', // Backend base URL
});

// Request interceptor to add the token to the headers
api.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if (token && config.url !== '/auth/login') {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
}, error => {
    return Promise.reject(error);
});

// Response interceptor to handle token expiration and other errors
api.interceptors.response.use(response => {
    return response;
}, error => {
    const navigate = getNavigate();

    if (error.response) {
        if (error.response.status === 401) {
            localStorage.removeItem('token');
            if (navigate) {
                navigate('/login');
            }
        } else if (error.response.status === 403) {
            return Promise.reject({...error, customMessage: 'No authorization'});
        } else if (error.response.status === 400) {
            const errorMessage = error.response.data.filename || error.response.data.message || 'Error adding file';
            return Promise.reject({...error, customMessage: errorMessage});
        }
    }
    return Promise.reject(error);
});

export const login = (username, password) => {
    return api.post('/auth/login', {username, password});
};

export const getCurrentUser = () => {
    return api.get('/users/me');
};

export const getUserFiles = () => {
    return api.get('/users/me/files');
};

export const getAllFiles = () => {
    return api.get('/files');
};

export const addFile = (file) => {
    return api.post('/files', file);
};
export const searchFileByFilename = (filename) => {
    return api.get(`/files/search`, {params: {filename}});
};
export const searchFilesByUsername = (username) => {
    return api.get(`/users/files/search`, {params: {username}});
};
export const getAllCourts = () => {
    return api.get('/users');
}


export default api;
