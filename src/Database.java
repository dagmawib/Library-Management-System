import java.sql.*;

public class Database {

    public static final String DB_NAME = "Database.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\user\\Downloads\\Library-Management-System-main\\" + DB_NAME;
    public static final String TABLE_BOOKS = "books";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AUTHOR = "author";


    public static void main(String[] args) {

        try{
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("DROP TABLE IF EXISTS " + TABLE_BOOKS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_BOOKS +
                    "(" +  COLUMN_TITLE + " text, " +
                    COLUMN_ID + " integer, " +
                    COLUMN_AUTHOR + " text" + ")"
            );

            statement.execute("INSERT INTO " + TABLE_BOOKS +
                    "(" + COLUMN_TITLE + "," +
                    COLUMN_ID + "," +
                    COLUMN_AUTHOR +
                    ")" + "VALUES ('the power of subconscious mind' , 01 , 'Murphy')" );

            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_BOOKS);//short way of the above
            while(results.next()){
                System.out.println(results.getString("title") + " " +
                        results.getInt("ID") + " " +
                        results.getString("author"));
            }
            results.close();
        }catch(SQLException e){
            System.out.println("something went wrong " + e.getMessage());
        }



    }


}
