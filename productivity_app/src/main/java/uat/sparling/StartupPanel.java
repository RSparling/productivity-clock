package uat.sparling;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartupPanel extends JPanel {
    private final JTextField breakIntervalField;
    private final JTextField breakDurationField;
    private final JCheckBox motivationalPicturesCheckBox;
    private final JButton startButton;
    private final RunningPanel runningPanel;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;

    public StartupPanel() {
        cardLayout = new CardLayout(); //create a new layout manager
        mainPanel = new JPanel(cardLayout); //adds this as the main panel

        JPanel inputPanel = new JPanel(new GridLayout(4, 2)); //creates a new input panels with a 4x2 grid
        // Labels
        JLabel breakIntervalLabel = new JLabel("Break Interval (minutes):"); //creates label for break interval
        JLabel breakDurationLabel = new JLabel("Break Duration (minutes):"); //creates label for break duration

        // Text fields
        breakIntervalField = new JTextField(); //gets the input for break interval
        breakDurationField = new JTextField(); //gets the input for break duration

        motivationalPicturesCheckBox = new JCheckBox("Show motivational pictures"); //creates a checkbox for motivational pictures

        // Start button
        startButton = new JButton("Start"); //creates a start button
        startButton.addActionListener((ActionEvent e) -> { //adds an action listener to the start button, basically event handling
            startBreakTimer();
        });

        // Add components to input panel
        inputPanel.add(breakIntervalLabel); //adds the break interval label to the input panel
        inputPanel.add(breakIntervalField); //adds the break interval field to the input panel
        inputPanel.add(breakDurationLabel); //adds the break duration label to the input panel
        inputPanel.add(breakDurationField); //adds the break duration field to the input panel
        inputPanel.add(motivationalPicturesCheckBox); //adds the motivational pictures checkbox to the input panel
        inputPanel.add(new JLabel()); // Empty cell
        inputPanel.add(startButton); //adds the start button to the input panel

        // Running panel
        runningPanel = new RunningPanel(); //this is so it can be switched to

        // Add panels to main panel
        mainPanel.add(inputPanel, "InputPanel"); //adds the input panel to the main panel
        mainPanel.add(runningPanel, "RunningPanel"); //adds the running panel to the main panel

        // Set the initial panel
        cardLayout.show(mainPanel, "InputPanel"); //shows the input panel

        // Add main panel to the StartupPanel
        setLayout(new BorderLayout()); //sets the layout of the panel
        add(mainPanel, BorderLayout.CENTER); //adds the main panel to the center of the border layout
    }

    private void startBreakTimer() { //this is the method that is called when the start button is pressed
        try { //try catch in case of an error with input or file manager
            int breakInterval = Integer.parseInt(breakIntervalField.getText());
            int breakDuration = Integer.parseInt(breakDurationField.getText());
            boolean showMotivationalPictures = motivationalPicturesCheckBox.isSelected();
            File selectedDirectory = null;
            if (showMotivationalPictures) { //if the motivational pictures checkbox is selected then do file explorer
                selectedDirectory = FileExplorer.openFileExplorer();
                if (selectedDirectory == null) {
                    JOptionPane.showMessageDialog(this, "No directory selected", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            new TimerClass(breakInterval, breakDuration, runningPanel, showMotivationalPictures, selectedDirectory); //creates a new timer class with the input values
            cardLayout.show(mainPanel, "RunningPanel"); //switches to the running panel
        } catch (NumberFormatException e) { //output error message if the input is not a number
            JOptionPane.showMessageDialog(this, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}