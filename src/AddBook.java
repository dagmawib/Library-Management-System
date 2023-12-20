import java.util.Scanner;
import java.util.ArrayList;

public class AddBook {
        private int id;
        private String title;
        private String author;
        private boolean borrowed;

        public AddBook(String title, String author) {
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
        public static AddBook main() {
            Scanner scanner = new Scanner(System.in);
            ArrayList<AddBook> books = new ArrayList<>();

            while (true) {
                System.out.print("Enter book title (or type 'exit' to stop adding books): ");
                String title = scanner.nextLine();

                if (title.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.print("Enter book author: ");
                String author = scanner.nextLine();

                // Create a new Book object and add it to the ArrayList
                AddBook newBook = new AddBook(title, author);
                books.add(newBook);

                System.out.println("Book added: " + title + " by " + author);
            }

            System.out.println("\nList of Books Added:");
            for (AddBook book : books) {
                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor());
            }

            scanner.close();
            return null;
        }
    }


