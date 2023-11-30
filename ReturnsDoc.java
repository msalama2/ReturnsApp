package ReturnsApp;

import javax.swing.*;

// Main class ReturnsDoc extending JFrame
public class ReturnsDoc extends JFrame {
    
     // Constructor for ReturnsDoc class
    public ReturnsDoc() {
        // Set frame properties
        initializeFrame();// Initialize frame settings
        createUI();// Create UI components
        setVisible(true);// Set frame visibility
    }
    
    // Method to initialize frame settings
    private void initializeFrame() {
        setTitle("ReturnsApp");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set system look and feel for the UI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();// Handle look and feel exception
        }
    }

    // Method to create UI components
    private void createUI() {
        ReturnsUIComponents.createUIComponents(getContentPane());// Call method to create UI components from ReturnsUIComponents class
    }

    // Main method to run the ReturnsDoc class
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReturnsDoc::new);// Create and show ReturnsDoc instance in a separate thread
    }
    }
    