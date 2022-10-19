package exercices;

import database.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class GetMinionNames {
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
                "select DISTINCT m.name, m.age, v.name as villain_name from minions m " +
                        "join minions_villains mv on m.id = mv.minion_id " +
                        "join villains v on v.id = mv.villain_id " +
                        "where v.id = ?;");

        System.out.println("Enter Villain id:");
        String villainId = scanner.nextLine();

        statement.setInt(1, Integer.parseInt(villainId));

        ResultSet result = statement.executeQuery();
        if(!result.next()) {
            System.out.println(String.format("No villain with ID %s exists in the database.", villainId));
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("Villain: %s", result.getString("villain_name")));
            builder.append(System.lineSeparator());
            builder.append(String.format("%d. %s %s", result.getRow(), result.getString("name"), result.getString("age")));
            builder.append(System.lineSeparator());
            while (result.next()) {
                builder.append(String.format("%d. %s %s", result.getRow(), result.getString("name"), result.getString("age")));
                builder.append(System.lineSeparator());
            }
            System.out.println(builder.toString());
        }
    }
}
