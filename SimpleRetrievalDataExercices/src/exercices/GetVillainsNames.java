package exercices;

import database.DB;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetVillainsNames {
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
        Connection connection = DB.getConnection(props);

        PreparedStatement statement = connection.prepareStatement(
                "select v.name, count(distinct mv.minion_id) as minion_count " +
                "from villains v join minions_villains mv on v.id = mv.villain_id \n" +
                "group by mv.villain_id \n" +
                "having minion_count > 15\n" +
                "order by minion_count desc");

        ResultSet result = statement.executeQuery();

        while(result.next()) {
            System.out.println(result.getString("name") + " " + result.getString("minion_count"));
        }
    }
}
