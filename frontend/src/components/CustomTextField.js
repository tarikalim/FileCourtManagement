import React from 'react';
import { TextField } from '@mui/material';

const CustomTextField = ({ label, value, onChange, type = 'text', ...props }) => {
    return (
        <TextField
            label={label}
            value={value}
            onChange={onChange}
            type={type}
            fullWidth
            margin="normal"
            {...props}
        />
    );
};

export default CustomTextField;
