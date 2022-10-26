package exercices;

import database.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChangeTownNamesCasing {

    private static final String SELECT_TOWN_QUERY = "select * from towns t where t.country = ?";

    private static final String UPDATE_TOWN_QUERY = "update towns t set t.name = UPPER(t.name) where t.country = ? ";

    public static void main(String[] args) throws SQLException {

        Connection con = DB.getConnection();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter country name");
        String countryName = scanner.nextLine();

        PreparedStatement statement = con.prepareStatement(UPDATE_TOWN_QUERY);
        statement.setString(1, countryName);
        int affectedRows = statement.executeUpdate();

        if(affectedRows > 0) {
            System.out.printf("%d town names were affected.%n", affectedRows);
            statement = con.prepareStatement(SELECT_TOWN_QUERY);
            statement.setString(1, countryName);
            ResultSet result = statement.executeQuery();
            List<String> townNames = new ArrayList<>();
            while(result.next()) {
               townNames.add(result.getString("name"));
            }
            System.out.println("[" + townNames.stream().collect(Collectors.joining(", ")) + "]");
        } else {
            System.out.println("No town names were affected.");
        }
    }
}
