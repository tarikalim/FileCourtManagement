import React, { useEffect, useState } from 'react';
import { Modal, Box, Typography, List, ListItem, ListItemText, Grid } from '@mui/material';

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

const listStyle = {
    maxHeight: 300,
    overflow: 'auto',
};

const CommonListModal = ({ open, onClose, title, fetchItems, renderItem }) => {
    const [items, setItems] = useState([]);

    const fetchAllItems = async () => {
        try {
            const response = await fetchItems();
            setItems(response.data);
        } catch (error) {
            console.error('Failed to fetch items', error);
        }
    };

    useEffect(() => {
        if (open) {
            fetchAllItems();
        }
    }, [open]);

    return (
        <Modal
            open={open}
            onClose={onClose}
            aria-labelledby="common-list-modal-title"
            aria-describedby="common-list-modal-description"
        >
            <Box sx={style}>
                <Typography id="common-list-modal-title" variant="h6" component="h2">
                    {title}
                </Typography>
                <List sx={listStyle}>
                    {items.map((item) => renderItem(item))}
                </List>
            </Box>
        </Modal>
    );
};

export default CommonListModal;
