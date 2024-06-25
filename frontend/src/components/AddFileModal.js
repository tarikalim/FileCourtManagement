import React, { useState } from 'react';
import { Modal, Box, Typography, TextField, Button, Snackbar, Alert } from '@mui/material';
import { addFile } from '../services/api';

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

const AddFileModal = ({ open, onClose }) => {
    const [filename, setFilename] = useState('');
    const [error, setError] = useState('');
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            await addFile({ filename });
            setFilename('');
            setSnackbarMessage('File added successfully!');
            setSnackbarOpen(true);
            setError('');
        } catch (error) {
            setError(error.customMessage || 'Error adding file');
            setSnackbarMessage('');
        }
    };

    const handleSnackbarClose = () => {
        setSnackbarOpen(false);
    };

    return (
        <div>
            <Modal
                open={open}
                onClose={onClose}
                aria-labelledby="add-file-modal-title"
                aria-describedby="add-file-modal-description"
            >
                <Box sx={style}>
                    <Typography id="add-file-modal-title" variant="h6" component="h2">
                        Add File
                    </Typography>
                    <form onSubmit={handleSubmit}>
                        <TextField
                            label="Filename"
                            value={filename}
                            onChange={(e) => setFilename(e.target.value)}
                            required
                            fullWidth
                            margin="normal"
                        />
                        <Button type="submit" variant="contained" color="primary">
                            Add File
                        </Button>
                    </form>
                    {error && (
                        <Typography color="error" variant="body2">
                            {error}
                        </Typography>
                    )}
                </Box>
            </Modal>
            <Snackbar
                open={snackbarOpen}
                autoHideDuration={6000}
                onClose={handleSnackbarClose}
            >
                <Alert onClose={handleSnackbarClose} severity={snackbarMessage ? 'success' : 'error'}>
                    {snackbarMessage || error}
                </Alert>
            </Snackbar>
        </div>
    );
};

export default AddFileModal;
