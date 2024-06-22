import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Container, Typography } from '@mui/material';
import Login from './components/Login';
import Dashboard from './components/Dashboard';
import PrivateRoute from './utils/PrivateRoute';

function App() {
    return (
        <Router>
            <Container maxWidth="md">
                <Typography variant="h2" gutterBottom>
                </Typography>
                <Routes>
                    <Route path="/login" element={<Login />} />
                    <Route path="/dashboard" element={<PrivateRoute component={Dashboard} />} />
                    <Route path="/" element={<Login />} />
                </Routes>
            </Container>
        </Router>
    );
}

export default App;
