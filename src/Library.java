import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Library {
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
