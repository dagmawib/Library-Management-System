public class LibraryManagementSystem {
    private Library library;

    public LibraryManagementSystem() {
        this.library = new Library();
    }

    public Library getLibrary() {
        return library;
    }

    public void borrowBook(AddBook book, Member member) {
        if (!book.isBorrowed()) {
            book.setBorrowed(true);
            System.out.println("Book \"" + book.getTitle() + "\" is successfully borrowed by " + member.getName() + ".");
        } else {
            System.out.println("Book \"" + book.getTitle() + "\" is already borrowed.");
        }
    }

    public void returnBook(AddBook book, Member member) {
        if (book.isBorrowed()) {
            book.setBorrowed(false);
            System.out.println("Book \"" + book.getTitle() + "\" is successfully returned by " + member.getName() + ".");
        } else {
            System.out.println("Book \"" + book.getTitle() + "\" is not currently borrowed.");
        }
    }

    public AddBook get() {

        return null;
    }
}
