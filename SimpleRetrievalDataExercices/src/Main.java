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
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter database user:");
        String user = scanner.nextLine();

        System.out.printf("Enter password for database user %s:%n", user);
        String password = scanner.nextLine();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        System.out.println(props);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", props);

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");

        System.out.println("Enter filter salary:");
        String salary = scanner.nextLine();

        statement.setDouble(1, Double.parseDouble(salary));

        ResultSet result = statement.executeQuery();

        while(result.next()) {
            System.out.println(result.getString("first_name") + " " + result.getString("last_name") + " " + result.getDouble("salary"));
        }
    }
}