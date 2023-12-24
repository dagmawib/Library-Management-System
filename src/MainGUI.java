import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.*;
//import java.util.List;

import static java.sql.DriverManager.getConnection;


public class MainGUI {
//    Declares instance variables for the GUI components and a LibraryManagementSystem instance.
    public JFrame frame;
    public JTextField searchTextField;
    public JLabel searchResultLabel;
    public JTextField borrowTextField;
    public JTextField borrowerNameTextField;
    public JLabel borrowResultLabel;
    public JTextField returnTextField;
    public JTextField returnerNameTextField;
    public JLabel returnResultLabel;

    public JTextField addBookTextField;
    public JTextField authorTextField;
    public JLabel addBookLabel;

    public LibraryManagementSystem libraryManagementSystem;

    MainGUI(String userID) {
//        Initializes the LibraryManagementSystem, creates and configures the main JFrame.
        libraryManagementSystem = new LibraryManagementSystem();
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setPreferredSize(new Dimension(1500, 400));
        frame.setResizable(true);
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(0xF0F0F0)); // Light Gray background

//        Creates a main panel with a BorderLayout and sets its background color.
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xF0F0F0)); // Light Gray background

//        Creates a panel for searching with specified background color and an empty border.
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(0xF0F0F0)); // Light Gray background
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add an empty border

//        Creates a label for searching with an increased font size.
        JLabel searchLabel = new JLabel("Search Book:");
        Font labelFont = new Font(searchLabel.getFont().getName(), Font.BOLD, searchLabel.getFont().getSize() * 2);
        searchLabel.setFont(labelFont); // Increase font size
        searchPanel.add(searchLabel);

//        Creates a text field with a placeholder and adds it to the search panel.
        searchTextField = createPlaceholderTextField("Enter book title");
        searchPanel.add(searchTextField);

//        Creates a search button with an action listener and adds it to the search panel.
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchBook()); // lambda expression
        searchButton.setBackground(new Color(0x4CAF50)); // Green button
        searchButton.setForeground(Color.WHITE); // White text
        searchButton.setFocusPainted(false); // No focus border
        searchPanel.add(searchButton);

//        Creates a label for displaying search results and adds it to the search panel.
        searchResultLabel = new JLabel();
        searchResultLabel.setForeground(new Color(0x4CAF50)); // Green text
        searchPanel.add(searchResultLabel);

//        Creates a panel for borrowing with specified background color and an empty border.
        JPanel borrowPanel = new JPanel();
        borrowPanel.setBackground(new Color(0xF0F0F0)); // Light Gray background
        borrowPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add an empty border

//        Creates labels and text fields for borrowing and adds them to the borrow panel.
        JLabel borrowLabel = new JLabel("Borrow Book:");
        borrowLabel.setFont(labelFont); // Use the same font as the label
        borrowPanel.add(borrowLabel);

        borrowTextField = createPlaceholderTextField("Enter book title");
        borrowPanel.add(borrowTextField);

        // Add a label and text field for borrower name
        JLabel borrowerNameLabel = new JLabel("Borrower Name:");
        borrowerNameLabel.setFont(labelFont); // Use the same font as the label
        borrowPanel.add(borrowerNameLabel);

        borrowerNameTextField = createPlaceholderTextField("Enter borrower's name");
        borrowPanel.add(borrowerNameTextField);

//        Creates a borrow button with an action listener and a label for displaying borrow results, and adds them to the borrow panel
        JButton borrowButton = new JButton("Borrow");
        borrowButton.addActionListener(e -> borrowBook()); //lambda expression
        borrowButton.setBackground(new Color(0x2196F3)); // Blue button
        borrowButton.setForeground(Color.WHITE); // White text
        borrowButton.setFocusPainted(false); // No focus border
        borrowPanel.add(borrowButton);

        borrowResultLabel = new JLabel();
        borrowResultLabel.setForeground(new Color(0x2196F3)); // Blue text
        borrowPanel.add(borrowResultLabel);

        JPanel returnPanel = new JPanel();
        returnPanel.setBackground(new Color(0xF0F0F0)); // Light Gray background
        returnPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add an empty border

