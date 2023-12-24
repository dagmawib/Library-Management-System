import java.util.ArrayList;
import java.util.List;
public class Library {
    public List<AddBook> books;
    public Library() {
        this.books = new ArrayList<>();

    }

    public AddBook searchBook(String title) {
        // Simulate searching the library for the book
        for (AddBook book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
    public void borrowBook(String book) {
        // Simulate updating the book's borrowed status


    }
    public void returnBook(AddBook book) {
        // Simulate updating the book's borrowed status
        if (book != null && book.isBorrowed()) {
            book.setBorrowed(false);
        }
    }
//    Defines a method getAllAvailableBooks that returns a list of all available (not borrowed) books in the librar
    public List<AddBook> getAllAvailableBooks() {
        List<AddBook> availableBooks = new ArrayList<>();
        for (AddBook book : books) {
            if (!book.isBorrowed()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
//    Defines a method getAllBookTitles that returns an array of all book titles in the library.
    public String[] getAllBookTitles() {
        String[] titles = new String[books.size()];
        for (int i = 0; i < books.size(); i++) {
            titles[i] = books.get(i).getTitle();
        }
        return titles;
    }
}
