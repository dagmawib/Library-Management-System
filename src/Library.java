import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<AddBook> books;

    public Library() {
        this.books = new ArrayList<>();
        initializeBooks();
    }

    private void initializeBooks() {
        // Simulate adding books to the library
        books.add(new AddBook("Book1", "Author1"));
        books.add(new AddBook("Book2", "Author2"));
        books.add(new AddBook("Book3", "Author3"));
        books.add(new AddBook("Book4", "Author4"));
        books.add(new AddBook("Book5", "Author5"));
        books.add(new AddBook("Book6", "Author6"));
        books.add(new AddBook("Book7", "Author7"));
        // Add more books as needed
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

    public void borrowBook(AddBook book) {
        // Simulate updating the book's borrowed status
        if (book != null && !book.isBorrowed()) {
            book.setBorrowed(true);
        }
    }

    public void returnBook(AddBook book) {
        // Simulate updating the book's borrowed status
        if (book != null && book.isBorrowed()) {
            book.setBorrowed(false);
        }
    }

    public List<AddBook> getAllAvailableBooks() {
        List<AddBook> availableBooks = new ArrayList<>();
        for (AddBook book : books) {
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
