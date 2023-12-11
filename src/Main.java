
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

class Book {
    private int id;
    private String title;
    private String author;
    private boolean borrowed;

    public Book(int id, String title, String author, boolean borrowed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.borrowed = borrowed;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }
}

class Library {
    public Book searchBook(String title) {
        // Simulate searching the library for the book
        if (title.equalsIgnoreCase("Book1")) {
            return new Book(1, "Book1", "Author1", false);
        } else if (title.equalsIgnoreCase("Book2")) {
            return new Book(2, "Book2", "Author2", true);
        } else {
            return null;
        }
    }

    public void updateBookBorrowedStatus(Book book) {
        // Simulate updating the book's borrowed status
        book.setBorrowed(true);
    }
}

class Member {
    private String name;
    private String contactDetails;

    public Member(String name, String contactDetails) {
        this.name = name;
        this.contactDetails = contactDetails;
    }

    public String getName() {
        return name;
    }

    public String getContactDetails() {
        return contactDetails;
    }
}

class LibraryManagementSystem {
    private Library library;

    public LibraryManagementSystem() {
        this.library = new Library();
    }

    public Library getLibrary() {
        return library;
    }

    public void borrowBook(Book book, Member member) {
        if (!book.isBorrowed()) {
            book.setBorrowed(true);
            System.out.println("Book \"" + book.getTitle() + "\" is successfully borrowed by " + member.getName() + ".");
        } else {
            System.out.println("Book \"" + book.getTitle() + "\" is already borrowed.");
        }
    }

    public void returnBook(Book book, Member member) {
        if (book.isBorrowed()) {
            book.setBorrowed(false);
            System.out.println("Book \"" + book.getTitle() + "\" is successfully returned by " + member.getName() + ".");
        } else {
            System.out.println("Book \"" + book.getTitle() + "\" is not currently borrowed.");
        }
    }
}

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