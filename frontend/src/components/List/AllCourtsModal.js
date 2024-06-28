import React from 'react';
import { ListItem, ListItemText, Grid } from '@mui/material';
import CommonListModal from './CommonListModal';
import { getAllCourts } from '../../services/api';

const AllCourtsModal = ({ open, onClose }) => {
    const renderCourtItem = (court) => (
        <ListItem key={court.id}>
            <Grid container justifyContent="space-between">
                <Grid item>
                    <ListItemText primary={court.username} />
                </Grid>
                <Grid item>
                    <ListItemText secondary={`Role: ${court.role}`} />
                </Grid>
            </Grid>
        </ListItem>
    );

    return (
        <CommonListModal
            open={open}
            onClose={onClose}
            title="All Courts"
            fetchItems={getAllCourts}
            renderItem={renderCourtItem}
        />
    );
};

export default AllCourtsModal;
