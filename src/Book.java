import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Book {
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
