import java.sql.*;

public class Database {
    public static final String DB_NAME = "Database.sqlite";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Hp\\Desktop\\Library-Management-System\\" + DB_NAME;
    public static final String TABLE_BOOKS = "books";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AUTHOR = "author";
    public static boolean COLUMN_BORROWED;


    private static final int COLUMN_BORROWED_TRUE = 1;
    private static final int COLUMN_BORROWED_FALSE = 0;

    public static void main(String[] args) {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("DROP TABLE IF EXISTS " + TABLE_BOOKS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_BOOKS +
                    "(" + COLUMN_ID + " INT AUTO_INCREMENT PRIMARY KEY, " +
                    COLUMN_TITLE + " VARCHAR(100) NOT NULL, " +
                    COLUMN_AUTHOR + " VARCHAR(100) NOT NULL, " +
                    COLUMN_BORROWED + " BOOLEAN NOT NULL" +
                    ")"
            );
            statement.execute("INSERT INTO books VALUES(1, 'Book1', 'Author1', " + COLUMN_BORROWED_FALSE + ")");
            statement.execute ("INSERT INTO books VALUES(2, 'Book2', 'Author2', " + COLUMN_BORROWED_FALSE + ")");
            statement.execute("INSERT INTO books VALUES(3, 'Book3', 'Author3', "  + COLUMN_BORROWED_FALSE + ")");
            statement.execute("INSERT INTO books VALUES(4, 'Book4', 'Author4', " + COLUMN_BORROWED_FALSE + ")");
            statement.execute("INSERT INTO books VALUES(5, 'Book5', 'Author5', "  + COLUMN_BORROWED_FALSE + ")");
            statement.execute("INSERT INTO books VALUES(6, 'Book6', 'Author6', " + COLUMN_BORROWED_FALSE + ")");
            statement.execute("INSERT INTO books VALUES(7, 'Book7', 'Author7', " + COLUMN_BORROWED_FALSE + ")");
            statement.execute("INSERT INTO books VALUES(8, 'Book8', 'Author8', "  + COLUMN_BORROWED_FALSE + ")");
            statement.execute("INSERT INTO books VALUES(9, 'Book9', 'Author9', " + COLUMN_BORROWED_TRUE + ")");
            statement.execute("INSERT INTO books VALUES(10, 'Book10', 'Author10', "  + COLUMN_BORROWED_TRUE + ")");
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_BOOKS);
            while (results.next()) {
                int id = results.getInt(COLUMN_ID);
                String title = results.getString(COLUMN_TITLE);
                String author = results.getString(COLUMN_AUTHOR);
                boolean borrowed = results.getBoolean(String.valueOf(COLUMN_BORROWED));
                System.out.println(id + " " + title + " " + author + " " + borrowed);
            }
            System.out.println("Database Connected Successfully");
            results.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());

        }
    }
}



