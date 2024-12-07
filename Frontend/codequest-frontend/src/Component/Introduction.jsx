import React from 'react';
import { createTheme, ThemeProvider, Typography, Button } from '@mui/material';

const theme = createTheme({
  typography: {
    fontFamily: 'Roboto, sans-serif', // Ensure Roboto is the global font
  },
});

const Introduction = () => {
  return (
    <ThemeProvider theme={theme}>
      <div className="Wrapper-Intro">
        <div className="Intro-Banner">
          <div>
            <Typography
              variant="h4"
              className="Welcome-Intro"
              sx={{ fontFamily: 'Roboto, sans-serif', fontWeight: 700 }}
            >
              Welcome to CodeQuest!
            </Typography>
            <br />
            <Typography
              variant="h6"
              className="Welcome-SubIntro"
              sx={{ fontFamily: 'Roboto, sans-serif' }}
            >
              Your journey to coding excellence starts here.
            </Typography>
            <br />
            <Typography
              variant="h6"
              className="Welcome-SubIntro"
              sx={{ fontFamily: 'Roboto, sans-serif' }}
            >
              Practice! Learn! Succeed.
            </Typography>
            <div className="button-grid">
              <Button
                sx={{
                  borderRadius: '20px',
                  padding: '10px 20px',
                  fontSize: '13px',
                  fontFamily: 'Roboto, sans-serif', // Apply Roboto here
                }}
                variant="outlined"
              >
                Login
              </Button>
              <Button
                sx={{
                  borderRadius: '20px',
                  padding: '10px 20px',
                  fontSize: '13px',
                  fontFamily: 'Roboto, sans-serif', // Apply Roboto here
                }}
                className="Register-Button"
                variant="contained"
              >
                REGISTER
              </Button>
            </div>
          </div>

          <img
            className="studying-image"
            src="person_studying.png"
            alt="Person studying"
          />
          <img
            className="interview-image"
            src="Interview_Ready.png"
            alt="Interview ready"
          />
        </div>
      </div>
    </ThemeProvider>
  );
};

export default Introduction;
