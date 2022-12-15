package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class MyConnector {

    public static final String PLEASE_ENTER_USER_FOR_DATABASE_CONNECTION = "Please enter user for database connection!";
    public static final String PLEASE_ENTER_PASSWORD_FOR_USER_S_N = "Please enter password for user %s%n";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    private static Connection connection;
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String  DB_NAME = "Custom_ORM";
    public static void createConection() throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.println(PLEASE_ENTER_USER_FOR_DATABASE_CONNECTION);
        String username = scanner.nextLine();
        System.out.printf(PLEASE_ENTER_PASSWORD_FOR_USER_S_N, username);
        String password = scanner.nextLine();

        Properties properties = new Properties();
        properties.setProperty(USER, username);
        properties.setProperty(PASSWORD, password);

        connection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME, properties);
    }

    public static Connection getConnection() { return connection; }
}

