package exercices;

import database.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IncreaseMinionsAge {

    private static final String UPDATE_MINIONS_AGES_BY_RANGE = "update minions m \n" +
            "\tset m.age = m.age + 1, m.name = LOWER(m.name) where m.id in (?)";

    private static final String SELECT_FROM_MINIONS = "select * from minions m where m.id in (?) order by id";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        Connection conn = DB.getConnection();

        System.out.println("Please enter minion id sequence to increase this minions age");
        String minionsIds = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.joining(","));

        PreparedStatement statement = conn.prepareStatement(UPDATE_MINIONS_AGES_BY_RANGE.replace("?", minionsIds));

        statement.executeUpdate();

        statement = conn.prepareStatement(SELECT_FROM_MINIONS.replace("?", minionsIds));
        ResultSet result = statement.executeQuery();

        while(result.next()) {
            System.out.println(result.getString("name") + " " + result.getString("age"));
        }
    }
}