//        Creates labels and text fields for returning and adds them to the return panel.
        JLabel returnLabel = new JLabel("Return Book:");
        returnLabel.setFont(labelFont); // Use the same font as the label
        returnPanel.add(returnLabel);

        returnTextField = createPlaceholderTextField("Enter book title");
        returnPanel.add(returnTextField);

        // Add a label and text field for returner name
        JLabel returnerNameLabel = new JLabel("Returner Name:");
        returnerNameLabel.setFont(labelFont); // Use the same font as the label
        returnPanel.add(returnerNameLabel);
        returnerNameTextField = createPlaceholderTextField("Enter returner's name");
        returnPanel.add(returnerNameTextField);

//        Creates a return button with an action listener and a label for displaying return results, and adds them to the return panel.
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> returnBook()); //lambda expression
        returnButton.setBackground(new Color(0xFF5722)); // Deep Orange button
        returnButton.setForeground(Color.WHITE); // White text
        returnButton.setFocusPainted(false); // No focus border
        returnPanel.add(returnButton);

        returnResultLabel = new JLabel();
        returnResultLabel.setForeground(new Color(0xFF5722)); // Deep Orange text
        returnPanel.add(returnResultLabel);

//        Adds the search, borrow, and return panels to the main panel with specified border layout positions.
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(borrowPanel, BorderLayout.CENTER);
        panel.add(returnPanel, BorderLayout.SOUTH);

//        Creates a button for viewing all books with an action listener and adds it to the main panel.
        JButton viewAllBooksButton = new JButton("View All Books");
        viewAllBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAllBooks();
            }
            public void viewAllBooks() {
                try {
                    StringBuilder booksText;
                    try (Connection conn = getConnection(Database.CONNECTION_STRING)) {
                        Statement statement = conn.createStatement();
                        ResultSet results = statement.executeQuery("SELECT * FROM " + Database.TABLE_BOOKS);
//
                        // Create a StringBuilder to store the books information
                        booksText = new StringBuilder("All Books:\n");

                        while (results.next()) {
                            String title = results.getString(Database.COLUMN_TITLE);
                            String author = results.getString(Database.COLUMN_AUTHOR);
                            boolean borrow = results.getBoolean(String.valueOf(Database.COLUMN_BORROWED));
                            if (!borrow) {
                                booksText.append("- ").append(title).append(" ==> ").append(author).append("\n");
                            }
                        }

                        results.close();
                        //conn.close();
                    }

                    // Show the list of books directly in the GUI
                    JOptionPane.showMessageDialog(frame, booksText.toString(), "Available Books", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException e) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Error retrieving books from the database.", e);

                    JOptionPane.showMessageDialog(frame, "Error retrieving books from the database.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        viewAllBooksButton.setBackground(new Color(0x607D8B)); // Blue Gray button
        viewAllBooksButton.setForeground(Color.WHITE); // White text
        viewAllBooksButton.setFocusPainted(false); // No focus border
        panel.add(viewAllBooksButton, BorderLayout.EAST);

//        Sets the frame layout, configures the layout constraints, and adds the main panel to the frame.
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(panel, gbc);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        //instance of iDandPasswords class
        IDandPasswords iDandPasswords = new IDandPasswords();

//        Creates a logout button with an action listener and adds it to the return panel.
        JButton logoutButton = new JButton("Logout"); // Create the logout button
        logoutButton.addActionListener(e -> {
            frame.dispose();

            // Assuming iD and Passwords is an object of type that has getLoginInfo() method
            //  HashMap<String, String> loginInfoOriginal = iDandPasswords.getLoginInfo();
            @SuppressWarnings("unchecked")
            HashMap<String, String> loginInfoOriginal = (HashMap<String, String>) iDandPasswords.getLoginInfo();
            new LoginPage(loginInfoOriginal);
        });
        returnPanel.add(logoutButton);
    }
//    Creates a text field with a placeholder text and a focus listener to show/hide the placeholder.
   private JTextField createPlaceholderTextField(String placeholder) {
        JTextField textField = new JTextField(20);
        textField.setBackground(new Color(0xFFFFFF)); // White background
        textField.setPreferredSize(new Dimension(200, 30)); // Increase size
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setText(placeholder); // Placeholder text

        // Add focus listener to show/hide placeholder
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                }
            }
        });
        return textField;
    }
