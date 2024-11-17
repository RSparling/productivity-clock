package uat.sparling;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TimerClass { // This class is used to create a timer for the productivity clock
    private final int breakInterval;
    private final int breakDuration;
    private final boolean showMotivationalPictures;
    private Timer timer;
    private boolean inBreakMode;
    private final RunningPanel runningPanel;
    private ImagePanel imagePanel;
    private JFrame imageFrame;

    public TimerClass(int breakInterval, int breakDuration, RunningPanel runningPanel, boolean showMotivationalPictures, 
            File imageDirectory) { // Constructor, sets up everything to make a timer for the productivity clock
        this.breakInterval = breakInterval;
        this.breakDuration = breakDuration;
        this.runningPanel = runningPanel;
        this.showMotivationalPictures = showMotivationalPictures;
        this.inBreakMode = false;

        if (showMotivationalPictures && imageDirectory != null) { // If the user wants to show motivational pictures
            imagePanel = new ImagePanel(imageDirectory);
            imageFrame = new JFrame("Motivational Image");
            imageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            imageFrame.setSize(400, 400);
            imageFrame.add(imagePanel);
        }

        startWorkMode();
    }

    private void startWorkMode() { // Start the work mode
        inBreakMode = false; // Not in break mode
        Sound.playWorkChime(); // Play work mode chime
        updatePanel("Work Mode", breakInterval + " minutes remaining"); // Update the panel with the mode and time
        timer = new Timer(1000, new ActionListener() { // 1000 ms = 1 second
            int timeRemaining = breakInterval * 60; // convert to seconds

            @Override // Override the actionPerformed method
            public void actionPerformed(ActionEvent e) { // What to do when the timer is running
                timeRemaining--; // Decrement the time remaining
                updatePanel("Work Mode", formatTime(timeRemaining)); // Update the panel with the mode and time
                if (timeRemaining <= 0) { // If the time remaining is less than or equal to 0
                    timer.stop(); // Stop the timer
                    startBreakMode(); // Start the break mode
                }
            }
        });
        timer.start(); // Start the timer
    }

    private void startBreakMode() { // Start the break mode
        inBreakMode = true;
        Sound.playBreakChime(); // Play break mode chime
        updatePanel("Break Mode", breakDuration + " minutes remaining");
        if (showMotivationalPictures && imagePanel != null) {
            imagePanel.showRandomImage();
            imageFrame.setVisible(true);
        }
        timer = new Timer(1000, new ActionListener() { // 1000 ms = 1 second
            int timeRemaining = breakDuration * 60; // convert to seconds

            @Override // Override the actionPerformed method
            public void actionPerformed(ActionEvent e) { // What to do when the timer is running
                timeRemaining--; // Decrement the time remaining
                updatePanel("Break Mode", formatTime(timeRemaining)); // Update the panel with the mode and time
                if (timeRemaining <= 0) { // If the time remaining is less than or equal to 0
                    timer.stop(); // Stop the timer
                    if (imageFrame != null) { // If the image frame is not null
                        imageFrame.setVisible(false); // Hide the image frame
                    } // Hide the image frame
                    startWorkMode(); // Start the work mode
                }
            }
        });
        timer.start(); // Start the timer
    } 

    private void updatePanel(String mode, String time) { // Update the panel with the given mode and time
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runningPanel.updateMode(mode);
                runningPanel.updateTime(time);
            }
        });
    }

    private String formatTime(int totalSeconds) { // Format the time in minutes and seconds
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}