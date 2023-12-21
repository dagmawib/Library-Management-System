import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        try {
            // Add logo above the login form
            ImageIcon logoIcon = new ImageIcon("logo.png");
            Image logoImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading the logo: " + ex.getMessage());
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(380, 380);
        frame.setResizable(true);
        frame.setVisible(true);

        frame.getContentPane().setBackground(new Color(0xC4DFDF));
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0xC4DFDF)); // Set background color

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(0xC4DFDF)); // Set background color
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add an empty border
        JLabel searchLabel = new JLabel("Search Book:");
        Font labelFont = new Font(searchLabel.getFont().getName(), Font.PLAIN, searchLabel.getFont().getSize() * 2);
        searchLabel.setFont(labelFont); // Increase font size
        searchPanel.add(searchLabel);

        searchTextField = new JTextField(40);
        searchTextField.setBackground(new Color(0xC4DFDF)); // Set background color
        searchPanel.add(searchTextField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBook();
            }
        });
        searchButton.setBackground(new Color(0xC4DFDF)); // Set background color
        searchPanel.add(searchButton);

        searchResultLabel = new JLabel();
        searchResultLabel.setBackground(new Color(0xC4DFDF)); // Set background color
        searchPanel.add(searchResultLabel);

        JPanel borrowPanel = new JPanel();
        borrowPanel.setBackground(new Color(0xC4DFDF)); // Set background color
        borrowPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add an empty border
        JLabel borrowLabel = new JLabel("Borrow Book:");
        borrowLabel.setFont(labelFont); // Use the same font as the label
        borrowPanel.add(borrowLabel);

        borrowTextField = new JTextField(40);
        borrowTextField.setBackground(new Color(0xC4DFDF)); // Set background color
        borrowPanel.add(borrowTextField);

        // Add a label and text field for borrower name
        JLabel borrowerNameLabel = new JLabel("Borrower Name:");
        borrowerNameLabel.setFont(labelFont); // Use the same font as the label
        borrowPanel.add(borrowerNameLabel);

        borrowerNameTextField = new JTextField(40);
        borrowerNameTextField.setBackground(new Color(0xC4DFDF)); // Set background color
        borrowPanel.add(borrowerNameTextField);

        JButton borrowButton = new JButton("Borrow");
        borrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrowBook();
            }
        });
        borrowButton.setBackground(new Color(0xC4DFDF)); // Set background color
        borrowPanel.add(borrowButton);

        borrowResultLabel = new JLabel();
        borrowResultLabel.setBackground(new Color(0xC4DFDF)); // Set background color
        borrowPanel.add(borrowResultLabel);

        JPanel returnPanel = new JPanel();
        returnPanel.setBackground(new Color(0xC4DFDF)); // Set background color
        returnPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add an empty border
        JLabel returnLabel = new JLabel("Return Book:");
        returnLabel.setFont(labelFont); // Use the same font as the label
        returnPanel.add(returnLabel);

        returnTextField = new JTextField(40);
        returnTextField.setBackground(new Color(0xC4DFDF)); // Set background color
        returnPanel.add(returnTextField);

        // Add a label and text field for returner name
        JLabel returnerNameLabel = new JLabel("Returner Name:");
        returnerNameLabel.setFont(labelFont); // Use the same font as the label
        returnPanel.add(returnerNameLabel);

        returnerNameTextField = new JTextField(40);
        returnerNameTextField.setBackground(new Color(0xC4DFDF)); // Set background color
        returnPanel.add(returnerNameTextField);

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });
        returnButton.setBackground(new Color(0xC4DFDF)); // Set background color
        returnPanel.add(returnButton);

        returnResultLabel = new JLabel();
        returnResultLabel.setBackground(new Color(0xC4DFDF)); // Set background color
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
            }
        });
        viewAllBooksButton.setBackground(new Color(0xC4DFDF)); // Set background color
//        panel.add(viewAllBooksButton, BorderLayout.SOUTH);

        // Center components when window is maximized
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(panel, gbc);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
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

        // Placeholder for AddBook.main() - Replace with actual implementation
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
            borrowResultLabel.setText("Book borrowed: " + book.getTitle());
        } else {
            borrowResultLabel.setText("Book not found.");
        }
    }

    private void returnBook() {
        String title = returnTextField.getText();
        String returnerName = returnerNameTextField.getText();
        AddBook book = libraryManagementSystem.get();
    }
}
