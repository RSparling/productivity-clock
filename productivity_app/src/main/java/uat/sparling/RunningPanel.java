package uat.sparling;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RunningPanel extends JPanel { // This class is used to display the running panel
    private final JLabel modeLabel; // Label to display the mode
    private final JLabel timeLabel; // Label to display the time

    public RunningPanel() { // Constructor, sets up everything to make a panel that displays the running panel
        setLayout(new GridLayout(2, 1)); // Set the layout to a 2x1 grid, one for mode and one for time

        modeLabel = new JLabel("Mode: ", SwingConstants.CENTER); // Create a new label for the mode
        timeLabel = new JLabel("Time: ", SwingConstants.CENTER); // Create a new label for the time

        add(modeLabel); // Add the mode label to the panel
        add(timeLabel); // Add the time label to the panel
    }

    public void updateMode(String mode) { // Update the mode label with the given mode
        modeLabel.setText("Mode: " + mode);
    }

    public void updateTime(String time) { // Update the time label with the given time
        timeLabel.setText("Time: " + time);
    }
}