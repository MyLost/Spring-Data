package lecture;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
/**
 *  Creating simple JDBC connection to MySQL database named softuni.
 *  Program retrieves simple data from database softuni via java code.
 *  User, password and salary are read from console.
 *  You need MySql JDBC driver library to can run this example!
 */
public class Main {

    public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/soft_uni";

    public static final String QUERY = "SELECT * FROM employees WHERE salary > ?";

    public static final String ENTER_DATABASE_USER = "Enter database user:";

    public static final String ENTER_DATABASE_PASSWORD = "Enter password for database user %s:%n";

    public static final String ENTER_QUERY_CRITERIA_MESSAGE = "Enter filter salary:";

    public static final String FIRSTNAME_COLUMN_NAME = "first_name";

    public static final String LASTNAME_COLUMN_NAME = "last_name";

    public static final String SALARY_COLUMN_NAME = "salary";
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.println(ENTER_DATABASE_USER);
        String user = scanner.nextLine();

        System.out.printf(ENTER_DATABASE_PASSWORD, user);
        String password = scanner.nextLine();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        System.out.println(props);
        Connection connection = DriverManager.getConnection(CONNECTION_URL, props);

        PreparedStatement statement = connection.prepareStatement(QUERY);

        System.out.println(ENTER_QUERY_CRITERIA_MESSAGE);
        String salary = scanner.nextLine();

        statement.setDouble(1, Double.parseDouble(salary));

        ResultSet result = statement.executeQuery();

        while(result.next()) {
            System.out.println(result.getString(FIRSTNAME_COLUMN_NAME) + " " + result.getString(LASTNAME_COLUMN_NAME) + " " + result.getDouble(SALARY_COLUMN_NAME));
        }
    }
}