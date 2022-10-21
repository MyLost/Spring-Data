import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
/**
 *  Creating simple JDBC connection to MySQL database named diablo.
 *  Program retrieves simple data from database diablo via java code.
 *  User, password and username are read from console.
 *  You need MySql JDBC driver library to can run this example!
 */
public class MainDiablo {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter database user:");
        String user = scanner.nextLine();

        System.out.println(String.format("Enter password for database user %s:", user));
        String password = scanner.nextLine();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        System.out.println(props.toString());
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", props);

        System.out.println("Please enter username to retrieve data");
        String username = scanner.nextLine();

        PreparedStatement statement = connection.prepareStatement("select user_name ,first_name , last_name , count(user_id) as `count` \n" +
                "from users u \n" +
                "join users_games ug on ug.user_id = u.id \n" +
                "where user_name = ? \n" +
                "group by user_id;");

        statement.setString(1, username);

        ResultSet result = statement.executeQuery();

        if(!result.next()) {
            System.out.println("No such user exists");
        } else {
            while(result.next()) {
                StringBuilder builder = new StringBuilder();
                builder.append("User: " + result.getString("user_name"));
                builder.append(System.lineSeparator());
                builder.append(String.format("%s %s has played %d games",
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getInt("count")));
                System.out.println(builder.toString());
            }
        }
    }
}
