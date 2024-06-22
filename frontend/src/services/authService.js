import axios from 'axios';

const API_URL = 'http://localhost:5000/';

const login = async (username, password) => {
    const response = await axios.post(API_URL + 'auth/login', { username, password });
    if (response.data) {
        localStorage.setItem('token', response.data);
    }
    return response.data;
};

const logout = () => {
    localStorage.removeItem('token');
};

const getCurrentUser = () => {
    return localStorage.getItem('token');
};

axios.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default {
    login,
    logout,
    getCurrentUser,
};
