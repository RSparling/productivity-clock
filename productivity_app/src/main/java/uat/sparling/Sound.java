package uat.sparling;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Sound { // This class is used to play the chimes
    private static final float SAMPLE_RATE = 44100; // Sample rate in Hz
    private static final int DURATION_MS = 100; // Duration of each beep in milliseconds

    public static void playWorkChime() { // This method is used to play the work chime
        playTone(261.63); // C4
        playTone(277.18); // C#4
    }

    public static void playBreakChime() { // This method is used to play the break chime
        playTone(261.63); // C4
        playTone(246.94); // B3 (C4 flat)
    }

    private static void playTone(double frequency) { // This method is used to play a tone at a given frequency
        try { // Try to play the tone
            byte[] buffer = generateTone(frequency, DURATION_MS); // Generate the tone
            AudioFormat format = new AudioFormat(SAMPLE_RATE, 8, 1, true, false); // Create the audio format
            Clip clip = AudioSystem.getClip(); // Get a new clip
            clip.open(format, buffer, 0, buffer.length); // Open the clip with the buffer
            clip.start(); // Start playing the clip
            Thread.sleep(DURATION_MS); // Wait for the tone to finish playing
        } catch (LineUnavailableException | InterruptedException e) { // Catch any exceptions
            e.printStackTrace(); // Print the stack trace
        }
    }

    private static byte[] generateTone(double frequency, int durationMs) { // This method is used to generate a tone at a given frequency and duration
        int length = (int) (SAMPLE_RATE * durationMs / 1000); // Calculate the length of the buffer
        byte[] buffer = new byte[length]; // Create a new buffer
        for (int i = 0; i < length; i++) { // Loop through the buffer
            double angle = 2.0 * Math.PI * i / (SAMPLE_RATE / frequency); // Calculate the angle
            buffer[i] = (byte) (Math.sin(angle) * 127); // Calculate the value at the current index
        }
        return buffer;  // Return the buffer
    }
}