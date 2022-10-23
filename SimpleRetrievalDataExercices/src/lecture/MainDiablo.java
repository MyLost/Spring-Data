package lecture;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
/**
 *  Creating simple JDBC connection to MySQL database named diablo.
 *  Program retrieves simple data from database diablo via java code.
 *  User, password and username are read from console.
 *  You need MySql JDBC driver library to can run this example!
 *
 *
 */
public class MainDiablo {

    private static final String JDBC_CONNECTION_STRING = "jdbc:mysql://localhost:3306/diablo";

    private static final String ENTER_DATABASE_USER = "Enter database user:";

    private static final String ENTER_DATABASE_USER_PASSWORD = "Enter password for database user %s:";

    private static final String ENTER_USERNAME = "Please enter username to retrieve data";

    private static final String QUERY = "select user_name ,first_name , last_name , count(user_id) as `count` \n" +
            "from users u \n" +
            "join users_games ug on ug.user_id = u.id \n" +
            "where user_name = ? \n" +
            "group by user_id;";

    private static final String NO_SUCH_USER = "No such user exists";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println(ENTER_DATABASE_USER);
        String user = scanner.nextLine();

        System.out.println(String.format(ENTER_DATABASE_USER_PASSWORD, user));
        String password = scanner.nextLine();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        System.out.println(props);
        Connection connection = DriverManager.getConnection(JDBC_CONNECTION_STRING, props);

        System.out.println(ENTER_USERNAME);
        String username = scanner.nextLine();

        PreparedStatement statement = connection.prepareStatement(QUERY);

        statement.setString(1, username);

        ResultSet result = statement.executeQuery();

        if(!result.next()) {
            System.out.println(NO_SUCH_USER);
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
