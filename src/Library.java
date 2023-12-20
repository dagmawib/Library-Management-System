import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
        initializeBooks();
    }

    private void initializeBooks() {
        // Simulate adding books to the library
        books.add(new Book(1, "Book1", "Author1", false));
        books.add(new Book(2, "Book2", "Author2", true));
        // Add more books as needed
    }

    public Book searchBook(String title) {
        // Simulate searching the library for the book
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void borrowBook(Book book) {
        // Simulate updating the book's borrowed status
        if (book != null && !book.isBorrowed()) {
            book.setBorrowed(true);
        }
    }

    public void returnBook(Book book) {
        // Simulate updating the book's borrowed status
        if (book != null && book.isBorrowed()) {
            book.setBorrowed(false);
        }
    }

    public List<Book> getAllAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (!book.isBorrowed()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public String[] getAllBookTitles() {
        String[] titles = new String[books.size()];
        for (int i = 0; i < books.size(); i++) {
            titles[i] = books.get(i).getTitle();
        }
        return titles;
    }
}
