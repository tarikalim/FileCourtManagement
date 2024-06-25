import React from 'react';
import { ListItem, ListItemText, Grid } from '@mui/material';
import CommonListModal from './CommonListModal';
import { getAllFiles } from '../../services/api';

const AllFilesModal = ({ open, onClose }) => {
    const renderFileItem = (file) => (
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
    );

    return (
        <CommonListModal
            open={open}
            onClose={onClose}
            title="All Files"
            fetchItems={getAllFiles}
            renderItem={renderFileItem}
        />
    );
};

export default AllFilesModal;
