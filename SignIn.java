package ReturnsApp;

import javax.swing.*;
import java.awt.*;

// Define an abstract class Employee
abstract class Employee {
    abstract String getEmployeeType();// Abstract method to get employee type
}

// Define a subclass InternalEmployee extending Employee
class InternalEmployee extends Employee {
    @Override
    String getEmployeeType() {
        return "Internal";// Return type as Internal
    }
}

// Define a subclass ExternalEmployee extending Employee
class ExternalEmployee extends Employee {
    @Override
    String getEmployeeType() {
        return "External";// Return type as External
    }
}

// Main class SignIn extending JFrame
public class SignIn extends JFrame {
    // Declare class variables
    private JTextField badgeNumberField;
    private static final Color BLUE_COLOR = new Color(30, 144, 255);
    private static final Color RED_COLOR = Color.RED;
    private static final Color WHITE_COLOR = Color.WHITE;
    private static final String WARNING_MESSAGE = "USED BY INTERNAL EMPLOYEES ONLY. ANYONE FOUND USING THIS APP AND NOT AUTHORIZED WILL BE CHARGED!";

    // Constructor for SignIn class
    public SignIn() {
        initializeFrame();// Initialize frame settings
        createUIComponents();// Create UI components
        setVisible(true);// Set frame visibility
    }

    // Method to initialize frame settings
    private void initializeFrame() {
        // Set frame properties
        setTitle("ReturnsApp Employee Sign In");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

     // Method to create UI components
    private void createUIComponents() {
        // Create panel with background color
        JPanel panel = new JPanel();
        panel.setBackground(BLUE_COLOR);

        // Create and configure UI elements
        JLabel titleLabel = new JLabel("ReturnsApp Employee Sign In");
        titleLabel.setForeground(WHITE_COLOR);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Add UI elements to the panel
        JLabel employeeBadgeLabel = new JLabel("Enter Employee Badge Number:");
        employeeBadgeLabel.setForeground(WHITE_COLOR);

        badgeNumberField = new JTextField();
        badgeNumberField.setPreferredSize(new Dimension(150, 25));
        badgeNumberField.setHorizontalAlignment(JTextField.CENTER);
        badgeNumberField.addActionListener(e -> authenticateEmployee());

        JLabel caution = new JLabel(WARNING_MESSAGE);
        caution.setForeground(RED_COLOR);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> authenticateEmployee());

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(titleLabel);
        panel.add(caution);
        panel.add(employeeBadgeLabel);
        panel.add(badgeNumberField);
        panel.add(submitButton);

        add(panel); // Add the panel to the frame
    }

    // Method to authenticate employee based on badge number
    private void authenticateEmployee() {
        String enteredBadgeNumber = badgeNumberField.getText();
        if (enteredBadgeNumber.isEmpty()) {
            // Show error message for empty badge number
            JOptionPane.showMessageDialog(null, "Please enter a valid Employee Number.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
          
            Employee employee; // Declare employee object

            // Check entered badge number
            if (enteredBadgeNumber.equals("Null")) {
                // Create InternalEmployee object if badge number is "Null"
                employee = new InternalEmployee();
            } else {
                // Create ExternalEmployee object for other badge numbers
                employee = new ExternalEmployee();
            }

             // Check the employee type
            if (employee.getEmployeeType().equals("Internal")) {
                // If employee is internal, open returns document
                dispose();
                openReturnsDoc();
            } else {
                // Show error message for wrong badge number
                JOptionPane.showMessageDialog(null, "Wrong Badge Number! Please enter a valid Employee Number.", "Error", JOptionPane.ERROR_MESSAGE);
                badgeNumberField.setText(""); // Clear badge number field
            }
        }
    }

    // Method to open returns document
    private void openReturnsDoc() {
        // Invoke ReturnsDoc in a separate thread
        SwingUtilities.invokeLater(() -> {
            ReturnsDoc returnsDoc = new ReturnsDoc();
            returnsDoc.setVisible(true);
        });
    }
 
    // Main method to run the SignIn class
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignIn::new);
    }
}