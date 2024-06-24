import React, { useEffect, useState } from 'react';
import { getCurrentUser } from '../services/api';
import { Card, CardContent, CardHeader, Box, Typography, Avatar } from '@mui/material';
import { deepOrange } from '@mui/material/colors';

const UserDetails = () => {
    const [user, setUser] = useState(null);

    const fetchUserDetails = async () => {
        try {
            console.log('Fetching user details...');
            const response = await getCurrentUser();
            console.log('User details fetched:', response.data);
            setUser(response.data);
        } catch (error) {
            console.error('Failed to fetch user details', error);
        }
    };

    useEffect(() => {
        fetchUserDetails();
    }, []);

    if (!user) return <Typography>Loading...</Typography>;

    return (
        <Box position="absolute" bottom={16} left={16} padding={2}>
            <Card
                sx={{
                    minWidth: 275,
                    backgroundColor: '#424242',
                    color: 'white',
                    borderRadius: 2,
                    boxShadow: 3,
                }}
            >
                <CardHeader
                    avatar={
                        <Avatar sx={{ bgcolor: deepOrange[500] }}>
                            {user.username.charAt(0).toUpperCase()}
                        </Avatar>
                    }
                    title={user.username}
                    subheader={`ID: ${user.id}`}
                    subheaderTypographyProps={{ color: 'gray' }}
                />
                <CardContent>
                    <Typography variant="body2" color="white">
                        <strong>Role:</strong> {user.role}
                    </Typography>
                </CardContent>
            </Card>
        </Box>
    );
};

export default UserDetails;
