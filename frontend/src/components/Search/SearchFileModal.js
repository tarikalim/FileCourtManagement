import React from 'react';
import { Box, Typography } from '@mui/material';
import CommonSearchModal from './CommonSearchModal';
import { searchFileByFilename } from '../../services/api';

const SearchFileModal = ({ open, onClose }) => {
    const renderFileItem = (file) => (
        <Box key={file.id} mt={2}>
            <Typography><strong>ID:</strong> {file.id}</Typography>
            <Typography><strong>Filename:</strong> {file.filename}</Typography>
            <Typography><strong>Assigned User:</strong> {file.assignedUser}</Typography>
        </Box>
    );

    return (
        <CommonSearchModal
            open={open}
            onClose={onClose}
            title="Search File by Filename"
            searchPlaceholder="Filename"
            searchFunction={searchFileByFilename}
            renderItem={renderFileItem}
        />
    );
};

export default SearchFileModal;
