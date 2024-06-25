import React, {useState} from 'react';
import {Modal, Box, Typography, TextField, Button, List, ListItem, ListItemText} from '@mui/material';
import {searchFilesByUsername} from '../services/api';

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

const SearchCourtModal = ({open, onClose}) => {
    const [searchQuery, setSearchQuery] = useState('');
    const [error, setError] = useState('');
    const [files, setFiles] = useState([]);

    const handleSearch = async () => {
        try {
            const response = await searchFilesByUsername(searchQuery);
            setFiles(response.data);
            setError('');
        } catch (error) {
            setError(error.response.data.message);
            setFiles([]);
        }
    };

    return (
        <Modal
            open={open}
            onClose={onClose}
            aria-labelledby="search-court-modal-title"
            aria-describedby="search-court-modal-description"
        >
            <Box sx={style}>
                <Typography id="search-court-modal-title" variant="h6" component="h2">
                    Search Court by Username
                </Typography>
                <TextField
                    label="Username"
                    value={searchQuery}
                    onChange={(e) => setSearchQuery(e.target.value)}
                    fullWidth
                    margin="normal"
                />
                <Button variant="contained" color="primary" onClick={handleSearch}>
                    Search
                </Button>
                {error && (
                    <Typography color="error" variant="body2" style={{marginTop: '10px'}}>
                        {error}
                    </Typography>
                )}
                {files.length > 0 && (
                    <List>
                        {files.map((file) => (
                            <ListItem key={file.id}>
                                <ListItemText primary={file.filename}/>
                            </ListItem>
                        ))}
                    </List>
                )}
            </Box>
        </Modal>
    );
};

export default SearchCourtModal;
