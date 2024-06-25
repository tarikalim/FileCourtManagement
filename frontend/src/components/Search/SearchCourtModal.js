import React from 'react';
import { ListItem, ListItemText } from '@mui/material';
import CommonSearchModal from './CommonSearchModal';
import { searchFilesByUsername } from '../../services/api';

const SearchCourtModal = ({ open, onClose }) => {
    const renderCourtItem = (file) => (
        <ListItem key={file.id}>
            <ListItemText primary={file.filename} />
        </ListItem>
    );

    return (
        <CommonSearchModal
            open={open}
            onClose={onClose}
            title="Search Court by Username"
            searchPlaceholder="Username"
            searchFunction={searchFilesByUsername}
            renderItem={renderCourtItem}
        />
    );
};

export default SearchCourtModal;
