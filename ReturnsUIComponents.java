package ReturnsApp;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReturnsUIComponents {
     // Lists to store order numbers and details
    private static List<String> orderNumbers = new ArrayList<>();
    private static List<String> orderDetails = new ArrayList<>();

     // Method to create UI components
    public static void createUIComponents(Container contentPane) {
       
        // Create a tabbed pane to hold different panels
        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel for scanning returns with customer details
        JPanel scanReturnsPanel = new JPanel(new GridLayout(10, 2, 0, 0));
        scanReturnsPanel.setBackground(Color.WHITE);

        // Create labels and text fields for customer details
        JLabel[] labels = {
                new JLabel("Customer Name:"), new JLabel("Order Number:"),
                new JLabel("Product Name being Returned:"), new JLabel("Size:"),
                new JLabel("Color:"), new JLabel("Gender:")
        };

        JTextField[] fields = {
                new JTextField(), new JTextField(),
                new JTextField(), new JTextField(),
                new JTextField(), new JTextField()
        };
        
        // Add labels and fields to the scanReturnsPanel 
        for (int i = 0; i < labels.length; i++) {
            scanReturnsPanel.add(labels[i]);
            scanReturnsPanel.add(fields[i]);
        }
        
        // Panels for displaying return logs and searching returns
        JPanel returnLogsPanel = createReturnLogsPanel();
        JPanel searchReturnsPanel = createSearchReturnsPanel();
        
        // Button to save details entered
        JButton saveDetailsButton = new JButton("Save Details");
        saveDetailsButton.addActionListener(e -> {
            // Get entered details as a string and store order number and details
            String detailsToLog = getEnteredDetailsAsString(
                    fields[0].getText(), fields[1].getText(),
                    fields[2].getText(), fields[3].getText(),
                    fields[4].getText(), fields[5].getText());

            // Store order number and its details
            orderNumbers.add(fields[1].getText()); // Order number
            orderDetails.add(detailsToLog); // Details

            returnLogsArea.append(detailsToLog);
            clearFields(fields);
        });

         // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveDetailsButton);

        tabbedPane.addTab("ScanReturns", scanReturnsPanel);
        tabbedPane.addTab("ReturnLogs", returnLogsPanel);
        tabbedPane.addTab("SearchReturns", searchReturnsPanel);

        // Add tabbed pane and button panel to the content pane
        contentPane.add(tabbedPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

     // JTextArea to display return logs
    private static JTextArea returnLogsArea = new JTextArea();

    // Method to create panel for displaying return logs
    private static JPanel createReturnLogsPanel() {
         
        // Create a panel to hold return logs
        JPanel returnLogsPanel = new JPanel(new BorderLayout());
        returnLogsPanel.setBackground(Color.WHITE);

        // Create and configure returnLogsArea (JTextArea)
        returnLogsArea.setEditable(false);
        returnLogsArea.setMargin(new Insets(8, 10, 10, 10));

        // Add returnLogsArea to a scroll pane
        JScrollPane scrollPane = new JScrollPane(returnLogsArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Add scroll pane to returnLogsPanel
        returnLogsPanel.add(scrollPane, BorderLayout.CENTER);

        return returnLogsPanel;
    }

     // Method to create panel for searching returns
    private static JPanel createSearchReturnsPanel() {
        // Create a panel to hold search returns
        JPanel searchReturnsPanel = new JPanel(new BorderLayout());
        searchReturnsPanel.setBackground(Color.WHITE);

        // Create and configure searchResultArea (JTextArea)
        JTextArea searchResultArea = new JTextArea();
        searchResultArea.setEditable(false);
        searchResultArea.setMargin(new Insets(8, 10, 10, 10));

        // Add searchResultArea to a scroll pane
        JScrollPane searchScrollPane = new JScrollPane(searchResultArea);
        searchScrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Create search panel with searchField and searchButton
        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchLog(searchResultArea, searchField.getText()));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Search Order Number:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Add searchPanel and searchScrollPane to searchReturnsPanel
        searchReturnsPanel.add(searchPanel, BorderLayout.NORTH);
        searchReturnsPanel.add(searchScrollPane, BorderLayout.CENTER);

        return searchReturnsPanel;
    }

    // Method to perform search based on order number
    private static void searchLog(JTextArea searchResultArea, String searchTerm) {
        if (searchTerm != null && !searchTerm.isEmpty()) {
            int index = orderNumbers.indexOf(searchTerm);
            if (index != -1) {
                searchResultArea.setText(""); // Clear existing search result
                searchResultArea.append("Searched Order Number: " + searchTerm + "\n\n");
                searchResultArea.append(orderDetails.get(index));
            } else {
                JOptionPane.showMessageDialog(null, "Order number not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

     // Method to clear text fields
    private static void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

     // Method to format entered details as a string
    private static String getEnteredDetailsAsString(String customerName, String orderNumber, String productName,
                                                    String size, String color, String gender) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        return "Timestamp: " + formattedDateTime + "\n" +
                "Employee Badge Number: Null\n" +
                "Customer Name: " + customerName + "\n" +
                "Order Number: " + orderNumber + "\n" +
                "Product Name: " + productName + "\n" +
                "Size: " + size + "\n" +
                "Color: " + color + "\n" +
                "Gender: " + gender + "\n\n";
    }
}
