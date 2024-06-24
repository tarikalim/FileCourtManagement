import React, { useState } from 'react';
import { Modal, Box, Typography, TextField, Button } from '@mui/material';
import { searchFileByFilename } from '../services/api';

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

const SearchFileModal = ({ open, onClose }) => {
    const [searchQuery, setSearchQuery] = useState('');
    const [error, setError] = useState('');
    const [fileData, setFileData] = useState(null);

    const handleSearch = async () => {
        try {
            const response = await searchFileByFilename(searchQuery);
            setFileData(response.data);
            setError('');
        } catch (error) {
            setError('File not found');
            setFileData(null);
        }
    };

    return (
        <Modal
            open={open}
            onClose={onClose}
            aria-labelledby="search-file-modal-title"
            aria-describedby="search-file-modal-description"
        >
            <Box sx={style}>
                <Typography id="search-file-modal-title" variant="h6" component="h2">
                    Search File
                </Typography>
                <TextField
                    label="Filename"
                    value={searchQuery}
                    onChange={(e) => setSearchQuery(e.target.value)}
                    fullWidth
                    margin="normal"
                />
                <Button variant="contained" color="primary" onClick={handleSearch}>
                    Search
                </Button>
                {error && (
                    <Typography color="error" variant="body2" style={{ marginTop: '10px' }}>
                        {error}
                    </Typography>
                )}
                {fileData && (
                    <Box mt={2}>
                        <Typography><strong>ID:</strong> {fileData.id}</Typography>
                        <Typography><strong>Filename:</strong> {fileData.filename}</Typography>
                        <Typography><strong>Assigned User:</strong> {fileData.assignedUser}</Typography>
                    </Box>
                )}
            </Box>
        </Modal>
    );
};

export default SearchFileModal;
