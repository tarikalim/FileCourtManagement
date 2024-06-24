import React, {useEffect, useState} from 'react';
import {getUserFiles} from '../services/api';
import {Box, Typography, List, ListItem, ListItemText} from '@mui/material';

const UserFiles = () => {
    const [files, setFiles] = useState([]);

    const fetchUserFiles = async () => {
        try {
            const response = await getUserFiles();
            setFiles(response.data);
        } catch (error) {
            console.error('Failed to fetch user files', error);
        }
    };

    useEffect(() => {
        fetchUserFiles();
    }, []);

    return (
        <Box>
            <Typography variant="h6">Assigned Files </Typography>
            <List>
                {files.map((file) => (
                    <ListItem key={file.id}>
                        <ListItemText primary={file.filename}/>
                    </ListItem>
                ))}
            </List>
        </Box>
    );
};

export default UserFiles;
