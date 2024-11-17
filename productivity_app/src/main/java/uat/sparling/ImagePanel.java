package uat.sparling;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel { // This class is used to display images
    private final List<String> imagePaths; // List of image paths
    private final JLabel imageLabel; // Label to display the image

    public ImagePanel(File directory) { // Constructor, sets up everything to make a panel that displays images
        imagePaths = new ArrayList<>(); // Create a new list of image paths
        loadImages(directory); // Load images from the directory
        imageLabel = new JLabel(); // Create a new label
        setLayout(new BorderLayout()); // Set the layout to border layout
        add(imageLabel, BorderLayout.CENTER); // Add the image label to the center of the panel
    }

    private void loadImages(File directory) { // Load images from the directory that was retrieved by FileExplorer
        File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png"));
        if (files != null) {
            for (File file : files) {
                imagePaths.add(file.getAbsolutePath());
            }
        }
    }

    public void showRandomImage() { // Show a random image from the list of image paths
        if (!imagePaths.isEmpty()) { // If there are images to display
            Random random = new Random(); // Create a new random object
            String imagePath = imagePaths.get(random.nextInt(imagePaths.size())); // Get a random image path
            ImageIcon imageIcon = new ImageIcon(imagePath); // Create an image icon from the image path
            imageLabel.setIcon(imageIcon); // Set the image icon to the image label
        }
    }
}