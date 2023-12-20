import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainGUI {
    private JFrame frame;
    private JTextField searchTextField;
    private JLabel searchResultLabel;
    private JTextField addBookTextFied;
    private JTextField authorTextField;
    private JLabel addBookLabel;
    private JTextField borrowTextField;
    private JTextField borrowerNameTextField;
    private JLabel borrowResultLabel;
    private JTextField returnTextField;
    private JTextField returnerNameTextField;
    private JLabel returnResultLabel;

    private LibraryManagementSystem libraryManagementSystem;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IDandPasswords idandPasswords = new IDandPasswords();
            LoginPage loginPage = new LoginPage(idandPasswords.getLoginInfo() );//MainGUI::new);
        });
    }


    MainGUI(String userID) {
        libraryManagementSystem = new LibraryManagementSystem();
        frame = new JFrame("Library Management System");
        JLabel MainPage = new JLabel("Hello");

        frame.setBounds(0, 0, 200, 35);
        frame.setFont(new Font(null, Font.PLAIN, 25));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setResizable(true);
        frame.setVisible(true);
        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        JPanel panel = new JPanel(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search Book:"));
        searchTextField = new JTextField(20);
        searchPanel.add(searchTextField);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBook();
            }
        });
        searchPanel.add(searchButton);
        searchResultLabel = new JLabel();
        searchPanel.add(searchResultLabel);

        JPanel borrowPanel = new JPanel();
        borrowPanel.add(new JLabel("Borrow Book:"));
        borrowTextField = new JTextField(20);
        borrowPanel.add(borrowTextField);

        // Add a label and text field for borrower name
        borrowPanel.add(new JLabel("Borrower Name:"));
        borrowerNameTextField = new JTextField(20);
        borrowPanel.add(borrowerNameTextField);

        JButton borrowButton = new JButton("Borrow");
        borrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                borrowBook();
            }
        });
        borrowPanel.add(borrowButton);
        borrowResultLabel = new JLabel();
        borrowPanel.add(borrowResultLabel);

        JPanel returnPanel = new JPanel();
        returnPanel.add(new JLabel("Return Book:"));
        returnTextField = new JTextField(20);
        returnPanel.add(returnTextField);

        // Add a label and text field for returner name
        returnPanel.add(new JLabel("Returner Name:"));
        returnerNameTextField = new JTextField(20);
        returnPanel.add(returnerNameTextField);

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });
        returnPanel.add(returnButton);
        returnResultLabel = new JLabel();
        returnPanel.add(returnResultLabel);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(borrowPanel, BorderLayout.CENTER);
        panel.add(returnPanel, BorderLayout.SOUTH);

        JButton viewAllBooksButton = new JButton("View All Books");
        viewAllBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAllBooks();
            }
        });
        panel.add(viewAllBooksButton, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
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
    private void addBook(){
        String title = addBookTextFied.getText();
        String author = authorTextField.getText();

        AddBook book = AddBook.main();
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
        AddBook book = libraryManagementSystem.getLibrary().searchBook(title);
        if (book != null) {
            libraryManagementSystem.getLibrary().returnBook(book);
            returnResultLabel.setText("Book returned: " + book.getTitle());
        } else {
            returnResultLabel.setText("Book not found.");
        }
    }

    private void viewAllBooks() {
        List<AddBook> availableBooks = libraryManagementSystem.getLibrary().getAllAvailableBooks();

        StringBuilder message = new StringBuilder("Available Books:\n");
        for (AddBook book : availableBooks) {
            message.append(book.getTitle()).append("\n");
        }

        JOptionPane.showMessageDialog(frame, message.toString(), "Available Books", JOptionPane.INFORMATION_MESSAGE);
    }
}
