import java.sql.*;

public class Database {
    public static final String DB_NAME = "Database.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\user\\Downloads\\Library-Management-System-main\\" + DB_NAME;
    public static final String TABLE_BOOKS = "books";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AUTHOR = "author";
    private static boolean COLUMN_BORROWED;

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("DROP TABLE IF EXISTS " + TABLE_BOOKS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_BOOKS +
                    "(" + COLUMN_ID + " INT AUTO_INCREMENT PRIMARY KEY, " +
                    COLUMN_TITLE + " VARCHAR(100) NOT NULL, " +
                    COLUMN_AUTHOR + " VARCHAR(100) NOT NULL, " +
                    COLUMN_BORROWED + " BOOLEAN NOT NULL DEFAULT FALSE" +
                    ")"
            );

            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_BOOKS);
            while (results.next()) {
                int id = results.getInt(COLUMN_ID);
                String title = results.getString(COLUMN_TITLE);
                String author = results.getString(COLUMN_AUTHOR);
                boolean borrowed = results.getBoolean(String.valueOf(COLUMN_BORROWED));
                System.out.println(id + " " + title + " " + author + " " + borrowed);
            }
            results.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    }



