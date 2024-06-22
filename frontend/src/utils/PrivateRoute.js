// src/utils/PrivateRoute.js
import React from 'react';
import { Navigate } from 'react-router-dom';

const PrivateRoute = ({ component: Component }) => {
    return localStorage.getItem('token') ? (
        <Component />
    ) : (
        <Navigate to="/login" />
    );
};

export default PrivateRoute;
