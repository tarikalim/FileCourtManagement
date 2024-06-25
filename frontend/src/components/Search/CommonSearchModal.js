import React, { useState } from 'react';
import { Modal, Box, Typography, TextField, Button } from '@mui/material';

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
const CommonSearchModal = ({ open, onClose, title, searchPlaceholder, searchFunction, renderItem }) => {
    const [searchQuery, setSearchQuery] = useState('');
    const [error, setError] = useState('');
    const [results, setResults] = useState(null);

    const handleSearch = async () => {
        try {
            const response = await searchFunction(searchQuery);
            setResults(response.data);
            setError('');
        } catch (error) {
            setError(error.response.data.message || 'Not found');
            setResults(null);
        }
    };

    return (
        <Modal
            open={open}
            onClose={onClose}
            aria-labelledby="common-search-modal-title"
            aria-describedby="common-search-modal-description"
        >
            <Box sx={style}>
                <Typography id="common-search-modal-title" variant="h6" component="h2">
                    {title}
                </Typography>
                <TextField
                    label={searchPlaceholder}
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
                {results && (
                    <Box mt={2}>
                        {renderItem(results)}
                    </Box>
                )}
            </Box>
        </Modal>
    );
};

export default CommonSearchModal;
