package Model;
import  java.sql.*;
/**
 * Created by super on 5/5/2019.
 */
public class BasketballDriver {
    public  Statement statement;

    public BasketballDriver() throws SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/basketball";

        Connection connection = DriverManager.getConnection(connectionString, "root", "1234");

        statement = connection.createStatement();
    }

    @Override
    protected void finalize() throws Throwable {
        statement.close();
    }
}
