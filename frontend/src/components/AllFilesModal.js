import React, { useEffect, useState } from 'react';
import { Modal, Box, Typography, List, ListItem, ListItemText, Grid } from '@mui/material';
import { getAllFiles } from '../services/api';

const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

const AllFilesModal = ({ open, onClose }) => {
    const [files, setFiles] = useState([]);

    const fetchAllFiles = async () => {
        try {
            const response = await getAllFiles();
            setFiles(response.data);
        } catch (error) {
            console.error('Failed to fetch all files', error);
        }
    };

    useEffect(() => {
        if (open) {
            fetchAllFiles();
        }
    }, [open]);

    return (
        <Modal
            open={open}
            onClose={onClose}
            aria-labelledby="all-files-modal-title"
            aria-describedby="all-files-modal-description"
        >
            <Box sx={style}>
                <Typography id="all-files-modal-title" variant="h6" component="h2">
                    All Files
                </Typography>
                <List>
                    {files.map((file) => (
                        <ListItem key={file.id}>
                            <Grid container justifyContent="space-between">
                                <Grid item>
                                    <ListItemText primary={file.filename} />
                                </Grid>
                                <Grid item>
                                    <ListItemText secondary={`Assigned to: ${file.assignedUser}`} />
                                </Grid>
                            </Grid>
                        </ListItem>
                    ))}
                </List>
            </Box>
        </Modal>
    );
};

export default AllFilesModal;
