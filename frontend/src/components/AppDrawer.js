import React, { useState } from 'react';
import { Drawer, List, ListItem, ListItemIcon, ListItemText, IconButton, AppBar, Toolbar, Typography, Box, Button } from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import FolderIcon from '@mui/icons-material/Folder';
import ListIcon from '@mui/icons-material/List';
import AddIcon from '@mui/icons-material/Add';
import SearchIcon from '@mui/icons-material/Search';
import { useNavigate } from 'react-router-dom';
import UserFilesModal from './UserFilesModal';
import AllFilesModal from './AllFilesModal';
import AddFileModal from './AddFileModal';
import SearchFileModal from './SearchFileModal';
import SearchCourtModal from './SearchCourtModal';

const AppDrawer = () => {
    const [drawerOpen, setDrawerOpen] = useState(false);
    const [userFilesModalOpen, setUserFilesModalOpen] = useState(false);
    const [allFilesModalOpen, setAllFilesModalOpen] = useState(false);
    const [addFileModalOpen, setAddFileModalOpen] = useState(false);
    const [searchFileModalOpen, setSearchFileModalOpen] = useState(false);
    const [searchCourtModalOpen, setSearchCourtModalOpen] = useState(false);
    const navigate = useNavigate();

    const toggleDrawer = (open) => (event) => {
        if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
            return;
        }
        setDrawerOpen(open);
    };

    const handleUserFilesModalOpen = () => {
        setUserFilesModalOpen(true);
    };

    const handleUserFilesModalClose = () => {
        setUserFilesModalOpen(false);
    };

    const handleAllFilesModalOpen = () => {
        setAllFilesModalOpen(true);
    };

    const handleAllFilesModalClose = () => {
        setAllFilesModalOpen(false);
    };

    const handleAddFileModalOpen = () => {
        setAddFileModalOpen(true);
    };

    const handleAddFileModalClose = () => {
        setAddFileModalOpen(false);
    };

    const handleSearchFileModalOpen = () => {
        setSearchFileModalOpen(true);
    };

    const handleSearchFileModalClose = () => {
        setSearchFileModalOpen(false);
    };

    const handleSearchCourtModalOpen = () => {
        setSearchCourtModalOpen(true);
    };

    const handleSearchCourtModalClose = () => {
        setSearchCourtModalOpen(false);
    };

    const handleBackToLogin = () => {
        navigate('/login');
    };

    const menuItems = [
        { text: 'Assigned Files', icon: <FolderIcon />, onClick: handleUserFilesModalOpen },
        { text: 'List All Files', icon: <ListIcon />, onClick: handleAllFilesModalOpen },
        { text: 'Add File', icon: <AddIcon />, onClick: handleAddFileModalOpen },
        { text: 'Search File', icon: <SearchIcon />, onClick: handleSearchFileModalOpen },
        { text: 'Search Court', icon: <SearchIcon />, onClick: handleSearchCourtModalOpen },
    ];

    return (
        <div>
            <AppBar position="fixed">
                <Toolbar>
                    <IconButton
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        onClick={toggleDrawer(true)}
                        sx={{ marginRight: 2 }}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Typography variant="h6" noWrap component="div" sx={{ flexGrow: 1 }}>
                        Bakirkoy Court House File Automation System
                    </Typography>
                    <Button color="inherit" onClick={handleBackToLogin}>
                        Back To Login Menu
                    </Button>
                </Toolbar>
            </AppBar>
            <Drawer
                anchor="left"
                open={drawerOpen}
                onClose={toggleDrawer(false)}
            >
                <Box
                    sx={{ width: 250 }}
                    role="presentation"
                    onClick={toggleDrawer(false)}
                    onKeyDown={toggleDrawer(false)}
                >
                    <List>
                        {menuItems.map((item, index) => (
                            <ListItem button key={index} onClick={item.onClick}>
                                <ListItemIcon>{item.icon}</ListItemIcon>
                                <ListItemText primary={item.text} />
                            </ListItem>
                        ))}
                    </List>
                </Box>
            </Drawer>
            <UserFilesModal open={userFilesModalOpen} onClose={handleUserFilesModalClose} />
            <AllFilesModal open={allFilesModalOpen} onClose={handleAllFilesModalClose} />
            <AddFileModal open={addFileModalOpen} onClose={handleAddFileModalClose} />
            <SearchFileModal open={searchFileModalOpen} onClose={handleSearchFileModalClose} />
            <SearchCourtModal open={searchCourtModalOpen} onClose={handleSearchCourtModalClose} />
        </div>
    );
};

export default AppDrawer;
