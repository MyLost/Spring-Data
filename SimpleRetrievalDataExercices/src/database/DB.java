package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class DB {

    private static final String JDBC_CONNECTION_STRING = "jdbc:mysql://localhost:3306/minions_db";
    private static Connection instance = null;

    private static final String ENTER_DATABASE_USER = "Enter database user:";

    private static final String ENTER_DATABASE_PASSWORD = "Enter password for database user %s:";

    public static Connection getConnection(Properties props) throws SQLException {

        if(instance == null) {
            instance = createConnection(props);
        }

        return instance;
    }

    public static Connection getConnection() throws SQLException {

        if(instance == null) {
            instance = createConnection();
        }

        return instance;
    }

    private static Connection createConnection(Properties props) throws SQLException {
        return DriverManager.getConnection(JDBC_CONNECTION_STRING, props);
    }

    private static Connection createConnection() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println(ENTER_DATABASE_USER);
        String user = scanner.nextLine();

        System.out.println(String.format(ENTER_DATABASE_PASSWORD, user));
        String password = scanner.nextLine();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        System.out.println(props);

        return DriverManager.getConnection(JDBC_CONNECTION_STRING, props);
    }
}
