import React from 'react';
import {Container, Box} from '@mui/material';
import UserDetails from '../components/UserDetails';
import AppDrawer from '../components/AppDrawer';

const Home = () => {
    return (
        <Container>
            <Box display="flex" flexDirection="row" alignItems="center">
                <AppDrawer/>
                <Box display="flex" flexDirection="column" alignItems="center" flexGrow={1}>

                    <UserDetails/>
                </Box>
            </Box>
        </Container>
    );
};

export default Home;
