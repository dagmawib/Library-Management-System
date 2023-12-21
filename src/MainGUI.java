import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.List;


public class MainGUI {
    private JFrame frame;
    private JTextField searchTextField;
    private JLabel searchResultLabel;
    private JTextField borrowTextField;
    private JTextField borrowerNameTextField;
    private JLabel borrowResultLabel;
    private JTextField returnTextField;
    private JTextField returnerNameTextField;
    private JLabel returnResultLabel;

    private LibraryManagementSystem libraryManagementSystem;

    MainGUI(String userID) {
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

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xF0F0F0)); // Light Gray background

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(0xF0F0F0)); // Light Gray background
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add an empty border

        JLabel searchLabel = new JLabel("Search Book:");
        Font labelFont = new Font(searchLabel.getFont().getName(), Font.BOLD, searchLabel.getFont().getSize() * 2);
        searchLabel.setFont(labelFont); // Increase font size
        searchPanel.add(searchLabel);

        searchTextField = createPlaceholderTextField("Enter book title");
        searchPanel.add(searchTextField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBook();
            }
        });
        searchButton.setBackground(new Color(0x4CAF50)); // Green button
        searchButton.setForeground(Color.WHITE); // White text
        searchButton.setFocusPainted(false); // No focus border
        searchPanel.add(searchButton);

        searchResultLabel = new JLabel();
        searchResultLabel.setForeground(new Color(0x4CAF50)); // Green text
        searchPanel.add(searchResultLabel);

        JPanel borrowPanel = new JPanel();
        borrowPanel.setBackground(new Color(0xF0F0F0)); // Light Gray background
        borrowPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add an empty border
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

        JButton borrowButton = new JButton("Borrow");
        borrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrowBook();
            }
        });
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

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });
        returnButton.setBackground(new Color(0xFF5722)); // Deep Orange button
        returnButton.setForeground(Color.WHITE); // White text
        returnButton.setFocusPainted(false); // No focus border
        returnPanel.add(returnButton);

        returnResultLabel = new JLabel();
        returnResultLabel.setForeground(new Color(0xFF5722)); // Deep Orange text
        returnPanel.add(returnResultLabel);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(borrowPanel, BorderLayout.CENTER);
        panel.add(returnPanel, BorderLayout.SOUTH);

        JButton viewAllBooksButton = new JButton("View All Books");
        viewAllBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAllBooks();
            }
            private void viewAllBooks() {
                List<AddBook> availableBooks = libraryManagementSystem.getLibrary().getAllAvailableBooks();

                if (!availableBooks.isEmpty()) {
                    StringBuilder booksText = new StringBuilder("Available Books:\n");
                    for (AddBook book : availableBooks) {
                        booksText.append("- ").append(book.getTitle()).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, booksText.toString(), "Available Books", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "No available books.", "Available Books", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        viewAllBooksButton.setBackground(new Color(0x607D8B)); // Blue Gray button
        viewAllBooksButton.setForeground(Color.WHITE); // White text
        viewAllBooksButton.setFocusPainted(false); // No focus border
       panel.add(viewAllBooksButton, BorderLayout.EAST);

        // Center components when the window is maximized
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        frame.add(panel, gbc);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        IDandPasswords iDandPasswords = new IDandPasswords();
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HashMap<String, String> loginInfoOriginal = new HashMap<>(); // Initialize loginInfoOriginal with appropriate values
                loginInfoOriginal = iDandPasswords.getLoginInfo();
                new LoginPage(loginInfoOriginal);
            }
        });
        returnPanel.add(logoutButton);
        logoutButton.add(returnPanel, BorderLayout.SOUTH);
    }
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
    private JPanel createVerticalGap() {
        JPanel gapPanel = new JPanel();
        gapPanel.setOpaque(false); // Make the panel transparent
        gapPanel.setPreferredSize(new Dimension(0, 20)); // Set the vertical gap size
        return gapPanel;
    }
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
        Label addBookTextFied = null;
        String title = addBookTextFied.getText();
        Label authorTextField = null;
        String author = authorTextField.getText();

        // Placeholder for AddBook.main() - Replace with the actual implementation
        AddBook book = AddBook.main();
        Label addBookLabel = null;
        addBookLabel.setText(book.getTitle() + " Successfully added!");
    }
    private void borrowBook() {
        String title = borrowTextField.getText();
        String borrowerName = borrowerNameTextField.getText();
        AddBook book = libraryManagementSystem.getLibrary().searchBook(title);
        if (book != null) {
            libraryManagementSystem.getLibrary().borrowBook(book);
            borrowResultLabel.setText("Book borrowed: " + book.getTitle() + " by " + borrowerName);
        } else {
            borrowResultLabel.setText("Book not found.");
        }
    }
    private void returnBook() {
        String title = returnTextField.getText();
        String returnerName = returnerNameTextField.getText();
        AddBook book = libraryManagementSystem.getLibrary().searchBook(title);
        if (book == null) {
            libraryManagementSystem.getLibrary().returnBook(book);
            returnResultLabel.setText("Book returned: " + title + " by " + returnerName);
        } else {
            returnResultLabel.setText("Book not found.");
        }
    }
}