//    Creates a transparent panel used as a vertical gap in the layout.
    public JPanel createVerticalGap() {
        JPanel gapPanel = new JPanel();
        gapPanel.setOpaque(false); // Make the panel transparent
        gapPanel.setPreferredSize(new Dimension(0, 20)); // Set the vertical gap size
        return gapPanel;
    }
//    Retrieves the title from the search text field, calls searchBook method in the library, and updates the search result label
    private void searchBook() {
        String title = searchTextField.getText();
        AddBook book = libraryManagementSystem.getLibrary().searchBook(title);
        if (book != null) {
            searchResultLabel.setText("Book found: " + book.getTitle());
        } else {
            searchResultLabel.setText("Book not found.");
        }
    }
    private void addBook() {
        // Assuming addBookTextField and authorTextField are JTextField components in your GUI
        String title = addBookTextField.getText();
        String author = authorTextField.getText();

        // Placeholder for AddBook.main() - Replace with the actual implementation
        AddBook book = AddBook.main();

        // Assuming addBookLabel is a JLabel component in your GUI
        addBookLabel.setText(book.getTitle() + " Successfully added!");
    }
    public void borrowBook() {
        try (Connection conn = getConnection(Database.CONNECTION_STRING)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM " + Database.TABLE_BOOKS);

            String borrowerName = borrowerNameTextField.getText();
            String title = borrowTextField.getText();

            boolean bookFound = false;

            while (results.next()) {
                String fetchedTitle = results.getString(Database.COLUMN_TITLE);
                int id = results.getInt(Database.COLUMN_ID);
                boolean borrowStatus = results.getBoolean(String.valueOf(Database.COLUMN_BORROWED));
                if (fetchedTitle.equalsIgnoreCase(title)) {
                    bookFound = true;
                    // Once the book is borrowed it will change the borrow status.
                    PreparedStatement updateStatement = conn.prepareStatement("UPDATE " + Database.TABLE_BOOKS + " SET " + Database.COLUMN_BORROWED + " = ? WHERE " + Database.COLUMN_ID + " = ?");
                    updateStatement.setBoolean(1, true);
                    updateStatement.setInt(2, id);
                    updateStatement.executeUpdate();
                    if (!borrowStatus) {
                        borrowResultLabel.setText("Book borrowed: " + title + " by " + borrowerName);
                        
                    } else {
                        borrowResultLabel.setText("Book already borrowed.");
                    }
                    break; // Exit the loop once the book is found
                }
            }

            if (!bookFound) {
                borrowResultLabel.setText("Book not found.");
            }

            results.close();
          //  conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    private void returnBook() {
        try (Connection conn = getConnection(Database.CONNECTION_STRING)) {
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM " + Database.TABLE_BOOKS);

            String borrowerName = borrowerNameTextField.getText();
            String title = borrowTextField.getText();

            boolean bookFound = false;

            while (results.next()) {
                String fetchedTitle = results.getString(Database.COLUMN_TITLE);
                int id = results.getInt(Database.COLUMN_ID);
                boolean borrowStatus = results.getBoolean(String.valueOf(Database.COLUMN_BORROWED));
                if (fetchedTitle.equalsIgnoreCase(title)) {
                    bookFound = true;
                    // Once the book is borrowed it will change the borrow status.
                    PreparedStatement updateStatement = conn.prepareStatement("UPDATE " + Database.TABLE_BOOKS + " SET " + Database.COLUMN_BORROWED + " = ? WHERE " + Database.COLUMN_ID + " = ?");
                    updateStatement.setBoolean(1, false);
                    updateStatement.setInt(2, id);
                    updateStatement.executeUpdate();
                    if (borrowStatus) {
                        returnResultLabel.setText("Book returned: " + title + " by " + borrowerName);

                    } else {
                        returnResultLabel.setText("Book wasn't borrowed.");
                    }
                    break; // Exit the loop once the book is found
                }
            }

            if (!bookFound) {
                returnResultLabel.setText("Book not found.");
            }

            results.close();
           // conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    }