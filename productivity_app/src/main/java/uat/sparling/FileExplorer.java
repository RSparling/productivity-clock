package uat.sparling;

import java.io.File;

import javax.swing.JFileChooser;

public class FileExplorer { // This class is used to open a file explorer to select a directory
    public static File openFileExplorer() {
        JFileChooser fileChooser = new JFileChooser(); // Create a new file chooser, this i something that java provides
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // we only want a directory
        int result = fileChooser.showOpenDialog(null); // show the file chooser
        if (result == JFileChooser.APPROVE_OPTION) { // if the user selects a directory
            return fileChooser.getSelectedFile(); // return the selected directory
        } 
        return null; //else return null, this is sketchy because it could cause a null pointer exception
    }
}