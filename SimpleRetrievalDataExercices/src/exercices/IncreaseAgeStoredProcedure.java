package exercices;

import database.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {

    private static final String UPDATE_MINION_AGE_BY_ID = "call usp_get_older(?);";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        Connection conn = DB.getConnection();

        System.out.println("Please enter minion id to update his age with factor of 1");
        Integer minionId = Integer.parseInt(scanner.nextLine());

        PreparedStatement statement = conn.prepareStatement(UPDATE_MINION_AGE_BY_ID);
        statement.setInt(1, minionId);
        statement.execute();
    }
}
