package uat.sparling;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Productivity Clock"); //this creates a new Jframe which is being used for GUI
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //what to do when the frame is closed
        frame.setSize(400, 150); //size of the frame, was 300 but to small for text
        frame.add(new StartupPanel()); //adds the startup panel to the frame
        frame.setVisible(true); //makes it visible
    }
}