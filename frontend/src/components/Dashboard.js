// src/components/Dashboard.js
import React from 'react';
import { Container, Typography } from '@mui/material';

function Dashboard() {
    return (
        <Container>
            <Typography variant="h4" gutterBottom>
                Dashboard
            </Typography>
            <Typography>
                Welcome to the application!
            </Typography>
        </Container>
    );
}

export default Dashboard;
