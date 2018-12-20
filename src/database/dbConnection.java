package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
public class dbConnection {
    /**
     *
     */
    private static final String USERNAME = "dbusername";
    private static final String PASSWORD = "dbpassword";
    private static final String SQLCONN = "jdbc:mySQL://localhost/login";
    private static final String SQLite = "jdbc:sqlite:sample1.sqlite";   //database file needs to placed into project directory

    /**
     *
     * Connect to database
     * @return
     * @throws SQLException
     */
    public static Connection getCOnnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQLite);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}