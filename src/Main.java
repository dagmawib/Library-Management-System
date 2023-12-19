
/*
*
* here is GUI form of Library Management Form
*
*
*
* */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    private JFrame frame;
    private JTextField searchTextField;
    private JLabel searchResultLabel;
    private JTextField borrowTextField;
    private JTextField borrowerNameTextField;
    private JLabel borrowResultLabel;
    private JTextField returnTextField;
    private JTextField returnerNameTextField;
    private JLabel returnResultLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
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
        returnerNameTextField = new JTextField(20);
        returnPanel.add(returnerNameTextField);
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });
        returnPanel.add(returnButton);
        returnResultLabel =new JLabel();
        returnPanel = new JPanel();
        returnPanel.add(new JLabel("Return Book:"));
        returnTextField = new JTextField(20);
        returnPanel.add(returnTextField);
        returnerNameTextField = new JTextField(20);
        returnPanel.add(returnerNameTextField);
        returnButton = new JButton("Return");
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

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void searchBook() {
        String title = searchTextField.getText();
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        Book book = libraryManagementSystem.getLibrary().searchBook(title);
        if (book != null) {
            searchResultLabel.setText("Book found: " + book.getTitle());
        } else {
            searchResultLabel.setText("Book not found.");
        }
    }

    private void borrowBook() {
        String title = borrowTextField.getText();
        String borrowerName = borrowerNameTextField.getText();
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        Book book = libraryManagementSystem.getLibrary().searchBook(title);
        if (book != null) {
            libraryManagementSystem.borrowBook(book, new Member(borrowerName, ""));
            borrowResultLabel.setText("Book borrowed: " + book.getTitle());
        } else {
            borrowResultLabel.setText("Book not found.");
        }
    }

    private void returnBook() {
        String title = returnTextField.getText();
        String returnerName = returnerNameTextField.getText();
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        Book book = libraryManagementSystem.getLibrary().searchBook(title);
        if (book != null) {
            libraryManagementSystem.returnBook(book, new Member(returnerName, ""));
            returnResultLabel.setText("Book returned: " + book.getTitle());
        } else {
            returnResultLabel.setText("Book not found.");
        }
    }
}